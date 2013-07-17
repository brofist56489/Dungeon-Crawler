package com.gb.war.level.tiles;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.level.Level;

public class TreeTile extends Tile {

	public TreeTile(int id) {
		super(id, true, 2);
	}
	
	public void render(Bitmap b, Level l, int x, int y) {
		@SuppressWarnings("unused")
		int subTexture = l.getSubTexture(x >> 4, y >> 4);

		ImageManager.renderFromImage("tileMap", b, x, y, Tile.GRASS.getId(), 16);
		ImageManager.renderFromImage("tileMap", b, x, y, tileId, 16);
	}
}
