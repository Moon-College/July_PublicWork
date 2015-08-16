package com.mumu.valueanimation;

import android.R.xml;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageView ball;
	private WindowManager wm;
	private float windowHeight;
	private float windowWidht;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ball = (ImageView) this.findViewById(R.id.ball);
		
		wm = (WindowManager)MainActivity.this.getSystemService(Context.WINDOW_SERVICE);
		windowHeight = wm.getDefaultDisplay().getHeight();
		windowWidht = wm.getDefaultDisplay().getWidth();
		
		Log.i("INFO", "windowHeight=" + windowHeight + " ,windowWidht=" +windowWidht);
	}

	public void verticalDrop(View v) {
		
		ValueAnimator animator = new ValueAnimator();
		animator.setFloatValues(0,windowHeight);
		animator.setDuration(1000);
		animator.start();
		animator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float percent = (Float)animation.getAnimatedValue();
				ball.setTranslationY(percent);
			}
		});
		
		animator.addListener(new AnimatorListenerAdapter() {
			
			@Override
			public void onAnimationEnd(Animator animation) {
				//复位
				ball.setX(0);
				ball.setY(0);
				Toast.makeText(MainActivity.this, "End", Toast.LENGTH_SHORT).show();
			}
			
		});

	}

	public void parabolaTranlate(View v) {
		ValueAnimator animator = ValueAnimator.ofFloat(0, 1).setDuration(1500);
		animator.start();
		animator.addUpdateListener(new AnimatorUpdateListener() {
			public void onAnimationUpdate(ValueAnimator animation) {
				float percent = (Float) animation.getAnimatedValue();
				float x = windowWidht * percent;
				float y = (float) (- 0.007 * (x -240) * (x-240) + 240*240*0.007);
				ball.setTranslationX(x);
				ball.setTranslationY(y);
			}
		});
		
		animator.addListener(new AnimatorListener() {
			public void onAnimationStart(Animator animation) { }
			public void onAnimationRepeat(Animator animation) { }
			public void onAnimationCancel(Animator animation) { }
			public void onAnimationEnd(Animator animation) {
				// 复位
				ball.setX(0);
				ball.setY(0);
				Toast.makeText(MainActivity.this, "End", Toast.LENGTH_SHORT).show();
			}
		});

	}

	public void discolorTranlate(View v) {
		//抛物线移动
		parabolaTranlate(null);
		
		ArgbEvaluator evaluator = new ArgbEvaluator();
		ValueAnimator animator = ValueAnimator.ofInt(Color.BLUE, Color.RED, Color.YELLOW).setDuration(1500);
		animator.setEvaluator(evaluator);
		animator.start();
		animator.addUpdateListener(new AnimatorUpdateListener() {
			public void onAnimationUpdate(ValueAnimator animation) {
				int color = (Integer) animation.getAnimatedValue();
				ball.setBackgroundColor(color);
			}
		});
		
	}


}
