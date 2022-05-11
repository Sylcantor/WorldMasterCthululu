package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.List;

public class Monster extends Entity {

    // Si chaque monstre connait l'emplacement du player -> method MonstreNearPlayer -> Envoie le monstre comme target au player qui peut faire ses attaques.
    private final Player player;

    private float time = 0f;
    private final float timeBetweenHurt = 2f;


    private final Texture chtululuTexture = new Texture("cthululu.png");
    private final Texture chtululuTextureDamaged = new Texture("cthululu_damaged.png");


    public Monster(Sprite sprite, TiledMapTileLayer collisionLayer, String name, List<Capacity> capacities, int hp, Player player){
        super(sprite, collisionLayer,name,capacities,hp);
        this.player = player;
        velocity.x = speed;
    }

    @Override
    public void update(float delta){
        //setting time between attack received
        if(time > timeBetweenHurt){
            time = 0f;
            player.setTexture(chtululuTexture);
        }

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

        float tileWidth = collisionLayer.getTileWidth(), tileHeight = collisionLayer.getTileHeight();


        //move on x position
        setX(getX() + velocity.x * delta);

        // calculate the increment for step in #collidesLeft() and #collidesRight()
        increment = collisionLayer.getTileWidth();
        increment = getWidth() < increment ? getWidth() / 2 : increment / 2;

        if(velocity.x < 0){
            collisionX = (collidesLeft("blocked")||collidesLeft("monster_blocked"));
        }else if(velocity.x > 0){
            collisionX = (collidesRight("blocked")|| collidesLeft("monster_blocked"));
        }

        //react to x collision
        if(collisionX){
            setX(oldX);
            velocity.x = -velocity.x;
        }

        //react to player collision
        if(collidesPlayer()){
            if(time==0f)
                player.setHp(player.getHp()-1);
                player.setTexture(chtululuTextureDamaged);
        }

        //time increment at each render
        if(time!=0f || collidesPlayer())
            time += delta;
    }

    private boolean collidesPlayer() {
        return player.getBoundingRectangle().overlaps(this.getBoundingRectangle());
    }

    @Override
    void useCapacity(Capacity capacity) {
        // TODO
    }
}
