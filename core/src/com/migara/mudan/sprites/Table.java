package com.migara.mudan.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Table extends Sprite {

    private int length;
    private delik[][] table;
    private Texture hole;
    public Array<Stone> stones;

    public enum delik{
        BOS(0), DOLU(0), HARITA_DISI(1);

        private final int code;
        delik(final int code) {
            this.code = code;
        }
    }

    public Table(int length){
        this.length = length;
        table = new delik[length][length];
        stones = new Array<Stone>();
        constructTable();
        hole = new Texture("delik.png");
    }

    public void constructTable(){       //English
        for (int i = 0; i < 2; i++) {
            table[i][0] = delik.HARITA_DISI;
            table[i][1] = delik.HARITA_DISI;
            table[i][2] = delik.DOLU;
            table[i][3] = delik.DOLU;
            table[i][4] = delik.DOLU;
            table[i][5] = delik.HARITA_DISI;
            table[i][6] = delik.HARITA_DISI;
        }

        for (int i=0; i<7; i++)
            table[2][i] = delik.DOLU;
        for (int i=0; i<7; i++)
            table[3][i] = delik.DOLU;
        for (int i=0; i<7; i++)
            table[4][i] = delik.DOLU;
        table[3][3] = delik.BOS;

        for (int i = 5; i < 7; i++) {
            table[i][0] = delik.HARITA_DISI;
            table[i][1] = delik.HARITA_DISI;
            table[i][2] = delik.DOLU;
            table[i][3] = delik.DOLU;
            table[i][4] = delik.DOLU;
            table[i][5] = delik.HARITA_DISI;
            table[i][6] = delik.HARITA_DISI;
        }

        int id=0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (table[i][j] == delik.DOLU){
                    stones.add(new Stone(new Vector2(i,j),id));
                    id++;
                }
            }
        }
    }

    @Override
    public void draw(Batch batch) {
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++)
                if (table[i][j] == delik.BOS)
                    batch.draw(hole, 15+i*30 , 75+j*30,30,30);
    }
}
