package com.migara.mudan.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by musa on 2.08.2017.
 */

public class Particle {           // bu animasyon texture atlas için aslında daha çok bu bir particle effect

        private Array<TextureRegion> frames;
        private float maxFrameTime;             // bir frame'in ne kadar süreceği
        private float currentFrameTime;
        private int frameCount;
        private int frame;
        private int life;       // disposa kadar geçecek süre

        public Particle(TextureRegion region, int frameCount , float cycleTime, int life){
            frames = new Array<TextureRegion>();
            int frameWidth = region.getRegionWidth() / (int)Math.sqrt(frameCount);
            int frameHeight = region.getRegionHeight() / (int)Math.sqrt(frameCount);
            for (int i=0; i<(int)Math.sqrt(frameCount); i++){
                for (int j=0; j<(int)Math.sqrt(frameCount); j++){
                    frames.add(new TextureRegion(region, i * frameWidth, j * frameHeight , frameWidth, frameHeight));
                }
            }
            this.frameCount = frameCount;
            this.life = life;
            maxFrameTime = cycleTime / frameCount;
            frame = 0;
        }

        public void update(float dt){
            currentFrameTime += dt;
            if (currentFrameTime > maxFrameTime){
                frame++;
                currentFrameTime = 0;
            }
            if (frame >= frameCount){
                frame =0;
            }
        }

        public TextureRegion getFrame(int n){
            return frames.get((frame+n)%frames.size);
        }

        public float getLife() {
            return life;
        }
}
