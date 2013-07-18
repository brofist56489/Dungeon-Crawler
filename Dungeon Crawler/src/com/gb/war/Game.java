package com.gb.war;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.gb.war.graphics.Font;
import com.gb.war.graphics.ImageManager;
import com.gb.war.graphics.Screen;
import com.gb.war.gui.InventoryMenu;
import com.gb.war.gui.Menu;
import com.gb.war.input.KeyHandler;
import com.gb.war.input.Mouse;
import com.gb.war.input.MouseHandler;
import com.gb.war.items.Item;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Entity;
import com.gb.war.level.entities.Player;
import com.gb.war.level.tiles.Tile;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 256;
	public static final int HEIGHT = WIDTH / 4 * 3;
	public static final int SCALE = 3;
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
//		Sound.calm.play();
		Tile.init();
		Entity.init();
		Item.init();
		Font.init();
		
		instance = this;
		keys = new KeyHandler(this);
		mouse = new MouseHandler(this);
		mouseImage = new Mouse();

		screen = new Screen(WIDTH, HEIGHT);
		level = new Level(100, 100);
		Point pPos = getSafeSpawn();
		player = new Player(pPos.x, pPos.y, level, keys, mouse);
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
				boolean isApp = (Boolean) GlobalVariables.get("isApplet");
				if(!isApp)
						frame.setTitle(NAME + " " + ticks + " tps, " + frames + " fps");
				ticks = frames = 0;
			}
		}
	}

	public void tick() {
		tickCount++;
		if(!hasFocus()) {
			keys.releaseAll();
		}
		keys.tick();
		mouse.tick();
		if(menu != null) {
			menu.tick();
		} else {
			level.tick();
			if(keys.inventory.isClicked()) {
				InventoryMenu m = new InventoryMenu(this, level);
				m.addInventory("Player", player.inventory);
				setMenu(m);
			}
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}
		screen.fill(0x9a89a8);
		screen.clearLighting(255);
		
		if(menu != null) {
			menu.render(screen);
		} else {
			level.applyOffset(player.getX() + player.getW() / 2, player.getY() + player.getH() / 2, screen);
			
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
		int xo = (getWidth() - WIDTH * SCALE) / 2 + 5;
		int yo = (getHeight() - HEIGHT * SCALE) / 2 + 20;
		g.drawImage(image, xo, yo, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
		bs.show();
	}
	
	public void renderInterface()  {
		screen.clearOffset();
		int health = player.getHealth();
		float max_health = player.getMAX_HEALTH();
		int w = (int) (health / max_health * 50);
		int x = WIDTH / 2 - w;
		
		screen.renderRect(WIDTH / 2 - 52, 0, 104, 8, 0, false);
		screen.renderRect(WIDTH / 2 - 40, 8, 80, 7, 0, false);
		
		screen.renderRect(x, 2, w * 2, 4, 0xff0000, false);
		
		int mana = player.getMana();
		float max_mana = player.getMAX_MANA();
		w = (int) ((mana / max_mana) * 38);
		x = WIDTH / 2 - w;
		screen.renderRect(x, 8, w, 4, 0xff, false);
		
		int stamina = player.getStamina();
		float max_stamina = player.getMAX_STAMINA();
		w = (int) ((stamina / max_stamina) * 38);
		x = WIDTH / 2;
		screen.renderRect(x, 8, w, 4, 0xff00, false);
		
		x = 42;
		int y = -2;
		ImageManager.renderFromImage("tileMap", screen, x, y, 14*16, 16, false);
		ImageManager.renderFromImage("tileMap", screen, x + 14, y, 14*16 + 1, 16, false);
		ImageManager.renderFromImage("tileMap", screen, x, y + 14, 15*16, 16, false);
		ImageManager.renderFromImage("tileMap", screen, x + 14, y + 14, 15*16 + 1, 16, false);
		if (player.inventory.getItem(0, 0) != null)
			player.inventory.getItem(0, 0).render(screen, x + 6, y + 6);

		level.applyOffset(player.getX() + player.getW() / 2, player.getY() + player.getH() / 2, screen);
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
		try {
			game.frame.setIconImage(ImageIO.read(Game.class.getResource("/textures/icon2.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		game.frame.setResizable(true);
		game.frame.setVisible(true);
		game.frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null cursor"));
		game.frame.setLocationRelativeTo(null);
		game.frame.add(game);
		game.frame.pack();
		
		GlobalVariables.set("isApplet", false);

		game.start();
	}
	
	/**
	 * Loops through until a
	 * safe spawn location for the player is found.
	 * @return A Point containing a safe spawn location - 
	 * free from obstruction
	 * @author Giant Behemoth
	 */
	public Point getSafeSpawn() {
		Random r = new Random();
		int x = r.nextInt(10);
		int y = r.nextInt(10);
		boolean foundX = false;
		while(foundX == false) {
			if(level.getTile(x, y) == Tile.CHEST || level.getTile(x, y) == Tile.DUNGEON_WALL || level.getTile(x, y) == Tile.TREE) {
				System.out.println("Spawn Obstructed By: " + level.getTile(x,  y).getTileId());
				x = r.nextInt(10);
				y = r.nextInt(10);
				System.out.println("Recalculating Spawn...");
			} else foundX = true;
		}
		System.out.println(x + ", " + y);
		return new Point(x * 16, y * 16);
	}

}
