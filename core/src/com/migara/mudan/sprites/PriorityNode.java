package com.migara.mudan.sprites;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * The priority node stores the state of the board configuration like distance
 * from start node, heuristic cost and the move that led to this configuration
 * before adding into the Priority Queue
 *
 */
public class PriorityNode {

    private List<Integer> pagodas = new ArrayList<Integer>(Pagoda.NUM_PAGODAS);

    List<Integer> getPagodas() {
        return pagodas;
    }

    void setPagodas(List<Integer> pagodas) {
        this.pagodas = pagodas;
    }

    private int cost;
    private int distance;
    private Move m_move;

    public Move getMove() {
        return m_move;
    }

    public void setMove(Move mv) {
        m_move = mv;
    }

    private long state;
    private long m_prevState;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public long getState() {
        return state; // states;
    }

    public void setState(long state) {
        this.state = state;
    }

    public long getPrevState() {
        return m_prevState; // states;
    }

    public void setPrevState(long m_prevState) {
        this.m_prevState = m_prevState;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

}
