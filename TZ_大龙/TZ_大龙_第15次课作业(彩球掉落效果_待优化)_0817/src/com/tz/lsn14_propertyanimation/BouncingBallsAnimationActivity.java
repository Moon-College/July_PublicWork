package com.tz.lsn14_propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.tz.lsn15_propertyanimation.R;

public class BouncingBallsAnimationActivity extends Activity implements OnTouchListener{

    private LinearLayout ll;
    
    private int[] COLOR = { 
    						0xFF000000,
				    		0xFF444444,
				    		0xFF888888,
				    		0xFFCCCCCC,
				    		0xFFFFFFFF,
				    		0xFFFF0000,
				    		0xFF0000FF,
				    		0xFFFFFF00,
				    		0xFF00FFFF,
				    		0xFFFF00FF};
    private final int RADIUS = 50;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bouncing_balls);
        ll = (LinearLayout)findViewById(R.id.ll);
        
        setRepeatBackgroundColor(ll);
        
//        CircleCanvasView cv = new CircleCanvasView(this, 30, 100, 200,Color.rgb(255, 0, 0));
//        ll.addView(cv);
        
        ll.setOnTouchListener(this);

    }
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
	}

	private void setRepeatBackgroundColor(View view) {
		ObjectAnimator animator = ObjectAnimator.ofInt(view, "backgroundColor", 0XFFFF8080, 0XFF8080FF);
		animator.setRepeatMode(ValueAnimator.REVERSE);
		animator.setRepeatCount(ValueAnimator.INFINITE);
		animator.setEvaluator(new ArgbEvaluator());
		animator.setDuration(3000);
		animator.start();
	}
	
	private void createCircle(float x, float y) {
		
		int height = getWindowManager().getDefaultDisplay().getHeight();
		int rand = (int)(Math.random() * (COLOR.length - 1));
		System.out.println("x:" + x + " y:" + y + " rand:" + rand);
        final CircleCanvasView cv = new CircleCanvasView(this, RADIUS, x, y - RADIUS * 2, COLOR[rand]);
//        ll.addView(cv, Math.max(0, ll.getChildCount() + 1));
        ll.addView(cv);
        
        
		ValueAnimator anim = ValueAnimator.ofFloat(y-cv.getHeight() - cv.getMeasuredHeight(), height, y);
		anim.setTarget(cv);
		anim.setDuration(1000);
		anim.setInterpolator(new AccelerateInterpolator());
		anim.start();
		anim.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				cv.setTranslationY((Float)animation.getAnimatedValue());
			}
		});
		
		anim.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				super.onAnimationEnd(animation);
				if(cv != null) {
					ViewGroup parent = (ViewGroup) cv.getParent();
					parent.removeView(cv);
				}
			}
			
		});
        
        
//        PropertyValuesHolder holder = PropertyValuesHolder.ofFloat("translationY", y, height, y);
//        ValueAnimator vAnimator = ValueAnimator.ofPropertyValuesHolder(holder);
//        vAnimator.setTarget(cv);
//        vAnimator.setInterpolator(new LinearInterpolator());
//        vAnimator.setDuration(1000);
//        vAnimator.start();
//        
//        vAnimator.addListener(new AnimatorListenerAdapter() {
//
//			@Override
//			public void onAnimationEnd(Animator animation) {
//				// TODO Auto-generated method stub
//				super.onAnimationEnd(animation);
//				System.out.println("over");
//				if(cv != null) {
//					ViewGroup parent = (ViewGroup) cv.getParent();
//					parent.removeView(cv);
//				}
//			}
//        	
//		});
        
        
        
	}
	

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX();
		float y = event.getY();
		
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				createCircle(x,y);
				
				break;
			case MotionEvent.ACTION_MOVE:
				if((int)x%5 == 0) {
					createCircle(x,y);
				}
				break;
			case MotionEvent.ACTION_UP:
				
				break;
			default:
				break;
		}
		return true;
	}

	

}
