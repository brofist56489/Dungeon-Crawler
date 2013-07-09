package com.bh.war.items;

import com.bh.war.input.MouseHandler;
import com.bh.war.level.Level;
import com.bh.war.level.entities.FireBurstEntity;
import com.bh.war.level.entities.Player;
import com.bh.war.level.entities.projectile.FlameProjectile;

public class FireWandItem extends ToolItem {

	public FireWandItem(int level) {
		super(level);
		textId = 0;
	}
	
	public void onLeftClick(MouseHandler m, Level l, Player p) {
		if(!m.isLeftClickedButton() || p.getMana() <= 4) return;
		float cx = p.getX() + p.getW() / 2;
		float cy = p.getY() + p.getH() / 2;
		double a = Math.atan2(m.getyPos() - cy, m.getxPos() - cx);
		double spread = 0.3;
		for(double i = -spread; i <= spread * level / 2; i += spread) {
			double ax = Math.cos(a + i) * 5;
			double ay = Math.sin(a + i) * 5;
			FlameProjectile f = new FlameProjectile((int)cx, (int)cy, (float)ax, (float)ay, l);
			l.addEntity(f);
		}
		p.setMana(-4);
	}
	
	public void onRightClick(MouseHandler m, Level l, Player p) {
		if(!m.isRightClickedButton() || p.getMana() <= 50) return;
		if(level < MAX_LEVEL) return;
		FireBurstEntity f = new FireBurstEntity((int)m.getxPos(), (int)m.getyPos(), l);
		l.addEntity(f);
		p.setMana(-50);
	}
}
