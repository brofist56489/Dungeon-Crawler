package com.gb.war.items;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;
import com.gb.war.level.entities.projectile.ArrowProjectile;

public class CrossbowItem extends ToolItem {

	public CrossbowItem(int level) {
		super(level);
		textId = 17;
		setName("Crossbow");
	}
	
	public void onLeftClick(MouseHandler m, Level l, Player p) {
		if(!m.isLeftClickedButton() || p.getStamina() <= 25) return;
		float cx = p.getX() + p.getW() / 2;
		float cy = p.getY() + p.getH() / 2;
		double a = Math.atan2(m.getyPos() - cy, m.getxPos() - cx);
		double spread = 0.2;
		for(double i = -spread; i <= spread; i += spread) {
			double ax = Math.cos(a + i) * 5;
			double ay = Math.sin(a + i) * 5;
			ArrowProjectile f = new ArrowProjectile((int)cx, (int)cy, (float)ax, (float)ay, l);
			l.addEntity(f);
		}
		p.setStamina(-5);
	}
}