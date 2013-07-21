package com.gb.war.gui;

import com.gb.war.Game;
import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.Font;
import com.gb.war.graphics.ImageManager;
import com.gb.war.graphics.Screen;
import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;

public class MainMenu extends Menu {

	public MainMenu(Game game, Level level) {
		super(game, level);
		ImageManager.loadImage("/textures/menu/main/logo.png", "MENU_MAIN_TITLE");
	}
	
	public void init() {
		objects.add(new Button(this, 108, 90, "Play") {
			public void onClick(MouseHandler m) {
				menu.game.setMenu(null);
			}
			
			public void render(Bitmap b) {
				Font.render(text, b, x + 2, y + 2, 0x3f3f3f);
				Font.render(text, b, x, y, color);
			}
		});
		
		objects.add(new Button(this, 108, 130, "Quit") {
			public void onClick(MouseHandler m) {
				System.exit(0);
			}
			public void render(Bitmap b) {
				Font.render(text, b, x + 2, y + 2, 0x3f3f3f);
				Font.render(text, b, x, y, color);
			}
		});
	}
	
	public void render(Screen screen) {
		ImageManager.render("MENU_MAIN_TITLE", screen, 0, 0);
		super.render(screen);
	}
}
