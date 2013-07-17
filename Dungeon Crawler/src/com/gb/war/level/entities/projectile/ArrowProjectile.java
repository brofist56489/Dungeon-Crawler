package com.gb.war.level.entities.projectile;

import com.gb.war.level.Level;

public class ArrowProjectile extends Projectile {

	public ArrowProjectile(int x, int y, float ax, float ay, Level l) {
		super(x, y, ax, ay, l, "PROJECTILE_ARROW");
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
	}
}