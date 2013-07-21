package com.gb.war.gui;

import com.gb.war.Game;
import com.gb.war.graphics.Screen;
import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;

public class PauseMenu extends Menu {

	public PauseMenu(Game game, Level level) {
		super(game, level);
	}
	
	public void init() {
		objects.add(new Button(this, 104, 50, "Resume") {
			public void onClick(MouseHandler m) {
				menu.game.setMenu(null);
			}
		});

		objects.add(new Button(this, 112, 100, "Quit") {
			public void onClick(MouseHandler m) {
				System.exit(0);
			}
		});
		
		objects.add(new Button(this, 88, 75, "FullScreen") {
			public void onClick(MouseHandler m) {
				menu.game.shouldFullScreen = !menu.game.shouldFullScreen;
			}
		});
	}
	
	public void render(Screen screen) {
		Player player = game.player;
		level.applyOffset(player.getX() + player.getW() / 2, player.getY() + player.getH() / 2, screen);
		
		if(screen.lightingEnabled) {
			level.preRender(screen);
		}
		
		level.renderBackground(screen, (int)player.getX() + player.getW() / 2, (int)player.getY() + player.getH() / 2);
		level.renderSprites(screen);
		
		game.renderInterface();
		screen.fade(100);
		
		screen.clearOffset();
		
		super.render(screen);
	}
}
