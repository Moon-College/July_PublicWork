package com.fantasyado.drawanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

/**
 * created at:2015年8月12日 上午9:22:41 project name:SplashAnimation
 * 
 * @author Fantasy ado
 * @version 1.0
 * @since JDK 1.7 File name:Splash.java description:
 */

public class Splash extends View {

	private float bigRadius;
	private float smallRadius;
	private int mCenterX;
	private int mCenterY;
	private int[] mColors = { Color.RED, Color.YELLOW, Color.GREEN,
			Color.MAGENTA, Color.BLUE, Color.BLACK };
	private Paint mPaint;
	private Paint mpaintBg;
	private boolean shoudRotate = true;

	public Splash(Context context, AttributeSet attrs) {
		super(context, attrs);

		init(context);

	}

	public Splash(Context context) {
		this(context, null);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		mCenterX = w / 2;
		mCenterY = h / 2;

	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		drawBackground(canvas);

		drawCircle(canvas);
		if (shoudRotate) {

			doRotation();
			shoudRotate = false;
		}

		 
	} 

	private float radius = 0;

	private void showBeauty(Canvas canvas) {
		
		 Toast.makeText(getContext(), "i am invoked ", 0).show();
		 
		 mpaintBg.setAlpha(0);
		mpaintBg.setColor(PixelFormat.TRANSLUCENT);
		mpaintBg.setStyle(Paint.Style.STROKE);
	 
		 mpaintBg.setStrokeWidth(300);
		   
	 canvas.drawCircle(mCenterX, mCenterY, radius, mpaintBg);

	}

	private void doShowUp() {
		ValueAnimator animator = ValueAnimator.ofFloat(0f, mCenterX * 2);
		animator.setDuration(3200);
		animator.setInterpolator(new LinearInterpolator());
		animator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				radius = (Float) animation.getAnimatedValue();

				invalidate();
			}
		});
		animator.start();

	}
	
	  private void hide() {
		// TODO Auto-generated method stub
		  this.setVisibility(View.GONE);

	}

	float startAngle = 0;

	private void drawCircle(Canvas canvas) {

		for (int i = 0; i < 6; i++) {
			mPaint.setColor(mColors[i]);
			float cx = (float) (mCenterX + bigRadius
					* Math.cos(startAngle + deltaAngle));
			float cy = (float) (mCenterY + bigRadius
					* Math.sin(startAngle + deltaAngle));
			Log.i("=======", Math.sin(startAngle) + "");
			Log.i("=======", "" + Math.cos(startAngle));

			canvas.drawCircle(cx, cy, smallRadius, mPaint);

			startAngle += Math.PI / 3;
		}

	}

	private void drawBackground(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawColor(Color.WHITE);

	}

	private void init(Context context) {

		bigRadius = 90;
		smallRadius = bigRadius / 8;
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Paint.Style.FILL);
		mpaintBg = new Paint();
		mpaintBg.setAntiAlias(true);

	}

	private float deltaAngle;

	public void doRotation() {

		ValueAnimator animator = ValueAnimator.ofFloat(0f, (float) Math.PI * 2);
		animator.setDuration(1200);
		animator.setInterpolator(new LinearInterpolator());
		animator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				deltaAngle = (Float) animation.getAnimatedValue();

				invalidate();
			}
		});
		animator.setRepeatCount(3);
		animator.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				super.onAnimationEnd(animation);
				doGather();
			}
		});

		animator.start();
	}

	public void doGather() {
		ValueAnimator animator = ValueAnimator.ofFloat(bigRadius, 0);
		animator.setDuration(2000);
		animator.setInterpolator(new AnticipateOvershootInterpolator(10));
		animator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				bigRadius = (Float) animation.getAnimatedValue();

				invalidate();
			}
		});
		animator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				super.onAnimationEnd(animation);
			//	doShowUp();
			  hide();
				
			}
		});
		animator.start();

	}

}
