package com.gb.war.items;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;

public class PresentItem extends ToolItem {

	public PresentItem(int level) {
		super(level);
		textId = 3;
		setName("Random Present");
	}
	
	public void onLeftClick(MouseHandler m, Level l, Player p) {
		p.inventory.removeItem(0, 0);
		p.inventory.add(RandomItem.genRanItem());
	}
}
