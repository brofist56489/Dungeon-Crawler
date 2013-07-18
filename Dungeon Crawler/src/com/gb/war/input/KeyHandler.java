package com.gb.war.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import com.gb.war.Game;

public class KeyHandler implements KeyListener {
	public class Key {
		private boolean pressed;
		private boolean clicked;
		private int absorbs, presses;
		
		public Key() {
			keys.add(this);
		}
		
		public void toggle(boolean press) {
			if (pressed != press) {
				pressed = press;
				if (press) {
					presses++;
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
	}
	
	public KeyHandler(Game game) {
		game.addKeyListener(this);
	}
	
	public void tick() {
		for (Key k : keys) {
			k.tick();
		}
	}
	
	public List<Key> keys = new ArrayList<Key>();
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key sprint = new Key();
	public Key inventory = new Key();

	public void toggle(int e, boolean press) {
		if (e == KeyEvent.VK_UP || e == KeyEvent.VK_W) {
			up.toggle(press);
		}
		if (e == KeyEvent.VK_DOWN || e == KeyEvent.VK_S) {
			down.toggle(press);
		}
		if (e == KeyEvent.VK_LEFT || e == KeyEvent.VK_A) {
			left.toggle(press);
		}
		if (e == KeyEvent.VK_RIGHT || e == KeyEvent.VK_D) {
			right.toggle(press);
		}
		if (e == KeyEvent.VK_SHIFT) {
			sprint.toggle(press);
		}
		if (e == KeyEvent.VK_E) {
			inventory.toggle(press);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		toggle(e.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent e) {
		toggle(e.getKeyCode(), false);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void releaseAll() {
		for(Key k : keys) {
			k.toggle(false);
		}
	}

}
