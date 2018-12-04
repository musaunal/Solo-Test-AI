package com.migara.mudan.sprites;

import java.text.MessageFormat;
import java.util.Formatter;
/**
 * Used to store a jump on the board.
 */
public class Move {
    int m_fromx, m_fromy, m_tox, m_toy;

    public Move(int fromx, int fromy, int tox, int toy) {
        super();
        this.m_fromx = fromx;
        this.m_fromy = fromy;
        this.m_tox = tox;
        this.m_toy = toy;
    }

    @Override
    public String toString() {
        return "Move{" +
                "m_fromx=" + m_fromx +
                ", m_fromy=" + m_fromy +
                ", m_tox=" + m_tox +
                ", m_toy=" + m_toy +
                "}\n";
    }
}