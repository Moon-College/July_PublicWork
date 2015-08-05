package com.example.filebrowser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
/**
 * 
 * @author 泛音
 * 文件浏览器
 */
public class FileBrowserActivity extends Activity {
	private ListView listfile;
	//当前文件目录
	private String currentpath;
	private TextView txt1;
	private ImageView images;
	private TextView textview;
	private ImageButton imagebt1;
	
	private int[] img = { R.drawable.file, R.drawable.folder, R.drawable.ic_launcher };
	private File[] files;
	private SimpleAdapter simple;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_browser);
		listfile = (ListView) findViewById(R.id.listFile);
		txt1 = (TextView) findViewById(R.id.txt1);
		imagebt1 = (ImageButton) findViewById(R.id.imageBt1);
		init(Environment.getExternalStorageDirectory());
		listfile.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// 获取单击的文件或文件夹的名称
				String folder = ((TextView) arg1.findViewById(R.id.txtview)).getText().toString();
				try {
					File filef = new File(currentpath + '/'+ folder);
					init(filef);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		//回根目录
		imagebt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				init(Environment.getExternalStorageDirectory());	
			}
		});
		
	}

	// 界面初始化
	public void init(File f) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			// 获取SDcard目录下所有文件名
			files = f.listFiles();
			if (!files.equals(null)) {
				currentpath=f.getPath();
				txt1.setText("当前目录为:"+f.getPath());
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < files.length; i++) {
					Map<String, Object> maps = new HashMap<String, Object>();
					if (files[i].isFile())
						maps.put("image", img[0]);
					else
						maps.put("image", img[1]);
					maps.put("filenames", files[i].getName());
					list.add(maps);
				}
				simple = new SimpleAdapter(this, list,
						R.layout.item, new String[] { "image",
								"filenames" }, new int[] { R.id.images,
								R.id.txtview });
				listfile.setAdapter(simple);

			}
		} else {
			System.out.println("该文件为空");
		}
	}

}
