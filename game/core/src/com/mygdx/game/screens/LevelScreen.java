package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.ButtonListener;

public class LevelScreen extends WmcScreen {

    Stage stage;

    Button button_1;
    Button button_2;
    Button button_3;

    Texture background;

    TextureRegion title;
    SpriteBatch batch;


    OrthographicCamera camera;

    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;

    Button.ButtonStyle buttonStyle;

    public LevelScreen(Game game) {super(game);}

    @Override
    public void show () {


        camera = new OrthographicCamera();

        stage = new Stage();

        background = new Texture("Menu.png");
        title = new TextureRegion(background, 0, 0, stage.getWidth(), stage.getHeight());
        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("Buttons/buttons.pack"));
        skin.addRegions(buttonAtlas);

        buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = skin.getDrawable("Button");
        buttonStyle.down = skin.getDrawable("Button_down");

        button_1 = new Button(buttonStyle);
        button_1.setName("Forest");
        button_1.setPosition((stage.getWidth()/2)-button_1.getWidth()/2, (stage.getHeight()/2)-button_1.getHeight()/2);

        button_2 = new Button(buttonStyle);
        button_2.setName("Desert");
        button_2.setPosition((stage.getWidth()/2)-button_2.getWidth()/2, button_1.getY()-button_1.getHeight()-10);


        button_3 = new Button(buttonStyle);
        button_3.setName("Aquatic");
        button_3.setPosition((stage.getWidth()/2)-button_1.getWidth()/2, button_2.getY()-button_2.getHeight()-10);

        button_1.addListener(new ButtonListener(button_1,game));
        button_2.addListener(new ButtonListener(button_2,game));
        button_3.addListener(new ButtonListener(button_2,game));

        stage.addActor(button_1);
        stage.addActor(button_2);
        stage.addActor(button_3);

    }

    @Override
    public void render(float delta){

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(title, 0, 0);
        batch.end();

        stage.draw();
    }

    @Override
    public void hide () {
        Gdx.app.debug("Wmc", "dispose main menu");

    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,width,height);
        super.resize(width, height);
    }
}
