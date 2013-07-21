package com.gb.war.items;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Cow;
import com.gb.war.level.entities.Entity;
import com.gb.war.level.entities.Player;

public class CowWandItem extends ToolItem {
	int cowCount;

	public CowWandItem(int level) {
		super(level);
		textId = 5;
		setName("Cow Wand");
		cowCount = 0;
	}

	public void onRightClick(MouseHandler m, Level l, Player p) {
		if (!m.isRightClickedButton() || p.getMana() <= 50)
			return;

		if (!Entity.safeSpawn(m.getxPos() / 16, m.getyPos() / 16, l))
			return;
		Cow f = new Cow((int) m.getxPos(), (int) m.getyPos(), l);
		l.addEntity(f);
	}
}
