package com.gb.war.input;

import com.gb.war.Game;
import com.gb.war.graphics.ImageManager;
import com.gb.war.graphics.Screen;

public class Mouse {
	
	private int x;
	private int y;
	
	public Mouse() {
		ImageManager.loadImage("/textures/mouse.png", "mouseTexture");
	}

	private void getPos() {
		x = Game.instance.mouse.getxPos();
		y = Game.instance.mouse.getyPos();
	}
	
	public void render(Screen screen) {
		getPos();
		ImageManager.render("mouseTexture", screen, x, y, false);
	}
}
