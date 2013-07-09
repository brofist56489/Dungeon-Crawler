package com.bh.war;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.bh.war.graphics.ImageManager;
import com.bh.war.graphics.Screen;
import com.bh.war.gui.InventoryMenu;
import com.bh.war.gui.Menu;
import com.bh.war.input.KeyHandler;
import com.bh.war.input.Mouse;
import com.bh.war.input.MouseHandler;
import com.bh.war.items.Item;
import com.bh.war.level.Level;
import com.bh.war.level.entities.Entity;
import com.bh.war.level.entities.Player;
import com.bh.war.level.tiles.Tile;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 250;
	public static final int HEIGHT = WIDTH / 4 * 3;
	public static final int SCALE = 2;
	public static final String NAME = "Game";
	public static Game instance;

	JFrame frame;
	Thread gameThread;
	boolean running = false;
	public static int tickCount = 0;

	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	public Screen screen;
	public KeyHandler keys;
	public MouseHandler mouse;
	private Mouse mouseImage;
	
	public Menu menu;
	
	public Level level;
	
	public Player player;

	private void init() {
		Tile.init();
		Entity.init();
		Item.init();
		
		instance = this;
		keys = new KeyHandler(this);
		mouse = new MouseHandler(this);
		mouseImage = new Mouse();

		screen = new Screen(WIDTH, HEIGHT);
		level = new Level(100, 100);
		player = new Player(-100, -100, level, keys, mouse);
		level.addEntity(player);
		
		setMenu(null);
	}
	
	public void setMenu(Menu m) {
		menu = m;
	}

	public void run() {
		init();

		long lt = System.nanoTime();
		double nsPt = 1000000000.0 / 60.0;
		double d = 0.0;
		int ticks = 0;
		int frames = 0;
		long ltr = System.currentTimeMillis();
		boolean shouldRender = false;

		while (running) {
			long now = System.nanoTime();
			d += (now - lt) / nsPt;
			lt = now;
			shouldRender = false;

			while (d >= 1) {
				tick();
				ticks++;
				d--;
				shouldRender = true;
			}
			
			if (shouldRender){
				render();
				frames++;
			}
			
			long cur = System.currentTimeMillis();
			if (cur - ltr >= 1000) {
				ltr += 1000;
				frame.setTitle(NAME + " " + ticks + " tps, " + frames + " fps");
				ticks = frames = 0;
			}
		}
	}

	public void tick() {
		tickCount++;
		keys.tick();
		mouse.tick();
		if(menu != null) {
			menu.tick();
		} else {
			level.tick();
			if(keys.inventory.isClicked()) {
				setMenu(new InventoryMenu(this, level, player.inventory));
			}
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.fill(0x0);
		screen.clearLighting(255);
		
		if(menu != null) {
			menu.render(screen);
		} else {
			screen.setOffset(player.getX() + player.getW() / 2, player.getY() + player.getH() / 2);
			
			if(screen.lightingEnabled) {
				level.preRender(screen);
			}
			
			level.renderBackground(screen, (int)player.getX() + player.getW() / 2, (int)player.getY() + player.getH() / 2);
			level.renderSprites(screen);
			
			renderInterface();
		}
		mouseImage.render(screen);
		pixels = screen.setPixels(pixels);
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	public void renderInterface()  {
		screen.clearOffset();
		int health = player.getHealth();
		float max_health = player.getMAX_HEALTH();
		int w = (int) (health / max_health * 50);
		int x = WIDTH / 2 - w;
		
		screen.renderRect(WIDTH / 2 - 52, 0, 104, 8, 0);
		screen.renderRect(WIDTH / 2 - 40, 8, 80, 7, 0);
		
		screen.renderRect(x, 2, w * 2, 4, 0xff0000);
		
		int mana = player.getMana();
		float max_mana = player.getMAX_MANA();
		w = (int) ((mana / max_mana) * 38);
		x = WIDTH / 2 - w;
		screen.renderRect(x, 8, w, 4, 0xff);
		
		int stamina = player.getStamina();
		float max_stamina = player.getMAX_STAMINA();
		w = (int) ((stamina / max_stamina) * 38);
		x = WIDTH / 2;
		screen.renderRect(x, 8, w, 4, 0xff00);
		
		x = 42;
		int y = -2;
		ImageManager.renderFromImage("tileMap", screen, x, y, 14*16, 16);
		ImageManager.renderFromImage("tileMap", screen, x + 14, y, 14*16 + 1, 16);
		ImageManager.renderFromImage("tileMap", screen, x, y + 14, 15*16, 16);
		ImageManager.renderFromImage("tileMap", screen, x + 14, y + 14, 15*16 + 1, 16);
		if (player.inventory.getItem(0, 0) != null)
			player.inventory.getItem(0, 0).render(screen, x + 6, y + 6);

		screen.setOffset(player.getX() + player.getW() / 2, player.getY() + player.getH() / 2);
	}
	
	public synchronized void start() {
		if (running)
			return;
		gameThread = new Thread(this, "MAIN_game");
		gameThread.start();

		running = true;
	}

	public synchronized void stop() {
		running = false;
	}

	public static void main(String[] args) {
		Game game = new Game();

		game.frame = new JFrame(NAME);
		game.frame.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.frame.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setResizable(true);
		game.frame.setVisible(true);
		game.frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null cursor"));
		game.frame.setLocationRelativeTo(null);
		game.frame.add(game);
		game.frame.pack();

		game.start();
	}
}
