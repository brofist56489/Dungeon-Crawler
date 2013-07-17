package com.gb.war.gui;

import com.gb.war.Game;
import com.gb.war.graphics.Screen;
import com.gb.war.level.Level;

public class Menu {
	protected Game game;
	protected Level level;
	
	public Menu(Game game, Level level) {
		this.game = game;
		this.level = level;
	}
	
	public Menu(Menu m) {
		this.game = m.getGame();
		this.level = m.getLevel();
	}
	
	public void tick() {
	}
	
	public void render(Screen screen) {
	}

	public Game getGame() {
		return game;
	}

	public Level getLevel() {
		return level;
	}
}
