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
        
        //����ͼƬ�ؼ�
        img_anim = (ImageView) findViewById(R.id.img_anim);
        
        //���ñ�����Դ
        img_anim.setBackgroundResource(R.drawable.frame_anim);
        
        //�õ�������������ǿ��ת����֡����
        mAnimationDrawable = (AnimationDrawable) img_anim.getBackground();
        
        //���� ���볡��Activity������֡����
    }
    
    @Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
    	//�����ý���
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
