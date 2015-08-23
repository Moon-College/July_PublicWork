package com.mumu.customtoast;

import com.mumu.customtoast.R.layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	private WindowManager mWM;
	private LayoutInflater inflater;
	private LinearLayout ll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inflater = LayoutInflater.from(MainActivity.this);
		ll = (LinearLayout)inflater.inflate(R.layout.custom_toast, null);
	}


	/**
	 * 通过WindowManager显示广告View
	 * @param v
	 */
	public void showCustomToast(View v){
		Toast toast = new Toast(MainActivity.this);
		
		toast.setView(ll);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		
		toast.show();
	}
	
	
	/**
	 * 从WindowManager中移出，取消显示
	 * @param v
	 */
	public void showAdvertising (View v) {
		
		mWM = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
		mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		mParams.format = PixelFormat.TRANSLUCENT;
		mParams.type = WindowManager.LayoutParams.TYPE_TOAST;
		mParams.setTitle("Toast");
		mParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
		mParams.gravity = Gravity.CENTER;
		
		mWM.addView(ll, mParams);
	}
	
	public void cancelAdvertising (View v) {
	
		if(mWM != null){
			mWM.removeView(ll);
		}
	}
	
}
