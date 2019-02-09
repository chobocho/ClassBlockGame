package com.chobocho.tetris;

public interface ITetrisGameState {
    public void addRemoveLineCount(int line);
    public void ClearBoard();
    public void update();
}
