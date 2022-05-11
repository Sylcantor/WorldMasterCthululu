package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.screens.GameScreen;

import java.util.List;

public final class Player extends Entity implements InputProcessor {

    private int nombrePA;
    List<PointAction> pointsAction;

    private float time = 0f;
    private final float timeBetweenHurt = 2f;

    private Texture chtululuTexture = new Texture("cthululu.png");
    private Texture chtululuTextureDamaged = new Texture("cthululu_damaged.png");

    public Player(Sprite sprite, TiledMapTileLayer collisionLayer, String name, List<Capacity> capacities, int hp, int nbPA, List<PointAction> pointsAction){
        super(sprite,collisionLayer,name,capacities,hp);
        this.nombrePA=nbPA;
        this.pointsAction=pointsAction;
        this.speed = 150;
    }



    public void addCapacite(Capacity c){
        //this.capacity.add(c);
    }

    public int getNombrePointAction(){
        return this.nombrePA;
    }

    public void setNombrePointAction(int pa){
        this.nombrePA=pa;
    }

    @Override
    void useCapacity(Capacity capacity) {
        //TODO
    }


    @Override
    public void update(float delta){


        if (delta > 0.1f){
            delta = 0.01f;
        }


        //setting time between attack received

        if(time > timeBetweenHurt){
            time = 0f;
            this.setTexture(chtululuTexture);
        }

        //set old gravity
        float oldGravity = gravity;

        //gravity
        velocity.y -= gravity * delta;

        //velocity clamp
        if(velocity.y > speed){
            velocity.y = speed;
        }
        else if(velocity.y < -speed){
            velocity.y = -speed;
        }

        //save old position
        float oldX = getX(), oldY = getY();
        boolean collisionX = false, collisionY = false;
        boolean trapCollisionX = false, trapCollisionY = false;


        //move on x position
        setX(getX() + velocity.x * delta);

        // calculate the increment for step in #collidesLeft() and #collidesRight()
        increment = collisionLayer.getTileWidth();
        increment = getWidth() < increment ? getWidth() / 2 : increment / 2;

        if(velocity.x < 0){ // going left
            collisionX = collidesLeft("blocked");
            trapCollisionX = (collidesLeft("trap")|| collidesLeft("trap_water"));
        }else if(velocity.x > 0){ // going right
            collisionX = collidesRight("blocked");
            trapCollisionX = (collidesRight("trap")|| collidesRight("trap_water"));
        }

        //react to x collision
        if(collisionX){
            setX(oldX);
            velocity.x = 0;
        }

        //react to x trap collision
        if(trapCollisionX && time==0f){
            this.setHp(this.getHp()-1);
            this.setTexture(chtululuTextureDamaged);
            time += delta;
        }

        else{
            if(Gdx.input.isKeyPressed(Input.Keys.Q)){
                velocity.x = -speed;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.D)){
                velocity.x = speed;
            }
        }

        //move on y position
        setY(getY() + velocity.y * delta * 5f);


        // calculate the increment for step in #collidesBottom() and #collidesTop()
        increment = collisionLayer.getTileHeight();
        increment = getHeight() < increment ? getHeight() / 2 : increment / 2;


        if(velocity.y < 0) { // going down
            canJump = collisionY = collidesBottom("blocked");
            trapCollisionY = (collidesBottom("trap")|| collidesBottom("trap_water"));
        }
        else if(velocity.y > 0) { // going up
            collisionY = collidesTop("blocked");
            trapCollisionY = (collidesTop("trap")|| collidesTop("trap_water"));

        }

        // react to y collision
        if(collisionY) {
            setY(oldY);
            velocity.y = 0;
        }

        //react to y trap collision
        if(trapCollisionY && time==0f){
            this.setHp(this.getHp()-1);
            this.setTexture(chtululuTextureDamaged);
            time += delta;
        }



        //time increment at each render
        if(time!=0f || trapCollisionX || trapCollisionY)
            time += delta;
    }



    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.SPACE) {
            if (canJump) {
                velocity.y = speed / 1.8f;
                canJump = false;
            }
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        switch (keycode) {
            case Keys.Q:
            case Keys.D:
                velocity.x = 0;
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
