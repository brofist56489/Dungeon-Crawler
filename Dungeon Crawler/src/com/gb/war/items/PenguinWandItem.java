package com.gb.war.items;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Penguin;
import com.gb.war.level.entities.Player;

public class PenguinWandItem extends ToolItem {
	int penguinCount;
	public PenguinWandItem(int level) {
		super(level);
		textId = 5;
		setName("Penguin Wand");
		penguinCount = 0;
	}
	
	public void onRightClick(MouseHandler m, Level l, Player p) {
		if(!m.isRightClickedButton() || p.getMana() <= 50) return;
		Penguin f = new Penguin((int)m.getxPos(), (int)m.getyPos(), l);
		l.addEntity(f);
	}
}
