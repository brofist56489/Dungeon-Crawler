package com.gb.war.items;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;
import com.gb.war.level.tiles.Tile;

public class DungeonTeleporterItem extends ToolItem {

	public DungeonTeleporterItem(int level) {
		super(level);
		setName("Dungeon Teleporter");
		textId = 4;
	}
	
	public void onLeftClick(MouseHandler m, Level l, Player p) {
		if(!m.isLeftClickedButton()) return;
		for(int i = 0; i < l.getHeight(); i++) {
			for(int j = 0; j < l.getWidth(); j++) {
				if(l.getTile(j,  i) == Tile.DUNGEON_WALL) {
					p.setX(16 * (j + 1));
					p.setY(16 * (i + 1));
					return;
				}
			}
		}
	}
}