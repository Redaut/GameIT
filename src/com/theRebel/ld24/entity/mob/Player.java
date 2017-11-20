package com.theRebel.ld24.entity.mob;

import java.util.Random;

import com.theRebel.ld24.entity.Entity;
import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.graphics.Sprite;
import com.theRebel.ld24.input.InputHandler;
import com.theRebel.ld24.level.Level;
import com.theRebel.ld24.level.LightTile;

public class Player extends Mob {
	
	private InputHandler input;
	int spriteFlip = 0;
	public boolean night = false;
	Random random = new Random();

	
	public Player(InputHandler input) {
		this.input = input;
		sprite = Sprite.player0;
		x = 150;
		y = 85;
	}
	
	public Player(int x, int y, InputHandler input) {
		this.input = input;
		this.x = x;
		this.y = y;
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
		
		for(int i = 0; i < level.entities.size(); i++) {
			Entity e = level.entities.get(i);
			if(e instanceof female) {
				if(((x >> 4) == (e.x >> 4)) && ((y >> 4) == (e.y << 4)) && ((female) e).canSpawnChild) {

					for (int c = 0; c < random.nextInt(4) + 1; c++)  {
						Entity m = new ForestMob();
					m.x = x;
					m.y = y;
					level.add(m);
					//((female) e).canSpawnChild = false;
					}
				}
			}
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
		
		screen.renderMob(x, y, this, spriteFlip, false);
		renderLighting(LightTile.lightTiles);
		
	}
	
	
	
}
