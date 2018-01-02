package com.chobocho.tetris;
import java.util.*;

/**
 * 
 */
public class TetrisGameOverState extends TetrisGameState {

    /**
     * Default constructor
     */
    public TetrisGameOverState(Tetris tetris) {
        this.tetris = tetris;
    }

    public void update() {
        TetrisLog.d("TetrisGameOverState.update()");
        tetris.getObserver().updateGameOver();
    }

}