package com.gb.war.items;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;
import com.gb.war.level.entities.RagingNinjaEntity;
import com.gb.war.level.entities.projectile.ShurikenProjectile;


public class ShurikenItem extends ToolItem {

	public ShurikenItem(int level) {
		super(level);
		textId = 20;
		setName("Shuriken");
	}
	
	public void onLeftClick(MouseHandler m, Level l, Player p) {
		if (!m.isLeftClickedButton() || p.getStamina() <= 5)
			return;
		float cx = p.getX();
		float cy = p.getY();
		double a = Math.atan2(m.getyPos() - cy, m.getxPos() - cx);
		double ax = Math.cos(a) * 2;
		double ay = Math.sin(a) * 2;
		ShurikenProjectile f = new ShurikenProjectile((int) cx, (int) cy, (float) ax, (float) ay, l);
		l.addEntity(f);
		p.setStamina(-5);
//		p.inventory.removeItem(0, 0);
	}
	
	
	public void onRightClick(MouseHandler m, Level l, Player p) {
		if(!m.isRightClickedButton() || p.getStamina() <= 50) return;
		if(level < MAX_LEVEL) return;
		RagingNinjaEntity f = new RagingNinjaEntity((int)m.getxPos(), (int)m.getyPos(), l);
		l.addEntity(f);
		p.setStamina(-50);
	}
}