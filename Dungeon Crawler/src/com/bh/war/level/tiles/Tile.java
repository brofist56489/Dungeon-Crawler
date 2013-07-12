package com.bh.war.level.tiles;

import com.bh.war.graphics.Bitmap;
import com.bh.war.graphics.ImageManager;
import com.bh.war.level.Level;

public class Tile {
	
	public static void init() {
		ImageManager.loadImage("/textures/tileMap.png", "tileMap");
	}
	
	
	public static Tile[] tiles = new Tile[256];
	
	public static Tile VOID = new Tile(0, false, 0);
	public static Tile GRASS = new GrassTile(1);
	public static Tile TREE = new TreeTile(2);
	public static Tile DUNGEON_WALL = new DungeonWallTile(3);
	public static Tile DUNGEON_BACK = new DungeonBackTile(4);
	
	private final int id;
	protected final int tileId;
	private boolean light;
	private int lightRadius = 0;
	private boolean solid;
	
	public Tile(int id, boolean solid, int tId) {
		this.id = id;
		if (tiles[id] != null) {
			throw new RuntimeException("Dulplicate tiles at " + id);
		}
		tiles[id] = this;
		this.solid = solid;
		this.tileId = tId;
	}
	
	public void render(Bitmap screen, Level l, int x, int y) {
		ImageManager.renderFromImage("tileMap", screen, x, y, tileId, 16, 0);
	}

	public boolean isLight() {
		return light;
	}

	public void setLight(boolean light) {
		this.light = light;
	}

	public int getLightRadius() {
		return lightRadius;
	}

	public void setLightRadius(int lightRadius) {
		this.lightRadius = lightRadius;
	}

	public int getId() {
		return id;
	}

	public boolean isSolid() {
		return solid;
	}

	public int getTileId() {
		return tileId;
	}
}
