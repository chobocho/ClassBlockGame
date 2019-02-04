package com.chobocho.player;

import com.chobocho.tetris.Score;

public interface Player {
    public boolean setInputDevice(PlayerInput pi);
    public boolean setView(PlayerUI pu);
    public boolean setSCore(Score score);
    public boolean left();
    public boolean right();
    public boolean down();
    public boolean rotate();
    public boolean bottom();
    public boolean play();
    public boolean pauseOrResume();
    public boolean pause();
    public boolean resume();
    public boolean touch (int x, int y);
}
