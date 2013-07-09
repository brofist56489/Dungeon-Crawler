package com.bh.war.gui;

import com.bh.war.Game;
import com.bh.war.graphics.Bitmap;
import com.bh.war.level.Level;

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
	
	public void render(Bitmap b) {
	}

	public Game getGame() {
		return game;
	}

	public Level getLevel() {
		return level;
	}
}
