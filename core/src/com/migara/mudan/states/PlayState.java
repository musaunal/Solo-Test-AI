package com.migara.mudan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.migara.mudan.MudanDemo;
import com.migara.mudan.sprites.Move;
import com.migara.mudan.sprites.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by musa on 13.05.2017.
 */

public class PlayState extends State{

    private Texture background;
    private Texture playBtn;
    private Table table;

    private Set<Long> m_unsolvableStates = new HashSet<>();
    private LinkedList<Move> m_moves = new LinkedList<>();
    int[][] hamleler = new int[][]{{-2, 0}, {2, 0}, {0, -2}, {0, 2}};
    long geçen_Süre = 0;
    boolean a = true;
    boolean bittimi = false;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, MudanDemo.WITDH / 2 , MudanDemo.HEIGHT / 2);   // kamerayı zoomlar döndürür orjini değiştirir
        background = new Texture("background.png");
        table = new Table(7);
        Gdx.app.log("Başladı", "");
        new Thread(this::dfs).start();          // threadin içine atar bu şekilde render methodunu engellemez
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)){ }
        if (a){

            a = false;
        }
        if (bittimi){
            Gdx.app.log("" , "" + m_moves.toString());
            Gdx.app.log("time to compleate : " + geçen_Süre, "");
            bittimi = false;
        }
    }

    boolean dfs() {
        long startTime = System.currentTimeMillis();
        try {

            List<Integer> iterOrder = new ArrayList<>(7);
            List<Integer> deltaOrder = new ArrayList<>(4); //new int[4];
            int idx = 0;
            for(int i = 0; i<7; i++)
                iterOrder.add(idx++);

            idx = 0;
            for(int i = 0; i<4; i++)
                deltaOrder.add(idx++);

            Collections.shuffle(iterOrder);
            Collections.shuffle(deltaOrder);

            for(int x: iterOrder)
                for(int y: iterOrder) {
                    if(this.table.table[x][y] == Table.delik.DOLU) {
                        for(int index: deltaOrder) {
                            int[] delta = hamleler[index];
                            int dx = delta[0];
                            int dy = delta[1];
                            if(validMove(x, y, dx, dy)) {
                                table.move(x, y, dx, dy);
                                Long boardCfg = table.bitMap();

                                if(!m_unsolvableStates.contains(boardCfg)) {
                                    if (dfs()) {
                                        m_moves.push(new Move(x, y, x + dx, y + dy));
                                        bittimi = true;
                                        return true;
                                    }
                                }
                                m_unsolvableStates.addAll(table.getSymmetricConfigs());
                                table.restore(x, y, dx, dy);
                            }
                        }
                    }
                }
        } finally {
            this.geçen_Süre = System.currentTimeMillis() - startTime;
        }
        return isGoalState(table);
    }

    private boolean isGoalState(Table state){
        int pegCount=0;
        if(state.table[3][3] != Table.delik.DOLU)
            return false;
        for(int i=0; i<7 ;i++)
            for(int j=0; j<7 ;j++)
                if(state.table[i][j] == Table.delik.DOLU)
                    pegCount++;
        if(pegCount == 1)
            return true;
        return false;
    }

    private boolean validMove(int x, int y, int dx, int dy) {
        int stepx = x + dx/2, stepy = y + dy/2,
                jumpx = x + dx, jumpy = y + dy;
        if(table.invalidPos(stepx, stepy) || table.invalidPos(jumpx, jumpy))
            return false;

//        Hole intHole = m_board.get(stepx,  stepy);      // üzerinden atladığı
//        Hole destHole = m_board.get(jumpx, jumpy);      // hedef nokta
        if(table.table[stepx][stepy] == Table.delik.HARITA_DISI
                || table.table[stepx][stepy] == Table.delik.BOS
                || table.table[jumpx][jumpy] == Table.delik.HARITA_DISI
                || table.table[jumpx][jumpy] != Table.delik.BOS)
            return false;
        return true;
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
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        table.dispose();
    }
}
