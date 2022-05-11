package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;

public class Map {
    private String name;
    private final TiledMap tiledMap;
    private final Player player;

    private int x_spawn;
    private int y_spawn;

    public TiledMapTileLayer tiledMapTileLayer;

    Array<Monster> monsters = new Array<>();

    public Map(String mapName){
        this.name = mapName;
        this.tiledMap = new TmxMapLoader().load(mapName);
        this.tiledMapTileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);

        Texture chtululuTexture = new Texture("cthululu.png");
        this.player = new Player(new Sprite(chtululuTexture),tiledMapTileLayer,
                "Cthululu", null, 5,10,null);
        setMap();
    }

    public Array<Monster> getMonsters(){

        return monsters;
    }

    private void setSpawn(){
        this.x_spawn = 0;
    }

    private void setMap() {
        Monster monster;
        for (int x = 0; x < tiledMapTileLayer.getWidth(); x++) {
            for (int y = 0; y < tiledMapTileLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = tiledMapTileLayer.getCell(x, y);
                if (cell != null) {
                    TiledMapTile tile = cell.getTile();
                    if (tile != null) {
                        if (tiledMapTileLayer.getCell(x, y).getTile().getProperties()
                                .containsKey("monster")){
                            monster = new Monster(new Sprite(new Texture("Boule_de_suif.png")), tiledMapTileLayer, "Boule de Suif", null, 1,this.player);
                            monster.setPosition(x*this.tiledMapTileLayer.getTileWidth(), (y+1)*this.tiledMapTileLayer.getTileHeight());
                            monsters.add(monster);
                        }
                        else if (tiledMapTileLayer.getCell(x, y).getTile().getProperties()
                                .containsKey("spawn")){
                            this.x_spawn = x;
                            this.y_spawn = y;
                        }
                        if (tiledMapTileLayer.getCell(x, y).getTile().getProperties()
                                .containsKey("nykau")){
                            monster = new Monster(new Sprite(new Texture("Nykau_l_escargot.png")), tiledMapTileLayer, "Nykau l escargot", null, 1,this.player);
                            monster.setPosition(x*this.tiledMapTileLayer.getTileWidth(), (y+1)*this.tiledMapTileLayer.getTileHeight());
                            monsters.add(monster);
                        }
                    }
                }
            }
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TiledMap getTiledMap() { return this.tiledMap;}

    public Player getPlayer() { return this.player;}

    public int getXSpawn() {
        return this.x_spawn;
    }

    public int getY_spawn(){
        return this.y_spawn;
    }
}