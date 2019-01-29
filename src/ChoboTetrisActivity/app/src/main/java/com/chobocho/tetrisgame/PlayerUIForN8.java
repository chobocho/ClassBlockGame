package com.chobocho.tetrisgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.chobocho.player.PlayerUI;
import com.chobocho.tetris.R;

public class PlayerUIForN8 extends PlayerUI {
    private Context mConext;
    Paint mPaint;

    Bitmap mGameBack;
    Bitmap mGameStart;
    Bitmap mGameOver;
    Bitmap mNumbers;
    Bitmap shadowBlock;

    Bitmap leftArrow;
    Bitmap rightArrow;
    Bitmap downArrow;
    Bitmap bottomArrow;
    Bitmap rotateArrow;
    Bitmap playBtn;
    Bitmap pauseBtn;

    Bitmap[] mTile   = new Bitmap[10];
    Bitmap[] mNumber = new Bitmap[10];

    boolean isLoadedImage = false;

    public PlayerUIForN8(Context context) {
        mConext = context;
        init();
    }

    @Override
    public void onDraw(Canvas g) {
        if (isLoadedImage == false) {
            return;
        }
    }

    private void init() {
        loadImage(mConext);

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);

        isLoadedImage = true;
    }

    private void loadImage(Context context) {
        mGameBack = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.backimage);
        mGameStart = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.start);
        mGameOver = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.gameover);

        mTile[0] =  BitmapFactory.decodeResource(context.getResources(),
                R.drawable.black);
        mTile[1] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.yellow);
        mTile[2] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.blue);
        mTile[3] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.red);
        mTile[4] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.gray);
        mTile[5] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.green);
        mTile[6] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.magenta);
        mTile[7] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.orange);
        mTile[8] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.cyan);
        shadowBlock = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.shadow);

        leftArrow = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.left);
        rightArrow = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.right);
        downArrow  = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.down);
        rotateArrow = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.rotate);
        playBtn = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.play);
        pauseBtn = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.pause);
        bottomArrow =  BitmapFactory.decodeResource(context.getResources(),
                R.drawable.bottom);
    }
}
