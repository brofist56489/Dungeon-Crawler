package com.gb.war.items;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Entity;
import com.gb.war.level.entities.Player;
import com.gb.war.level.entities.Sheep;

public class SheepWandItem extends ToolItem {
	int penguinCount;

	public SheepWandItem(int level) {
		super(level);
		textId = 6;
		setName("Sheep Wand");
		penguinCount = 0;
	}

	public void onRightClick(MouseHandler m, Level l, Player p) {
		if (!m.isRightClickedButton() || p.getMana() <= 50)
			return;

		if (!Entity.safeSpawn(m.getxPos() / 16, m.getyPos() / 16, l))
			return;
		Sheep f = new Sheep((int) m.getxPos(), (int) m.getyPos(), l);
		l.addEntity(f);

	}
}
