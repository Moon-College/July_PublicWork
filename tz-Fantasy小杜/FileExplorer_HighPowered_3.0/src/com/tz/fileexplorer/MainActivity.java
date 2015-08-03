package com.tz.fileexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.R.interpolator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tz.fileexplorer.adapter.FileAdapter;
import com.tz.fileexplorer.bean.MyFile;

/**
 * 高性能文件浏览器 (侧重浏览图片)
 */
public class MainActivity extends Activity {

	private ListView lv;
	private String rootPath;
	private List<MyFile> list = new ArrayList<MyFile>();
	private FileAdapter adapter;
	private int index;
	private static int MSG_REFRESH = 0x001;
	private static String TAG = "MainActivity";
	RelativeLayout rootview;
	PopupWindow pWindow;
	/* private MyFile slectedFile; */

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0x001:

				break;

			default:
				break;
			}

		};
	};
	private Calendar calendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lv = (ListView) findViewById(R.id.lv);
		// 判断SD卡是否正常挂在
		rootview = (RelativeLayout) findViewById(R.id.rootView);

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// 得到sd卡根目录
			rootPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
		} else {
			Toast.makeText(this, "SD Error", 0).show();
		}

		initData(rootPath);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//
				MyFile myFile = (MyFile) adapter.getItem(position);
				File file = new File(myFile.getPath());
				
				//如果点击的是目录，则列出该目录所有文件和文件夹
				if (file.isDirectory()) {
					initData(file.getAbsolutePath());
				}
				
				//如果点击是图片则调用系统的图片查看器进行展示
				else if (file.getName().toLowerCase().endsWith("png")||file.getName().toLowerCase().endsWith("jpg")
						||file.getName().toLowerCase().endsWith("gif")) {
					
					//调用系统的图片浏览器查看图片
//					Intent intent = new Intent();
//					intent.setAction(android.content.Intent.ACTION_VIEW);
//					intent.setDataAndType(Uri.fromFile(file), "image/*");
//					startActivity(intent);
					
					//调用自己写的Activity查看点击的图片
					
					Intent intent = new Intent("com.fantasyado.action_imageDisplay");
					intent.putExtra("path", file.getPath());
					startActivity(intent);
					 
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.timePicker:
			OnTimeSetListener listener = new OnTimeSetListener() {

				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
				 
				      calendar.set(2015, hourOfDay, minute);
				}
			};

			long time = System.currentTimeMillis();
			  calendar = Calendar.getInstance();
			calendar.setTimeInMillis(time);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
		 
		    final 	TimePickerDialog tpd = new TimePickerDialog(MainActivity.this,
					listener, hour, minute, true);
			tpd.show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initData(String path) {
		list.clear();
		// 遍历SD卡
		File file = new File(path);
		File[] listFiles = file.listFiles();

		MyFile file_back = new MyFile("返回", BitmapFactory.decodeResource(
				getResources(), R.drawable.dirs), file.getParentFile()
				.getAbsolutePath(), false);
		list.add(file_back);
		for (File f : listFiles) {
			MyFile myFile = new MyFile(f.getAbsolutePath());
			myFile.setName(f.getName());
			myFile.setPath(f.getAbsolutePath());
			if (f.isDirectory()) {// 是文件夹
				myFile.setIcon(false);
				myFile.setIcon(BitmapFactory.decodeResource(getResources(),
						R.drawable.dirs));
			} else {
				// 文件
				// 图片
				if (f.getName().toLowerCase().endsWith(".png")
						|| f.getName().toLowerCase().endsWith(".jpg")
						|| f.getName().toLowerCase().endsWith(".jpeg")
						|| f.getName().toLowerCase().endsWith(".gif")) {
					myFile.setIcon(true);
					myFile.setIcon(null);
					// myFile.setIcon(BitmapFactory.decodeResource(getResources(),
					// R.drawable.file));
				} else {// 其他文件
					myFile.setIcon(false);
					myFile.setIcon(BitmapFactory.decodeResource(getResources(),
							R.drawable.file));
				}
			}
			list.add(myFile);
		}
		adapter = new FileAdapter(list, this);
		lv.setAdapter(adapter);
		lv.setOnItemLongClickListener(new MyListener());
	}

	class MyListener implements OnItemLongClickListener, OnClickListener {

		Button btn_delete;
		Button btn_detail;

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			View popView = View.inflate(MainActivity.this,
					R.layout.popwindow_layout, null);
			btn_delete = (Button) popView.findViewById(R.id.btn_delete);
			btn_detail = (Button) popView.findViewById(R.id.btn_detail);
			btn_delete.setOnClickListener(this);
			btn_detail.setOnClickListener(this);

			pWindow = new PopupWindow(popView, dip2px(getApplicationContext(),
					80), dip2px(getApplicationContext(), 160), true);
			pWindow.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.bg));
			pWindow.showAsDropDown(view, 100, 0);
			index = position;
			Log.i(TAG, "OnLongClick index is:" + index);

			return true;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_delete:
				Toast.makeText(getApplicationContext(), "btn_delete Pressed",
						Toast.LENGTH_SHORT).show();
				MyFile file = list.get(index);
				list.remove(index);
				delete(file);
				adapter.notifyDataSetChanged();
				pWindow.dismiss();
				// lv.invalidate();
				// rootview.invalidate();
				break;
			case R.id.btn_detail:
				Toast.makeText(getApplicationContext(), "btn_detail Pressed",
						Toast.LENGTH_SHORT).show();
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("file detail");
				MyFile myFile = list.get(index);
				StringBuilder sb = new StringBuilder();

				sb.append("file name:" + myFile.getName());
				sb.append("\n");
				sb.append("file path:" + myFile.getAbsolutePath());
				sb.append("\n");
				sb.append("file size:" + getLength(myFile) + "Kb");
				builder.setMessage(sb.toString());
				builder.setPositiveButton("ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});
				builder.show();

				break;
			default:
				break;
			}
		}

	}

	/**
	 * 删除给定的文件或文件夹下的所有子文件
	 * 
	 * @author Fantastyado
	 * @parameter file:给定要删除的文件
	 * 
	 */
	public void delete(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File file2 : files) {
					this.delete(file2);
				}
				file.delete();
			}

		}
	}

	/**
	 * 
	 * @author Fantastyado
	 * @parameter context：上下文
	 * @parameter dpValue:给定的dp值 dp和px的转换，给定dp,返回对应的px,+0.5是四舍五入的意思
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 获得指定文件的大小，如果是文件夹 则递归调用统计所有子文件夹的大小结果以Kb 返回
	 * 
	 * @author Fantastyado
	 * @param file
	 *            要计算大小的文件或文件夹
	 */
	public static long getLength(File file) {
		long length = 0;
		if (file.isFile()) {
			length = file.length() / 1024;
		} else if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File file2 : files) {
				length += getLength(file2);
			}
		}
		return length;

	}
}
