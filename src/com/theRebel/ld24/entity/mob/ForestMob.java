package com.theRebel.ld24.entity.mob;

import java.util.Random;

import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.graphics.Sprite;
import com.theRebel.ld24.level.LightTile;

public class ForestMob extends Mob {
	int spriteFlip = 0;
	final Random random = new Random();
	int xa, ya;

	public ForestMob() {
		x = 30*16;
		y = 50 * 16;
	}

	public void update() {
		if (random.nextInt(40) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
		}
		if (xa != 0 || ya != 0) {
			walking = true;
			move(xa, ya);
		} else {
			walking = false;
		}
		if (health <= 0) remove();
	}

	int anim = 0;

	public void render(Screen screen) {
		anim++;
		if (dir == 0) {
			sprite = Sprite.player0;
			spriteFlip = 0;
			if (walking) {
				sprite = Sprite.player1;
				if (anim % 250 > 125) {
					spriteFlip = 0;
				} else {
					spriteFlip = 1;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.player4;
			spriteFlip = 0;
			if (walking) {
				if (anim % 250 > 125) {
					sprite = Sprite.player4;
				} else {
					sprite = Sprite.player5;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.player2;
			spriteFlip = 0;
			if (walking) {
				sprite = Sprite.player3;
				if (anim % 250 > 125) {
					spriteFlip = 0;
				} else {
					spriteFlip = 1;
				}
			}

		}
		if (dir == 3) {
			sprite = Sprite.player4;
			spriteFlip = 1;
			if (walking) {
				if (anim % 250 > 125) {
					sprite = Sprite.player4;
				} else {
					sprite = Sprite.player5;
				}
			}
		}
		screen.renderMob(x, y, this, spriteFlip, false);
		renderLighting(LightTile.lightTiles);
	}
}
