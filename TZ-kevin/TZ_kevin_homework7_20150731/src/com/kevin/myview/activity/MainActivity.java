package com.kevin.myview.activity;

import com.kevin.view.VolumeView;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * @author kevin.li
 * @version 1.0.0
 * @create 20150731
 * @function 自定义view的显示 音量调节器
 */
public class MainActivity extends Activity {
	/**
	 * 自定义音量控制view
	 */
	private VolumeView voView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		voView = (VolumeView) findViewById(R.id.volumeView1);
	}

	/**
	 * 对音量的控制
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		int max = voView.getMax();
		int grayNumber = voView.getGrayNumber();
		switch (keyCode) {
		// 调低音量
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			// 往下按 音量减小 对应灰色按钮 多
			grayNumber++;

			if (grayNumber > max) {// 注意可等于max
				return super.onKeyDown(keyCode, event);
			} else {
				// 赋值 重绘制
				voView.setGrayNumber(grayNumber);
				voView.invalidate();
			}

			break;
		// 调高音量
		case KeyEvent.KEYCODE_VOLUME_UP:
			// 往上按 音量增大 对应灰色按钮 少
			grayNumber--;
			if (grayNumber < 0) {// 注意可等于0
				return super.onKeyDown(keyCode, event);
			} else {
				// 赋值 重绘制
				voView.setGrayNumber(grayNumber);
				voView.invalidate();
			}

			break;
		}
		return super.onKeyDown(keyCode, event);

	}
}
