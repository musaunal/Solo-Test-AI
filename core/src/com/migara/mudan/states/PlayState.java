package com.migara.mudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.migara.mudan.MudanDemo;
import com.migara.mudan.sprites.Animation;
import com.migara.mudan.sprites.HalbertPenguin;
import com.migara.mudan.sprites.Particle;
import com.migara.mudan.sprites.IceBlock;
import com.migara.mudan.sprites.Stone;
import com.migara.mudan.sprites.Table;

/**
 * Created by musa on 13.05.2017.
 */

public class PlayState extends State{

    private Texture background;
    private Table table;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, MudanDemo.WITDH / 2 , MudanDemo.HEIGHT / 2);   // kamerayı zoomlar döndürür orjini değiştirir
        background = new Texture("background.png");
        table = new Table(7);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
            Gdx.app.log("bastı","");
        }
        Stone s = table.stones.get(1);
        s.tass.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                s.koordinat.y += 2;
                return true;
            }
        });
        //TODO: sağ sol yukarı

    }

    @Override
    public void update(float dt) {
        handleInput();
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(background, 0, 180,background.getWidth(), background.getHeight()*5/3);
        table.draw(sb);
        for (Stone s : table.stones) {
            s.draw(sb, s.koordinat.x, s.koordinat.y);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
