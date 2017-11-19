package com.theRebel.ld24.entity.mob;

import java.util.Random;

import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.graphics.Sprite;
import com.theRebel.ld24.level.LightTile;

public class Dummy extends Mob {

	int spriteFlip = 0;
	final Random random = new Random();
	int xa, ya;
	
	public Dummy() {
		x = random.nextInt(30*16) + 45*16;
		y = random.nextInt(30*16) + 27*16;
	}
	
	public void update() {
		if(random.nextInt(40) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
		}
		move(xa, ya);
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
		
		screen.renderMob(x, y, this, spriteFlip, false);
		renderLighting(LightTile.lightTiles);
	}
	
}
