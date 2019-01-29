package com.chobocho.player;

import android.graphics.Canvas;

public abstract class PlayerUI {
    private int width;
    private int height;
    protected Player player;

    public  void registerPlayer(Player player) {
        this.player = player;
    }
    public abstract void onDraw(Canvas g);

    public void setScreenSize(int w, int h) {
        width = w;
        height = h;
    }
}
