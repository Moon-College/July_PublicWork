package com.tz.mytoast;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 自定义Toast、模Toast实现流氓广告
 * @author Administrator
 *
 */
public class MainActivity extends Activity {

	private WindowManager mWM;
	private Boolean isShow = false;//流氓toast是否显示，默认不显示
	private View view;//自定义视图
	private TextView tv_toast;//自定义视图片提示文本

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 加载自定义布局
		LayoutInflater inflater = LayoutInflater.from(this);
		view = inflater.inflate(R.layout.mytoast, null);
		tv_toast = (TextView) view.findViewById(R.id.tv_toast);
	}

	/**
	 * 显示自定义Toast
	 * 
	 * @param v
	 */
	public void show(View v) {
		view.setBackgroundColor(Color.RED);
		tv_toast.setText("我的自定义Toast");
		Toast toast = new Toast(this);
		// 加载视图
		toast.setView(view);
		toast.show();
	}

	/**
	 * 显示流氓Toast
	 * 
	 * @param v
	 */
	public void showIndecency(View v) {
		if (!isShow) {
			//注意context，使用getApplicationContext，使toast在桌面也可显示
			mWM = (WindowManager) getApplicationContext()
					.getSystemService(this.WINDOW_SERVICE);
			WindowManager.LayoutParams params = new WindowManager.LayoutParams();
			params.height = WindowManager.LayoutParams.WRAP_CONTENT;
			params.width = WindowManager.LayoutParams.WRAP_CONTENT;
			params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
					| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
					| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
			params.format = PixelFormat.TRANSLUCENT;
			params.type = WindowManager.LayoutParams.TYPE_TOAST;
			params.setTitle("流氓Toast");
			
			params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
            if ((params.gravity & Gravity.HORIZONTAL_GRAVITY_MASK) == Gravity.FILL_HORIZONTAL) {
            	params.horizontalWeight = 1.0f;
            }
            if ((params.gravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.FILL_VERTICAL) {
            	params.verticalWeight = 1.0f;
            }
            params.x = 0;
            params.y = 0;
			tv_toast.setText("我是流氓Toast");
			mWM.addView(view, params);
		}
		isShow=true;

	}

	/**
	 * 隐藏流氓Toast
	 * 
	 * @param v
	 */
	public void hideIndecency(View v) {
		if (view != null) {
            if (view.getParent() != null) {
                mWM.removeView(view);
            }
        }
		isShow=false;
	}

}
