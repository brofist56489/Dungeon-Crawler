package com.gb.war.level.tiles;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.level.Level;

public class DungeonWallTile extends Tile {

	public DungeonWallTile(int id) {
		super(id, true, 3);
	}
	
	public void render(Bitmap screen, Level level, int x, int y) {
		int sub = level.getSubTexture(x>>4, y>>4);
		int xx = tileId;
		int yy = sub * 16;
		ImageManager.renderFromImage("tileMap", screen, x, y, xx+yy, 16);
	}
}
