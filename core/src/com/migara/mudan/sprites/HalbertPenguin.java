package com.migara.mudan.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class HalbertPenguin extends Penguin {

    private Texture h;
    private Sprite halbert;

    public HalbertPenguin(int x, int y) {
        super(x, y);
        texture = new Texture("teberli1.png");
        Animation = new Animation(new TextureRegion(texture) , 3 , 0.5f );
        bounds = new Rectangle(x,y ,texture.getWidth()/3 ,texture.getHeight());

        h= new Texture("halbert.png");
        halbert = new Sprite(h);
        halbert.setOrigin(halbert.getWidth(), halbert.getHeight()/8);
        fly = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    @Override
    public void update(float dt){
        Animation.update(dt);
        if(position.y > bounds.height+50)
            velocity.add(0,GRAVITY,0);
        else if(position.y <= bounds.height+23)
            position.y = bounds.height+23;
        velocity.scl(dt);               // her framede olaylarını tekrarlar yani aşağı düşer
        position.add(MOVEMENT * dt ,velocity.y,0);

        velocity.scl(1/dt);         // scale'i geri alıp böylece sonraki framede de yeniden kullanılabilsin
        bounds.setPosition(position.x,position.y);
        halbert.setPosition(bounds.x, bounds.y);
    }

    @Override
    public void attack() {
        halbert.setRotation(-90);
    }

    public void dispose(){
        texture.dispose();
        fly.dispose();
        h.dispose();
    }

    public void jump(){
        velocity.y = 250;
        fly.play(0.5f);
    }

    public Sprite getHalbert(){
        return halbert;
    }
}
