package com.tz.fileexplorer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

import com.tz.fileexplorer.adapter.FileRecorder;
import com.tz.fileexplorer.fragment.InfoFrag;
import com.tz.fileexplorer.fragment.MainFrag;
import com.tz.fileexplorer.fragment.SecondFrag;
import com.tz.fileexplorer.fragment.ThirdFrag;

/**
 * 
 * @author wztscau
 * @lastedit 2015年9月2日
 *
 */
public class MainActivity extends BaseActivity implements
		OnCheckedChangeListener {

	private static final String TAG = "MainActivity";
	private static final String TAG_MAIN = "main";
	private static final String TAG_SECOND = "second";
	private static final String TAG_THIRD = "third";
	private static final String TAG_FOURTH = "info";
	private Fragment mainFrag;
	private Fragment fourthFrag;
	private Fragment thirdFrag;
	private Fragment secondFrag;
	private int tabId = R.id.rb1;
	private FragmentManager fManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		getRadioGroup().setOnCheckedChangeListener(this);
		fManager = getFragmentManager();
		onChangeFragment(tabId);
	}

	private void init() {
		setTitle("文件浏览器", Color.BLUE);
		setTitleBarColor(Color.rgb(200, 200, 200));
		setTabText("文件", "好友", "外星人", "信息");
		setTabTextColor(Color.BLACK);
		hideMoreButton(true);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		onChangeFragment(checkedId);
		tabId = checkedId;
	}

	/**
	 * 设置底部按钮被选中时的颜色
	 * 
	 * @param color
	 * @param checkId
	 */
	private void setBGColor(int color, int checkId) {
		for (int i = 1; i <= 4; i++) {
			getRadioButton(i).setBackgroundColor(Color.TRANSPARENT);
			if (checkId == i) {
				getRadioButton(i).setBackgroundColor(color);
			}
		}
	}
	
	/**
	 * 监听底部按钮切换的动作
	 * 
	 * @param checkId
	 */
	private void onChangeFragment(int checkId) {
		final int color = Color.GREEN;
		FragmentTransaction ft = fManager.beginTransaction();
		mainFrag = fManager.findFragmentByTag(TAG_MAIN);
		secondFrag = fManager.findFragmentByTag(TAG_SECOND);
		thirdFrag = fManager.findFragmentByTag(TAG_THIRD);
		fourthFrag = fManager.findFragmentByTag(TAG_FOURTH);
		if (mainFrag == null) {
			mainFrag = new MainFrag();
			ft.add(R.id.container, mainFrag, TAG_MAIN);
		}
		if (secondFrag == null) {
			secondFrag = new SecondFrag();
			ft.add(R.id.container, secondFrag, TAG_SECOND);
		}
		if (thirdFrag == null) {
			thirdFrag = new ThirdFrag();
			ft.add(R.id.container, thirdFrag, TAG_THIRD);
		}
		if (fourthFrag == null) {
			fourthFrag = new InfoFrag();
			ft.add(R.id.container, fourthFrag, TAG_FOURTH);
		}
		switch (checkId) {
		case R.id.rb1:
			hideFragments(ft, secondFrag, thirdFrag, fourthFrag);
			ft.show(mainFrag);
			hideMoreButton(true);
			setBGColor(color, 1);
			break;
		case R.id.rb2:
			hideFragments(ft, mainFrag, thirdFrag, fourthFrag);
			ft.show(secondFrag);
			hideMoreButton(true);
			setBGColor(color, 2);
			break;
		case R.id.rb3:
			hideFragments(ft, mainFrag, secondFrag, fourthFrag);
			ft.show(thirdFrag);
			hideMoreButton(false);
			setBGColor(color, 3);
			break;
		case R.id.rb4:
			hideFragments(ft, mainFrag, secondFrag, thirdFrag);
			ft.show(fourthFrag);
			hideMoreButton(true);
			setBGColor(color, 4);
			break;
		}
		ft.commit();
	}

	/**
	 * 选择隐藏哪些fragment
	 * 
	 * @param ft
	 *            fragment事务
	 * @param fs
	 *            多个fragment
	 */
	private static void hideFragments(FragmentTransaction ft, Fragment... fs) {
		for (Fragment f : fs) {
			if (f != null) {
				ft.hide(f);
			}
		}
	}

	@Override
	public void onBackPressed() {
		switch (tabId) {
		case R.id.rb1:
			((MainFrag) mainFrag).onBackPressed();
			break;
		case R.id.rb2:
		case R.id.rb3:
		case R.id.rb4:
			finish();
			break;
		}
	}

}
