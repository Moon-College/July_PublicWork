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
 * View Animation��tweened animation�� ���䶯��
 * ��ʳ�� ��ӭ����
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
         * ����ƽ��Ч�� 
         */
        //����ͼƬ�ؼ�
        img_anim_up = (ImageView) findViewById(R.id.img_msj_up);
        //���ض���  ����������
        Animation animation_up = AnimationUtils.loadAnimation(this, R.anim.msj_translate_up);
        //��������
        img_anim_up.startAnimation(animation_up);
        
        
        /**
         * ����ƽ��Ч��
         */
        //����ͼƬ�ؼ�
        img_anim_down = (ImageView) findViewById(R.id.img_msj_down);
        //���ض���  ����������
        Animation animation_down = AnimationUtils.loadAnimation(this, R.anim.msj_translate_down);
        //��������
        img_anim_down.startAnimation(animation_down);
       
        /**
         * ���䶯�� Ч��
         */
        img_anim_alpha = (ImageView)findViewById(R.id.img_bg);
        Animation animation_alpha = AnimationUtils.loadAnimation(this, R.anim.msj_alpha);
        img_anim_alpha.startAnimation(animation_alpha);
        
        animation_alpha.setAnimationListener(new AnimationListener() {
			
			@Override //������ʼ
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override //�����ظ�
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override //��������
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
