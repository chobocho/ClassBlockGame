package com.chobocho.player;

import com.chobocho.tetris.*;

public class PlayerImpl implements Player {
    private Tetris tetris = null;
    private PlayerInput playerInput = null;

    public PlayerImpl(int width, int height) {
        tetris = new Tetris(width, height);
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

    public boolean touch (int x, int y) {
        if (playerInput == null) {
            return false;
        }

        playerInput.touch(x, y);

        return true;
    }

    public boolean left() {
        tetris.moveLeft();
        return true;
    }
    public boolean right() {
        tetris.moveRight();
        return true;
    }
    public boolean down() {
        tetris.moveDown();
        return true;
    }
    public boolean rotate() {
        tetris.rotate();
        return true;
    }
    public boolean bottom() {
        tetris.moveBottom();
        return true;
    }

    public boolean play() {
        tetris.play();
        return true;
    }
    public boolean resume() {
        tetris.resume();
        return true;
    }
    public boolean pause() {
        tetris.pause();
        return true;
    }
}
