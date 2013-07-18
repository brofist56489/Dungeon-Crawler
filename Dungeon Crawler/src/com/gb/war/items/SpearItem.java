package com.gb.war.items;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;
import com.gb.war.level.entities.projectile.SpearProjectile;

public class SpearItem extends ToolItem {

	public SpearItem(int level) {
		super(level);
		textId = 18;
		setName("Spear");
	}

	public void onLeftClick(MouseHandler m, Level l, Player p) {
		if (!m.isLeftClickedButton())
			return;
		float cx = p.getX() + p.getW() / 2;
		float cy = p.getY() + p.getH() / 2;
		double a = Math.atan2(m.getyPos() - cy, m.getxPos() - cx);
		double ax = Math.cos(a) * 3;
		double ay = Math.sin(a) * 3;
		SpearProjectile f = new SpearProjectile((int) cx, (int) cy, (float) ax, (float) ay, l);
		l.addEntity(f);
		super.onLeftClick(m, l, p);
	}

}