package com.tz.bouncingballs;

import java.util.Random;
import com.tz.bouncingballs.view.BouncingBall;
import android.Manifest.permission;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
/**
 * 
 * @author wztscau
 * @lastedit 2015��8��15��
 *
 */
public class MainActivity extends Activity implements OnTouchListener {

	private RelativeLayout parent;
	private Random random;
	private int scnHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		parent = (RelativeLayout) findViewById(R.id.parent);
		random = new Random();
		parent.setOnTouchListener(this);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		//��������ܵõ��ؼ�������ֵ
		scnHeight = parent.getBottom();
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN
				||event.getAction()==MotionEvent.ACTION_MOVE){
			
		float cx = event.getX();
		float cy = event.getY();
		
		//ÿ�δ���һ��bouncing ball����
		final BouncingBall ball = new BouncingBall(this, cx, cy, 30f,
				random.nextInt(BouncingBall.colorLen));
		//�����������ball����
		parent.addView(ball);
		ball.startAnimation(parent, scnHeight, 3000);
		}
		return true;
	}

}
