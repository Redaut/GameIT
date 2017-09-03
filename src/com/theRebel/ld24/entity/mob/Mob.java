package com.theRebel.ld24.entity.mob;

import com.theRebel.ld24.entity.Entity;
import com.theRebel.ld24.graphics.Sprite;

public class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0;
	
	public void move(int xa, int ya) {
		if(xa != 0 && ya != 0){
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		if(!collision(xa, ya) && !collision2(xa, ya)){
			x += xa;
			y += ya;
		}
	}

	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for(int i = 0; i < 4; i++) {
			int xt = ((x+xa) + (i%2*2-1) *4) >> 4;
			int yt = ((y+ya) + (i/2*2-2) *3) >> 4;
			
			if(level.getTile(xt+1, yt+1).solid()) {
				solid = true;
			}
		}
		return solid;
	}
	
	private boolean collision2(int xa, int ya) {
		boolean solid = false;
		for(int i = 0; i < 4; i++) {
			int xt = ((x+xa) + (i%2*2-1) *8) >> 4;
			int yt = ((y+ya) + (i/2*2-1) *6) >> 4;
			
			if(level.getTile2(xt+1, yt+1).solid()) {
				solid = true;
			}
		}
		return solid;
	}
	
}
