package com.gb.war.level.entities.projectile;


import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.level.Level;
import com.gb.war.level.entities.EntityKindness;

public class ShurikenProjectile extends Projectile {

	private double rad;
	
	public ShurikenProjectile(int x, int y, float ax, float ay, Level l) {
		super(x, y, ax, ay, l, "PROJECTILE_SHURIKEN");
		setKindness(EntityKindness.FRIENDLY);
		xo = 2;
		yo = 3;
		w = 11;
		h = 11;
		rad = 0;
	}
	
	public void tick() {
		super.tick();
		rad -= Math.PI / 8;
		if(rad < 0) {
			rad += Math.PI * 2;
		}
	}
	
	public void render(Bitmap b) {
		ImageManager.renderRotatedImage(entityType, b, (int)x, (int)y, rad);
	}
}