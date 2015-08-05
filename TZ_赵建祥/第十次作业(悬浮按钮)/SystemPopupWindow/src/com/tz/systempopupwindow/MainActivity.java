package com.tz.systempopupwindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * ����������ť
 * @author �Խ���
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//��������û��500���룬�ж��Ƿ������棬����ʾ��ť
		Intent service = new Intent();
		service.setClass(this, PopUpService.class);
		service.putExtra("displayWH", getDisplayWidthAndHeight());
		startService(service);
		//���������󣬽��Լ��ر�
		this.finish();
	}

	/**
	 * ��ȡ��Ļ���
	 * @return 
	 */
	public int[] getDisplayWidthAndHeight() {
		int width = getWindowManager().getDefaultDisplay().getWidth();
		int height = getWindowManager().getDefaultDisplay().getHeight();
		return new int[] { width, height };
	}

}
