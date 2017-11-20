package com.theRebel.ld24.entity;

import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.level.Level;

public class Entity {

	public int x, y;
	public boolean removed = false;
	protected Level level;
	public int health = 100;
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		
	}
	
	public void remove() {
		removed = true;
	}

	public final void init(Level level) {
		this.level = level;
	}
	
}
