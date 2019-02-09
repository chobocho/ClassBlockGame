package com.chobocho.tetris;

public abstract class Score {
    private int score;
    private int highScore;

    public Score() {
        this.score = 0;
        this.highScore = 0;
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
    public int getHighScore() { return highScore; }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHighScore(int score) {
        this.highScore = score;
    }

    protected void addScore(int score) {
        this.score += score;
    }
    public void updateHighScore() {
        if (this.score > this.highScore) {
            this.highScore = this.score;
        }
    }

    public void removeLIne(int removedLineCount) {
        calculatorScore(removedLineCount);
    }
    protected abstract void calculatorScore(int removedLineCount);
    protected abstract void ClearBoard();
}
