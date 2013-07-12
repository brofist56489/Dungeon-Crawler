package com.bh.war.level.tiles;

import com.bh.war.graphics.Bitmap;
import com.bh.war.graphics.ImageManager;
import com.bh.war.level.Level;

public class DungeonBackTile extends Tile {

	public DungeonBackTile(int id) {
		super(id, false, 4);
	}
	
	public void render(Bitmap screen, Level level, int x, int y) {
		int sub = level.getSubTexture(x>>4, y>>4);
		int xx = tileId;
		int yy = sub * 16;
		ImageManager.renderFromImage("tileMap", screen, x, y, xx+yy, 16);
	}
}
