package com.gb.war.items.resources;

import com.gb.war.input.MouseHandler;
import com.gb.war.level.Level;
import com.gb.war.level.entities.Player;

public class HealthPotionResource extends Resource {

	public HealthPotionResource(String name, int tId) {
		super(name, tId);
	}
	
	public boolean interact(MouseHandler m, Player p, Level l) {
		if(!m.isLeftClickedButton()) return false;
		p.setHealth(-20);
		p.inventory.removeResource(this, 1);
		return true;
	}
}
