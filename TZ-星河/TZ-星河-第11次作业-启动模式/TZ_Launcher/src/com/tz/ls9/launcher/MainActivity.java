package com.tz.ls9.launcher;

import java.util.ArrayList;
import java.util.List;

import com.example.tz_launcher.R;
import com.tz.ls9.launcher.adapter.AppsAdapter;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {
	private GridView gv;
	private List<ResolveInfo> activitys=new ArrayList<ResolveInfo>();
	private AppsAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gv=(GridView)findViewById(R.id.gv_launcher);
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ResolveInfo resolveinfo=activitys.get(position);
				String className=resolveinfo.activityInfo.name;
				String pn=resolveinfo.activityInfo.packageName;
				Intent intent=new Intent();
				intent.setClassName(pn, className);
				startActivity(intent);
			}
		});
		
		gv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				ResolveInfo resolveinfo=activitys.get(position);
				String pn=resolveinfo.activityInfo.packageName;
				Intent intent=new Intent();
				intent.setAction(Intent.ACTION_DELETE);
				intent.setData(Uri.parse("package:"+pn));
				startActivityForResult(intent, 100);
				return true;
			}
		});
		new MyTask().execute();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==100){
			new MyTask().execute();
			adapter.notifyDataSetChanged();
		}
	}
	
	private class MyTask extends AsyncTask<Void, Void, Boolean>{

		@Override
		protected Boolean doInBackground(Void... params) {
			PackageManager pm=getPackageManager();
			Intent intent=new Intent();
			intent.setAction(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			activitys=pm.queryIntentActivities(intent, 0);
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			adapter=new AppsAdapter(activitys, MainActivity.this);
			gv.setAdapter(adapter);
		
		}
		
	}

}
