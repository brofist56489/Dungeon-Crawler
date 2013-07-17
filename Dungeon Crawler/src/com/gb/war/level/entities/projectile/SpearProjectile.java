package com.gb.war.level.entities.projectile;

import com.gb.war.level.Level;

public class SpearProjectile extends Projectile {

	public SpearProjectile(int x, int y, float ax, float ay, Level l) {
		super(x, y, ax, ay, l, "PROJECTILE_SPEAR");
		life = 5;
		xo = 1;
		yo = 5;
		w = 14;
		h = 5;
	}
}
