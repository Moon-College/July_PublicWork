package com.example.tz_kevin_homework5_20150724;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * 
 * @author kevin.li
 * @time 20150724
 * @version 1.0.0
 * @function ��activity�Ļ��࣬�������¼�
 */
public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	/**
	 * 
	 * @param title
	 *            ���ñ���
	 * @param isReturn
	 *            �Ƿ������ؼ�
	 */
	protected void setupTopBaseView(String title, boolean isReturn) {
		// TODO Auto-generated method stub
		this.findViewById(R.id.iv_back).setVisibility(View.VISIBLE);
		this.findViewById(R.id.iv_back).setOnClickListener(baseListener);
		((TextView) this.findViewById(R.id.tv_title)).setText(title);
		if (!isReturn) {
			this.findViewById(R.id.iv_back).setVisibility(View.INVISIBLE);
		}
	}

	/**
	 * �������ذ�ť
	 */
	protected OnClickListener baseListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();// �رյ�ǰҳ��
		}
	};

	/**
	 * Activity��ת
	 * 
	 * @param cls
	 */
	protected void goIntent(Class<?> cls) {
		goIntent(this, cls);
	}

	/**
	 * Activity��ת
	 * 
	 * @param context
	 * @param cls
	 */
	protected void goIntent(Context context, Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(context, cls);
		context.startActivity(intent);
	}

	/**
	 * �����Ļ����ط��رռ���
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		try {
			InputMethodManager manager = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
			manager.hideSoftInputFromWindow(getCurrentFocus()
					.getApplicationWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return super.onTouchEvent(event);
	}
}
