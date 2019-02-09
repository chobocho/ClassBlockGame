package com.chobocho.tetrisgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.chobocho.player.*;
import com.chobocho.tetris.Score;

import static android.content.Context.MODE_PRIVATE;

public class TetrisViewForN8 extends View implements PlayerObserver {
	Context mContext;
	Player player;
	PlayerInput playerInput;
	PlayerUI playerUI;
	Score playerScore;

	int   gameSpeed = 0;
	int   highScore = 0;

	public static final int EMPTY_MESSAGE = 0;


	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			Log.d("Tetris", "There is event");
			if (player != null && player.isPlayState()) {
                player.MoveDown();
				gameSpeed = 700 - (player.getScore() / 10000);
                if (mHandler.hasMessages(EMPTY_MESSAGE)) {
                    mHandler.removeMessages(EMPTY_MESSAGE);
                }
				mHandler.sendEmptyMessageDelayed(EMPTY_MESSAGE, gameSpeed);
			}
		}
	};
	
	public TetrisViewForN8(Context context, Player player) {
		super(context);
		this.mContext = context;
		loadHIghScore();

		this.player = player;
		playerInput = new PlayerInputImplForN8();
		playerUI = new PlayerUIForN8(mContext);
		playerScore = new PlayerScoreImpl();
		playerScore.setHighScore(this.highScore);

		player.setInputDevice(playerInput);
		player.setView(playerUI);
		player.setSCore(playerScore);
		player.register(this);
		player.init();
    }

	public void setScreenSize(int w, int h) {
		playerUI.setScreenSize(w, h);
	}

	public void startGame() {
		mHandler.sendEmptyMessage(EMPTY_MESSAGE);
	}

	public void pauseGame() {
		if (mHandler.hasMessages(EMPTY_MESSAGE)) {
			mHandler.removeMessages(EMPTY_MESSAGE);
			Log.d("Tetris", "Removed event");
		}
		if (player != null) {
			player.pause();
		}
		saveScore();
	}

	public void update() {
		Log.d("Tetris", "View.update()");
		invalidate();
	}

	public void onDraw(Canvas canvas) {
	    if (playerUI == null) {
	        return;
        }
		playerUI.onDraw(canvas);
	}

	public boolean onTouchEvent(MotionEvent event) {
		Log.d("Tetris", ">> X: " + event.getX() + " Y: " + event.getY());

		if (playerInput == null) {
		    return false;
        }
        if (MotionEvent.ACTION_DOWN != event.getAction()) {
            return false;
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
        return playerInput.touch(x, y);
	}

	public void loadHIghScore() {
		Log.d("Tetris", "load()");
		SharedPreferences pref = mContext.getSharedPreferences("choboTetris", MODE_PRIVATE);
		this.highScore = pref.getInt("highscore", 0);
	}

	public void saveScore() {
		Log.d("Tetris", "saveScore()");
		if (this.highScore > player.getHighScore()) {
		    return;
        }
		SharedPreferences pref = mContext.getSharedPreferences("choboTetris", MODE_PRIVATE);
		SharedPreferences.Editor edit = pref.edit();

		edit.putInt("highscore", player.getHighScore());
		edit.commit();
	}

}
