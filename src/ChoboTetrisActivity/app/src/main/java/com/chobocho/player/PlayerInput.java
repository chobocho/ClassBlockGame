package com.chobocho.player;

public abstract class PlayerInput {
    public static int LEFT = 0;
    public static int RIGHT = 1;
    public static int UP = 2;
    public static int DOWN = 3;
    public static int BOTTOM = 4;
    public static int ROTATE = 6;
    public static int START = 7;
    public static int RESUME = 8;
    public static int NONE = -1;

    protected Player player = null;

    protected int startX = 0;
    protected int startY = 0;
    protected int BLOCK_IMAGE_SIZE = 60;

    public abstract int pressKey(int key);
    public abstract boolean touch(int x, int y);

    public  void registerPlayer(Player player) {
        this.player = player;
    }

    protected void left() {
        player.MoveLeft();
    }

    protected void right() {
        player.MoveRight();
    }

    protected void down() {
        player.MoveDown();
    }

    protected void rotate() {
        player.rotate();
    }

    protected void bottom() { player.MoveBottom();}

    protected void play() { player.play(); }

    protected void pauseOrResume() { player.pauseOrResume(); }

}
