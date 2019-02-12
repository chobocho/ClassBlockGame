package com.chobocho.tetrisgame;

import android.app.*;
import android.os.Bundle;
import android.util.Log;

import com.chobocho.player.Player;
import com.chobocho.player.PlayerImpl;

public class ChoboTetrisActivity extends Activity {
	private final int BOARD_WIDTH = 10;
	private final int BOARD_HEIGHT = 20;

	private TetrisViewForN8 twN8;
	private Player player;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
		
	    Log.e("Test", "W" + screenWidth + " H" + screenHeight);

		player = new PlayerImpl(BOARD_WIDTH, BOARD_HEIGHT);

		twN8 = new TetrisViewForN8(this, player);
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