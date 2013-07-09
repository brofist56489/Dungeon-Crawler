package com.bh.war.level.tiles;

import com.bh.war.graphics.Bitmap;
import com.bh.war.graphics.ImageManager;
import com.bh.war.level.Level;

public class GrassTile extends Tile {

	public GrassTile(int id) {
		super(id, false, 1);
	}
	
	public void render(Bitmap b, Level l, int x, int y) {
		int subTexture = l.getSubTexture(x >> 4, y >> 4);
		ImageManager.renderFromImage("tileMap", b, x, y, tileId, 16);
		
		if (subTexture== 1) ImageManager.renderFromImage("tileMap", b, x, y, 16, 16);
		if (subTexture == 2) ImageManager.renderFromImage("tileMap", b, x + 8, y, 16, 16);
		if (subTexture == 4) ImageManager.renderFromImage("tileMap", b, x, y + 8, 16, 16);
		if (subTexture == 8) ImageManager.renderFromImage("tileMap", b, x + 8, y + 8, 16, 16);
	}
}
