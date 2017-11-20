package com.theRebel.ld24.menu;

import com.theRebel.ld24.Game;
import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.input.InputHandler;

public class HelpMenu extends Menu {
	public HelpMenu(InputHandler input) {
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
		String[] text = {"Controls:", //
				"\n\n", //
				"MOVE RIGHT : D or Right Arrow ", //
				"\n", //
				"MOVE LEFT : A or Left Arrow", //
				"\n", // 
				" MOVE FRONT : W or Up Arrow",//
				"\n", // 
				"MOVE BACK : S or Down Arrow ",//
				"\n", // 
				"Rules:", //
				"\n\n\n", //		 
		"Player cannot move in water and cannot pass through trees and stones.", //
		"Player has to find and touch the opposite gender character in the selected biome to produce the clones.", //
		"Timer is set to 3 minutes after which the game stops and the final score is the population at that point. "};
		for (int i = 0; i < text.length; i++) {
			screen.renderText(text[i], 20 + 2, 50 + i * 30 + 2, 20, 0, 0);
			screen.renderText(text[i], 20, 50 + i * 30, 20, 0, 0xffffff);
		}
		//screen.renderText(options, 30 + 3, 500 + 3, 50, 1, 0);
		screen.renderText(options, 380, 550, 50, 1, 0xffffff);
			
	}
}
