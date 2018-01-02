package com.chobocho.tetris;
import java.util.*;

/**
 * 
 */
public class TetrisPauseState extends TetrisGameState {

    /**
     * Default constructor
     */
    public TetrisPauseState(Tetris tetris) {
        this.tetris = tetris;
    }

    public void update() {
        TetrisLog.d("TetrisPauseState.update()");
        tetris.getObserver().updatePause();
    }

}