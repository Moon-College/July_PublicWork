package com.tz.systempopupwindow;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.view.Gravity;
import android.view.ViewManager;
import android.view.WindowManager;

import com.tz.systempopupwindow.util.DensityUtil;
import com.tz.systempopupwindow.util.Util;

/**
 * ��ʱ�жϵ�ǰ��Ƿ�����ҳ
 * @author �Խ���
 * 
 */
public class PopUpService extends Service {

	private int[] displayWH;// ��Ļ���
	private ViewManager mWindowManager;
	public static boolean isShow = false;// pop��Ť�Ƿ�����ʾ
	public static boolean runFlag = true;//�첽�������б�־
	
	private int btnWidth=60;
	private int btnHeight=40;
	

	@Override
	public IBinder onBind(Intent intent) {

		displayWH = intent.getIntArrayExtra("displayWH");
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// ��ʼ��windowManager
		if (mWindowManager == null) {
			mWindowManager = (WindowManager) getApplicationContext()
					.getSystemService(Context.WINDOW_SERVICE);
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		runFlag = false;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		displayWH = intent.getIntArrayExtra("displayWH");
		
		WindowManager.LayoutParams lps = createPopupLayout();
		MyButton homeButton = new MyButton(getApplicationContext(),
				mWindowManager, lps);
		homeButton.setText("home");
		
		//��һ���첽����ÿ500�����ж�һ�µ�ǰ�Ƿ���ҳ
		UpdatePopTask task = new UpdatePopTask(homeButton);
		task.execute(null, null, null);
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * ������ťWindowManager.LayoutParams����
	 * @return
	 */
	private WindowManager.LayoutParams createPopupLayout() {
		WindowManager.LayoutParams p = new WindowManager.LayoutParams();
		//���ϽǶ���
		p.gravity = Gravity.LEFT | Gravity.TOP;
		p.width = DensityUtil.dip2px(getApplicationContext(), btnWidth);
		p.height = DensityUtil.dip2px(getApplicationContext(), btnHeight);
		p.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		p.type = WindowManager.LayoutParams.TYPE_PHONE;
		//�ð�ť����Ļ�ұ߾�����ʾ
		p.x = displayWH[0]-DensityUtil.dip2px(getApplicationContext(), btnWidth);
		p.y = (displayWH[1]-DensityUtil.dip2px(getApplicationContext(), btnHeight))/2;
		// p.softInputMode = mSoftInputMode;
		p.setTitle("PopupWindow:" + Integer.toHexString(hashCode()));

		return p;
	}

	/**
	 * ����pop��ť����ʾ
	 * 
	 * @author Administrator
	 * 
	 */
	class UpdatePopTask extends AsyncTask<Void, Boolean, Boolean> {

		private MyButton homeButton;// ��ʾ�������button

		public UpdatePopTask(MyButton homeButton) {
			super();
			this.homeButton = homeButton;
		}

		@Override
		protected Boolean doInBackground(Void... params) {

			while (runFlag) {
				// ����ҳ����pop��ťû����ʾ
				if (Util.isHome(PopUpService.this) && !isShow) {
					isShow = true;
					publishProgress(isShow);
				}
				// �������棬������
				else if (!Util.isHome(PopUpService.this) && isShow) {
					isShow = false;
					publishProgress(isShow);
				}
				try {
					Thread.currentThread().sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return isShow;
		}

		@Override
		protected void onProgressUpdate(Boolean... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			if (values[0]) {
				mWindowManager.addView(homeButton, homeButton.getLps());
			} else {
				mWindowManager.removeView(homeButton);
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result) {
				mWindowManager.addView(homeButton, homeButton.getLps());
			} else {
				mWindowManager.removeView(homeButton);
			}
		}

	}

}
