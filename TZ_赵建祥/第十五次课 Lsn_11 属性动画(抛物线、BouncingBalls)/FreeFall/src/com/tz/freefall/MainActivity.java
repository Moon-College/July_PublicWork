package com.tz.freefall;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv_freefall;
	public int v=100;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        iv_freefall=(ImageView) findViewById(R.id.iv_freefall);
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    	// TODO Auto-generated method stub
    	super.onWindowFocusChanged(hasFocus);;
    	setAnimation();
    }
    
    private void setAnimation(){
    	ValueAnimator valueAnimator=new ValueAnimator();
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setDuration(4000);
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {

			public PointF evaluate(float fraction, PointF startValue,
					PointF endValue) {
				PointF point=new PointF();
				float t=4*fraction;
				float sx=v*t;
				float sy=(float)1/2*50f*t*t;
				point.x=sx;
				point.y=sy;
				return point;
			}
        	
		});
        valueAnimator.start();
        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				PointF point=(PointF) animation.getAnimatedValue();
				iv_freefall.setX(point.x);
				iv_freefall.setY(point.y);
			}
		});
        
        //不知道为什么了加了这个，退出后就会异常
        /*valueAnimator.addListener(new AnimatorListenerAdapter() {
        	@Override
        	public void onAnimationEnd(Animator animation) {
        		// TODO Auto-generated method stub
        		super.onAnimationEnd(animation);
        		ViewGroup parent=(ViewGroup) iv_freefall.getParent();
        		parent.removeView(iv_freefall);
        		
        	}
		});*/

    }
    
}