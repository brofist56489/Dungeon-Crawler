package com.gb.war.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import com.gb.war.Game;

public class KeyHandler implements KeyListener {
	public class Key {
		private boolean pressed;
		private boolean clicked;
		private int absorbs, presses;
		private int code;
		private String rep;
		
		public Key(int code, String val) {
			keys.put(code, this);
			this.code = code;
			this.rep = val;
		}
		
		public void toggle(boolean press) {
			if (pressed != press) {
				pressed = press;
				if (press) {
					presses+=2;
				}
			}
		}
		
		public void tick() {
			if (absorbs < presses) {
				absorbs++;
				clicked = true;
			} else if (absorbs > presses) {
				absorbs = presses;
				clicked = false;
			} else {
				clicked = false;
			}
		}
		
		public boolean isPressed() {
			return pressed;
		}
		
		public boolean isClicked() {
			return clicked;
		}

		public int getCode() {
			return code;
		}

		public String getRep() {
			return rep;
		}
	}
	
	public KeyHandler(Game game) {
		game.addKeyListener(this);
		for(int i = 0; i < 512; i++) {
			new Key(i, KeyEvent.getKeyText(i));
		}
	}
	
	public void tick() {
		for (Key k : keys.values()) {
			k.tick();
		}
	}
	
	public HashMap<Integer, Key> keys = new HashMap<Integer, Key>();

	public Key toggle(int e, boolean press) {
		for(Key k : keys.values()) {
			if(k.getCode() == e) {
				k.toggle(press);
				return k;
			}
		}
		
		return null;
	}
	
	public void keyPressed(KeyEvent e) {
		Key k = toggle(e.getKeyCode(), true);
		tick();
		if(Game.instance.menu != null)
			if(k != null && k.clicked) {
				Game.instance.menu.onKey(k);
			}
	}

	public void keyReleased(KeyEvent e) {
		toggle(e.getKeyCode(), false);
	}

	public void keyTyped(KeyEvent e) {
	}
	
	public boolean isKeyDown(int k) {
		return keys.get(k).isPressed();
	}
	
	public boolean isKeyPressed(int k) {
		return keys.get(k).isClicked();
	}

	public void releaseAll() {
		for(Key k : keys.values()) {
			k.toggle(false);
		}
	}

}