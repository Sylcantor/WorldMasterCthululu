package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.screens.*;

public class ButtonListener extends ChangeListener {
    Button button;
    Game game;
    public ButtonListener(Button button, Game game) {
        this.button = button;
        this.game = game;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        if(button.getName().equals("Play")){
            game.setScreen(new GameScreen(game,"Maps/Map_1.tmx"));
        }
        else if(button.getName().equals("Level")){
            game.setScreen(new LevelScreen(game));
        }
        else if(button.getName().equals("Settings")){
            game.setScreen(new SettingsScreen(game));
        }
        else if(button.getName().equals("Forest")){
            game.setScreen(new ForestLevelScreen(game));
        }
        else if(button.getName().equals("Desert")){
            game.setScreen(new DesertLevelScreen(game));
        }
        else if(button.getName().equals("Aquatic")){
            game.setScreen(new AquaticLevelScreen(game));
        }
    }
}
