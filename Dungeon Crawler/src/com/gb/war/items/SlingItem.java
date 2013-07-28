package com.gb.war.items;

import com.gb.war.input.MouseHandler;
import com.gb.war.items.resources.Resource;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;
import com.gb.war.level.entities.projectile.RockProjectile;

public class SlingItem extends ToolItem {

	public SlingItem(int level) {
		super(level);
		textId = 8;
		setName("Sling");
	}

	public void onLeftClick(MouseHandler m, Level l, Player p) {
		if (!m.isLeftClickedButton() || p.getStamina() <= 10 || !p.inventory.contains(Resource.rock))
			return;
		float cx = p.getX();
		float cy = p.getY();
		double a = Math.atan2(m.getyPos() - cy, m.getxPos() - cx);
		double ax = Math.cos(a) * 5;
		double ay = Math.sin(a) * 5;
		RockProjectile f = new RockProjectile((int) cx, (int) cy, (float) ax, (float) ay, l);
		l.addEntity(f);
//		p.setStamina(-10);
		p.inventory.removeResource(Resource.rock, 1);
	}
}
