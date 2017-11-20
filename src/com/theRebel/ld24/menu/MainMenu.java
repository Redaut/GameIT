package com.theRebel.ld24.menu;

import com.theRebel.ld24.Game;
import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.input.InputHandler;

public class MainMenu extends Menu {
	
	public MainMenu(InputHandler input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	String[] options = {"Play", "Help", "About"};
	int selected = 0;
	int timer = 10;
	
	public void update() {
		if(timer > 0) timer--;
		if(input.down && selected < options.length-1 && timer == 0) {
			selected++;
			timer = 10;
		}
		if(input.up && selected > 0 && timer == 0) {
			selected--;
			timer = 10;
		}
		
		if(selected < 0) selected = 0;
		if(selected > 2) selected = 2;
		
		if(selected == 0) {
			options[selected] = "> " + "Play" + " <"; 
		} else options[0] = "Play";
		
		if(selected == 1) {
			options[selected] = "> " + "Help" + " <"; 
			if(input.use) Game.menu = new HelpMenu(input);
		} else options[1] = "Help";
		
		if(selected == 2) {
			options[selected] = "> " + "About" + " <"; 
			if(input.use) Game.menu = new AboutMenu(input);
		} else options[2] = "About";
		
		if(selected == 0 && input.use) {
			input.releaseAll();
			Game.menu = new PlayMenu(input);
		}
	}
	
	public void render(Screen screen) {
		for(int i = 0; i < options.length; i++) {
			screen.renderText(options[i], 200, 200 + i*40, 30, 0, 0xffffff);
			
		}
	}
	
}
