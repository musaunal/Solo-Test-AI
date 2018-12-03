package com.migara.mudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.migara.mudan.MudanDemo;

import java.util.Random;

/**
 * Created by musa on 13.05.2017.
 */

public class IceBlock {
    public static  final  int ICE_WITDH = 52;
    public static  final  int MIN_GAP = 125;
    private static  final  int FLUCTUATION_Y = 70;
    private static  final  int FLUCTUATION_X = 180;
    private static  final  int LOWEST_OPENING_BOT = -200;
    private static  final  int LOWEST_OPENING_TOP = 520;

    private Texture topIce, bottomICE;
    private Vector2 posTopIce, posBotIce;
    private Rectangle boundsTop , boundsBot;
    private Random rand;

    public IceBlock(float x){
        rand = new Random();
        topIce = new Texture("b1.png");
        bottomICE = new Texture("b2.png");

        posTopIce = new Vector2(x,MudanDemo.HEIGHT -(rand.nextInt(FLUCTUATION_Y) + LOWEST_OPENING_TOP));
        posBotIce = new Vector2(x + MIN_GAP + rand.nextInt(FLUCTUATION_X) , LOWEST_OPENING_BOT + rand.nextInt(FLUCTUATION_Y));
        boundsTop = new Rectangle(posTopIce.x , posTopIce.y , topIce.getWidth()-15, topIce.getHeight());
        boundsBot = new Rectangle(posBotIce.x , posBotIce.y , bottomICE.getWidth()-15, bottomICE.getHeight());
    }

    public void repositionTop(float x){
        posTopIce.set(x, MudanDemo.HEIGHT -(rand.nextInt(FLUCTUATION_Y) + LOWEST_OPENING_TOP));
        boundsTop.setPosition(posTopIce.x, posTopIce.y);
    }

    public void repositionBot(float x){
        posBotIce.set(x + MIN_GAP + rand.nextInt(FLUCTUATION_X) , LOWEST_OPENING_BOT + rand.nextInt(FLUCTUATION_Y));
        boundsBot.setPosition(posBotIce.x, posBotIce.y);
    }

    public boolean collides (Rectangle player){
        return player.overlaps(boundsBot) || player.overlaps(boundsTop);
    }

    public void dispose(){
        topIce.dispose();
        bottomICE.dispose();
    }

    public Texture getTopIce() {
        return topIce;
    }

    public Texture getBottomICE() {
        return bottomICE;
    }

    public Vector2 getPosTopIce() {
        return posTopIce;
    }

    public Vector2 getPosBotIce() {
        return posBotIce;
    }
}
