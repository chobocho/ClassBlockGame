package com.chobocho.tetris;

import android.content.Context;
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

public class TetrisViewForN8 extends View implements ITetrisObserver {
	ITetris tetris;
	Bitmap mGameBack;
	Bitmap mGameStart;
	Bitmap mGameOver;
	Bitmap mNumbers;

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

	final int N8_width = 1080;
	final int N8_height = 1920;
	private int screenWidth;
	private int screenHeigth;

	public static final int BOARD_WIDTH = 10;
	public static final int BOARD_HEIGHT = 20;
	public static final int BLOCK_IMAGE_SIZE = 60;

	public static final int GM_LOADING = 0;
	public static final int GM_START = 1;
	public static final int GM_PLAY = 2;
	public static final int GM_GAME_OVER = 3;
	public static final int GM_PAUSE = 4;
	public static final int DELAY = 500;

	int     gameState = GM_LOADING; 

	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			Log.d("Tetris", "There is event");
			if (gameState == GM_PLAY) {
				mHandler.removeMessages(0);
				tetris.moveDown();
				gameSpeed = 700 - (tetris.getScore() / 10000);
				mHandler.removeMessages(0);
				mHandler.sendEmptyMessageDelayed(0, gameSpeed);
			}
		}
	};
	
	public TetrisViewForN8(Context context) {
		super(context);
		gameState = GM_LOADING;

		loadImage(context);

		mPaint = new Paint();
		mPaint.setColor(Color.BLUE);
		mPaint.setStrokeWidth(3);
		mPaint.setAntiAlias(true);
		mPaint.setTextSize(BLOCK_IMAGE_SIZE);

		init();
	}

	public void init() {
		gameState = GM_START;
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
	}

	public void setTetris(ITetris tetris) {
		this.tetris = tetris;
		tetris.init();
	}

	public void updateIdle() {
		Log.d("Tetris", "View.updateIdle()");
		gameState = GM_START;
		invalidate();
	}

	public void updatePlay() {
		Log.d("Tetris", "View.updatePlay()");
		gameState = GM_PLAY;
		invalidate();
	}

	public void updatePause() {
		Log.d("Tetris", "View.updatePause()");
		gameState = GM_PAUSE;
		invalidate();
	}

	public void updateGameOver() {
		Log.d("Tetris", "View.updateGameOver()");
		gameState = GM_GAME_OVER;
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

	public void onDraw(Canvas canvas) {
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
		canvas.drawText(Integer.toString(tetris.getScore()), 800, 700, mPaint);
		canvas.drawBitmap(leftArrow, null,
				new Rect(startX,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100,
						startX+200,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200), null);
		canvas.drawBitmap(downArrow, null,
				new Rect(startX+250,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100,
						startX+450,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200), null);
		canvas.drawBitmap(rotateArrow, null,
				new Rect(startX+500,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100,
						startX+700,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200), null);
		canvas.drawBitmap(rightArrow, null,
				new Rect(startX+750,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100,
						startX+950,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200), null);

		canvas.drawBitmap(bottomArrow, null,
				new Rect(startX+750,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT - 200,
						startX+950,
						startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT), null);

		switch (gameState) {
		case GM_START:
			canvas.drawBitmap(mGameStart, 190, 400, null);

			canvas.drawBitmap(playBtn, null,
					new Rect(startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100,
							startY,
							startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100 + 200,
							startY + 200), null);
			break;
		case GM_GAME_OVER:
			canvas.drawBitmap(mGameOver, 190, 400, null);
			break;
		case GM_PLAY:
		{
			Tetrominos block = tetris.getCurrentBlock();
			int [][]m_block = block.getBlock();

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
								null);
					}
				}
			}

			Tetrominos nextTetrominos = tetris.getNextBlock();
			int [][]nextblock = nextTetrominos.getBlock();

			w = nextTetrominos.getWidth();
			h = nextTetrominos.getHeight();
			x = nextTetrominos.getX();
			y = nextTetrominos.getY();
			type = nextTetrominos.getType();

			int nStartX = 600;
			int nStartY = startY + 5 * BLOCK_IMAGE_SIZE;

			for (i = 0; i < w; i++) {
				for (j = 0; j < h; j++) {
					if (nextblock[j][i] != Tetris.EMPTY) {
						canvas.drawBitmap(mTile[type], null,
								new Rect((x + i) * BLOCK_IMAGE_SIZE + nStartX,
										(y + j) * BLOCK_IMAGE_SIZE + nStartY,
										(x + i + 1) * BLOCK_IMAGE_SIZE + nStartX,
										(y + j+ 1) * BLOCK_IMAGE_SIZE + nStartY),
								null);
					}
				}
			}

			canvas.drawBitmap(pauseBtn, null,
					new Rect(startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100,
							startY,
							startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100 + 200,
							startY + 200), null);
		}	
		   break;
		case GM_PAUSE:
		{
			canvas.drawBitmap(mGameStart, 190, 400, null);
			canvas.drawBitmap(playBtn, null,
					new Rect(startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100,
							startY,
							startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100 + 200,
							startY + 200), null);

			mPaint.setTextSize(30);
			canvas.drawText("[" + screenWidth + "x" + screenHeigth +"]", 800, 1700, mPaint);
		}		
			break;
		default:
			break;
		}
	}

	public boolean onTouchEvent(MotionEvent event) {
		Log.d("Tetris", ">> X: " + event.getX() + " Y: " + event.getY());

		int startX = 80;
		int startY = 80;

		if (MotionEvent.ACTION_DOWN == event.getAction()) {


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
					tetris.moveDown();
				}
				return true;
			}

			else if (event.getX() > startX + 500&&
					event.getY() > startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 &&
					event.getX() < startX + 700 &&
					event.getY() < startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200) {
				if (tetris != null) {
					tetris.rotate();
				}
				return true;
			}

			else if (event.getX() > startX + 750&&
					event.getY() > startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 &&
					event.getX() < startX + 950 &&
					event.getY() < startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT + 100 + 200) {
				if (tetris != null) {
					tetris.moveRight();
				}
				return true;
			}

			else if (event.getX() > startX + 750&&
					event.getY() > startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT - 200 &&
					event.getX() < startX + 950 &&
					event.getY() < startY + BLOCK_IMAGE_SIZE * BOARD_HEIGHT) {
				if (tetris != null) {
					tetris.moveBottom();
				}
				return true;
			}

			if (GM_GAME_OVER == gameState) {
				if ((event.getX() > 190) && (event.getY() > 400)
						&& (event.getX() < 410) && (event.getY() < 500)) {
					init();
					tetris.init();
				}
			}

			else if (GM_START == gameState) {
				if ((event.getX() > 190) && (event.getY() > 400)
						&& (event.getX() < 410) && (event.getY() < 500)) {
					init();
					tetris.play();
					mHandler.sendEmptyMessageDelayed(0, gameSpeed);
				} else if (event.getX() > startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100 &&
						event.getY() > startY &&
						event.getX() < startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100 + 200 &&
						event.getY() < startY + 200) {
					tetris.play();
					mHandler.sendEmptyMessageDelayed(0, gameSpeed);
				}
			}
			
			else if (GM_PAUSE == gameState) {
				if ((event.getX() > 700) && (event.getY() > 50)
						&& (event.getY() < 200)) {
					tetris.resume();
					mHandler.sendEmptyMessageDelayed(0, gameSpeed);
					Log.d("Pause", ">> X: " + event.getX() + " Y: " + event.getY());
				} else if (event.getX() > startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100 &&
						   event.getY() > startY &&
						   event.getX() < startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100 + 200 &&
						   event.getY() < startY + 200) {
					tetris.resume();
					mHandler.sendEmptyMessageDelayed(0, gameSpeed);
				}
			}

			else if (GM_PLAY == gameState) {
				if ((event.getX() > 700) && (event.getY() > 50)
						&& (event.getY() < 200)) {
					tetris.pause();
					return true;
				}

				else if (event.getX() > startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100 &&
						event.getY() > startY &&
						event.getX() < startX + BLOCK_IMAGE_SIZE * BOARD_WIDTH + 100 + 200 &&
						event.getY() < startY + 200) {
					tetris.pause();
					return true;
				}
			}
		}
		return false;
	}
}
