package com.tz.desktop;

import java.util.List;
import javax.crypto.spec.IvParameterSpec;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {

	private AppsAdapter adapter;
	private GridView gv;
	private PackageManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AppsAsyncTask task = new AppsAsyncTask();
		task.execute();
		gv = (GridView) findViewById(R.id.gv);
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				
				ResolveInfo info = ((ResolveInfo)adapter.getItem(position));
				String className = info.activityInfo.name;
				String packageName = info.activityInfo.packageName;
				intent.setClassName(packageName, className);
				startActivity(intent);
			}
			
		});
		gv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				ResolveInfo info = ((ResolveInfo)adapter.getItem(position));
				StringBuffer buffer = new StringBuffer();
				buffer.append("labelRes--"+info.labelRes)
				.append("\nresolvePackageName--"+info.resolvePackageName)
				.append("\nactivityLabelRes--"+info.activityInfo.labelRes)
				.append("\nparentActivityName--"+info.activityInfo.parentActivityName)
				.append("\npermission--"+info.activityInfo.permission)
				.append("\nprocessName--"+info.activityInfo.processName)
				.append("\ntaskAffinity--"+info.activityInfo.taskAffinity)
				.append("\ntargetActivity--"+info.activityInfo.targetActivity)
				.append("\nactivityInfo--"+info.activityInfo.toString())
				;
				AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
				View v = View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
				dialog.setView(v);
				dialog.setMessage(buffer.toString());
				dialog.create().show();
				
				return true;
			}
			
		});
	}

	final class AppsAsyncTask extends AsyncTask<Void, Void, Void> {

		private List<ResolveInfo> list;

		@Override
		protected Void doInBackground(Void... params) {
			manager = getPackageManager();
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			list = manager.queryIntentActivities(intent,
					PackageManager.PERMISSION_GRANTED);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			super.onPostExecute(result);
			adapter = new AppsAdapter(manager, list, MainActivity.this);
			adapter.notifyDataSetChanged();
			gv.setAdapter(adapter);
		}

	}
}
