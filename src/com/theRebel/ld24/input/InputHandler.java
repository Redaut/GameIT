package com.theRebel.ld24.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	
	private boolean[] keys = new boolean[65536];
	public boolean up, down, left, right, back;
	public boolean use;
	
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		use = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_ENTER];
		back = (keys[KeyEvent.VK_ESCAPE]);
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void releaseAll() {
		up = down = right = back = use = false;		
	}
	
}
