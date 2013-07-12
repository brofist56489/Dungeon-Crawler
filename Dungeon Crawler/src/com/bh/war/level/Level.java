package com.bh.war.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bh.war.graphics.Bitmap;
import com.bh.war.level.entities.Entity;
import com.bh.war.level.generators.DungeonGenerator;
import com.bh.war.level.tiles.Tile;

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
			subTexture[i] = r.nextInt(4);
		}
		
		new DungeonGenerator(this, 0, 0, 15, 15).generate();
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) return null;
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
	
	public void renderBackground(Bitmap b, int x, int y) {
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
