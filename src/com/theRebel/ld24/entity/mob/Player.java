package com.theRebel.ld24.entity.mob;

import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.graphics.Sprite;
import com.theRebel.ld24.input.InputHandler;
import com.theRebel.ld24.level.Level;

public class Player extends Mob {
	
	private InputHandler input;
	int spriteFlip = 0;
	public boolean night = false;
	
	public Player(InputHandler input) {
		this.input = input;
		sprite = Sprite.player0;
	}
	
	public void update() {
		if(Level.brightness < -100) {
			night = true;
		}
		else {
			night = false;
		}
		int xa = 0, ya = 0;
		
		if(input.up) ya--;
		if(input.down) ya++;
		if(input.left) xa--;
		if(input.right) xa++;
		
		if(xa != 0 || ya != 0){
			move(xa, ya);
		}
	}
	
	public void render(Screen screen) {
		
		if(dir == 0) {
			sprite = Sprite.player0;
			spriteFlip = 0;
		}
		
		if(dir == 1) {
			sprite = Sprite.player4;
			spriteFlip = 0;
		}
		
		if(dir == 2) {
			sprite = Sprite.player3;
			spriteFlip = 0;
		}
		
		if(dir == 3) {
			sprite = Sprite.player4;
			spriteFlip = 1;
		}
		
		screen.renderMob(x, y, sprite, spriteFlip);
	}
	
}
