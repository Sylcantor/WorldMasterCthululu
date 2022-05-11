package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

import com.mygdx.game.Map;
import com.mygdx.game.Monster;
import com.mygdx.game.Player;


public class GameScreen extends WmcScreen {



    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private Map map;
    private Player player;
    private final String mapName;
    private Array<Monster> monsters;
    private Monster monster;

    private static int mapNumberIncrement = 1;

    public GameScreen(Game game,String mapName) {
        super(game);
        this.mapName = mapName;
    }

    private void drawMonsters(){
        for(int i=0;i<monsters.size;i++){
            monster = monsters.get(i);
            monster.draw(renderer.getBatch());
        }
    }

    @Override
    public void show() {
        map = new Map(mapName);
        player = map.getPlayer();
        monsters = map.getMonsters();
        monster = monsters.get(0);
        renderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
        camera = new OrthographicCamera();
        player.setPosition(map.getXSpawn() * map.tiledMapTileLayer.getTileWidth(), map.getY_spawn() *map.tiledMapTileLayer.getTileHeight());
        Gdx.input.setInputProcessor(player);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
        camera.update();
        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();
        drawMonsters();
        player.draw(renderer.getBatch());
        renderer.getBatch().end();

        if(player.getHp()==0 || player.isCellBlock("kill",player.getX(),player.getY())){
            game.setScreen(new GameScreen(game,this.mapName));
        }
        if(player.isCellBlock("end",player.getX(),player.getY())){
            mapNumberIncrement+=1;
            game.setScreen(new GameScreen(game,this.mapName.replace(String.valueOf(mapNumberIncrement-1),String.valueOf(mapNumberIncrement))));
        }


    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        map.getTiledMap().dispose();
        renderer.dispose();
    }
}
