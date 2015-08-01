package com.tz.baseactivity;

import com.tz.baseactivity.util.MyLog;
import com.tz.baseactivity.util.NetUtil;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {

	private Button bt_back;// ���ذ�ť
	private Button bt_more;// ���ఴť
	private TextView tv_title;// ����
	private LinearLayout ll_base;// ���������Դ󲼾�
	private LayoutInflater inflater;
	private View baseContent;// ����
	public static int netState = 0;// 0��û������ 1:2g 2:3g 3:4G 4:wifi
	public static String[] NET_STATE = { "������", "2G", "3G", "4G", "WIFI" };

	private NetworkChangeReceiver networkChangeReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflater = LayoutInflater.from(this);
		// ��������Ĳ��ּ��ؽ���
		setContentView(R.layout.base_main);
		// ��ʼ���ؼ�
		initView();

		// ����״̬�����仯ʱ����������״̬
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		networkChangeReceiver = new NetworkChangeReceiver();
		registerReceiver(networkChangeReceiver, intentFilter);
		//��������ȡһ��
		netState=getNetState();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(networkChangeReceiver);
	}

	/**
	 * ����view ��������
	 * 
	 * @param baseContent
	 *            ��Ҫ��ӵ�BaseActivity�Ŀؼ�
	 */
	public void setBaseContentView(View baseContent) {
		if (ll_base != null) {
			this.baseContent = baseContent;
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 0);
			baseContent.setLayoutParams(params);
			ll_base.addView(baseContent);
		}
	}

	/**
	 * ������ԴID,��������
	 * 
	 * @param baseContent
	 *            ��Ҫ��ӵ�BaseActivity�Ŀؼ�
	 */
	public void setBaseContentView(int resoureId) {
		View view = inflater.inflate(resoureId, null);
		if (view != null) {
			setBaseContentView(view);
		}
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		ll_base = (LinearLayout) findViewById(R.id.ll_base);
		bt_back = (Button) findViewById(R.id.bt_back);
		bt_more = (Button) findViewById(R.id.bt_more);
		tv_title = (TextView) findViewById(R.id.tv_title);
	}

	/**
	 * ����baseContent����
	 */
	public void setBaseContentBackGroundColor(int color) {
		if (baseContent != null) {
			this.baseContent.setBackgroundColor(color);
		}
	}

	/**
	 * �����˳���ť
	 * 
	 * @return
	 */
	public Button getBt_back() {
		return bt_back;
	}

	/**
	 * ���ظ��ఴť
	 * 
	 * @return
	 */
	public Button getBt_more() {
		return bt_more;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public View getBaseContent() {
		return baseContent;
	}

	/**
	 * ���ñ���
	 */
	public void setTitle(String title) {
		tv_title.setText(title);
	}

	/**
	 * ��ȡ����״̬
	 * 
	 * @return 0��û������ 1:2g 2:3g 3:4G 4:wifi
	 */
	public int getNetState() {
		int state = 0;
		// ���ж�wifi�Ƿ����
		ConnectivityManager connectivityManager = (ConnectivityManager) this
				.getSystemService(Activity.CONNECTIVITY_SERVICE);
		int activeState = NetUtil.getActiveNetwork(connectivityManager);
		MyLog.i("jzhao", "activeState-->" + activeState);
		if (activeState == 0) {
			return 0;
		} else if (activeState == 1) {
			return 4;
		}
		// ��ȡ�ֻ�����״̬
		TelephonyManager telphonyManager = (TelephonyManager) this
				.getSystemService(Activity.TELEPHONY_SERVICE);
		state = NetUtil.getNetworkClass(telphonyManager);
		if (state != 0) {
			return state;
		}
		return state;
	}

	class NetworkChangeReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// ���緢���仯ʱ����ȡ���µ�����״̬
			netState = getNetState();
			Toast.makeText(context, NET_STATE[netState], Toast.LENGTH_LONG).show();
		}

	}

}
