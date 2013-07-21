package com.gb.war.items;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;
import com.gb.war.level.entities.projectile.EarthProjectile;
public class EarthWandItem extends ToolItem {
	public EarthWandItem(int level) {
		super(level);
		textId = 7;
		setName("Earth Wand : " + level);
	}

	public void onLeftClick(MouseHandler m, Level l, Player p) {
		if (!m.isLeftClickedButton() || p.getMana() <= 4)
			return;
		float cx = p.getX() + p.getW() / 2;
		float cy = p.getY() + p.getH() / 2;
		double a = Math.atan2(m.getyPos() - cy, m.getxPos() - cx);
		double spread = 0.3;
		for (double i = -spread; i <= spread * level / 2; i += spread) {
			double ax = Math.cos(a + i) * 5;
			double ay = Math.sin(a + i) * 5;
			EarthProjectile f = new EarthProjectile((int) cx, (int) cy, (float) ax, (float) ay, l);
			l.addEntity(f);
		}
		super.onLeftClick(m, l, p);
		p.setMana(-4);
	}
}
