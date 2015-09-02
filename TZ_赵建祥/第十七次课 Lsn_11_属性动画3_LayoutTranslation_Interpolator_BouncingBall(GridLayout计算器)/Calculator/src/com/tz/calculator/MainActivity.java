package com.tz.calculator;

import com.tz.calculator.util.DensityUtil;

import android.os.Bundle;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private EditText et_result;
	private LinearLayout ll_result;
	private ImageView iv_pull;
	private GridLayout gl_btns;
	
	private boolean isShow=true;
	private RelativeLayout rl_pull;
	
	private float container_height=0;
	private RelativeLayout rl_container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		container_height=rl_container.getHeight();
	}

	private void initView() {
		rl_container=(RelativeLayout) findViewById(R.id.rl_container);
		et_result = (EditText) findViewById(R.id.et_result);
		ll_result=(LinearLayout) findViewById(R.id.ll_result);
		rl_pull = (RelativeLayout) findViewById(R.id.rl_pull);
		iv_pull = (ImageView) findViewById(R.id.iv_pull);
		gl_btns = (GridLayout) findViewById(R.id.gl_btns);
		
		int childCount=gl_btns.getChildCount();
		for(int i=0;i<childCount;i++){
			Button btn=(Button) gl_btns.getChildAt(i);
			
			btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Button t=(Button)v;
					et_result.setText(t.getText());
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * 切换View
	 * @param v
	 */
	public void cutView(View v){
		//开始键盘是显示的
		if(isShow){
			//切换控件所在布局的高度
			float rl_pull_height=rl_pull.getHeight();
			//顶部高度变大
			float scaleY=(container_height-2*DensityUtil.dip2px(this, 10f)-rl_pull_height)/ll_result.getHeight();
			
			ll_result.setPivotX(0f);
			ll_result.setPivotY(0f);
			ValueAnimator animator1=ObjectAnimator.ofFloat(ll_result, "scaleY", 1f,scaleY);
			animator1.setDuration(300);
			//切换控件所在布局，向下平移
			ValueAnimator animator2=ObjectAnimator.ofFloat(rl_pull, "translationY", 0,container_height-2*DensityUtil.dip2px(this, 10f)-rl_pull_height-ll_result.getHeight());
			animator2.setDuration(300);
			
			//键盘层向下移出
			ValueAnimator animator3=ObjectAnimator.ofFloat(gl_btns, "translationY", 0,container_height-2*DensityUtil.dip2px(this, 10f)-rl_pull_height-ll_result.getHeight());
			animator3.setDuration(300);
			
			AnimatorSet set=new AnimatorSet();
			set.playTogether(animator1,animator2,animator3);
			set.start();
			iv_pull.setImageDrawable(getResources().getDrawable(R.drawable.draw));
		}
		else{
			
			//切换控件所在布局的高度
			float rl_pull_height=rl_pull.getHeight();
			//顶部高度变大
			float scaleY=(container_height-2*DensityUtil.dip2px(this, 10f)-rl_pull_height)/ll_result.getHeight();
			
			ll_result.setPivotX(0f);
			ll_result.setPivotY(0f);
			ValueAnimator animator1=ObjectAnimator.ofFloat(ll_result, "scaleY", scaleY,1f);
			animator1.setDuration(300);
			//切换控件所在布局，向下平移
			ValueAnimator animator2=ObjectAnimator.ofFloat(rl_pull, "translationY",container_height-2*DensityUtil.dip2px(this, 10f)-rl_pull_height-ll_result.getHeight(),0);
			animator2.setDuration(300);
			
			//键盘层向下移出
			ValueAnimator animator3=ObjectAnimator.ofFloat(gl_btns, "translationY", container_height-2*DensityUtil.dip2px(this, 10f)-rl_pull_height-ll_result.getHeight(),0);
			animator3.setDuration(300);
			
			AnimatorSet set=new AnimatorSet();
			set.playTogether(animator1,animator2,animator3);
			set.start();
			
			iv_pull.setImageDrawable(getResources().getDrawable(R.drawable.pull));
		}
		isShow=!isShow;
		
	}

}
