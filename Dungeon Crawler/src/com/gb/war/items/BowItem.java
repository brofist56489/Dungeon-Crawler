package com.gb.war.items;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;
import com.gb.war.level.entities.projectile.ArrowProjectile;

public class BowItem extends ToolItem {

	public BowItem(int level) {
		super(level);
		textId = 16;
		setName("Bow");
	}

	public void onLeftClick(MouseHandler m, Level l, Player p) {
		if (!m.isLeftClickedButton() || p.getStamina() <= 10)
			return;
		float cx = p.getX();
		float cy = p.getY();
		double a = Math.atan2(m.getyPos() - cy, m.getxPos() - cx);
		double ax = Math.cos(a) * 5;
		double ay = Math.sin(a) * 5;
		ArrowProjectile f = new ArrowProjectile((int) cx, (int) cy, (float) ax, (float) ay, l);
		l.addEntity(f);
		p.setStamina(-10);
	}
}
