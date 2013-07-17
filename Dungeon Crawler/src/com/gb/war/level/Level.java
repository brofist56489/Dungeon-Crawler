package com.gb.war.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.Screen;
import com.gb.war.items.BowItem;
import com.gb.war.items.DungeonTeleporterItem;
import com.gb.war.items.FireWandItem;
import com.gb.war.items.IceWandItem;
import com.gb.war.items.InventoryManager;
import com.gb.war.items.LightningWandItem;
import com.gb.war.items.PenguinWandItem;
import com.gb.war.items.PresentItem;
import com.gb.war.items.ShurikenItem;
import com.gb.war.items.SpearItem;
import com.gb.war.level.entities.Entity;
import com.gb.war.level.entities.Penguin;
import com.gb.war.level.entities.Sheep;
import com.gb.war.level.generators.DungeonGenerator;
import com.gb.war.level.tiles.Tile;

public class Level {
	private int width;
	private int height;
	private int[] tiles;
	private int[] data;
	private int[] subTexture;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	public Level(int w, int h) {
		width = w;
		height = h;
		tiles = new int[w * h];
		data = new int[w * h];
		subTexture = new int[w * h];
		
		Random r = new Random();
		for (int i = 0; i < w * h; i++) {
			tiles[i] = 1 + ((r.nextInt(10) == 0) ? 1 : 0);
		}
		
		for (int i = 0; i < 5; i++) {
			new DungeonGenerator(this, r.nextInt(width), r.nextInt(height), r.nextInt(10) + 7, r.nextInt(10) + 7).generate();
		}
		generateChest(0, 0);
		InventoryManager.addItem("CHEST_0_0", new FireWandItem(3), 7);
		InventoryManager.addItem("CHEST_0_0", new IceWandItem(3), 6);
		InventoryManager.addItem("CHEST_0_0", new LightningWandItem(3), 5);
		InventoryManager.addItem("CHEST_0_0", new BowItem(3), 4);
		InventoryManager.addItem("CHEST_0_0", new SpearItem(3), 3);
		InventoryManager.addItem("CHEST_0_0", new PresentItem(3), 2);
		InventoryManager.addItem("CHEST_0_0", new ShurikenItem(3), 1);
		InventoryManager.addItem("CHEST_0_0", new DungeonTeleporterItem(3));
		InventoryManager.addItem("CHEST_0_0", new PenguinWandItem(3));
		
		addEntity(new Penguin(96, 96, this));
		addEntity(new Sheep(96, 256, this));
	}
	
	public void generateChest(int x, int y) {
		InventoryManager.addInv("CHEST_"+x+"_"+y, 25, 5);
		setTile(x, y, Tile.CHEST);
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) return Tile.VOID;
		return Tile.tiles[tiles[x + y * width]];
	}
	
	public int getData(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) return 0;
		return data[x + y * width];
	}
	
	public void setTile(int x, int y, Tile t) {
		if (x < 0 || x >= width || y < 0 || y >= height) return;
		tiles[x + y * width] = t.getId();
	}
	
	public void setData(int x, int y, int d) {
		if (x < 0 || x >= width || y < 0 || y >= height) return;
		data[x + y * width] = d;
	}
	
	public int getSubTexture(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) return 0;
		return subTexture[x + y * width];
	}
	
	public void setSubTexture(int x, int y, int t) {
		if (x < 0 || x >= width || y < 0 || y >= height) return;
		subTexture[x + y * width] = t;
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
	
	public void tick() {
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
			if(!entities.get(i).alive) {
				entities.remove(i);
				i--;
			}
			
		}
	}
	
	public void preRender(Bitmap b) {
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).preRender(b);
		}
	}
	
	public void applyOffset(float x, float y, Screen screen) {
		screen.setOffset(x, y);
		int xo = screen.xOff;
		int yo = screen.yOff;
		
		if(xo < 0) xo = 0;
		if(yo < 0) yo = 0;
		if(xo > width * 16 - screen.getWidth()) xo = width * 16 - screen.getWidth();
		if(yo > height * 16 - screen.getHeight() + 16) yo = height * 16 - screen.getHeight() + 16;
		
		screen.xOff = xo;
		screen.yOff = yo;
	}
	
	public void renderBackground(Bitmap b, int x, int y) {
		if(x < b.getWidth() / 2) x = b.getWidth() / 2;
		if(y < b.getHeight() / 2) y = b.getHeight() / 2;
		if(x > width * 16 - b.getWidth() / 2) x = width * 16 - b.getWidth() / 2;
		if(y > height * 16 - b.getHeight() / 2) y = height * 16 - b.getHeight() / 2;
		
		int xmin = (x >> 4) - 9;
		int xmax = (x >> 4) + 10;
		int ymin = (y >> 4) - 6;
		int ymax = (y >> 4) + 7;
		
		for (int yy = ymin; yy < ymax; yy++) {
			if (yy < 0 || yy >= height) continue;
			for (int xx = xmin; xx < xmax; xx++) {
				if (xx < 0 || xx >= width) continue;
				getTile(xx, yy).render(b, this, xx << 4, yy << 4);
			}
		}
	}
	
	public void renderSprites(Bitmap b) {
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(b);
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
