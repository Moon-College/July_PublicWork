package com.tz.lsn11_animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * View Animation��tweened animation�� ���䶯��
 * ����
 * @author dallon2
 *
 */
public class SetAnimationActivity extends Activity {
	
    private ImageView img_anim;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_set_activity);
        
        //����ͼƬ�ؼ�
        img_anim = (ImageView) findViewById(R.id.img_anim);
        
        //���ض���  ����������
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.set_anim);
        
        //��������
        img_anim.startAnimation(animation);
    }
    

}
