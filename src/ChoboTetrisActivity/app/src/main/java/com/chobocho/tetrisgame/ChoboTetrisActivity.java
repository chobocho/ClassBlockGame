package com.chobocho.tetrisgame;

import android.app.*;
import android.os.Bundle;
import android.util.Log;

import com.chobocho.player.Player;
import com.chobocho.player.PlayerImpl;
import com.chobocho.tetris.ITetris;
import com.chobocho.tetris.Tetris;

public class ChoboTetrisActivity extends Activity {
	public final int BOARD_WIDTH = 10;
	public final int BOARD_HEIGHT = 20;

	TetrisViewForN8 twN8;
	Player player;
	ITetris tetris;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
		
	    Log.e("Test", "W" + screenWidth + " H" + screenHeight);

	    tetris = new Tetris(BOARD_WIDTH, BOARD_HEIGHT);
		player = new PlayerImpl(BOARD_WIDTH, BOARD_HEIGHT);

		twN8 = new TetrisViewForN8(this, player);
		tetris.register(twN8);
		twN8.setTetris(this.tetris);
		twN8.setScreenSize(screenWidth,screenHeight);
		setContentView(twN8);
	}

	@Override
	public void onPause() {
		super.onPause();
		if (twN8 != null) {
			twN8.pauseGame();
		}
	}
}