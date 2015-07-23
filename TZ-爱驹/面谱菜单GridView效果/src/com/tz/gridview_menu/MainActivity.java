package com.tz.gridview_menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes.Name;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Nickname;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.SimpleAdapter;
/**
 * 
 * @author wztscau
 * @date 2015.7.13
 *
 */
public class MainActivity extends Activity {

	private String[] name = { "张飞", "曹操", "孙权", "吕布", "诸葛亮", "貂蝉", "关羽", "刘备" };
	private String[] nickname = {"翼德","孟德","仲谋","奉先","孔明","-","云长","玄德"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GridView gv = (GridView) findViewById(R.id.gv);
		//数据源，必须是list里面有个map
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		//添加数据
		for (int i = 0; i < 8; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "姓名："+name[i]);
			map.put("image", R.drawable.face1 + i);
			map.put("nickname", "字："+nickname[i]);
			data.add(map);
		}
		//使用simpleadapter会方便很多
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.face,
				new String[] { "image", "nickname", "name" }, 
				new int[] {
					R.id.iv_face, R.id.tv_author, R.id.tv_intro 
				});
		gv.setAdapter(adapter);
	}

}
