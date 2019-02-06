package com.chobocho.player;

import com.chobocho.tetris.*;

public class PlayerImpl implements Player, ITetrisObserver {
    private Tetris tetris = null;
    private PlayerInput playerInput = null;
    private PlayerObserver gameViewObserver = null;

    public PlayerImpl(int width, int height) {
        tetris = new Tetris(width, height);
        tetris.register(this);
    }

    public void init() {
        tetris.init();
    }

    public boolean setView() {
        return true;
    }

    public int getWidth() {
        return tetris.getWidth();
    }

    public int getHeight() {
        return tetris.getHeight();
    }

    public int[][] getBoard() {
        return tetris.getBoard();
    }

    public int getScore() {
        return tetris.getScore();
    }

    public int getRemovedLineCount() {
        return tetris.getRemovedLineCount();
    }

    public boolean setInputDevice(PlayerInput pi) {
        playerInput =  pi;
        playerInput.registerPlayer(this);
        return true;
    }

    public boolean setView(PlayerUI pu) {
        pu.registerPlayer(this);
        return true;
    }

    public boolean setSCore(Score score) {
        return tetris.setScore(score);
    }

    public boolean MoveLeft() {
        boolean isPlayState = (tetris != null  &&  tetris.isPlayState());

        if (!isPlayState) {
            return false;
        }
        tetris.moveLeft();
        return true;
    }
    public boolean MoveRight() {
        boolean isPlayState = (tetris != null  &&  tetris.isPlayState());
        if (!isPlayState) {
            return false;
        }

        tetris.moveRight();
        return true;
    }
    public boolean MoveDown() {
        boolean isPlayState = (tetris != null  &&  tetris.isPlayState());

        if (!isPlayState) {
            return false;
        }

        tetris.moveDown();
        return true;
    }
    public boolean rotate() {
        boolean isPlayState = (tetris != null  &&  tetris.isPlayState());

        if (!isPlayState) {
            return false;
        }

        tetris.rotate();
        return true;
    }

    public boolean MoveBottom() {
        boolean isPlayState = (tetris != null  &&  tetris.isPlayState());

        if (!isPlayState) {
            return false;
        }

        tetris.moveBottom();
        return true;
    }

    public boolean play() {
        if (tetris == null) {
            return false;
        }

        if (tetris.isIdleState()) {
            gameViewObserver.startGame();
            tetris.play();
            return true;
        }

        if (tetris.isGameOverState()) {
            tetris.init();
            return true;
        }

        if (tetris.isPauseState()) {
            return false;
        }

        tetris.play();
        return true;
    }
    public boolean resume() {
        boolean isPauseState = (tetris != null  &&  tetris.isPauseState());

        if (!isPauseState) {
            return false;
        }

        gameViewObserver.startGame();
        tetris.resume();
        return true;
    }
    public boolean pause() {
        boolean isPlayState = (tetris != null  &&  tetris.isPlayState());

        if (!isPlayState) {
            return false;
        }

        tetris.pause();
        return true;
    }

    public boolean pauseOrResume() {
        return true;
    }

    public boolean isIdleState() { return tetris.isIdleState(); }
    public boolean isGameOverState() { return tetris.isGameOverState(); }
    public boolean isPlayState() { return tetris.isPlayState(); }
    public boolean isPauseState() { return tetris.isPauseState(); }
    public boolean isEnableShadow() { return tetris.isEnableShadow(); }

    public Tetrominos getCurrentBlock() {
        return tetris.getCurrentBlock();
    }
    public Tetrominos getNextBlock() {
        return tetris.getNextBlock();
    }
    public Tetrominos getShadowBlock() {
        return tetris.getShadowBlock();
    }

    @Override
    public void register(PlayerObserver observer) {
        gameViewObserver = observer;
    }

    @Override
    public void update() {
        gameViewObserver.update();
    }
}
