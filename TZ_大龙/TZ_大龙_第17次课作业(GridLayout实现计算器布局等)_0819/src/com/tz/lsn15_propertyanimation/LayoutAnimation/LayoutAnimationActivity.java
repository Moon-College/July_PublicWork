package com.tz.lsn15_propertyanimation.LayoutAnimation;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.tz.lsn15_propertyanimation.R;

public class LayoutAnimationActivity extends Activity implements OnCheckedChangeListener {private CheckBox cb_appearing;
	private CheckBox cb_change_disappear;
	private CheckBox cb_disappear;
	private CheckBox cb_change_appear;
	private GridLayout mGridLayout;
	private LayoutTransition mTransition;
	private LinearLayout ll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout_animation);

//		LayoutTransition transition = new LayoutTransition();
//		transition.setAnimator(
//				LayoutTransition.APPEARING, //当一个view在viewGroup中出现时，对此view设置的动画
//				transition.getAnimator(LayoutTransition.APPEARING));
//		transition.setAnimator(
//				LayoutTransition.DISAPPEARING, //当一个view在viewGroup中消失时，对此view设置的动画
//				transition.getAnimator(LayoutTransition.DISAPPEARING));
//		transition.setAnimator(
//				//当一个view在viewGroup中出现时，对此view的其他view位置造成影响，对其他的view设置的动画
//				LayoutTransition.CHANGE_APPEARING,
//				transition.getAnimator(LayoutTransition.CHANGE_APPEARING));
//		transition.setAnimator(
//				//当一个view在viewGroup中消失时，对此view的其他view位置造成影响，对其他的view设置的动画
//				LayoutTransition.CHANGE_DISAPPEARING,
//				transition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING));

		initView();
	}

	private void initView() {
		cb_appearing = (CheckBox)findViewById(R.id.checkBox1_APPEARING);
		cb_change_appear = (CheckBox)findViewById(R.id.checkBox2_CHANGE_APPEARING);
		cb_disappear = (CheckBox)findViewById(R.id.checkBox3_DISAPPEARING);
		cb_change_disappear = (CheckBox)findViewById(R.id.checkBox4_CHANGE_DISAPPEARING);
		cb_appearing.setOnCheckedChangeListener(this);
		cb_change_appear.setOnCheckedChangeListener(this);
		cb_disappear.setOnCheckedChangeListener(this);
		cb_change_disappear.setOnCheckedChangeListener(this);

		//网格布局（类似LinearLayout和TableLayout）
		mGridLayout = new GridLayout(this);
		//设置5个列
		mGridLayout.setColumnCount((int)Math.round(getWindowManager().getDefaultDisplay().getWidth()/60));
		//设置4行
//		mGridLayout.setRowCount(4);
		ll = (LinearLayout)findViewById(R.id.ll);
		ll.addView(mGridLayout);

		//默认所有的动画是全开的
		mTransition = new LayoutTransition();
		mGridLayout.setLayoutTransition(mTransition);

	}

	private int value = 0;
	public void addButtons(View v){
		final Button btn = new Button(this);
		btn.setText(++value+"");
		//为了效果好看，插入到第二个位置
		mGridLayout.addView(btn, Math.min(1, mGridLayout.getChildCount()));
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//点击消失
				mGridLayout.removeView(btn);
			}
		});

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		mTransition = new LayoutTransition();
		mTransition.setAnimator(
				LayoutTransition.APPEARING, //当一个view在viewGroup中出现时，对此view设置的动画
				cb_appearing.isChecked()?ObjectAnimator.ofFloat(this, "scaleX", 0, 1):null);
//		cb_appearing.isChecked()?mTransition.getAnimator(LayoutTransition.APPEARING):null);
		mTransition.setAnimator(
				LayoutTransition.DISAPPEARING, //当一个view在viewGroup中消失时，对此view设置的动画
				cb_disappear.isChecked()?mTransition.getAnimator(LayoutTransition.DISAPPEARING):null);
		mTransition.setAnimator(
				//当一个view在viewGroup中出现时，对此view的其他view位置造成影响，对其他的view设置的动画
				LayoutTransition.CHANGE_APPEARING,
				cb_change_appear.isChecked()?mTransition.getAnimator(LayoutTransition.CHANGE_APPEARING):null);
		mTransition.setAnimator(
				//当一个view在viewGroup中消失时，对此view的其他view位置造成影响，对其他的view设置的动画
				LayoutTransition.CHANGE_DISAPPEARING,
				cb_change_disappear.isChecked()?mTransition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING):null);
		mGridLayout.setLayoutTransition(mTransition);
	}
}
