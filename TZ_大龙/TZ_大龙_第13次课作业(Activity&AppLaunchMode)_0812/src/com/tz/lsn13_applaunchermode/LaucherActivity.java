package com.tz.lsn13_applaunchermode;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

public class LaucherActivity extends Activity {
	private GridView gv_launcher;
	private List<ResolveInfo> activities;
	private MyLauncherAdapter adapter;
	private final int MAX_NUM_COLUMNS = 5;
	public final static String ACTION_UNINSTALL = "android.intent.action.PACKAGE_REMOVED";
	private static LaucherActivity mLaucherActivity;
	
	public static LaucherActivity getInstance() {
		if(mLaucherActivity == null) {
			mLaucherActivity = new LaucherActivity();
		}
		return mLaucherActivity;
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		gv_launcher = (GridView)findViewById(R.id.gv_launcher);
		
		ResolveTask task = new ResolveTask();
		task.execute();
		
		gv_launcher.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ResolveInfo resolveInfo = activities.get(position);
				String packageName = resolveInfo.activityInfo.packageName;
				String appName = resolveInfo.activityInfo.name;
				
				Intent intent = new Intent();
				intent.setClassName(packageName, appName);
				startActivity(intent);
			}
		});
		
		gv_launcher.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				//获取长按点击应用App的信息
				ResolveInfo resolveInfo = activities.get(position);
				String packageName = resolveInfo.activityInfo.packageName;
				String appName = resolveInfo.activityInfo.name;
				
				//卸载应用 
				Uri packageURI = Uri.parse("package:" + packageName);
				Intent intent = new Intent(Intent.ACTION_DELETE);
				intent.setData(packageURI);
				startActivity(intent);
				
				return false;
			}
		});
		
	}

	

	public void notifyDataChanged() {
		
		if(adapter != null) {
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					adapter.notifyDataSetChanged();
				}
			});
		}
	}
	
	
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		//用于设置表格的列数 最大为5
		int winWdith = FunctionHelper.getWindowWidth(this);
		int numColumns = (int) Math.floor(winWdith/120);
		gv_launcher.setNumColumns(numColumns >= MAX_NUM_COLUMNS? MAX_NUM_COLUMNS: numColumns);
		

	}
	
	
	class ResolveTask extends AsyncTask<Void, Void, Boolean> {



		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			PackageManager pm = getPackageManager();
			Intent intent = new Intent();
			//intent.setAction("android.intent.action.MAIN");
			intent.setAction(Intent.ACTION_MAIN);
//			intent.addCategory("android.intent.category.LAUNCHER");
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			activities = pm.queryIntentActivities(intent, 0);
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			adapter = new MyLauncherAdapter(LaucherActivity.this, activities);
			
			gv_launcher.setAdapter(adapter);
		}



	}
}
