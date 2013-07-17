package com.gb.war.level.entities.projectile;

import com.gb.war.graphics.Bitmap;
import com.gb.war.level.Level;

public class FlameProjectile extends Projectile {

	public FlameProjectile(int x, int y, float ax, float ay, Level l) {
		super(x, y, ax, ay, l, "PROJECTILE_FLAME");
		xo = 6;
		yo = 6;
		w = 10;
		h = 5;
	}
	
	public void preRender(Bitmap b) {
		b.addLightSource((int)x, (int)y, 20, 50);
	}
}
