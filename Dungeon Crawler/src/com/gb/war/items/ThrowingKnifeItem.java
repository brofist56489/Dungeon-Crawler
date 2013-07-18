package com.gb.war.items;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;
import com.gb.war.level.entities.RagingNinjaEntity;
import com.gb.war.level.entities.projectile.ThrowingKnifeProjectile;

public class ThrowingKnifeItem extends ToolItem {

	public ThrowingKnifeItem(int level) {
		super(level);
		textId = 19;
		setName("Throwing Knife");
	}

	public void onLeftClick(MouseHandler m, Level l, Player p) {
		if (!m.isLeftClickedButton() || p.getStamina() <= 5)
			return;
		float cx = p.getX();
		float cy = p.getY();
		double a = Math.atan2(m.getyPos() - cy, m.getxPos() - cx);
		double ax = Math.cos(a) * 2;
		double ay = Math.sin(a) * 2;
		ThrowingKnifeProjectile f = new ThrowingKnifeProjectile((int) cx, (int) cy, (float) ax, (float) ay, l);
		l.addEntity(f);
		p.setStamina(-5);
//		p.inventory.removeItem(0, 0);
	}
	
	
	public void onRightClick(MouseHandler m, Level l, Player p) {
		if(!m.isRightClickedButton() || p.getStamina() <= 50) return;
		RagingNinjaEntity f = new RagingNinjaEntity((int)m.getxPos(), (int)m.getyPos(), l);
		l.addEntity(f);
		p.setStamina(-50);
	}
}