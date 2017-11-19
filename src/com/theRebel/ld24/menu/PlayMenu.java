package com.theRebel.ld24.menu;

import com.theRebel.ld24.Game;
import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.input.InputHandler;

public class PlayMenu extends Menu {

	public PlayMenu(InputHandler input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	String[] options = {"Forest", "Wall Mob", "Grass Mob"};
	int selected = 0;
	int timer = 10;
	public static int biome = -1;
	
	public void update() {
		if(timer > 0) timer--;
		if(input.down && selected < options.length && timer == 0) {
			selected++;
			timer = 10;
		}
		if(input.up && selected > 0 && timer == 0) {
			selected--;
			timer = 10;
		}
		
		if(selected < 0) selected = 0;
		if(selected > options.length-1) selected = options.length-1;
		
		if(selected == 0) {
			options[selected] = "> " + "Forest" + " <"; 
			if(input.use) {
				biome = 0;
			}
		} else options[0] = "Forest";
		
		if(selected == 1) {
			options[selected] = "> " + "Wall Mob" + " <"; 
			if(input.use) {
				biome = 1;
			}
		} else options[1] = "Wall Mob";
		
		if(selected == 2) {
			options[selected] = "> " + "Grass Mob" + " <"; 
			if(input.use) {
				biome = 2;
			}
		} else options[2] = "Grass Mob";
		
		
		
		if(input.back) {
			Game.menu = new MainMenu(input);
		}
		
		if(input.use && timer == 0) Game.menu = null;
	}
	
	public void render(Screen screen) {
		screen.renderText("Select a Biome", 210, 120, 50, 0xffffff);
		for(int i = 0; i < options.length; i++) {
			screen.renderText(options[i], 200, 200 + i*40, 30, 0xffffff);
			
		}
	}
	
	
}
