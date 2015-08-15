package com.tz.mylauncher;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

import com.tz.mylauncher.adapter.LanucherAdapter;

/**
 * 自定义launcher
 * @author 赵建祥
 *
 */
public class MainActivity extends Activity {

	private GridView gv_launcher;
	private List<ResolveInfo> data;
	private PackageManager pm;
	private LanucherAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		//异步加载安装的应用程序
		LauncherTask task=new LauncherTask();
		task.execute(null);
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		gv_launcher = (GridView) findViewById(R.id.gv_launcher);
		//点击打开
		gv_launcher.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ResolveInfo resolveInfo= data.get(position);
				//resolveInfo.activityInfo.packageName
				
				Intent intent=new Intent();
				intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
				startActivity(intent);
				
			}
		});
		//长按缷载
		gv_launcher.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				ResolveInfo resolveInfo= data.get(position);
				Intent intent = new Intent();
			    intent.setAction(Intent.ACTION_DELETE);
			    intent.setData(Uri.parse("package:"+resolveInfo.activityInfo.packageName));
				startActivity(intent);

				return false;
			}

		});

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("jzhao", "onResume......");
		//异步加载安装的应用程序
		LauncherTask task=new LauncherTask();
		task.execute(null);
	}
	
	/**
	 * 异步任务加载安装的应用
	 * @author 赵建祥
	 *
	 */
	class LauncherTask extends AsyncTask<Void, Void, Boolean>{

		@Override
		protected Boolean doInBackground(Void... params) {
			pm = getPackageManager();
			Intent intent = new Intent();
			//意图直向应用程序主入口
			intent.setAction(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			data = pm.queryIntentActivities(intent,0);
			return true;
		}
		
		/**
		 * 应用数据加载成功后，初始化adapter，并将其设置到gridview
		 */
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			adapter=new LanucherAdapter(data, MainActivity.this, pm);
			gv_launcher.setAdapter(adapter);
		}
		
	}
}