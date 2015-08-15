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
 * �Զ���launcher
 * @author �Խ���
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
		//�첽���ذ�װ��Ӧ�ó���
		LauncherTask task=new LauncherTask();
		task.execute(null);
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		gv_launcher = (GridView) findViewById(R.id.gv_launcher);
		//�����
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
		//��������
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
		//�첽���ذ�װ��Ӧ�ó���
		LauncherTask task=new LauncherTask();
		task.execute(null);
	}
	
	/**
	 * �첽������ذ�װ��Ӧ��
	 * @author �Խ���
	 *
	 */
	class LauncherTask extends AsyncTask<Void, Void, Boolean>{

		@Override
		protected Boolean doInBackground(Void... params) {
			pm = getPackageManager();
			Intent intent = new Intent();
			//��ͼֱ��Ӧ�ó��������
			intent.setAction(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			data = pm.queryIntentActivities(intent,0);
			return true;
		}
		
		/**
		 * Ӧ�����ݼ��سɹ��󣬳�ʼ��adapter�����������õ�gridview
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