package com.gb.war.level.tiles;

import java.awt.Rectangle;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;

public class Tile {
	
	public static void init() {
		ImageManager.loadImage("/textures/tileMap.png", "tileMap");
	}
	
	
	public static Tile[] tiles = new Tile[256];
	
	public static Tile VOID = new Tile(0, true, 0);
	public static Tile GRASS = new GrassTile(1);
	public static Tile TREE = new TreeTile(2);
	public static Tile DUNGEON_WALL = new DungeonWallTile(3);
	public static Tile DUNGEON_BACK = new DungeonBackTile(4);
	public static Tile CHEST = new ChestTile(5);
	public static Tile DOOR = new DoorTile(6);
	
	private final int id;
	protected final int tileId;
	private boolean light;
	private int lightRadius = 0;
	private boolean solid;
	
	/**
	 *	Creates a new Tile.
	 *	@param id The id value of the new Tile
	 *	@param solid Is the Tile solid?
	 *	@param tId The TileMap texture id.
	 *	@author Giant Behemoth
	 */
	
	public Tile(int id, boolean solid, int tId) {
		this.id = id;
		if (tiles[id] != null) {
			throw new RuntimeException("Dulplicate tiles at " + id);
		}
		tiles[id] = this;
		this.solid = solid;
		this.tileId = tId;
	}
	
	public static Tile getTile(int id) {
		return tiles[id];
	}
	
	public boolean interact(MouseHandler m, Player p, Level l, int x, int y) {
		return false;
	}
	
	public Rectangle getCollBox(int x, int y, Level l) {
		return new Rectangle(x << 4, y << 4, 16, 16);
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
