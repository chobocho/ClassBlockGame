package com.chobocho.player;

import com.chobocho.tetris.*;

public class PlayerImpl implements Player {
    private Tetris tetris = null;
    private PlayerInput playerInput = null;

    public PlayerImpl(int width, int height) {
        tetris = new Tetris(width, height);
        tetris.init();
    }

    public boolean setView() {
        return true;
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

    public boolean touch (int x, int y) {
        if (playerInput == null) {
            return false;
        }

        playerInput.touch(x, y);

        return true;
    }

    public boolean left() {
        boolean isPlayState = (tetris != null  &&  tetris.isPlayState());

        if (!isPlayState) {
            return false;
        }
        tetris.moveLeft();
        return true;
    }
    public boolean right() {
        boolean isPlayState = (tetris != null  &&  tetris.isPlayState());
        if (!isPlayState) {
            return false;
        }

        tetris.moveRight();
        return true;
    }
    public boolean down() {
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

    public boolean bottom() {
        boolean isPlayState = (tetris != null  &&  tetris.isPlayState());

        if (!isPlayState) {
            return false;
        }

        tetris.moveBottom();
        return true;
    }

    public boolean play() {
        boolean isPlayState = (tetris != null  &&  tetris.isPlayState());

        if (!isPlayState) {
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
}
