package com.migara.mudan.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Table extends Sprite {

    public int length;
    public delik[][] table;
    private Texture hole;
    private Texture tas;

    public enum delik{
        BOS(0), DOLU(1), HARITA_DISI(0);

        private final int code;
        delik(final int code) {
            this.code = code;
        }

        public int bit(){
            assert code != -1;
            return code;
        }
    }

    public Table(int length){
        this.length = length;
        table = new delik[length][length];
        constructTable();
        hole = new Texture("delik.png");
        tas = new Texture("stone.png");
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
    }

    public void move(int x, int y, int dx, int dy) {
//        Gdx.app.log("move : "+ x +" "+ y +" "+ dx +" "+ dy, "");
        table[x][y] = delik.BOS;
        table[x + dx/2][y + dy/2] = delik.BOS;
        table[x + dx][y + dy] = delik.DOLU;
    }

    public void restore(int x, int y, int dx, int dy) {
//        Gdx.app.log("restore : "+ x +" "+ y +" "+ dx +" "+ dy, "");
        table[x + dx][y + dy] = delik.BOS;
        table[x + dx/2][y + dy/2] = delik.DOLU;
        table[x][y] = delik.DOLU;
    }

    public long bitMap() {
        long bitMap = 0;
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                bitMap |= table[i][j].bit();
                if( j != 7 - 1 || i != 7 - 1) bitMap <<= 1;
            }
        }
        return bitMap;
    }

    public boolean invalidPos(int stepx, int stepy) {
        return (stepx < 0 || stepx >= 7|| stepy < 0 || stepy >= 7)
                || ((stepx < 2 && stepy < 2) || (stepx < 2 && stepy > 4)
                ||(stepx > 4 && stepy < 2) || (stepx > 4 && stepy > 4));
    }

    private List<Long> getRotateConfigs() {
        List<Long> rotations = new ArrayList<Long>(3);
        for(int i = 0; i < 3; i++) {
            rotate();
            rotations.add(bitMap());
        }
        rotate();
        return rotations;
    }

    private void rotate() {
        for(int i = 0; i < (7)/2; i++) {
            for(int j = 0; j <= (7-1)/2; j++) {

                delik temp = table[i][j];
                table[i][j] = table[7-j-1][i];
                table[7-j-1][i] = table[7-i-1][7-j-1];
                table[7-i-1][7-j-1] = table[j][7-i-1];
                table[j][7-i-1] = temp;
            }
        }
    }

    private void verticalReflect() {
        for(int j = 0; j < 7/2; j++)
            for(int i = 0; i < 7; i++){
                delik temp = table[i][j];
                table[i][j] = table[i][7 - j -1];
                table[i][7- j - 1] = temp;
            }
    }

    public List<Long> getSymmetricConfigs() {
        List<Long> configs = new ArrayList<>();
        configs.add(bitMap());
        List<Long> rotations = getRotateConfigs();
        configs.addAll(rotations);
        verticalReflect();
        configs.add(bitMap());
        List<Long> reflectRotations = getRotateConfigs();
        configs.addAll(reflectRotations);
        verticalReflect();
        return configs;
    }

    @Override
    public void draw(Batch batch) {
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++){
                if (table[i][j] == delik.BOS)
                    batch.draw(hole, 15+i*30 , 75+j*30,30,30);
                if(table[i][j] == delik.DOLU)
                    batch.draw(tas, 15+i*30 , 75+j*30,30,30);
            }
    }

    public void dispose(){
        hole.dispose();
        tas.dispose();
    }
}
