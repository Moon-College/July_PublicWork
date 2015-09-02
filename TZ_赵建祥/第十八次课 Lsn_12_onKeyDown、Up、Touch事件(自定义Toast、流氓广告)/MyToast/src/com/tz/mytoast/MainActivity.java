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
 * �Զ���Toast��ģToastʵ����å���
 * @author Administrator
 *
 */
public class MainActivity extends Activity {

	private WindowManager mWM;
	private Boolean isShow = false;//��åtoast�Ƿ���ʾ��Ĭ�ϲ���ʾ
	private View view;//�Զ�����ͼ
	private TextView tv_toast;//�Զ�����ͼƬ��ʾ�ı�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// �����Զ��岼��
		LayoutInflater inflater = LayoutInflater.from(this);
		view = inflater.inflate(R.layout.mytoast, null);
		tv_toast = (TextView) view.findViewById(R.id.tv_toast);
	}

	/**
	 * ��ʾ�Զ���Toast
	 * 
	 * @param v
	 */
	public void show(View v) {
		view.setBackgroundColor(Color.RED);
		tv_toast.setText("�ҵ��Զ���Toast");
		Toast toast = new Toast(this);
		// ������ͼ
		toast.setView(view);
		toast.show();
	}

	/**
	 * ��ʾ��åToast
	 * 
	 * @param v
	 */
	public void showIndecency(View v) {
		if (!isShow) {
			//ע��context��ʹ��getApplicationContext��ʹtoast������Ҳ����ʾ
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
			params.setTitle("��åToast");
			
			params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
            if ((params.gravity & Gravity.HORIZONTAL_GRAVITY_MASK) == Gravity.FILL_HORIZONTAL) {
            	params.horizontalWeight = 1.0f;
            }
            if ((params.gravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.FILL_VERTICAL) {
            	params.verticalWeight = 1.0f;
            }
            params.x = 0;
            params.y = 0;
			tv_toast.setText("������åToast");
			mWM.addView(view, params);
		}
		isShow=true;

	}

	/**
	 * ������åToast
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
