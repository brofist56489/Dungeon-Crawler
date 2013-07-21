package com.gb.war.level.entities;

public enum EntityKindness {
	
	HOSTILE(1), PASSIVE(1), FRIENDLY(0);
	
	private int damageMul;
	EntityKindness(int dmg) {
		damageMul = dmg;
	}
	
	public int getDamage() {
		return damageMul;
	}
}
