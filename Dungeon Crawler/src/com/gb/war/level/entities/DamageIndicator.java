package com.gb.war.level.entities;

import com.gb.war.Game;
import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.Font;
import com.gb.war.level.Level;

public class DamageIndicator extends Entity {
	
	private String text;
	
	private int life = 20;
	
	public DamageIndicator(int x, int y, Level l, String damage) {
		super(x, y, l, "NULL");
		text = damage;
	}
	
	public void tick() {
		life--;
		if(life <= 0) {
			alive = false;
			return;
		}
		
		if(Game.tickCount % 3 == 0) {
			y -= 4;
		}
	}
	
	public void render(Bitmap b) {
		Font.render(text, b, (int)x + 1, (int)y + 1, 0x3f3f3f);
		Font.render(text, b, (int)x, (int)y, 0xff0000);
	}
}
