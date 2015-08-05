package com.tz.fileexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.tz.fileexplorer.adapter.FileAdapter;
import com.tz.fileexplorer.bean.MyFile;
import com.tz.fileexplorer.util.BitmapCache;
import com.tz.fileexplorer.util.LoadFileAsyncTask;

/**
 * 高性能文件浏览器 (侧重浏览图片)
 */
public class MainActivity extends Activity {
	ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 128, 10,
			TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	private ListView lv;
	private File rootFile;
	private List<MyFile> list = new ArrayList<MyFile>();
	private FileAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BitmapCache.getInstance().init(12);

		lv = (ListView) findViewById(R.id.lv);
		// 判断SD卡是否正常挂在
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// 得到sd卡根目录
			rootFile = Environment.getExternalStorageDirectory();
		} else {
			Toast.makeText(this, "SD Error", 0).show();
		}
		initData(rootFile.getAbsolutePath());

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MyFile myFile = (MyFile) adapter.getItem(position);
				File file = myFile.getFile();
				if (file.isDirectory()) {
					initData(file.getAbsolutePath());
				} else if (myFile.isIcon()) {
					// 打开图片
					Intent intent = new Intent(ShowPhotoActivity.ACTION);
					intent.putExtra(ShowPhotoActivity.EXTRA_PATH,
							file.getAbsolutePath());
					startActivity(intent);
				}
			}
		});

		lv.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (OnScrollListener.SCROLL_STATE_IDLE == scrollState) {
					reloadImage();
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}
		});
	}

	void reloadImage() {
		int first = lv.getFirstVisiblePosition();
		int last = lv.getLastVisiblePosition();
		Log.d("lkf", String.format("first=%d, last=%d", first, last));
		BitmapCache cache = BitmapCache.getInstance();
		for (int i = 0; i < list.size(); i++) {
			MyFile myFile = list.get(i);
			if (i >= first && i <= last) {
				Bitmap b = cache.getBitmap(myFile.getPath());
				if (b == null || b.isRecycled()) {
					new LoadFileAsyncTask() {
						protected void onPostExecute(Bitmap result) {
							super.onPostExecute(result);
							adapter.notifyDataSetChanged();
						}
					}.executeOnExecutor(threadPool, myFile.getPath());
				}
			} else {
				cache.removeBitmap(myFile.getPath());
			}
		}
	}

	private void initData(String path) {
		File file = new File(path);
		if (file.getParentFile() == null) {
			Toast.makeText(this, "无上级目录", Toast.LENGTH_SHORT).show();
			return;
		}
		list.clear();
		// 遍历SD卡
		File[] listFiles = file.listFiles();
		MyFile file_back = new MyFile(file.getParentFile(), "返回");
		list.add(file_back);
		if (listFiles != null && listFiles.length > 0) {
			for (File f : listFiles) {
				MyFile myFile = new MyFile(f, f.getName());
				list.add(myFile);
			}
		}
		adapter = (FileAdapter) lv.getAdapter();
		if (adapter == null) {
			adapter = new FileAdapter(list, this);
		}
		lv.setAdapter(adapter);
		lv.post(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				reloadImage();
			}
		});

	}

	@Override
	protected void onDestroy() {
		BitmapCache.getInstance().release();
		threadPool.shutdown();
		super.onDestroy();
	}
}
