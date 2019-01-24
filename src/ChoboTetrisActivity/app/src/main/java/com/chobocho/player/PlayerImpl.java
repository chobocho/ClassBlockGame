package com.chobocho.player;

import com.chobocho.tetris.Tetris;

public class PlayerImpl implements Player {
    Tetris tetris = null;

    public PlayerImpl() {

    }

    public boolean setView() {
        return true;
    }

    public boolean setInputDevice(PlayerInput pi) {
        pi.registerPlayer(this);
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
}
