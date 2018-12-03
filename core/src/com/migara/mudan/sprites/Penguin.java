package com.migara.mudan.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by musa on 13.05.2017.
 */

public abstract class Penguin{
    protected static final int GRAVITY = -15;
    protected static final int MOVEMENT = 100;
    protected Vector3 position;
    protected Vector3 velocity;
    protected Texture texture;
    protected Rectangle bounds;
    protected Animation Animation;
    protected Sound fly;

    public Penguin(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
    }

    public abstract void update(float dt);

    public abstract void jump();

    public abstract void attack();

    public abstract void dispose();

    public Rectangle getBounds(){
        return bounds;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getPenguin() { return Animation.getFrame();}
}
