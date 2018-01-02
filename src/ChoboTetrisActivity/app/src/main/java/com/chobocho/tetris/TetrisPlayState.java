package com.chobocho.tetris;

public class TetrisPlayState extends TetrisGameState {
    private Tetrominos currentTetrominos;
    private Tetrominos nextTetrominos;
    private TetrisBoard tetrisBoard;
    private int additionalPoint = 1;

    public TetrisPlayState(Tetris tetris, TetrisBoard board) {
        this.tetris = tetris;
        this.tetrisBoard = board;
        currentTetrominos = TetrominosFactory.create();
        nextTetrominos = TetrominosFactory.create();
    }

    public void init() {
        this.tetrisBoard.init();
        currentTetrominos = TetrominosFactory.create();
        nextTetrominos = TetrominosFactory.create();
        additionalPoint = 1;
    }

    public void update() {
       TetrisLog.d("TetrisPlayState.update()");
       tetris.getObserver().updatePlay();
    }

    public void moveLeft() {
       TetrisLog.d("TetrisPlayState.moveLeft()");
        currentTetrominos.moveLeft();
        if (tetrisBoard.isAcceptable(currentTetrominos) == false) {
            currentTetrominos.moveRight();
            TetrisLog.d("Not Accept");
        } else {
            TetrisLog.d("Accept");
        }
    }

    public void moveRight() {
        TetrisLog.d("TetrisPlayState.moveRight()");
        currentTetrominos.moveRight();
        if (tetrisBoard.isAcceptable(currentTetrominos) == false) {
            currentTetrominos.moveLeft();
            TetrisLog.d("Not Accept");
        } else {
            TetrisLog.d("Accept");
        }
    }

    public void rotate() {
        TetrisLog.d("TetrisPlayState.rotate()");
        currentTetrominos.rotate();
        if (tetrisBoard.isAcceptable(currentTetrominos) == false) {
            currentTetrominos.preRotate();
            TetrisLog.d("Not Accept");
        } else {
            TetrisLog.d("Accept");
        }
    }


    public void moveDown() {
        TetrisLog.d("TetrisPlayState.moveDown()");
        currentTetrominos.moveDown();
        if (tetrisBoard.isAcceptable(currentTetrominos) == false) {
            currentTetrominos.moveUp();
            TetrisLog.d("Can not move down");
            fixCurrentBlock();
            updateBoard();
            updateBlock() ;
        } else {
            TetrisLog.d("Accept");
        }
    }


    public void moveBottom() {
        TetrisLog.d("TetrisPlayState.moveBottom()");
        while(tetrisBoard.isAcceptable(currentTetrominos)) {
            currentTetrominos.moveDown();
        }
        if (tetrisBoard.isAcceptable(currentTetrominos) == false) {
            currentTetrominos.moveUp();
        }
    }

    public void fixCurrentBlock() {
        tetrisBoard.addTetrominos(currentTetrominos);
    }

    public void updateBlock() {
        currentTetrominos = nextTetrominos;
        nextTetrominos = TetrominosFactory.create();
    }

    public boolean gameOver() {
        TetrisLog.d("Game over!");
        return (tetrisBoard.isAcceptable(currentTetrominos) == false);
    }

    public void updateBoard() {
        int removedLine = tetrisBoard.arrange();
        additionalPoint = removedLine > 0 ? additionalPoint * 2 : 1;
        tetris.addSore(removedLine * 10 * additionalPoint + (1 << removedLine));
    }


    public Tetrominos getCurrentTetrominos() {
        return currentTetrominos;
    }

    public Tetrominos getNextTetrominos() {
        return nextTetrominos;
    }
}