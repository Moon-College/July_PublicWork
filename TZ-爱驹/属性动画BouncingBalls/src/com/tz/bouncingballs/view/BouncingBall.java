package com.tz.bouncingballs.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

public class BouncingBall extends View {

	private static int[] colors = { Color.WHITE, Color.BLUE, Color.CYAN,
			Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.YELLOW, Color.RED, };
	public static int colorLen = colors.length;
	private Paint paint;
	private float cy;
	private float cx;
	private float origY;
	private float radius;

	/**
	 * 
	 * @param context
	 *            上下文对象
	 * @param cx
	 *            圆心横坐标
	 * @param cy
	 *            圆心纵坐标
	 * @param radius
	 *            圆心半径
	 * @param color
	 *            颜色值
	 */
	public BouncingBall(Context context, float cx, float cy, float radius,
			int color) {
		super(context);
		this.cx = cx;
		this.cy = cy;
		this.radius = radius;
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(colors[color]);
		origY = cy;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawCircle(cx, cy, radius, paint);
		invalidate();
	}

	public float getOrigY() {
		return origY;
	}

	public float getRadius() {
		return radius;
	}

	/**
	 * @param parent
	 *            父控件
	 * @param scnHeight
	 *            屏幕高度
	 * @param duration
	 *            动画执行时长
	 */
	public void startAnimation(final ViewGroup parent, int scnHeight,
			final int duration) {
		ObjectAnimator animator = ObjectAnimator.ofFloat(BouncingBall.this,
				"translationY", 0,
				scnHeight - this.getOrigY() - this.getRadius(), 0);
		animator.setDuration(duration);
		animator.start();

		animator.addUpdateListener(new AnimatorUpdateListener() {
			private float alpha;
			private boolean flag;
			private static final int MAX_OPACITY = 25;

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				alpha = (float) animation.getAnimatedValue();
				//弹球要上升后即将到达最高点才能渐淡
				if (alpha > MAX_OPACITY)
					flag = true;
				if (alpha < MAX_OPACITY && flag) {
					paint.setAlpha((int) (alpha * 255 / MAX_OPACITY));
				}
			}
		});
		animator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				//释放内存
				animation = null;
				parent.removeView(BouncingBall.this);
			}
		});
	}
}