package com.chobocho.tetrisgame;

import android.app.*;
import android.os.Bundle;
import android.util.Log;

import com.chobocho.player.Player;
import com.chobocho.player.PlayerImpl;

public class ChoboTetrisActivity extends Activity {
	TetrisView twN8;
	BoardProfile profile;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
		profile = new BoardProfile(screenWidth, screenHeight);

	    Log.e("ChoboTetris", "W" + screenWidth + " H" + screenHeight + " B" + profile.blockSize());

		Player player = new PlayerImpl(profile);
		twN8 = new TetrisView(this, player, profile);
		setContentView(twN8);
	}

	@Override
	public void onPause() {
		super.onPause();
		if (twN8 != null) {
			twN8.pauseGame();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (twN8 != null) {
			twN8.resumeGame();
		}
	}
}