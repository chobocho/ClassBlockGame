package com.chobocho.tetris;

import java.util.*;


public interface ITetrisObserver {
    public void updateIdle();
    public void updatePlay();
    public void updatePause();
    public void updateGameOver();
}