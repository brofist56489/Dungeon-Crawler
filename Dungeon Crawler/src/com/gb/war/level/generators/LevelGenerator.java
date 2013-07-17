package com.gb.war.level.generators;

import com.gb.war.level.Level;

public abstract class LevelGenerator {
	protected Level level;
	protected int x = 0;
	protected int y = 0;
	
	public LevelGenerator(Level l) {
		level = l;
	}
	
	public abstract void generate();
}
