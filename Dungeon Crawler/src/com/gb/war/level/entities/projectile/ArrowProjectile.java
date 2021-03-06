package com.gb.war.level.entities.projectile;

import com.gb.war.level.Level;
import com.gb.war.level.entities.EntityKindness;

public class ArrowProjectile extends Projectile {

	public ArrowProjectile(int x, int y, float ax, float ay, Level l) {
		super(x, y, ax, ay, l, "PROJECTILE_ARROW");
		setKindness(EntityKindness.FRIENDLY);
		xo = 2;
		yo = 5;
		w = 13;
		h = 5;
		life = 3000;
	}
	
	public void tick() {
		tickLife();
		if(super.move()) {
			ax = ay = 0;
		}
		super.tick();
	}
}