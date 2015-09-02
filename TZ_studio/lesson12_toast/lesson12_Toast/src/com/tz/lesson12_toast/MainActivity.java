package com.tz.lesson12_toast;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button btn_toast;
	private Button btn_liumang_ad;
	private Button btn_liumang_ad_hiden;
	private WindowManager mWindowManager;
	
	private Boolean isShow = false;//流氓toast是否显示，默认不显示
	private TextView tv_toast;
	private View v;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_toast=(Button) findViewById(R.id.btn_toast);
		btn_liumang_ad=(Button) findViewById(R.id.btn_liumang_ad);
		btn_liumang_ad_hiden=(Button) findViewById(R.id.btn_liumang_ad_hiden);
		v = View.inflate(this, R.layout.custom_toast_activity, null);
		v.setBackgroundColor(Color.BLACK);
		tv_toast = (TextView) v.findViewById(R.id.tv_toast);
	}
	
	
	public void showCustomToast(View view){
		
		tv_toast.setText("我的自定义Toast");
		Toast toast = new Toast(this);
		// 加载视图
		toast.setView(v);
		toast.show();
	}
	
	
	
	/**
	 * 显示流氓Toast
	 * 
	 * @param v
	 */
	public void showLiumangAd(View view) {
		if (!isShow) {
			//注意context，使用getApplicationContext，使toast在桌面也可显示
			mWindowManager = (WindowManager) getApplicationContext()
					.getSystemService(this.WINDOW_SERVICE);
			WindowManager.LayoutParams params = new WindowManager.LayoutParams();
			params.height = WindowManager.LayoutParams.WRAP_CONTENT;
			params.width = WindowManager.LayoutParams.WRAP_CONTENT;
			params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
					| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
					| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
			params.format = PixelFormat.TRANSLUCENT;
			params.type = WindowManager.LayoutParams.TYPE_TOAST;
			params.setTitle("流氓广告Toast");
			
			params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
            if ((params.gravity & Gravity.HORIZONTAL_GRAVITY_MASK) == Gravity.FILL_HORIZONTAL) {
            	params.horizontalWeight = 1.0f;
            }
            if ((params.gravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.FILL_VERTICAL) {
            	params.verticalWeight = 1.0f;
            }
            params.x = 0;
            params.y = 0;
			tv_toast.setText("我是流氓广告Toast");
			mWindowManager.addView(v, params);
		}
		isShow=true;

	}

	/**
	 * 隐藏流氓Toast
	 * 
	 * @param v
	 */
	public void hideIndecency(View view) {
		if (v != null) {
            if (v.getParent() != null) {
                mWindowManager.removeView(v);
            }
        }
		isShow=false;
	}

	
}
