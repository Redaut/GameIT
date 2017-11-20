package com.theRebel.ld24.menu;

import com.theRebel.ld24.Game;
import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.input.InputHandler;

public class AboutMenu extends Menu {

	public AboutMenu(InputHandler input) {
		super(input);
		
	}
	
	String options = " > Back < ";
	int selected = 0;
	int timer = 10;
	
	public void update() {
		if(timer > 0) timer--;
				
		
		if(selected == 0 && input.use && timer == 0) {
			input.releaseAll();
			Game.menu = new MainMenu(input);
		}
	}
	
	public void render(Screen screen) {
		String[] text = {"Apocalypse has ended the human race on the planet Earth. Mother Earth is", //
				"\n", //
				"no more inhabitable. People who went to  mars, years before the destruction for ", //
				"\n", //
				"the scientific research returned to earth. They were shocked after watching the current", //
				"\n", // 
				" position of the planet Earth. The team then decided to reconstruct the planet by increasing",//
				"\n", // 
				"the human population in various cloning techniques their ancestors left for them. They ended up ",//
				"\n", // 
				"on a technique which allows them to clone just by touching the opposite gender.", //
				"\n\n\n", //		 
		"Help them reconstruct their planet Earth for the cause of flourishing the human race."};
		for (int i = 0; i < text.length; i++) {
			screen.renderText(text[i], 20 + 2, 50 + i * 30 + 2, 24, 0, 0);
			screen.renderText(text[i], 20, 50 + i * 30, 24, 0, 0xffffff);
		}
		screen.renderText(options, 350 + 3, 500 + 3, 50, 1, 0);
		screen.renderText(options, 350, 500, 50, 1, 0xffffff);
			
	}
	
}
