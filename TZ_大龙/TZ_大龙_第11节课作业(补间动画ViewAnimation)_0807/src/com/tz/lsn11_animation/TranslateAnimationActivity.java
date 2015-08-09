package com.tz.lsn11_animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * View Animation��tweened animation�� ���䶯��
 * ����һ�� alpha animation ���䶯��
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
         * ����Ч��
         */
        //����ͼƬ�ؼ�
        img_anim_up = (ImageView) findViewById(R.id.img_anim_up);
        //���ض���  ����������
        Animation animation_up = AnimationUtils.loadAnimation(this, R.anim.translate_anim_up);
        //��������
        img_anim_up.startAnimation(animation_up);
        
        
        
        /**
         * ����Ч��
         */
        //����ͼƬ�ؼ�
        img_anim_down = (ImageView) findViewById(R.id.img_anim_down);
        //���ض���  ����������
        Animation animation_down = AnimationUtils.loadAnimation(this, R.anim.translate_anim_down);
        //��������
        img_anim_down.startAnimation(animation_down);
        
        
    }
    

}
