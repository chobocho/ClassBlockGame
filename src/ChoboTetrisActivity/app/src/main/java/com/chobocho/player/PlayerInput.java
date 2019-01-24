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

    private Player player = null;

    public abstract int pressKey(int key);
    public abstract void registerPlayer(Player player);

    private void left() {
        player.left();
    }

    private void right() {
        player.right();
    }

    private void down() {
        player.down();
    }
    
    private void rotate() {
        player.rotate();
    }
}
