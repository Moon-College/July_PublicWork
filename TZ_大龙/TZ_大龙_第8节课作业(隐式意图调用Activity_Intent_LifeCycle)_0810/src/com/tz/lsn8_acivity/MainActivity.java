package com.tz.lsn8_acivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final String TAG = "log";
	private TextView tv;
	
	/**
	 * 创建的时候回调的方法
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
        /**
		 * 判断是否是系统干掉了后再创建的Activity
		 * 获得干掉之前保存的数据，恢复
		 */
        
		if(savedInstanceState!=null){
			int progress = savedInstanceState.getInt("progress");
			tv.setText(progress+"");
			String txt = savedInstanceState.getString("txt");
			tv.setText(txt);
		}
    }
    
    public void btnClick(View view) {
    	
    	switch (view.getId()) {
			case R.id.bt_jump:
		    	startActivity(new Intent(this, SecondActivity.class));
				break;
			case R.id.bt_photo:
		    	startActivity(new Intent(this, PhotoActivity.class));
				break;
			case R.id.bt_callActivity:
				//隐式意图调用 Activity
				Intent intent = new Intent();
				intent.setAction("ACTION_ABC");
		    	startActivity(intent);
				break;
	
			default:
				break;
		}
    	
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    	// TODO Auto-generated method stub
    	super.onWindowFocusChanged(hasFocus);
    	Log.i(TAG, "  ============onWindowFocusChanged");
    }

	/**	当我们的activity界面可见的时候
	 * (比如：初始化摄像头、播放器等；数据库连接；gps定位打开等)
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "  ============onStart");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(TAG, "  ============onRestart");
	}
	
	/**
	 * 界面获得焦点(前台)--可以与用户进行交互
	 * for your activity to start interacting with the user. 
	 * This is a good place to begin animations, 
	 * open exclusive-access devices (such as the camera), etc. 
	 * onResume里面可以：开启动画；开启一些系统设备，比如相机、音乐播放等。
	 * (一般在做项目的时候，恢复在onPause里面保存的数据)
	 * 
	 * Keep in mind that onResume is not the best indicator that your activity is visible to the user; a system window such as the keyguard may be in front. Use onWindowFocusChanged to know for certain that your activity is visible to the user 
	 * (for example, to resume a game). 恢复游戏的时候应该在onWindowFocusChanged里面写
	 * 看onResume的api就知道，真正获得焦点的是onWindowFocusChanged里面
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "  ============onResume");
	}
	
	//===============Running 运行出来了================
	/**
	 * 	当Activity失去焦点
	 * 	(一般在做项目的时候，比如游戏需要暂停、保存一些游戏进度等数据)
	 *	保存数据到哪里？自己想办法(本地文件里面、数据库、共享首选项等等)
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG, "  ============onPause");
	}
	
	/**Activity不可见的时候
	 * （一般做项目这个里面大部分时候保存数据不是在这里，而是在onPause里面）
	 * Note that this method may never be called, in low memory situations where the system does not have enough memory to keep your activity's process running after its onPause method is called. 
	 * 使用场景：用于Activity不可见的时候保存一些特殊数据及回收部分资源
	 * (比如：关闭摄像头、播放器等；数据库连接；gps定位关闭)
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "  ============onStop");
	}

	/**
	 * 可以确定Activity要byebye了
	 * 可以在此方法里面释放一些之前不好释放的资源,避免内存泄露
	 * 1.释放一些对象；xxobj = null；xxxBlueTooth.close();io.close();
	 * 2.关闭一些线程；（Activity退出了，里面的线程还在）
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "  ============onStart");
	}
	
	/** 系统在干掉你之前会调用onSaveInstanceState(Bundle bundle)
	 * 	在被干掉之前保存数据
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	/**
	 * 这函数在onStart 后面 回调 ，进对数据进行保存到Bundle，保证下次创建的时候可以取到数据
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		if(savedInstanceState!=null){
			int progress = savedInstanceState.getInt("progress");
			tv.setText(progress+"");
			String txt = savedInstanceState.getString("txt");
			tv.setText(txt);
		}
	}


}
