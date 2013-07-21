package com.gb.war.gui;

import com.gb.war.graphics.Bitmap;
import com.gb.war.graphics.Font;
import com.gb.war.input.KeyHandler.Key;
import com.gb.war.input.MouseHandler;

public class Button extends MenuObject {

	protected String text;
	protected int color = 0xff0000;
	
	public Button(Menu m, int x, int y, String text) {
		super(m, x, y, 0, 0);
		this.text = text;
		w = text.length() * 8;
		h = 16;
	}

	public void onClick(MouseHandler m) {
		
	}
	
	public void onHover(MouseHandler m) {
		color = 0x0000ff;
	}
	
	public void onNotHover(MouseHandler m) {
		color = 0xff0000;
	}

	public void onKey(Key e) {
		
	}
	
	public void render(Bitmap b) {
		b.renderRect(x - 2, y - 2, w + 4, h + 4, color);
		Font.render(text, b, x, y);
	}	
}
