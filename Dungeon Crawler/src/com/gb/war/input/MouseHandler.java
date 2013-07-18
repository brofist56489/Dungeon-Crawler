package com.gb.war.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.gb.war.Game;

public class MouseHandler implements MouseListener, MouseMotionListener {

	private int xPos;
	private int yPos;
	private boolean leftButton;
	private boolean middleButton;
	private boolean rightButton;
	private boolean leftClickedButton;
	private boolean middleClickedButton;
	private boolean rightClickedButton;
	
	private int leftAbsorbs;
	private int middleAbsorbs;
	private int rightAbsorbs;
	
	private int leftPresses;
	private int middlePresses;
	private int rightPresses;
	
	public MouseHandler(Game game) {
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
	}
	
	public void mouseDragged(MouseEvent arg0) {
		xPos = arg0.getX() * Game.WIDTH / Game.instance.getWidth();
		yPos = arg0.getY() * Game.HEIGHT / Game.instance.getHeight();
	}

	public void mouseMoved(MouseEvent arg0) {
		xPos = arg0.getX() * Game.WIDTH / Game.instance.getWidth();
		yPos = arg0.getY() * Game.HEIGHT / Game.instance.getHeight();
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
		xPos = arg0.getX() * Game.WIDTH / Game.instance.getWidth();
		yPos = arg0.getY() * Game.HEIGHT / Game.instance.getHeight();
	}

	public void mouseExited(MouseEvent arg0) {
		xPos = arg0.getX() * Game.WIDTH / Game.instance.getWidth();
		yPos = arg0.getY() * Game.HEIGHT / Game.instance.getHeight();
	}
	
	public void tick() {
		if (leftAbsorbs < leftPresses) {
			leftAbsorbs += 1;
			leftClickedButton = true;
		} else leftClickedButton = false;

		if (middleAbsorbs < middlePresses) {
			middleAbsorbs += 1;
			middleClickedButton = true;
		} else middleClickedButton = false;
		
		if (rightAbsorbs < rightPresses) {
			rightAbsorbs += 1;
			rightClickedButton = true;
		} else rightClickedButton = false;
	}

	public void mousePressed(MouseEvent arg0) {
		switch(arg0.getButton()) {
		default:
			break;
		case MouseEvent.BUTTON1:
			leftButton = true;
			leftPresses += 1;
			break;
		case MouseEvent.BUTTON2:
			middleButton = true;
			middlePresses += 1;
			break;
		case MouseEvent.BUTTON3:
			rightButton = true;
			rightPresses += 1;
			break;
		}
	}

	public void mouseReleased(MouseEvent arg0) {
		switch(arg0.getButton()) {
		default:
			break;
		case MouseEvent.BUTTON1:
			leftButton = false;
			break;
		case MouseEvent.BUTTON2:
			middleButton = false;
			break;
		case MouseEvent.BUTTON3:
			rightButton = false;
			break;
		}
	}

	public int getxPos() {
		return xPos + Game.instance.screen.xOff;
	}

	public int getyPos() {
		return yPos + Game.instance.screen.yOff;
	}

	public boolean isLeftButton() {
		return leftButton;
	}

	public boolean isMiddleButton() {
		return middleButton;
	}

	public boolean isRightButton() {
		return rightButton;
	}

	public boolean isLeftClickedButton() {
		return leftClickedButton;
	}

	public void setLeftClickedButton(boolean leftClickedButton) {
		this.leftClickedButton = leftClickedButton;
	}

	public boolean isMiddleClickedButton() {
		return middleClickedButton;
	}

	public void setMiddleClickedButton(boolean middleClickedButton) {
		this.middleClickedButton = middleClickedButton;
	}

	public boolean isRightClickedButton() {
		return rightClickedButton;
	}

	public void setRightClickedButton(boolean rightClickedButton) {
		this.rightClickedButton = rightClickedButton;
	}
}
