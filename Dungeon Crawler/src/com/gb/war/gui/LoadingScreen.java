package com.gb.war.gui;

import com.gb.war.Game;
import com.gb.war.graphics.ImageManager;
import com.gb.war.graphics.Screen;
import com.gb.war.level.Level;

public class LoadingScreen extends Menu {

	protected int duration = 60;
	
	public LoadingScreen(Game game, Level level) {
		super(game, level);
		ImageManager.loadImage("/textures/menu/loading/logo.png", "GB_LOAD_IMAGE");
	}
	
	public void tick() {
		duration--;
		if(duration <= 0) {
			game.setMenu(new MainMenu(game, level));
		}
	}
	
	public void render(Screen screen) {
		ImageManager.render("GB_LOAD_IMAGE", screen, 0, 0);
	}
}
