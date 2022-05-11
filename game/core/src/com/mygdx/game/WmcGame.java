package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.MainMenu;

public class WmcGame extends Game {


	@Override
	public void create () {
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void pause(){super.pause();}

	@Override
	public void resume(){
		super.resume();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
}
