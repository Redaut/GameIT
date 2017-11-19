package com.theRebel.ld24.menu;

import java.util.Random;

import com.theRebel.ld24.Game;
import com.theRebel.ld24.graphics.Screen;
import com.theRebel.ld24.input.InputHandler;

public class Menu {
	
	protected InputHandler input;
	protected Game game;
	protected final Random random = new Random();
	
	public Menu(InputHandler input) {
		this.input = input;
	}


	public void render(Screen screen) {
		// TODO Auto-generated method stub
		
	}

	public Menu(Game game) {
		this.game = game;
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

}
