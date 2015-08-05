package com.tz.systempopupwindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 桌面悬浮按钮
 * @author 赵建祥
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//启动服务，没隔500毫秒，判断是否在桌面，并显示按钮
		Intent service = new Intent();
		service.setClass(this, PopUpService.class);
		service.putExtra("displayWH", getDisplayWidthAndHeight());
		startService(service);
		//服务启动后，将自己关闭
		this.finish();
	}

	/**
	 * 获取屏幕宽高
	 * @return 
	 */
	public int[] getDisplayWidthAndHeight() {
		int width = getWindowManager().getDefaultDisplay().getWidth();
		int height = getWindowManager().getDefaultDisplay().getHeight();
		return new int[] { width, height };
	}

}
