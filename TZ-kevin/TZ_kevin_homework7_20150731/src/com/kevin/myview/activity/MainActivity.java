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
 * @function �Զ���view����ʾ ����������
 */
public class MainActivity extends Activity {
	/**
	 * �Զ�����������view
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
	 * �������Ŀ���
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		int max = voView.getMax();
		int grayNumber = voView.getGrayNumber();
		switch (keyCode) {
		// ��������
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			// ���°� ������С ��Ӧ��ɫ��ť ��
			grayNumber++;

			if (grayNumber > max) {// ע��ɵ���max
				return super.onKeyDown(keyCode, event);
			} else {
				// ��ֵ �ػ���
				voView.setGrayNumber(grayNumber);
				voView.invalidate();
			}

			break;
		// ��������
		case KeyEvent.KEYCODE_VOLUME_UP:
			// ���ϰ� �������� ��Ӧ��ɫ��ť ��
			grayNumber--;
			if (grayNumber < 0) {// ע��ɵ���0
				return super.onKeyDown(keyCode, event);
			} else {
				// ��ֵ �ػ���
				voView.setGrayNumber(grayNumber);
				voView.invalidate();
			}

			break;
		}
		return super.onKeyDown(keyCode, event);

	}
}
