package com.chobocho.player;

import com.chobocho.tetris.ITetrisObserver;
import com.chobocho.tetris.Score;
import com.chobocho.tetris.Tetrominos;

public interface Player {
    public boolean setInputDevice(PlayerInput pi);
    public boolean setView(PlayerUI pu);
    public boolean setSCore(Score score);
    public void register(PlayerObserver observer);
    public void init();
    public boolean MoveLeft();
    public boolean MoveRight();
    public boolean MoveDown();
    public boolean rotate();
    public boolean MoveBottom();
    public boolean play();
    public boolean pauseOrResume();
    public boolean pause();
    public boolean resume();
    public int getWidth();
    public int getHeight();
    public int[][] getBoard();
    public int getScore();
    public int getRemovedLineCount();

    public boolean isIdleState();
    public boolean isGameOverState();
    public boolean isPlayState();
    public boolean isPauseState();
    public boolean isEnableShadow();

    public Tetrominos getCurrentBlock();
    public Tetrominos getNextBlock();
    public Tetrominos getShadowBlock();
}
