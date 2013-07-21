package com.gb.war.level.tiles;

import java.awt.Rectangle;

import com.gb.war.graphics.Bitmap;
import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;

public class DoorTile extends Tile {

	public DoorTile(int id) {
		super(id, true, 6);
	}
	
	public boolean interact(MouseHandler m, Player p, Level l, int x, int y) {
		if(!m.isRightClickedButton()) return true;
		l.setData(x, y, 3 - l.getData(x, y));
		return true;
	}
	
	public Rectangle getCollBox(int x, int y, Level l) {
		Rectangle r = new Rectangle();
		r.x = (x * 16) + ((l.getData(x, y) == 2) ? 14 : 0);
		r.y = (y * 16) + ((l.getData(x, y) == 1) ? 14 : 0);
		r.width = (l.getData(x, y) >= 2) ? 2 : 16;
		r.height = (l.getData(x, y) >= 2) ? 16 : 2;
		return r;
	}
	
	public void render(Bitmap b, Level l, int x, int y) {
		Tile.getTile(l.getSubTexture(x / 16, y / 16)).render(b, l, x, y);
		
		Rectangle r = getCollBox(x / 16, y / 16, l);
		b.renderRect(r.x, r.y, r.width, r.height, 0xffffff);
	}
}
