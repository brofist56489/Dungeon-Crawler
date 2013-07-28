package com.gb.war.items.resources;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;

public class Resource {

	public static Resource arrow = new Resource("Arrow", 32);
	public static Resource rock = new Resource("Rock", 32);
	public static Resource health_potion = new HealthPotionResource("HP Potion", 48);
	
	private int textId;
	private String name;
	public Resource(String name, int tId) {
		this.name = name;
		this.textId = tId;
	}
	
	public int getTextId() {
		return textId;
	}

	public String getName() {
		return name;
	}
	
	public boolean interact(MouseHandler m, Player p, Level l) {
		return false;
	}
	
}
