package com.theRebel.ld24.entity.mob;

import java.util.Random;

import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.graphics.Sprite;
import com.theRebel.ld24.level.LightTile;

public class female extends Mob {
	int spriteFlip = 0;
	final Random random = new Random();
	int xa, ya;
	int anim = 0;
	public boolean canSpawnChild = true;

	public female() {
		x = 60*16;
		y = 30*16;
	}

	public void update() {
		if (random.nextInt(40) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
		}
		
			move(xa, ya);
		
	}

	public void render(Screen screen) {
	
		if (dir == 0) {
			sprite = Sprite.female0;
			spriteFlip = 0;
			
			
		}
		if (dir == 1) {
			sprite = Sprite.female4;
			spriteFlip = 0;
			
			
		}
		if (dir == 2) {
			sprite = Sprite.female2;
			spriteFlip = 0;
			

		}
		if (dir == 3) {
			sprite = Sprite.female4;
			spriteFlip = 1;
			
		}
		screen.renderMob(x, y, this, spriteFlip, false);
		renderLighting(LightTile.lightTiles);
	}
}