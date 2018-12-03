package com.migara.mudan.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Stone extends Sprite {

    public int id;
    public Vector2 koordinat;
    public Image tass;
    private Texture tas;

    public Stone(Vector2 v, int id){
        this.id = id;
        koordinat = v;
        tas = new Texture("stone.png");
        this.setRegion(tas);
        this.setBounds(v.x, v.y, tas.getWidth(), tas.getHeight());
        tass = new Image(tas);
        tass.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                Gdx.app.log("sda","asd");
                System.out.print("asdasd");
                koordinat.y += 2;
                return true;
            }
        });
    }


    public void draw(Batch batch, float x, float y) {
        batch.draw(tas, 15+x*30,75+y*30,30,30);
    }

}
