package com.tz.lsn11_animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * View Animation（tweened animation） 补间动画
 * 高仿QQ 抖动
 * @author dallon2
 *
 */
public class ShakeAnimationActivity extends Activity {
	
    private ImageView img_anim;
	private Animation animation;
	private int animaton_state = 0;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_qqshake_activity);
        
        /**
         * QQ 抖动动画
         */
        //换到图片控件
        img_anim = (ImageView) findViewById(R.id.img_anim);
        animation = AnimationUtils.loadAnimation(this, R.anim.qqshake_anim);
        //启动动画
        img_anim.startAnimation(animation);
        animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				animaton_state = 1;
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				animaton_state = 1;
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				animaton_state = 0;
			}
		});
        
    }
	
	public void clickBtn(View view) {
		if(animaton_state != 1) {
			img_anim.startAnimation(animation);
		}
	}
    

}
