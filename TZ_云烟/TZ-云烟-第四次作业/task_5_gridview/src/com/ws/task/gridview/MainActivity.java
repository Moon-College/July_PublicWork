package com.ws.task.gridview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private GridView gridview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gridview = (GridView) findViewById(R.id.gridview);//��ʼ���ؼ�

		// int[] img = { R.drawable.face1, R.drawable.face2, R.drawable.face3,
		// R.drawable.face4, R.drawable.face5, R.drawable.face6,
		// R.drawable.face7, R.drawable.face8, };
		String[] str = { "�ŷ�", "�ܲ�", "��Ȩ", "����", "�����", "����", "����", "����" };
		//���������б�
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		//ѭ����ֵ
		for (int i = 0; i < 8; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("img", R.drawable.face1 + i);
			map.put("name", str[i]);
			data.add(map);
		}
		//����������
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.gridview_item, new String[] { "img", "name" },
				new int[] { R.id.item_img, R.id.item_name });
		gridview.setAdapter(adapter);
	}

}
