package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;


public abstract class Entity extends Sprite {
    private String name;
    private List<Capacity> capacities = new ArrayList<>();
    private int hp;

    protected TiledMapTileLayer collisionLayer;

    protected Vector2 velocity = new Vector2();
    protected float increment;
    protected boolean canJump;
    protected float speed = 120, gravity = 60 * 1.8f;

    public Entity(Sprite sprite, TiledMapTileLayer collisionLayer, String name, List<Capacity> capacities, int hp){
        super(sprite);
        this.collisionLayer = collisionLayer;
        this.name = name;
        this.capacities = capacities;
        this.hp = hp;
    }

    @Override
    public void draw(Batch spriteBatch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }


    public abstract void update(float delta);

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public boolean isCellBlock(String block,float x, float y){
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && (cell.getTile().getProperties().containsKey(block) || (cell.getTile().getProperties().containsKey(block))) ;
    }

    public boolean collidesRight(String blockName) {
        for(float step = 0; step <= getHeight(); step += increment)
            if(isCellBlock(blockName,getX() + getWidth(), getY() + step))
                return true;
        return false;
    }

    public boolean collidesLeft(String blockName) {
        for(float step = 0; step <= getHeight(); step += increment)
            if(isCellBlock(blockName,getX(), getY() + step))
                return true;
        return false;
    }

    public boolean collidesTop(String blockName) {
        for(float step = 0; step <= getWidth(); step += increment)
            if(isCellBlock(blockName,getX() + step, getY() + getHeight()))
                return true;
        return false;

    }

    public boolean collidesBottom(String blockName) {
        for(float step = 0; step <= getWidth(); step += increment)
            if(isCellBlock(blockName,getX() + step, getY()))
                return true;
        return false;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public int getHp(){return hp;}


    public List<Capacity> getCapacities() {
        return capacities;
    }

    void setHp(int hp){this.hp = hp;}

    void setName(String name){this.name = name;}

    void givenDamage(Capacity capacity){
        this.hp = this.hp - capacity.getDamage();
    }

    abstract void useCapacity(Capacity capacity); //define if abstract or not
}




