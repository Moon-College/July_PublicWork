package com.tz.lsn11_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * View Animation（tweened animation） 补间动画
 * 美食杰 欢迎界面
 * @author dallon2
 *
 */
public class MSJWelcomeAnimationActivity extends Activity {
	
    
	private ImageView img_anim_up;
	private ImageView img_anim_down;
	private ImageView img_anim_alpha;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_msj_welcome_activity);
        
        /**
         * 向上平移效果 
         */
        //换到图片控件
        img_anim_up = (ImageView) findViewById(R.id.img_msj_up);
        //加载动画  动画工具类
        Animation animation_up = AnimationUtils.loadAnimation(this, R.anim.msj_translate_up);
        //启动动画
        img_anim_up.startAnimation(animation_up);
        
        
        /**
         * 向下平移效果
         */
        //换到图片控件
        img_anim_down = (ImageView) findViewById(R.id.img_msj_down);
        //加载动画  动画工具类
        Animation animation_down = AnimationUtils.loadAnimation(this, R.anim.msj_translate_down);
        //启动动画
        img_anim_down.startAnimation(animation_down);
       
        /**
         * 渐变动画 效果
         */
        img_anim_alpha = (ImageView)findViewById(R.id.img_bg);
        Animation animation_alpha = AnimationUtils.loadAnimation(this, R.anim.msj_alpha);
        img_anim_alpha.startAnimation(animation_alpha);
        
        animation_alpha.setAnimationListener(new AnimationListener() {
			
			@Override //动画开始
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override //动画重复
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override //动画结束
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent();
				intent.setClass(MSJWelcomeAnimationActivity.this, ShakeAnimationActivity.class);
				startActivity(intent);
				
				overridePendingTransition(R.anim.msj_enter_anim, R.anim.msj_exit_anim);
			}
		});
    }
    

}
