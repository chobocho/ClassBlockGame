package com.chobocho.player;

public interface Player {
    public boolean setInputDevice(PlayerInput pi);
    public boolean setView(PlayerUI pu);
    public boolean left();
    public boolean right();
    public boolean down();
    public boolean rotate();
    public boolean bottom();
}
