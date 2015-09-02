package com.fantasyado.biziercurve;

import java.util.Random;

import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private Random mRandom;

	private FrameLayout fl;
	private int[] imgs = {

	R.drawable.red, R.drawable.green, R.drawable.blue };
	private int width;
	private int height;
	private long lastTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {

		fl = (FrameLayout) findViewById(R.id.ll);

		mRandom = new Random();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (System.currentTimeMillis() - lastTime > 200) {
				startAnim();
				lastTime = System.currentTimeMillis();
			}

			break;
		case MotionEvent.ACTION_MOVE:
			if (System.currentTimeMillis() - lastTime > 200) {
				startAnim();
				lastTime = System.currentTimeMillis();
			}

			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);

		width = fl.getMeasuredWidth();

		height = fl.getMeasuredHeight();

	}

	// 随机产生贝塞尔曲线中间的控制点
	public PointF getPoint(int i) {
		PointF point = new PointF();
		point.x = mRandom.nextInt(width);
		if (i == 1) {

			point.y = mRandom.nextInt(height / 2) + height / 2;
		} else if (i == 2) {
			point.y = (float) mRandom.nextInt(height / 2);
		}
		return point;

	}

	private void startAnim() {
		// TODO Auto-generated method stub
		// 产生心形图片并添加到容器中
		final ImageView img = new ImageView(this);
		img.setImageResource(imgs[mRandom.nextInt(3)]);

		FrameLayout.LayoutParams lp = new LayoutParams(120, 120);
		lp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
		fl.addView(img, lp);

		// 产生贝塞尔曲线动画所需的四个点
		final PointF p1 = getPoint(1);
		final PointF p2 = getPoint(2);

		PointF p0 = new PointF((width - 120) / 2, height - 120);
		PointF p3 = new PointF(mRandom.nextInt(width), 0);
		System.out.println(img.getX() + "," + img.getY());

		// 贝塞尔曲线动画定义
		final ValueAnimator animator2 = ValueAnimator.ofObject(
				new TypeEvaluator<PointF>() {

					@Override
					public PointF evaluate(float t, PointF ps, PointF pe) {
						// TODO Auto-generated method stub

						float m = 1 - t; // 变量转换
						PointF point = new PointF();

						point.x = ps.x * m * m * m + 3 * p1.x * m * m * t + 3
								* p2.x * t * t * m + pe.x * t * t * t;
						point.y = ps.y * m * m * m + 3 * p1.y * m * m * t + 3
								* p2.y * t * t * m + pe.y * t * t * t;

						return point;
					}
				}, p0, p3);
		animator2.setDuration(2000);

		animator2.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				PointF point = null;

				point = (PointF) animation.getAnimatedValue();
				img.setX(point.x);
				img.setY(point.y);
				img.setAlpha(1 - animation.getAnimatedFraction() + 0.3f);

			}
		});
		animator2.addListener(new AnimatorListenerAdapter() {
			// 动画结束后移除New的ImageView 释放资源
			public void onAnimationEnd(android.animation.Animator animation) {
				fl.removeView(img);

			};

		});

		// ImageView出现动画 alpha加Scale
		ValueAnimator animator1 = ValueAnimator.ofFloat(0, 1);
		animator1.setDuration(300);
		animator1.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				float value = (Float) animation.getAnimatedValue();

				img.setAlpha(value);
				img.setScaleX(value);
				img.setScaleY(value);
			}
		});

		AnimatorSet set = new AnimatorSet();
		set.playSequentially(animator1, animator2);
		set.start();

	}

}
