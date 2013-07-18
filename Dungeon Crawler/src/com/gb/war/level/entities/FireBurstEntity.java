package com.gb.war.level.entities;

import com.gb.war.graphics.Bitmap;
import com.gb.war.level.Level;
import com.gb.war.level.entities.projectile.FlameProjectile;

public class FireBurstEntity extends Entity {

	private final int MAX_COOLDOWN = 20;
	private int cooldown =  MAX_COOLDOWN;
	
	private int life = 300;
	
	public FireBurstEntity(int x, int y, Level l) {
		super(x, y, l, "ENTITY_FIREBURST");
	}

	public void tick() {
		cooldown -= 1;
		if(cooldown == 0) {
			cooldown = MAX_COOLDOWN;
			burst();
		}
		life -= 1;
		if(life == 0) {
			alive = false;
		}
	}
	
	private void burst() {
		for(double i = 0; i < Math.PI * 2; i += Math.PI /4) {
			double ax = Math.cos(i) * 5;
			double ay = Math.sin(i) * 5;
			FlameProjectile f = new FlameProjectile((int)x, (int)y, (float)ax, (float)ay, level);
			level.addEntity(f);
		}
	}
	
	public void render(Bitmap b) {
		b.renderCircle((int)x, (int)y, 4, 0xFF0000);
	}
}
