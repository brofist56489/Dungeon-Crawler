package com.gb.war.level.entities.projectile;

import com.gb.war.graphics.Bitmap;
import com.gb.war.level.Level;
import com.gb.war.level.entities.EntityKindness;


public class FlameProjectile extends Projectile {

	public FlameProjectile(int x, int y, float ax, float ay, Level l) {
		super(x, y, ax, ay, l, "PROJECTILE_FLAME");;
		xo = 6;
		yo = 6;
		w = 10;
		h = 5;
		setDamage(10);
		setKindness(EntityKindness.FRIENDLY);
	}
	
	public void tick() {
		if(super.move()) {
			alive = false;
		}
		super.tick();
	}
	
	public void preRender(Bitmap b) {
		b.addLightSource((int)x, (int)y, 20, 50);
	}
}
