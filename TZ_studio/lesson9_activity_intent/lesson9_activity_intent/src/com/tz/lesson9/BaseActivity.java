package com.tz.lesson9;

import java.util.Stack;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class BaseActivity extends Activity {

	private LinearLayout ly_content;
	private Button btn_left;
	private Button btn_right;
	private TextView tv_title;
	private View contentView;

	private static Stack<Activity> activityStack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_title);

		ly_content = (LinearLayout) findViewById(R.id.ly_content);
		btn_left = (Button) findViewById(R.id.btn_left);
		btn_right = (Button) findViewById(R.id.btn_right);
		tv_title = (TextView) findViewById(R.id.tv_title);

		btn_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	// 检查网络是否可用
	public boolean checkNetWorkIsOk() {
		ConnectivityManager connectivity = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null){
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED){
						return true;
					}
		}
		return false;
	}

	protected Button getLeftBtn() {
		return btn_left;
	}

	protected Button getRightBtn() {
		return btn_right;
	}

	protected void setContentLayout(int resId) {
		View contentView = View.inflate(getApplicationContext(), resId, null);
		setContentLayout(contentView);
	}

	protected void setContentLayout(View contentView) {
		this.contentView = contentView;
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		contentView.setLayoutParams(lp);
		if (ly_content != null) {
			ly_content.addView(contentView);
		}
	}

	protected void hideLeftBtn() {
		if (btn_left != null) {
			btn_left.setVisibility(View.INVISIBLE);
		}
	}

	protected void hideRightBtn() {
		if (btn_right != null) {
			btn_right.setVisibility(View.INVISIBLE);
		}
	}

	protected void setTitle(String title) {
		if (btn_right != null) {
			tv_title.setText(title);
		}
	}

	protected View getMyContentView() {
		return contentView;
	}

	protected void setLeftBtnImage(int imgId) {
		if (btn_left != null) {
			btn_left.setBackgroundResource(imgId);
		}
	}

	protected void setRightBtnImage(int imgId) {
		if (btn_right != null) {
			btn_right.setBackgroundResource(imgId);
		}
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);

	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

}
