package com.bh.war.level.entities;

import com.bh.war.graphics.Bitmap;
import com.bh.war.graphics.Image;
import com.bh.war.graphics.ImageManager;
import com.bh.war.level.Level;

public abstract class Entity {
	
	public static void init() {
		ImageManager.loadImage("/textures/player.png", "ENTITY_PLAYER");
		ImageManager.setColorKey("ENTITY_PLAYER", 0x202020, 0x5fcf, 0x2fdf00, 0xffff00);
		
		ImageManager.loadImage("/textures/flameprojectile.png", "PROJECTILE_FLAME");
	}
	
	protected float x, y;
	protected float ax, ay;
	protected int w, h;
	
	protected Image image;
	protected String entityType;
	
	public boolean alive;
	
	protected Level level;
	
	protected int ANIMATION_DELAY_MAX = 20;
	protected int animation_delay = ANIMATION_DELAY_MAX;
	protected int dir = 0; //  0 
						   // 3 2
						   //  1
	protected int ANIMATION_FRAME_MAX = 4;
	protected int animation_frame = 0;
	
	protected int health;
	protected int MAX_HEALTH;
	protected int mana;
	protected int MAX_MANA;
	protected int stamina;
	protected int MAX_STAMINA;
	
	
	public Entity(int x, int y, Level l, String entityType) {
		this.x = x;
		this.y = y;
		this.level = l;
		this.entityType = entityType;
		alive = true;
	}
	
	public abstract void tick();
	
	public void preRender(Bitmap screen) {
	}
	
	public void render(Bitmap screen) {
		ImageManager.render(entityType, screen, (int)x, (int)y);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health += health;
	}

	public int getMAX_HEALTH() {
		return MAX_HEALTH;
	}

	public void setMAX_HEALTH(int mAX_HEALTH) {
		MAX_HEALTH = mAX_HEALTH;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana += mana;
	}

	public int getMAX_MANA() {
		return MAX_MANA;
	}

	public void setMAX_MANA(int mAX_MANA) {
		MAX_MANA = mAX_MANA;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina += stamina;
	}

	public int getMAX_STAMINA() {
		return MAX_STAMINA;
	}

	public void setMAX_STAMINA(int mAX_STAMINA) {
		MAX_STAMINA = mAX_STAMINA;
	}

	public void setW(int w) {
		this.w = w;
	}

	public void setH(int h) {
		this.h = h;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getAx() {
		return ax;
	}

	public void setAx(float ax) {
		this.ax = ax;
	}

	public float getAy() {
		return ay;
	}

	public void setAy(float ay) {
		this.ay = ay;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}
}
