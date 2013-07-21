package com.gb.war.items;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Entity;
import com.gb.war.level.entities.Penguin;
import com.gb.war.level.entities.Player;
import com.gb.war.level.entities.projectile.PenguinProjectile;

public class PenguinWandItem extends ToolItem {

	public PenguinWandItem(int level) {
		super(level);
		textId = 5;
		setName("Penguin Wand");
	}
	
	
	public void onLeftClick(MouseHandler m, Level l, Player p) {
		if(!m.isLeftClickedButton() || p.getMana() <= 4) return;
		float cx = p.getX();
		float cy = p.getY();
		double a = Math.atan2(m.getyPos() - cy, m.getxPos() - cx);
		double spread = 0.3;
		for(double i = -spread; i <= spread * level / 2; i += spread) {
			double ax = Math.cos(a + i) * 5;
			double ay = Math.sin(a + i) * 5;
			PenguinProjectile f = new PenguinProjectile((int)cx, (int)cy, (float)ax, (float)ay, l);
			l.addEntity(f);
		}
		p.setMana(-10);
	}

	public void onRightClick(MouseHandler m, Level l, Player p) {
		if (!m.isRightClickedButton() || p.getMana() <= 50)
			return;

		if (!Entity.safeSpawn(m.getxPos() / 16, m.getyPos() / 16, l))
			return;
		Penguin f = new Penguin((int) m.getxPos(), (int) m.getyPos(), l);
		l.addEntity(f);
	}
}
