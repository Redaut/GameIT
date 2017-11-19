package com.theRebel.ld24.entity;

import com.theRebel.ld24.entity.mob.Dummy;
import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.level.Level;

public class Population extends Interface {
	double population = 1;
	double growth = 0.1;
	private Level level;
	
	public Population(Level level) {
		this.level = level;
	}
	
	public void update() {
		int pop = (int) Math.floor(population);
		population += growth;
		if(pop % 100 == 0) level.add(new Dummy());
	}
	
	public void render(Screen screen) {
		int pop = (int) Math.floor(population);
		screen.renderText("Population : " + pop , 500, 30, 30, 0xffffff);
		
	}
}
