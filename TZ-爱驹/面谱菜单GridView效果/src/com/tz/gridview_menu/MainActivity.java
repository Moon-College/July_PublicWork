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

	private String[] name = { "�ŷ�", "�ܲ�", "��Ȩ", "����", "�����", "����", "����", "����" };
	private String[] nickname = {"���","�ϵ�","��ı","����","����","-","�Ƴ�","����"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GridView gv = (GridView) findViewById(R.id.gv);
		//����Դ��������list�����и�map
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		//�������
		for (int i = 0; i < 8; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "������"+name[i]);
			map.put("image", R.drawable.face1 + i);
			map.put("nickname", "�֣�"+nickname[i]);
			data.add(map);
		}
		//ʹ��simpleadapter�᷽��ܶ�
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.face,
				new String[] { "image", "nickname", "name" }, 
				new int[] {
					R.id.iv_face, R.id.tv_author, R.id.tv_intro 
				});
		gv.setAdapter(adapter);
	}

}
