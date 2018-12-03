package com.migara.mudan.sprites;

/**
 * Contains the heuristic definitions used by A* algorithm
 *
 *
 */
public class Heuristics {

    public static int manhattanCost(Table b){

        int value=0;
        int manDistance=0;
        for (int i = 0; i < 7 ; i++) {
            for (int j = 0; j < 7; j++) {
                if(b.table[i][j] == Table.delik.DOLU){
                    manDistance=Math.abs(i-3)+Math.abs(j-3);
                    value+=manDistance;
                }
            }
        }
        return value;
    }

    public static int weightedCost(Table m_board) {
        int[][] costMatrix =  new int[][]{
                { 0, 0, 4, 0, 4, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 4, 0, 3, 0, 3, 0, 4 },
                { 0, 0, 0, 1, 0, 0, 0 },
                { 4, 0, 3, 0, 3, 0, 4 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 4, 0, 4, 0, 0 }};
        return evaluateCostMatrix(m_board, costMatrix);

    }

    public static int evaluateCostMatrix(Table board, int[][] costMatrix ) {
        int pagodaValue = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (board.table[i][j] == Table.delik.DOLU) {
                    pagodaValue += costMatrix[i][j];
                }
            }
        }
        return (pagodaValue);
    }

}

