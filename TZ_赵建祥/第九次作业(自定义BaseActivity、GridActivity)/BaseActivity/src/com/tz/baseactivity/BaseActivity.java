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

	private Button bt_back;// 返回按钮
	private Button bt_more;// 更多按钮
	private TextView tv_title;// 标题
	private LinearLayout ll_base;// 最外层的线性大布局
	private LayoutInflater inflater;
	private View baseContent;// 内容
	public static int netState = 0;// 0：没有网络 1:2g 2:3g 3:4G 4:wifi
	public static String[] NET_STATE = { "无网络", "2G", "3G", "4G", "WIFI" };

	private NetworkChangeReceiver networkChangeReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflater = LayoutInflater.from(this);
		// 将带标题的布局加载进来
		setContentView(R.layout.base_main);
		// 初始化控件
		initView();

		// 网络状态发生变化时，更新网络状态
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		networkChangeReceiver = new NetworkChangeReceiver();
		registerReceiver(networkChangeReceiver, intentFilter);
		//先主动获取一次
		netState=getNetState();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(networkChangeReceiver);
	}

	/**
	 * 根据view 设置内容
	 * 
	 * @param baseContent
	 *            需要添加到BaseActivity的控件
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
	 * 根据资源ID,设置内容
	 * 
	 * @param baseContent
	 *            需要添加到BaseActivity的控件
	 */
	public void setBaseContentView(int resoureId) {
		View view = inflater.inflate(resoureId, null);
		if (view != null) {
			setBaseContentView(view);
		}
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		ll_base = (LinearLayout) findViewById(R.id.ll_base);
		bt_back = (Button) findViewById(R.id.bt_back);
		bt_more = (Button) findViewById(R.id.bt_more);
		tv_title = (TextView) findViewById(R.id.tv_title);
	}

	/**
	 * 设置baseContent背景
	 */
	public void setBaseContentBackGroundColor(int color) {
		if (baseContent != null) {
			this.baseContent.setBackgroundColor(color);
		}
	}

	/**
	 * 近回退出按钮
	 * 
	 * @return
	 */
	public Button getBt_back() {
		return bt_back;
	}

	/**
	 * 近回更多按钮
	 * 
	 * @return
	 */
	public Button getBt_more() {
		return bt_more;
	}

	/**
	 * 获取内容
	 * 
	 * @return
	 */
	public View getBaseContent() {
		return baseContent;
	}

	/**
	 * 设置标题
	 */
	public void setTitle(String title) {
		tv_title.setText(title);
	}

	/**
	 * 获取网络状态
	 * 
	 * @return 0：没有网络 1:2g 2:3g 3:4G 4:wifi
	 */
	public int getNetState() {
		int state = 0;
		// 先判断wifi是否可用
		ConnectivityManager connectivityManager = (ConnectivityManager) this
				.getSystemService(Activity.CONNECTIVITY_SERVICE);
		int activeState = NetUtil.getActiveNetwork(connectivityManager);
		MyLog.i("jzhao", "activeState-->" + activeState);
		if (activeState == 0) {
			return 0;
		} else if (activeState == 1) {
			return 4;
		}
		// 获取手机网络状态
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
			// 网络发生变化时，获取最新的网络状态
			netState = getNetState();
			Toast.makeText(context, NET_STATE[netState], Toast.LENGTH_LONG).show();
		}

	}

}
