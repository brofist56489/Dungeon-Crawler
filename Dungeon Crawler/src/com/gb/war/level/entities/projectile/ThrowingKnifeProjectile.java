package com.gb.war.level.entities.projectile;


import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.level.Level;
import com.gb.war.level.entities.EntityKindness;

public class ThrowingKnifeProjectile extends Projectile {

	private double rad;
	
	public ThrowingKnifeProjectile(int x, int y, float ax, float ay, Level l) {
		super(x, y, ax, ay, l, "PROJECTILE_THROWING_KNIFE");
		xo = 2;
		yo = 6;
		w = 15;
		h = 4;
		rad = 0;
		setKindness(EntityKindness.FRIENDLY);
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
