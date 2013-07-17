package com.gb.war.level.entities.projectile;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.ImageManager;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Mob;

public abstract class Projectile extends Mob {

	protected int life = 300;
	protected double rangle;
	
	public Projectile(int x, int y, float ax, float ay, Level l, String entityType) {
		super(x, y, l, entityType);
		this.ax = ax;
		this.ay = ay;
		getRad();
	}
	
	protected void getRad() {
		float nx = x + ax;
		float ny = y + ay;
		
		rangle = Math.PI - Math.atan2(ny - y, nx - x);
	}
	
	protected void tickLife() {
		life -= 1;
		if (life <= 0) {
			alive = false;
			return;
		}
	}
	
	public void tick() {
		tickLife();
		if(super.move()) {
			alive = false;
		}
	}
	
	public void render(Bitmap b) {
		ImageManager.renderRotatedImage(entityType, b, (int)x - xo, (int)y - yo, rangle);
	}
}