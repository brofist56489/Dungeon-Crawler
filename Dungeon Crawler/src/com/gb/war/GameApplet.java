package com.gb.war;

import java.applet.Applet;
import java.awt.BorderLayout;

public class GameApplet extends Applet {
	private static final long serialVersionUID = 1L;
	
	private Game game;                         
	                                           
	public void init() {                       
		game = new Game();                     
		setLayout(new BorderLayout());         
		add(game, BorderLayout.CENTER);        
		GlobalVariables.set("isApplet", true);
	}
	
	public synchronized void start() {
		game.start();
	}
	
	public synchronized void stop() {
		game.stop();
	}
}
