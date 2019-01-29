package com.chobocho.tetrisgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.chobocho.player.*;
import com.chobocho.tetris.ITetris;
import com.chobocho.tetris.ITetrisObserver;
import com.chobocho.tetris.R;
import com.chobocho.tetris.Tetris;
import com.chobocho.tetris.Tetrominos;


import static android.content.Context.MODE_PRIVATE;

public class TetrisViewForN8 extends View implements ITetrisObserver {
	Context mContext;
	Player player;
	PlayerInput playerInput;
	PlayerUI playerUI;

	ITetris tetris;
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
	
	Paint mPaint;

	int   gameSpeed = 0;
	int   highScore = 0;

	final int N8_width = 1080;
	final int N8_height = 1920;
	private int screenWidth;
	private int screenHeigth;

	public static final int BOARD_WIDTH = 10;
	public static final int BOARD_HEIGHT = 20;
	public static final int BLOCK_IMAGE_SIZE = 60;

	public static final int GM_LOADING = 0;
	public static final int GM_IDLE = 1;
	public static final int DELAY = 500;

	int     gameState = GM_LOADING; 

	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			Log.d("Tetris", "There is event");
			if (tetris != null && tetris.isPlayState()) {
				tetris.moveDown();
				gameSpeed = 700 - (tetris.getScore() / 10000);
				mHandler.sendEmptyMessageDelayed(0, gameSpeed);
			}
		}
	};
	
	public TetrisViewForN8(Context context, Player player) {
		super(context);
		this.mContext = context;
		load();
		gameState = GM_LOADING;
		loadImage(context);

		mPaint = new Paint();
		mPaint.setColor(Color.BLUE);
		mPaint.setStrokeWidth(3);
		mPaint.setAntiAlias(true);
		mPaint.setTextSize(BLOCK_IMAGE_SIZE);

		init();

		this.player = player;
		playerInput = new PlayerInputImplForN8();
		playerUI = new PlayerUIForN8(mContext);

		player.setInputDevice(playerInput);
		player.setView(playerUI);
	}

	public void init() {
		gameState = GM_IDLE;
	}

	public void setScreenSize(int w, int h) {
		this.screenWidth = w;
		this.screenHeigth = h;
	}

	public void pauseGame() {
		if (mHandler.hasMessages(0)) {
			mHandler.removeMessages(0);
			Log.d("Tetris", "Removed event");
		}
		if (tetris != null) {
			tetris.pause();
		}
	}

	public void setTetris(ITetris tetris) {
		this.tetris = tetris;
		tetris.init();
	}

	public void update() {
		Log.d("Tetris", "View.update()");
		invalidate();
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

	public void onDrawBlock(Canvas canvas, Tetrominos block, int startX, int startY) {
		onDrawBlock(canvas, block, startX, startY, null);
	}

	public void onDrawBlock(Canvas canvas, Tetrominos block, int startX, int startY, Paint paint) {
		int [][]m_block = block.getBlock();

		int i = 0, j = 0;
		int w = block.getWidth();
		int h = block.getHeight();
		int x = block.getX();
		int y = block.getY();
		int type = block.getType();

		for (i = 0; i < w; i++) {
			for (j = 0; j < h; j++) {
				if (m_block[j][i] != Tetris.EMPTY) {
					canvas.drawBitmap(mTile[type], null,
							new Rect((x + i) * BLOCK_IMAGE_SIZE + startX,
									(y + j) * BLOCK_IMAGE_SIZE + startY,
									(x + i + 1) * BLOCK_IMAGE_SIZE + startX,
									(y + j+ 1) * BLOCK_IMAGE_SIZE + startY),
							paint);
				}
			}
		}
	}

	public void onDraw(Canvas canvas) {
		playerUI.onDraw(canvas);
		int i = 0;
		int j = 0;
        String str_gameState = "";
		int startX = 80;
		int startY = 80;

		int width = tetris.getWidth();
		int height = tetris.getHeight();

		canvas.drawBitmap(mGameBack, null, new Rect(0, 0, N8_width, N8_height), null);

		Paint paint = new Paint();
		paint.setAlpha(128);

		int[][] m_Board = tetris.getBoard();

		// Draw board
		for (i = 0; i < width; i++) {
			for (j = 0; j < height; j++) {

				if (Tetris.EMPTY == m_Board[j][i]) {
					canvas.drawBitmap(mTile[m_Board[j][i]], null,
							new Rect(i * BLOCK_IMAGE_SIZE + startX,
									j * BLOCK_IMAGE_SIZE + startY,
									i * BLOCK_IMAGE_SIZE + startX + BLOCK_IMAGE_SIZE,
									j * BLOCK_IMAGE_SIZE + startY + BLOCK_IMAGE_SIZE), paint);
				} else {
					canvas.drawBitmap(mTile[m_Board[j][i]], null,
							new Rect(i * BLOCK_IMAGE_SIZE + startX,
									j * BLOCK_IMAGE_SIZE + startY,
									i * BLOCK_IMAGE_SIZE + startX + BLOCK_IMAGE_SIZE,
									j * BLOCK_IMAGE_SIZE + startY + BLOCK_IMAGE_SIZE), null);
				}
			}
		}
		mPaint.setTextSize(BLOCK_IMAGE_SIZE);

		canvas.drawText("Score", 760, 700, mPaint);
		canvas.drawText(Integer.toString(tetris.getScore()), 760, 760, mPaint);
		canvas.drawText("Line", 760, 820, mPaint);
		canvas.drawText(Integer.toString(tetris.getRemovedLineCount()), 760, 880, mPaint);
		canvas.drawText("High Score : " + Integer.toString(highScore), startX, startY + 1620, mPaint);


		canvas.drawBitmap(leftArrow, null,
				new Rect(startX,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100,
						startX+200,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200), null);
		canvas.drawBitmap(bottomArrow, null,
				new Rect(startX+250,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100,
						startX+450,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200), null);
		canvas.drawBitmap(rightArrow, null,
				new Rect(startX+500,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100,
						startX+700,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200), null);
		canvas.drawBitmap(rotateArrow, null,
				new Rect(startX+750,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100,
						startX+950,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200), null);

		canvas.drawBitmap(downArrow, null,
				new Rect(startX+750,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT - 200,
						startX+950,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT), null);

		if (tetris == null) {
			Log.d("Tetris", "Tetris is null");
			return;
		}

		if (tetris.isIdleState()) {
			canvas.drawBitmap(mGameStart, 190, 400, null);

			canvas.drawBitmap(playBtn, null,
					new Rect(startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100,
							startY,
							startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100 + 200,
							startY + 200), null);
		} else if (tetris.isGameOverState()) {
			canvas.drawBitmap(mGameOver, 190, 400, null);
		} else if (tetris.isPlayState()) {

			if (tetris.isEnableShadow()) {
				Tetrominos sblock = tetris.getShadowBlock();
				onDrawBlock(canvas, sblock, startX, startY, paint);
			}

			Tetrominos block = tetris.getCurrentBlock();
			onDrawBlock(canvas, block, startX, startY);

			Tetrominos nextTetrominos = tetris.getNextBlock();
			int nStartX = 600;
			int nStartY = startY + 5 * BLOCK_IMAGE_SIZE;
			onDrawBlock(canvas, nextTetrominos, nStartX, nStartY);

			canvas.drawBitmap(pauseBtn, null,
					new Rect(startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100,
							startY,
							startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100 + 200,
							startY + 200), null);
		} else if (tetris.isPauseState()) {
			canvas.drawBitmap(mGameStart, 190, 400, null);
			canvas.drawBitmap(playBtn, null,
					new Rect(startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100,
							startY,
							startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100 + 200,
							startY + 200), null);

			mPaint.setTextSize(30);
			canvas.drawText("[" + screenWidth + "x" + screenHeigth +"]", 800, 1700, mPaint);
		}
	}

	public boolean onTouchEvent(MotionEvent event) {
		Log.d("Tetris", ">> X: " + event.getX() + " Y: " + event.getY());

		int startX = 80;
		int startY = 80;

		if (MotionEvent.ACTION_DOWN == event.getAction()) {

			if (tetris == null) {
				return true;
			}

			if (tetris.isGameOverState()) {
				if ((event.getX() > 190) && (event.getY() > 400)
						&& (event.getX() < 410) && (event.getY() < 500)) {
					if (highScore < tetris.getScore()) {
						highScore = tetris.getScore();
						saveScore();
					}
					tetris.init();
				}
				return true;
			}

			else if (tetris.isIdleState()) {
				if ((event.getX() > 190) && (event.getY() > 400)
						&& (event.getX() < 410) && (event.getY() < 500)) {
					tetris.play();
					mHandler.sendEmptyMessageDelayed(0, gameSpeed);
				} else if ((event.getX() > 700) && (event.getY() > 50)
						&& (event.getY() < 250)) {
					tetris.play();
					mHandler.sendEmptyMessageDelayed(0, gameSpeed);
					return true;
				}
				return true;
			}

			else if (tetris.isPauseState()) {
				if (event.getX() > startX + 750&&
						event.getY() > startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 &&
						event.getX() < startX + 950 &&
						event.getY() < startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200) {
					if (tetris != null) {
						if (tetris.isEnableShadow()) {
							tetris.disableShadow();
						} else {
							Toast.makeText(mContext, "Enable shadow block!", Toast.LENGTH_SHORT).show();
							tetris.enableShadow();
						}
					}
					return true;
				}
				else if ((event.getX() > 700) && (event.getY() > 50)
						&& (event.getY() < 250)) {
					tetris.resume();
					mHandler.sendEmptyMessageDelayed(0, gameSpeed);
				} else if ((event.getX() > 190) && (event.getY() > 400)
						&& (event.getX() < 410) && (event.getY() < 500)) {
					tetris.resume();
					mHandler.sendEmptyMessageDelayed(0, gameSpeed);
				}
				return true;
			}

			else if (tetris.isPlayState()) {
				if ((event.getX() > 700) && (event.getY() > 50)
						&& (event.getY() < 250)) {
					tetris.pause();
					return true;
				}
			}

			if (event.getX() > startX &&
					event.getY() > startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 &&
					event.getX() < startX + 200 &&
					event.getY() < startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200) {
				if(tetris != null) {
					tetris.moveLeft();
				}
				return true;
			}

			else if (event.getX() > startX + 250&&
					event.getY() > startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 &&
					event.getX() < startX + 450 &&
					event.getY() < startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200) {
				if (tetris != null) {
					tetris.moveBottom();
					tetris.moveDown();
				}
				return true;
			}

			else if (event.getX() > startX + 500&&
					event.getY() > startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 &&
					event.getX() < startX + 700 &&
					event.getY() < startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200) {
				if (tetris != null) {
					tetris.moveRight();
				}
				return true;
			}

			else if (event.getX() > startX + 750&&
					event.getY() > startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 &&
					event.getX() < startX + 950 &&
					event.getY() < startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200) {
				if (tetris != null) {
					tetris.rotate();
				}
				return true;
			}

			else if (event.getX() > startX + 750&&
					event.getY() > startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT - 200 &&
					event.getX() < startX + 950 &&
					event.getY() < startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT) {
				if (tetris != null) {
					tetris.moveDown();
				}
				return true;
			}


		}
		return false;
	}

	public void load() {
		Log.d("Tetris", "load()");
		SharedPreferences pref = mContext.getSharedPreferences("choboTetris", MODE_PRIVATE);
		this.highScore = pref.getInt("highscore", 0);
	}

	public void saveScore() {
		Log.d("Tetris", "saveScore()");
		SharedPreferences pref = mContext.getSharedPreferences("choboTetris", MODE_PRIVATE);
		SharedPreferences.Editor edit = pref.edit();

		edit.putInt("highscore", this.highScore);
		edit.commit();
	}

}
