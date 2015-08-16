package com.tz.lsn11_animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * View Animation（tweened animation） 补间动画
 * 集合
 * @author dallon2
 *
 */
public class SetAnimationActivity extends Activity {
	
    private ImageView img_anim;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_set_activity);
        
        //换到图片控件
        img_anim = (ImageView) findViewById(R.id.img_anim);
        
        //加载动画  动画工具类
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.set_anim);
        
        //启动动画
        img_anim.startAnimation(animation);
    }
    

}
