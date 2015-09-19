package com.tz.flappybird;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

public class Drawer extends Thread {

	// ��Ϸ��ǰ����
	public static final int READY = 0;
	public static final int RUNNING = 1;
	public static final int OVER = 2;

	public static boolean isStart;
	public static boolean isDraw=true;//�Ƿ������ͼ
	//run���н���������isStart��־
	public static boolean isRunSetStart=false;
	private Context context;
	private SurfaceHolder holder;
	private int w;
	private int h;
	private Spirit bg;
	private Spirit floor;
	private Spirit getReady;
	private Spirit tap;
	private Spirit gameOver;
	private Bitmap pipeTopBm;
	private Spirit pipeTop1;
	private Bitmap pipeBottomBm;
	private Spirit pipeBottom1;
	private Spirit pipeTop2;
	private Spirit pipeBottom2;
	private Spirit bird;
	private Spirit panel;
	private int pipeSpan;
	private int pipeTopHeight1;
	private int pipeTopHeight2;
	private Canvas canvas;
	private Paint paint;
	private int sceneState;
	private boolean isHit;

	// ����÷�
	public static int SCORE = 0;

	public Drawer(Context context, SurfaceHolder holder, int width, int height) {
		this.context = context;
		this.holder = holder;
		this.w = width;
		this.h = height;
		paint = new Paint();
		isStart = true;

		// ��ʼ������
		initSpirits();
		// ��ʼ�������������
		initAttrs();
	}

	public void initAttrs() {
		// ����׼��ͼ�������
		getReady.setX((w - getReady.getWidth()) / 2);
		getReady.setY(h / 3);
		// ����׼�����ϵ�tap�ӱ�
		tap.setX((w - tap.getWidth()) / 2);
		tap.setY(h / 2);
		// ����gameOver����
		gameOver.setX((w - gameOver.getWidth()) / 2);
		gameOver.setY(h / 3);
		// ���÷������ӱ�
		panel.setX((w - panel.getWidth()) / 2);
		panel.setY(h / 2);

		// С��ӱꡢ�ٶ�
		bird.setX(w / 4);
		bird.setY(h / 2);
		bird.setYSpeed(2);

		// ���ӵļ�϶
		pipeSpan = h / 4;
		// �����������ӵĸ߶ȣ���������h/2��
		pipeTopHeight1 = h / 4;
		pipeTopHeight2 = h / 3;

		// ���Ӵӱ�����
		// ��Ϸ��ʼ��һ������λ����Ļ�Ҷ�
		pipeTop1.setX(w);
		pipeTop1.setScoreBeforeX(pipeTop1.getX());// ���ӵ÷�ǰλ��
		pipeTop1.setY(0);
		pipeBottom1.setX(w);
		pipeBottom1.setY(pipeTopHeight1 + pipeSpan);

		// �ڶ�����������
		pipeTop2.setX(3f * w / 2 + pipeBottom1.getWidth() / 2);
		pipeTop2.setScoreBeforeX(pipeTop2.getX());// ���ӵ÷�ǰλ��
		pipeTop2.setY(0);
		pipeBottom2.setX(3f * w / 2);
		pipeBottom2.setY(pipeTopHeight2 + pipeSpan);

		// ��ʼ���÷�
		this.SCORE = 0;
		isHit = false;

	}

	private void initSpirits() {
		// ����
		Bitmap bm_bg = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.bg1);
		bg = new Spirit(bm_bg);
		Bitmap bm_floor = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.floor);
		floor = new Spirit(bm_floor);
		Bitmap bm_ready = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.getready);
		getReady = new Spirit(bm_ready);
		Bitmap bm_tap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.tap);
		tap = new Spirit(bm_tap);

		Bitmap bm_over = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.gameover);
		gameOver = new Spirit(bm_over);
		Bitmap bm_panel = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.panel);
		panel = new Spirit(bm_panel);

		// ��һ�Թ���
		pipeTopBm = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.pipe1);
		pipeTop1 = new Spirit(pipeTopBm);
		pipeBottomBm = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.pipe2);
		pipeBottom1 = new Spirit(pipeBottomBm);
		// �ڶ��Թ���
		pipeTop2 = pipeTop1.getCloneObject();
		pipeBottom2 = pipeBottom1.getCloneObject();

		// ��
		int[] paths = new int[] { R.drawable.bird_1, R.drawable.bird_2,
				R.drawable.bird_3 };
		Bitmap[] bms = new Bitmap[paths.length];
		for (int i = 0; i < bms.length; i++) {
			Bitmap bitmap = BitmapFactory.decodeResource(
					context.getResources(), paths[i]);
			bms[i] = bitmap;
		}
		bird = new Spirit(bms);
	}

	/**
	 * ��һ���̲߳�ͣ�ϻ���ˮ��Ϸ����
	 */
	@Override
	public void run() {
		Log.i("jzhao", "run......");
		while (isStart) {
			//isDraw���ڿ��ƻ�ͼ��ͣ
			while(isDraw){
				Log.i("jzhao", "isStart......");
				try {
					// ��������
					canvas = holder.lockCanvas();
					if (canvas != null) {
						// ����Ϸ��ͼ
						drawGameView(canvas);
					}

				} catch (Exception e) {
					e.printStackTrace();
					Log.v("jzhao", "draw is Error!");
				} finally {
					if (canvas != null) {
						holder.unlockCanvasAndPost(canvas);
					}
				}
				
				if(Drawer.isRunSetStart){
					Log.i("jzhao", "isRunSetStart......");
					isDraw=false;
				}
			}
		}

	}

	/**
	 * ����Ϸ������ͼ
	 * 
	 * @param canvas
	 */
	private void drawGameView(Canvas canvas) {
		// ���б���
		canvas.drawBitmap(bg.getBitmap(), null, new Rect(0, 0, w, h), paint);
		// ���ذ�
		canvas.drawBitmap(floor.getBitmap(), null,
				new Rect(0, 3 * h / 4, w, h), paint);
		// �жϵ�ǰ��Ϸ����
		switch (getSceneState()) {
		case Drawer.READY:
			drawGameReadyView(canvas);
			break;
		case Drawer.RUNNING:
			drawGameRunningView(canvas);
			break;
		case Drawer.OVER:
			drawGameOverView(canvas);
			break;
		default:
			break;

		}

	}

	private void drawGameOverView(Canvas canvas) {
		// ��Ϸ�������棺gameoverͼ�� �������
		gameOver.drawSelf(canvas);
		panel.drawSelf(canvas);
		// ���÷�
		paint.setColor(Color.GREEN);
		paint.setTextSize(25);
		float score0Width = paint.measureText(0 + "");
		float scoreWidth = paint.measureText(SCORE + "");
		// ����ƫ����
		float offsetWidth = (scoreWidth - score0Width) / 2;
		canvas.drawText(SCORE + "", panel.getX() + 180 - offsetWidth,
				panel.getY() + 53, paint);

	}

	private void drawGameRunningView(Canvas canvas) {
		// ��Ϸ����ʱ
		// ���ƹ��ӵ��˼�
		// ��ƹ��ӣ��涨���������������Թ��ӣ�������һ�Ի���ȥ�ˣ���ֱ�����øù��ӣ�x=W��
		// ���ӵ��ˣ����ӾͲ����ˣ����������
		if (!isHit) {
			// ��һ�Թ���
			// ��¼�÷�֮ǰλ��
			pipeTop1.setScoreBeforeX(pipeTop1.getX());
			pipeTop1.setX(pipeTop1.getX() + Spirit.PIPE_SPEED);

			// �ӹ���ԭͼ����һ���Ӽ���ȥ��������ʾ�Ĳ���
			pipeTop1.setBitmap(Bitmap.createBitmap(pipeTopBm, 0,
					pipeTopBm.getHeight() - pipeTopHeight1,
					pipeTopBm.getWidth(), pipeTopHeight1));

			pipeBottom1.setX(pipeTop1.getX());
			pipeBottom1.setY(pipeTopHeight1 + pipeSpan);
			// Լ���������ӵĸ߶ȼ�������h/2
			pipeBottom1.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0,
					pipeBottom1.getWidth(), h / 2 - pipeTopHeight1));

			// �ڶ��Թ���
			// ��¼�÷�֮ǰλ��
			pipeTop2.setScoreBeforeX(pipeTop2.getX());
			pipeTop2.setX(pipeTop2.getX() + Spirit.PIPE_SPEED);
			pipeTop2.setBitmap(Bitmap.createBitmap(pipeTopBm, 0,
					pipeTopBm.getHeight() - pipeTopHeight2,
					pipeTopBm.getWidth(), pipeTopHeight2));
			pipeBottom2.setX(pipeTop2.getX());
			pipeBottom2.setY(pipeTopHeight2 + pipeSpan);
			pipeBottom2.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0,
					pipeBottomBm.getWidth(), h / 2 - pipeTopHeight2));

			// �ж��Ƿ�÷�
			List<Spirit> pipeList = new ArrayList<Spirit>();
			pipeList.add(pipeTop1);
			pipeList.add(pipeTop2);
			addScore(pipeList);
		}

		// ����С���λ��
		// ��С��һ�����ٶ�
		bird.setYSpeed(bird.getYSpeed() + Spirit.ADD_YSPEED);
		bird.setY(bird.getY() + bird.getYSpeed());

		// ���С�����ײ
		// �ж����������Ƿ񽻲棬�����˾ͱ�ʾ��ײ��
		if (bird.getY() <= 0 || birdHitPip(pipeTop1) || birdHitPip(pipeBottom1)
				|| birdHitPip(pipeTop2) || birdHitPip(pipeBottom2)) {
			isHit = true;
		}

		// С�������ذ壬�������gameover
		if (bird.getY() + bird.getHeight() >= 3 * h / 4) {
			setSceneState(Drawer.OVER);
		}
		// ���ӳ����ˣ��øöԹ����ٻص����ұߣ�ͬʱ����߶�Ϊ����ĸ߶�
		checkPipeOutBound(pipeTop1, true);
		checkPipeOutBound(pipeTop2, false);

		// �ø�ͼ������Լ�
		pipeTop1.drawSelf(canvas);
		pipeTop2.drawSelf(canvas);
		pipeBottom1.drawSelf(canvas);
		pipeBottom2.drawSelf(canvas);
		bird.drawSelf(canvas);
	}

	/**
	 * �ж��Ƿ�÷֣������ӷ���
	 * 
	 * @param pipeList
	 *            ���й���
	 */
	private void addScore(List<Spirit> pipeList) {
		if (pipeList == null || pipeList.size() == 0) {
			return;
		}
		for (Spirit pipe : pipeList) {
			// ����÷�ǰλ�������ұߣ���ǰλ��������ߣ���÷�
			if ((pipe.getScoreBeforeX() + pipe.getWidth()) > bird.getX()
					&& (pipe.getX() + pipe.getWidth()) <= bird.getX()) {
				SCORE++;
			}
		}
	}

	/**
	 * �������Ƿ��Ƴ�����Ļ
	 * 
	 * @param pipe
	 * @param isFirt
	 *            �Ƿ��ǵ�һ�Թ���
	 */
	private void checkPipeOutBound(Spirit pipe, boolean isFirt) {
		if ((pipe.getX() + pipe.getWidth()) <= 0) {
			// ���ӳ����˻ص����ұ�
			pipe.setX(w);
			if (isFirt) {
				// �߶ȷ� h/8 ~ 3h/8
				pipeTopHeight1 = (int) (Math.random() * (2 * h / 8) + h / 8);
			} else {
				pipeTopHeight2 = (int) (Math.random() * (2 * h / 8) + h / 8);
			}
		}

	}

	private boolean birdHitPip(Spirit pipeTop) {
		// intersect
		if (bird.getSpiritRectF().intersect(pipeTop.getSpiritRectF())) {
			return true;
		}
		return false;
	}

	private void drawGameReadyView(Canvas canvas) {
		// ׼�����������׼��ͼ�� tap С��
		getReady.drawSelf(canvas);
		tap.drawSelf(canvas);
		bird.drawSelf(canvas);
	}

	public int getSceneState() {
		return sceneState;
	}

	public void setSceneState(int sceneState) {
		this.sceneState = sceneState;
	}

	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}

	public Spirit getBird() {
		return bird;
	}

	public void setBird(Spirit bird) {
		this.bird = bird;
	}

	/**
	 * ��������
	 */
	public void saveAttrs(Activity activity) {
		// ʵ����SharedPreferences���󣨵�һ����
		SharedPreferences mySharedPreferences = activity.getSharedPreferences(
				"flappybird", Activity.MODE_PRIVATE);
		// ʵ����SharedPreferences.Editor���󣨵ڶ�����
		SharedPreferences.Editor editor = mySharedPreferences.edit();
		// ����һ��������־����SharedPreferences�Ƿ���ֵ��
		editor.putInt("saveFlag", 1);

		// ����׼��ͼ�������
		editor.putFloat("getReady_X", getReady.getX());
		editor.putFloat("getReady_Y", getReady.getY());
		// ����׼�����ϵ�tap�ӱ�
		editor.putFloat("tap_X", tap.getX());
		editor.putFloat("tap_Y", tap.getY());
		// ����gameOver����
		editor.putFloat("gameOver_X", gameOver.getX());
		editor.putFloat("gameOver_Y", gameOver.getY());
		// ���÷������ӱ�
		editor.putFloat("panel_X", panel.getX());
		editor.putFloat("panel_Y", panel.getY());

		// С��ӱꡢ�ٶ�
		editor.putFloat("bird_X", bird.getX());
		editor.putFloat("bird_Y", bird.getY());
		editor.putFloat("bird_YSpeed", bird.getYSpeed());

		// ���ӵļ�϶
		editor.putInt("pipeSpan", pipeSpan);
		// �����������ӵĸ߶ȣ���������h/2��
		editor.putInt("pipeTopHeight1", pipeTopHeight1);
		editor.putInt("pipeTopHeight2", pipeTopHeight2);

		// ���Ӵӱ�����
		// ��Ϸ��ʼ��һ������λ����Ļ�Ҷ�
		editor.putFloat("pipeTop1_X", pipeTop1.getX());
		editor.putFloat("pipeTop1_ScoreBeforeX", pipeTop1.getScoreBeforeX());
		editor.putFloat("pipeTop1_Y", pipeTop1.getY());
		editor.putFloat("pipeBottom1_X", pipeBottom1.getX());
		editor.putFloat("pipeBottom1_Y", pipeBottom1.getY());

		// �ڶ�����������
		editor.putFloat("pipeTop2_X", pipeTop2.getX());
		editor.putFloat("pipeTop2_ScoreBeforeX", pipeTop2.getScoreBeforeX());
		editor.putFloat("pipeTop2_Y", pipeTop2.getY());
		editor.putFloat("pipeBottom2_X", pipeBottom2.getX());
		editor.putFloat("pipeBottom2_Y", pipeBottom2.getY());

		// ��ʼ���÷�
		editor.putInt("SCORE", SCORE);
		editor.putBoolean("isHit", isHit);
		editor.putInt("sceneState", sceneState);

		// �ύ��ǰ����
		editor.commit();
	}

	/**
	 * �ָ�����
	 */
	public void restoreAttrs(Activity activity) {
		SharedPreferences sharedPreferences = activity.getSharedPreferences(
				"flappybird", Activity.MODE_PRIVATE);
		//
		if (sharedPreferences != null
				&& sharedPreferences.getInt("saveFlag", 0) == 1) {
			// ����׼��ͼ�������
			getReady.setX(sharedPreferences.getFloat("getReady_X", 0));
			getReady.setY(sharedPreferences.getFloat("getReady_Y", 0));
			// ����׼�����ϵ�tap�ӱ�
			tap.setX(sharedPreferences.getFloat("tap_X", 0));
			tap.setY(sharedPreferences.getFloat("tap_Y", 0));
			// ����gameOver����
			gameOver.setX(sharedPreferences.getFloat("gameOver_X", 0));
			gameOver.setY(sharedPreferences.getFloat("gameOver_Y", 0));
			// ���÷������ӱ�
			panel.setX(sharedPreferences.getFloat("panel_X", 0));
			panel.setY(sharedPreferences.getFloat("panel_Y", 0));

			// С��ӱꡢ�ٶ�
			bird.setX(sharedPreferences.getFloat("bird_X", 0));
			bird.setY(sharedPreferences.getFloat("bird_Y", 0));

			// bird.setYSpeed(sharedPreferences.getFloat("bird_YSpeed",0));

			// ���ӵļ�϶
			pipeSpan = sharedPreferences.getInt("pipeSpan", 0);
			// �����������ӵĸ߶ȣ���������h/2��
			pipeTopHeight1 = sharedPreferences.getInt("pipeTopHeight1", 0);
			pipeTopHeight2 = sharedPreferences.getInt("pipeTopHeight2", 0);

			// ���Ӵӱ�����
			// ��Ϸ��ʼ��һ������λ����Ļ�Ҷ�
			pipeTop1.setX(sharedPreferences.getFloat("pipeTop1_X", 0));
			pipeTop1.setScoreBeforeX(sharedPreferences.getFloat(
					"pipeTop1_ScoreBeforeX", 0));// ���ӵ÷�ǰλ��
			pipeTop1.setY(sharedPreferences.getFloat("pipeTop1_Y", 0));
			pipeBottom1.setX(sharedPreferences.getFloat("pipeBottom1_X", 0));
			pipeBottom1.setY(sharedPreferences.getFloat("pipeBottom1_Y", 0));

			// �ڶ�����������
			pipeTop2.setX(sharedPreferences.getFloat("pipeTop2_X", 0));
			pipeTop2.setScoreBeforeX(sharedPreferences.getFloat(
					"pipeTop2_ScoreBeforeX", 0));// ���ӵ÷�ǰλ��
			pipeTop2.setY(sharedPreferences.getFloat("pipeTop2_Y", 0));
			pipeBottom2.setX(sharedPreferences.getFloat("pipeBottom2_X", 0));
			pipeBottom2.setY(sharedPreferences.getFloat("pipeBottom2_Y", 0));

			// ��ʼ���÷�
			this.SCORE = sharedPreferences.getInt("SCORE", 0);
			isHit = sharedPreferences.getBoolean("isHit", false);
			sceneState = sharedPreferences.getInt("sceneState", 0);

			// ȡ��ֵ�����
			sharedPreferences.edit().clear().commit();

			Drawer.isRunSetStart=true;

		}
	}

}
