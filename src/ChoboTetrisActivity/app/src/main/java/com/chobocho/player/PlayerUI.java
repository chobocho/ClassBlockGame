package com.chobocho.player;

import android.graphics.Canvas;

public abstract class PlayerUI {
    private int width;
    private int height;

    public abstract void registerPlayer(Player player);
    public abstract void onDraw(Canvas g);

    public void setScreenSize(int w, int h) {
        width = w;
        height = h;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
