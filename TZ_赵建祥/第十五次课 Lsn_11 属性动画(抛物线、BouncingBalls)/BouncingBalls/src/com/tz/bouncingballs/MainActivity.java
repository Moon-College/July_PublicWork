package com.tz.bouncingballs;

import java.util.ArrayList;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tz.bouncingballs.util.MyLog;

/**
 * �ο�bouncingballs��ʵ����Ч��
 * 
 * @author �Խ���
 * 
 */
public class MainActivity extends Activity {
	public static float ball_width = 50f;
	public static float ball_height = 50f;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyLog.ISDEBUG = true;
		setContentView(R.layout.main);
		LinearLayout container = (LinearLayout) findViewById(R.id.container);
		container.addView(new MyAnimationView(this));
	}

	public class MyAnimationView extends View {

		private static final int RED = 0xffFF8080;
		private static final int BLUE = 0xff8080FF;
		private static final int CYAN = 0xff80ffff;
		private static final int GREEN = 0xff80ff80;

		public final ArrayList<ShapeHolder> balls = new ArrayList<ShapeHolder>();
		AnimatorSet animation = null;

		public MyAnimationView(Context context) {
			super(context);

			ValueAnimator colorAnim = ObjectAnimator.ofInt(this,
					"backgroundColor", RED, BLUE);
			colorAnim.setDuration(3000);
			colorAnim.setEvaluator(new ArgbEvaluator());
			colorAnim.setRepeatCount(ValueAnimator.INFINITE);
			colorAnim.setRepeatMode(ValueAnimator.REVERSE);
			colorAnim.start();
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			if (event.getAction() != MotionEvent.ACTION_DOWN
					&& event.getAction() != MotionEvent.ACTION_MOVE) {
				return false;
			}
			ShapeHolder newBall = addBall(event.getX(), event.getY());

			// ��ȡС��������ĸ߶�
			float starty = event.getY();
			float h = getHeight();
			float endy = h - newBall.getHeight();
			int duration = (int) (1000 * ((h - starty) / h));
			// ���䶯������
			AnimatorSet dropAnimatorSet = new AnimatorSet();
			// ������
			ValueAnimator dropAnimator = ObjectAnimator.ofFloat(newBall, "y",
					starty, endy);
			dropAnimator.setDuration(duration);
			// ������ѹ��
			// ����ѹ��������X��������С
			float startX = newBall.getX();
			float endX = startX - newBall.getWidth() / 4;
			ValueAnimator compressXAnimator = ObjectAnimator.ofFloat(newBall,
					"x", startX, endX);
			compressXAnimator.setDuration(duration / 4);

			// ����ѹ��������Y���������
			float compressStartY = endy;
			float compressEndY = h - (h - compressStartY) / 2;
			ValueAnimator compressYAnimator = ObjectAnimator.ofFloat(newBall,
					"y", compressStartY, compressEndY);
			compressYAnimator.setDuration(duration / 4);

			// ѹ����ȱ�Ϊ����1/2
			float startW = newBall.getWidth();
			float endW = startW + startW / 2;
			ValueAnimator compressWAnimator = ObjectAnimator.ofFloat(newBall,
					"width", startW, endW);
			compressWAnimator.setDuration(duration / 4);

			// ѹ���߶ȱ�Ϊ1/2
			float startH = newBall.getHeight();
			float endH = startH / 2;
			ValueAnimator compressHAnimator = ObjectAnimator.ofFloat(newBall,
					"height", startH, endH);
			compressHAnimator.setDuration(duration / 4);

			dropAnimatorSet.play(dropAnimator).before(compressWAnimator);
			dropAnimatorSet.play(compressWAnimator).with(compressHAnimator)
					.with(compressXAnimator).with(compressYAnimator);

			// ������������
			AnimatorSet riseAnimatorSet = new AnimatorSet();

			// ����ǰ��С����Ҫ�ָ�ԭ״
			// �߶ȱ��2��
			float drawStartH = ball_height / 2;
			float drawEndH = ball_height;
			ValueAnimator drawHAnimator = ObjectAnimator.ofFloat(newBall,
					"height", drawStartH, drawEndH);
			drawHAnimator.setDuration(duration / 4);

			// ��ȱ�Ϊ2/3
			float drawStartW = ball_width + ball_width / 2;
			float drawEndW = ball_width;
			ValueAnimator drawWAnimator = ObjectAnimator.ofFloat(newBall,
					"width", drawStartW, drawEndW);
			drawWAnimator.setDuration(duration / 4);

			// Y���������С��ԭʼ�߶ȵ�1/2
			float drawStartY = h - ball_height / 2;
			float drawEndY = h - ball_height;
			ValueAnimator drawYAnimator = ObjectAnimator.ofFloat(newBall, "y",
					drawStartY, drawEndY);
			drawYAnimator.setDuration(duration / 4);

			// X����������С��ԭʼ�߶ȵ�1/4
			float drawStartX = event.getX() - 25f - ball_width / 4;
			float drawEndX = event.getX() - 25f;
			ValueAnimator drawXAnimator = ObjectAnimator.ofFloat(newBall, "x",
					drawStartX, drawEndX);
			drawXAnimator.setDuration(duration / 4);

			// ����������
			float riseStartY = h - newBall.getHeight();
			float riseEndY = newBall.getY();
			ValueAnimator riseAnimator = ObjectAnimator.ofFloat(newBall, "y",
					riseStartY, riseEndY);
			riseAnimator.setDuration(duration);

			riseAnimatorSet.play(riseAnimator).after(drawWAnimator);
			riseAnimatorSet.play(drawWAnimator).with(drawHAnimator)
					.with(drawXAnimator).with(drawYAnimator);

			AnimatorSet animatorSet = new AnimatorSet();

			// �����С�򵭳�
			ValueAnimator alphaAnimator = ObjectAnimator.ofFloat(newBall,
					"alpha", 1f, 0f);
			alphaAnimator.setDuration(duration / 4);

			animatorSet.play(dropAnimatorSet).before(riseAnimatorSet);
			animatorSet.play(riseAnimatorSet).before(alphaAnimator);
			animatorSet.start();
			return true;
		}

		private ShapeHolder addBall(float x, float y) {
			OvalShape circle = new OvalShape();
			circle.resize(50f, 50f);
			ShapeDrawable drawable = new ShapeDrawable(circle);
			ShapeHolder shapeHolder = new ShapeHolder(drawable);
			shapeHolder.setX(x - 25f);
			shapeHolder.setY(y - 25f);
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			int color = 0xff000000 | red << 16 | green << 8 | blue;
			Paint paint = drawable.getPaint(); // new
												// Paint(Paint.ANTI_ALIAS_FLAG);
			int darkColor = 0xff000000 | red / 4 << 16 | green / 4 << 8 | blue
					/ 4;
			RadialGradient gradient = new RadialGradient(37.5f, 12.5f, 50f,
					color, darkColor, Shader.TileMode.CLAMP);
			paint.setShader(gradient);
			shapeHolder.setPaint(paint);
			balls.add(shapeHolder);
			return shapeHolder;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			//��ѭ����͸����Ϊ0���Ƴ�
			ArrayList<ShapeHolder> removeList = new ArrayList<ShapeHolder>();
			for (int i = 0; i < balls.size(); ++i) {
				if (balls.get(i).getAlpha() == 0) {
					removeList.add(balls.get(i));
				}
			}
			balls.removeAll(removeList);
			MyLog.i("jzho", "balls.size-->" + balls.size());
			for (int i = 0; i < balls.size(); ++i) {
				ShapeHolder shapeHolder = balls.get(i);
				canvas.save();
				canvas.translate(shapeHolder.getX(), shapeHolder.getY());
				shapeHolder.getShape().draw(canvas);
				canvas.restore();
			}
		}
	}
}