package com.chobocho.tetrisgame;

import android.util.Log;
import android.widget.Toast;

import com.chobocho.player.Player;
import com.chobocho.player.PlayerInput;

public class PlayerInputImplForN8 extends PlayerInput {
    private String TAG = this.getClass().getName();
    public static final int BOARD_HEIGHT = 20;

    public PlayerInputImplForN8() {
        startX = 80;
        startY = 80;
    }

    public int pressKey(int key) {
        return 0;
    }

    public boolean touch(int touchX, int touchY) {
        if (player == null) {
            return true;
        }

        if ((touchX > 190) && (touchY > 400)
                && (touchX < 410) && (touchY < 500)) {
            Log.d(TAG, "touch: ");
            play();
            return true;
        }


        if ((touchX > 190) && (touchY > 400)
                && (touchX < 410) && (touchY < 500)) {
            Log.d(TAG, "play1()");
            play();
            return true;
        }
        if ((touchX > 700) && (touchY > 50)
                && (touchY < 250)) {
            Log.d(TAG, "play2()");
            play();
            return true;
        }

        if (touchX > startX + 750 &&
                touchY > startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 &&
                touchX < startX + 950 &&
                touchY < startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200) {
            Log.d(TAG, "rotate()");
            rotate();
            return true;
        }

        if ((touchX > 700) && (touchY > 50)
                && (touchY < 250)) {
            Log.d(TAG, "pauseOrResume():1");
            pauseOrResume();
            return true;
        }
        if ((touchX > 190) && (touchY > 400)
                && (touchX < 410) && (touchY < 500)) {
            Log.d(TAG, "pauseOrResume():2");
            pauseOrResume();
            return true;
        }

        if (touchX > startX &&
                touchY > startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 &&
                touchX < startX + 200 &&
                touchY < startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200) {
            Log.d(TAG, "left()");
            left();
            return true;
        } else if (touchX > startX + 250 &&
                touchY > startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 &&
                touchX < startX + 450 &&
                touchY < startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200) {
            Log.d(TAG, "bottom() down()");
            bottom();
            down();
            return true;
        } else if (touchX > startX + 500 &&
                touchY > startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 &&
                touchX < startX + 700 &&
                touchY < startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200) {
            Log.d(TAG, "right()");
            right();
            return true;
        }

        if (touchX > startX + 750 &&
                touchY > startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT - 200 &&
                touchX < startX + 950 &&
                touchY < startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT) {
            Log.d(TAG, "down()");
            down();
            return true;
        }
        return false;
    }
}
