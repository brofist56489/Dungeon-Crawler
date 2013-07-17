package com.gb.war.level.tiles;

import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;

public class DoorTile extends Tile {

	public DoorTile(int id) {
		super(id, true, 6);
	}
	
	public boolean interact(Player p, Level l, int x, int y) {
		if(l.getData(x, y) == 0) {
			l.setData(x, y, 1);
		} else {
			l.setData(x, y, 0);
		}
		return true;
	}
}
