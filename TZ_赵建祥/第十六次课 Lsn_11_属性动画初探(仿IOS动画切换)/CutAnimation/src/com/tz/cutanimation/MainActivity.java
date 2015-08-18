package com.tz.cutanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * ��IOS�ؼ��л�Ч��
 * @author �Խ���
 *
 */
public class MainActivity extends Activity {

	private LinearLayout ll_top;
	private Button bt_top;
	private LinearLayout ll_bottom;
	private Button bt_bottom;
	private float topHeight;
	private float bottomHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initView();

		//��Ӽ�����ȡ�ؼ��ĸ߶ȣ�onWindowFocuseChangeҲӦ�ÿ��ԣ�
		ll_top.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						topHeight = ll_top.getHeight();
						ll_top.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
					}
				});
		ll_bottom.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						bottomHeight = ll_bottom.getHeight();
					}
				});
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		ll_top = (LinearLayout) findViewById(R.id.ll_top);
		ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);
		
		bt_top=(Button) ll_top.findViewById(R.id.bt_top);
		bt_bottom = (Button) ll_bottom.findViewById(R.id.bt_bottom);
	}

	public void showAnimation(View v) {
		//�������ʾ��ť�����䲻���ٵ��
		bt_top.setClickable(false);
		// top X������
		ValueAnimator animator1 = ObjectAnimator.ofFloat(ll_top, "scaleX",
				1.0F, 0.8F);
		animator1.setDuration(300);
		// top Y������
		ValueAnimator animator2 = ObjectAnimator.ofFloat(ll_top, "scaleY",
				1.0F, 0.8F);
		animator2.setDuration(300);
		// top ��ת15��
		ValueAnimator animator3 = ObjectAnimator.ofFloat(ll_top, "rotationX",
				0F, 15F);
		animator3.setDuration(300);

		// top �ٷ�����ת15��
		ValueAnimator animator4 = ObjectAnimator.ofFloat(ll_top, "rotationX",
				15F, 0F);
		animator4.setDuration(300);
		animator4.setStartDelay(300);

		// top ����Ĭ������λ�������ĵ㣬�������ź��СͼƬ������Ļ���ģ������һ�����룬�ʽ��������ƶ�topHeight/10f
		ValueAnimator animator5 = ObjectAnimator.ofFloat(ll_top,
				"translationY", 0, -topHeight * 0.1f);
		animator5.setDuration(300);
		animator5.setStartDelay(300);

		// top ��ɰ�͸��
		ValueAnimator animator6 = ObjectAnimator.ofFloat(ll_top, "alpha", 1f,
				0.5f);
		animator6.setDuration(300);
		animator6.setStartDelay(300);

		animator6.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				//top����ִ���꣬�����ص�bottom��ʾ����
				ll_bottom.setVisibility(View.VISIBLE);
			}
		});

		// bottom ����������
		ValueAnimator animator7 = ObjectAnimator.ofFloat(ll_bottom,
				"translationY", bottomHeight, 0f);
		animator7.setDuration(100);
		animator7.setStartDelay(550);

		AnimatorSet set = new AnimatorSet();
		set.playTogether(animator1, animator2, animator3, animator4, animator5,
				animator6, animator7);

		set.start();
	}

	public void hideAnimation(View v) {
		//��������ذ�ť������ʾ��ť�����ٵ��
		bt_top.setClickable(true);
		// bottom �������Ƴ�
		ValueAnimator animator11 = ObjectAnimator.ofFloat(ll_bottom,
				"translationY", 0f, bottomHeight);
		animator11.setDuration(100);
		animator11.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				//bottom����ִ���������
				ll_bottom.setVisibility(View.INVISIBLE);

			}
		});

		// top �ɰ�͸����ɲ�͸��
		ValueAnimator animator22 = ObjectAnimator.ofFloat(ll_top, "alpha",
				0.5f, 1f);
		animator22.setDuration(300);

		// top ��(-topHeight * 0.1f)�ƻ���ԭ��λ��
		ValueAnimator animator33 = ObjectAnimator.ofFloat(ll_top,
				"translationY", -topHeight * 0.1f, 0f);
		animator33.setDuration(300);

		// top ��ת15��
		ValueAnimator animator44 = ObjectAnimator.ofFloat(ll_top, "rotationX",
				0F, 15F);
		animator44.setDuration(300);

		// top ������ת15��
		ValueAnimator animator55 = ObjectAnimator.ofFloat(ll_top, "rotationX",
				15F, 0F);
		animator55.setDuration(300);
		animator55.setStartDelay(300);

		// top Y������
		ValueAnimator animator66 = ObjectAnimator.ofFloat(ll_top, "scaleY",
				0.8F, 1.0F);
		animator66.setDuration(300);
		animator66.setStartDelay(300);

		// top X������
		ValueAnimator animator77 = ObjectAnimator.ofFloat(ll_top, "scaleX",
				0.8F, 1.0F);
		animator77.setDuration(300);
		animator77.setStartDelay(300);

		AnimatorSet set = new AnimatorSet();
		set.playTogether(animator11, animator22, animator33, animator44,
				animator55, animator66, animator77);
		set.start();
	}

}
