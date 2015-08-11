package com.tz.lsn11_animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * View Animation（tweened animation） 补间动画
 * 分类一： alpha animation 渐变动画
 * @author dallon2
 *
 */
public class TranslateAnimationActivity extends Activity {
	
    private ImageView img_anim_up;
    private ImageView img_anim_down;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_translate_activity);
        
        /**
         * 向上效果
         */
        //换到图片控件
        img_anim_up = (ImageView) findViewById(R.id.img_anim_up);
        //加载动画  动画工具类
        Animation animation_up = AnimationUtils.loadAnimation(this, R.anim.translate_anim_up);
        //启动动画
        img_anim_up.startAnimation(animation_up);
        
        
        
        /**
         * 向下效果
         */
        //换到图片控件
        img_anim_down = (ImageView) findViewById(R.id.img_anim_down);
        //加载动画  动画工具类
        Animation animation_down = AnimationUtils.loadAnimation(this, R.anim.translate_anim_down);
        //启动动画
        img_anim_down.startAnimation(animation_down);
        
        
    }
    

}
