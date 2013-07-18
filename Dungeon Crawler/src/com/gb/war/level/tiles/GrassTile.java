<<<<<<< HEAD:Dungeon Crawler/src/com/bh/war/level/tiles/GrassTile.java
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
		
		if (subTexture == 1) ImageManager.renderFromImage("tileMap", b, x, y, 16, 16);
		if (subTexture == 2) ImageManager.renderFromImage("tileMap", b, x + 8, y, 16, 16);
		if (subTexture == 4) ImageManager.renderFromImage("tileMap", b, x, y + 8, 16, 16);
		if (subTexture == 8) ImageManager.renderFromImage("tileMap", b, x + 8, y + 8, 16, 16);
	}
}
=======
package com.gb.war.level.tiles;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.level.Level;

public class GrassTile extends Tile {

	public GrassTile(int id) {
		super(id, false, 1);
	}
	
	public void render(Bitmap b, Level l, int x, int y) {
		int subTexture = l.getSubTexture(x >> 4, y >> 4);
		ImageManager.renderFromImage("tileMap", b, x, y, tileId, 16);
		
		if (subTexture == 1) ImageManager.renderFromImage("tileMap", b, x, y, 16, 16);
		if (subTexture == 2) ImageManager.renderFromImage("tileMap", b, x + 8, y, 16, 16);
		if (subTexture == 4) ImageManager.renderFromImage("tileMap", b, x, y + 8, 16, 16);
		if (subTexture == 8) ImageManager.renderFromImage("tileMap", b, x + 8, y + 8, 16, 16);
	}
}
>>>>>>> 6668155fa944bf0f2746b000f15efd7a3d876bc8:Dungeon Crawler/src/com/gb/war/level/tiles/GrassTile.java
