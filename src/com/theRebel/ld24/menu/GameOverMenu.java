package com.theRebel.ld24.menu;

import com.theRebel.ld24.Game;
import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.input.InputHandler;

public class GameOverMenu extends Menu {
	
	public GameOverMenu(InputHandler input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	String[] options = {"Play Again?", "Main Menu"};
	int selected = 0;
	int timer = 10;
	
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
		if(selected > 1) selected = 1;
		
		if(selected == 0) {
			options[selected] = "> " + "Play Again?" + " <"; 
		} else options[0] = "Play Again?";
		
		if(selected == 1) {
			options[selected] = "> " + "Main Menu" + " <"; 
			if(input.use) Game.menu = new MainMenu(input);
		} else options[1] = "Main Menu";
		
		
	}
	
	public void render(Screen screen) {
		for(int i = 0; i < options.length; i++) {
			screen.renderText(options[i], 200, 200 + i*40, 30, 0xffffff);
			
		}
	}
	
}
