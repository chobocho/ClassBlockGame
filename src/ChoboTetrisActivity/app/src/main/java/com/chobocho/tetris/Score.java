package com.chobocho.tetris;

public abstract class Score {
    private int score;

    public Score() {
        this.score = 0;
    }
    public Score (int score) {
        this.score = score;
    }

    public void init() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public abstract void removeLIne(int line);
}
