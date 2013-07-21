package com.gb.war.level.tiles;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.level.Level;

public class DungeonBackTile extends Tile {

	public DungeonBackTile(int id) {
		super(id, false, 4);
	}
	
	public void render(Bitmap screen, Level level, int x, int y) {
		int sub = level.getSubTexture(x>>4, y>>4);
		if(sub >= 4) {
			sub = 0;
		}
		int xx = tileId;
		int yy = sub * 16;
		ImageManager.renderFromImage("tileMap", screen, x, y, xx+yy, 16);
	}
}
