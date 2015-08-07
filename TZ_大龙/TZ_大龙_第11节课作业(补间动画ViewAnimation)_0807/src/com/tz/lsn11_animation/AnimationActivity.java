package com.tz.lsn11_animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AnimationActivity extends Activity {
	
    private ImageView img_anim;
    
    private AnimationDrawable mAnimationDrawable;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_activity_main);
        
        //换到图片控件
        img_anim = (ImageView) findViewById(R.id.img_anim);
        
        //设置背景资源
        img_anim.setBackgroundResource(R.drawable.frame_anim);
        
        //得到背景，将背景强制转化成帧动画
        mAnimationDrawable = (AnimationDrawable) img_anim.getBackground();
        
        //需求： 进入场景Activity即启动帧动画
    }
    
    @Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
    	//窗体获得焦点
		super.onWindowFocusChanged(hasFocus);
		mAnimationDrawable.start();
	}

	public void fly(View view) {
    	if(!mAnimationDrawable.isRunning()) {
    		mAnimationDrawable.start();
    	}
    }
    public void stop(View view) {
    	if(mAnimationDrawable.isRunning()) {
    		mAnimationDrawable.stop();
    	}
    }


}
