package com.gb.war.level.entities.projectile;

import java.util.Random;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.level.Level;

public class LightningProjectile extends Projectile {

	Random rand;

	public LightningProjectile(int x, int y, float ax, float ay, Level l) {
		super(x, y, ax, ay, l, "PROJECTILE_LIGHTNING");
		xo = 4;
		yo = 1;
		w = 15;
		h = 11;
		rand = new Random();
	}

	public void preRender(Bitmap b) {
		b.addLightSource((int) x, (int) y, 20, 50);
	}

	public void render(Bitmap b) {
		ImageManager.render(entityType, b, (int) x, (int) y, rand.nextInt(4));
	}
}
