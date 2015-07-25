package com.tz;

import java.util.ArrayList;
import java.util.List;

import com.tz.adapter.MyAdapter;
import com.tz.bean.MemberInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemLongClickListener{

	private ListView lv;
	private List<MemberInfo> lv_info;
	private MyAdapter adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (ListView) findViewById(R.id.lv_show);		//��ʼ��listview�ؼ�
	
		initData();    	//��ʼ������
	
		adapter = new MyAdapter(this, lv_info);
		
		lv.setAdapter(adapter);    //Ϊlistview��������
		
		lv.setOnItemLongClickListener(this);
	}
	
	/**
	 * ����itemɾ������
	 */
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		//��ȡ��item�Ķ�����Ϣ
		MemberInfo info = (MemberInfo) adapter.getItem(position);
		lv_info.remove(info);
		//ˢ��adapter
		adapter.notifyDataSetChanged();
		return true;
	}
	
	
	/**
	 * ��List��ϸ�ֵ
	 */
	private void initData() {
		
		lv_info = new ArrayList<MemberInfo>();
		MemberInfo info1 = new MemberInfo("�ŷ�","��","��˿","������ì",R.drawable.face1);
		MemberInfo info2 = new MemberInfo("�ܲ�","��","����","����",R.drawable.face2);
		MemberInfo info3 = new MemberInfo("��Ȩ","��","����","����",R.drawable.face3);
		MemberInfo info4 = new MemberInfo("����","��","����","���컭�",R.drawable.face4);
		MemberInfo info5 = new MemberInfo("�����","��","С��","��������",R.drawable.face5);
		MemberInfo info6 = new MemberInfo("����","Ů","Ů��","����",R.drawable.face6);
		MemberInfo info7 = new MemberInfo("����","��","ů��","�������µ�",R.drawable.face7);
		MemberInfo info8 = new MemberInfo("����","��","������","����˫�ɽ�",R.drawable.face8);
		lv_info.add(info1);
		lv_info.add(info2);
		lv_info.add(info3);
		lv_info.add(info4);
		lv_info.add(info5);
		lv_info.add(info6);
		lv_info.add(info7);
		lv_info.add(info8);
		
	}

}
