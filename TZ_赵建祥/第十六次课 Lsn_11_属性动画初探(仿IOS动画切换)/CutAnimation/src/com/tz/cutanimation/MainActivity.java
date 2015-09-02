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
 * 仿IOS控件切换效果
 * @author 赵建祥
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

		//添加监听获取控件的高度（onWindowFocuseChange也应该可以）
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
	 * 初始化控件
	 */
	private void initView() {
		ll_top = (LinearLayout) findViewById(R.id.ll_top);
		ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);
		
		bt_top=(Button) ll_top.findViewById(R.id.bt_top);
		bt_bottom = (Button) ll_bottom.findViewById(R.id.bt_bottom);
	}

	public void showAnimation(View v) {
		//点击完显示按钮后，让其不可再点击
		bt_top.setClickable(false);
		// top X轴缩放
		ValueAnimator animator1 = ObjectAnimator.ofFloat(ll_top, "scaleX",
				1.0F, 0.8F);
		animator1.setDuration(300);
		// top Y轴缩放
		ValueAnimator animator2 = ObjectAnimator.ofFloat(ll_top, "scaleY",
				1.0F, 0.8F);
		animator2.setDuration(300);
		// top 旋转15度
		ValueAnimator animator3 = ObjectAnimator.ofFloat(ll_top, "rotationX",
				0F, 15F);
		animator3.setDuration(300);

		// top 再反向旋转15度
		ValueAnimator animator4 = ObjectAnimator.ofFloat(ll_top, "rotationX",
				15F, 0F);
		animator4.setDuration(300);
		animator4.setStartDelay(300);

		// top 由于默认缩放位置是中心点，所以缩放后的小图片会在屏幕中心，与项部有一定距离，故将其向上移动topHeight/10f
		ValueAnimator animator5 = ObjectAnimator.ofFloat(ll_top,
				"translationY", 0, -topHeight * 0.1f);
		animator5.setDuration(300);
		animator5.setStartDelay(300);

		// top 变成半透明
		ValueAnimator animator6 = ObjectAnimator.ofFloat(ll_top, "alpha", 1f,
				0.5f);
		animator6.setDuration(300);
		animator6.setStartDelay(300);

		animator6.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				//top动画执行完，将隐藏的bottom显示出来
				ll_bottom.setVisibility(View.VISIBLE);
			}
		});

		// bottom 从下面移入
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
		//点击完隐藏按钮后，让显示按钮可以再点击
		bt_top.setClickable(true);
		// bottom 从下面移出
		ValueAnimator animator11 = ObjectAnimator.ofFloat(ll_bottom,
				"translationY", 0f, bottomHeight);
		animator11.setDuration(100);
		animator11.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				//bottom动画执行完后隐藏
				ll_bottom.setVisibility(View.INVISIBLE);

			}
		});

		// top 由半透明变成不透明
		ValueAnimator animator22 = ObjectAnimator.ofFloat(ll_top, "alpha",
				0.5f, 1f);
		animator22.setDuration(300);

		// top 由(-topHeight * 0.1f)移回了原来位置
		ValueAnimator animator33 = ObjectAnimator.ofFloat(ll_top,
				"translationY", -topHeight * 0.1f, 0f);
		animator33.setDuration(300);

		// top 旋转15度
		ValueAnimator animator44 = ObjectAnimator.ofFloat(ll_top, "rotationX",
				0F, 15F);
		animator44.setDuration(300);

		// top 反向旋转15度
		ValueAnimator animator55 = ObjectAnimator.ofFloat(ll_top, "rotationX",
				15F, 0F);
		animator55.setDuration(300);
		animator55.setStartDelay(300);

		// top Y轴缩放
		ValueAnimator animator66 = ObjectAnimator.ofFloat(ll_top, "scaleY",
				0.8F, 1.0F);
		animator66.setDuration(300);
		animator66.setStartDelay(300);

		// top X轴缩放
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
