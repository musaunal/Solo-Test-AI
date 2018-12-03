package com.migara.mudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.migara.mudan.MudanDemo;
import com.migara.mudan.sprites.Particle;

/**
 * Created by musa on 3.08.2017.
 */

public class GameOver extends State {

    private Texture fire;
    private Particle particle;
    private Texture playBtn;
    private BitmapFont gm;

    protected GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, MudanDemo.WITDH / 2 , MudanDemo.HEIGHT / 2);
        fire = new Texture("blue.png");
        particle = new Particle(new TextureRegion(fire) , 16 , 0.5f ,5);
        playBtn = new Texture("playbtn.png");
        gm = new BitmapFont();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        particle.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        for(int i=0; i<particle.getLife(); i++){
            sb.draw(particle.getFrame(i),  (int)(Math.random()*500) -25,  (int)(Math.random()*500) -25);
            sb.draw(particle.getFrame(i),  (int)(Math.random()*500) -25,  (int)(Math.random()*500) -25);
        }
        gm.setColor(Color.WHITE);
        gm.draw(sb,"GAME OVER", camera.position.x - playBtn.getWidth()/2, camera.position.y*3/2);
        sb.draw(playBtn, camera.position.x - playBtn.getWidth()/2, camera.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        fire.dispose();
        playBtn.dispose();
        gm.dispose();
    }
}
