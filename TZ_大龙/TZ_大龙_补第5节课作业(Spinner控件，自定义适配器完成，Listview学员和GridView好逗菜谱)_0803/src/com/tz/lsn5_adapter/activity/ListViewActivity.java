package com.tz.lsn5_adapter.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.tz.lsn5_adapter.R;
import com.tz.lsn5_adapter.adapter.MyListAdapter;
import com.tz.lsn5_adapter.object.UserInfo;

public class ListViewActivity extends Activity {
	private ListView lv;
	private MyListAdapter adapter;
	private List<UserInfo> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		
		init();
	}

	private void init() {
		
		list = getListItem();
		
		lv = (ListView) findViewById(R.id.lv);
		
//		SimpleAdapter adapter = new SimpleAdapter(
//				this, 
//				data, // List<? extends Map<String, ?>> data
//				R.layout.activity_listview_item, //������Դ
//				new String[]{"icon","nickName","sex","faceValue","hobby"}, 
//				new int[]{R.id.icon,R.id.nickName,R.id.sex,R.id.faceValue,R.id.hobby});
		
		adapter = new MyListAdapter(this, list);
		
		lv.setAdapter(adapter);
		
		//������Ŀ�¼�
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				UserInfo userInfo = (UserInfo) adapter.getItem(position);
				Toast.makeText(ListViewActivity.this, "������ˣ�" + userInfo.getNickname(), Toast.LENGTH_SHORT).show();
			}
		});
		
		//˫����Ŀ�¼�
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final UserInfo userInfo = (UserInfo) adapter.getItem(position);
				
				AlertDialog.Builder builder =  new AlertDialog.Builder(ListViewActivity.this);
				builder.setTitle("��ܰ��ʾ(��Ҫ)");
				builder.setMessage("��ȷʵҪɾ���û� �� " + userInfo.getNickname() + "������Ϣ��");
				builder.setPositiveButton("ȷ��", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if(deleteUser(userInfo)) {
							adapter.notifyDataSetChanged();
							Toast.makeText(ListViewActivity.this, "ɾ���ɹ���", Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(ListViewActivity.this, "ɾ��ʧ�ܣ�", Toast.LENGTH_LONG).show();
						}
					}

				});
				builder.setNegativeButton("�����", null);
				builder.show();
				
				return false;
			}
		});
	}
	
	//ɾ���û�
	private boolean deleteUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		for (UserInfo user : list) {
			if(user.getImgId() == userInfo.getImgId()) {
				list.remove(user);
				return true;
			}
		}
		
		return false;
	}

	/**
	 * ��ʼ���û���Ϣ������ list
	 * @return
	 */
	private List<UserInfo> getListItem() {
		// TODO Auto-generated method stub
		List<UserInfo> list = new ArrayList<UserInfo>();
		
		list.add(new UserInfo(R.drawable.icon_1, "�޶�", "����", "��", 95, "�ڿ�,����"));
		list.add(new UserInfo(R.drawable.icon_2, "���躭", "����", "Ů", 85, "����"));
		list.add(new UserInfo(R.drawable.icon_3, "���", "��ľ��", "Ů", 65, "����"));
		list.add(new UserInfo(R.drawable.icon_4, "�Ų�", "����", "��", 85, "����Ů"));
		list.add(new UserInfo(R.drawable.icon_5, "���Ϻ�", "���Ϻ�", "Ů", 92, "����ë��"));
		list.add(new UserInfo(R.drawable.icon_6, "Danny", "Danny", "��", 95, "�ڿ�"));
		list.add(new UserInfo(R.drawable.icon_7, "�޶�", "Ricky", "��", 95, "���"));
		list.add(new UserInfo(R.drawable.icon_7, "����", "Alex", "Ů", 95, "�ô���"));
		list.add(new UserInfo(R.drawable.icon_8, "Jason", "Jason", "��", 95, "�ڿ�,����"));
		list.add(new UserInfo(R.drawable.icon_9, "David", "David", "��", 95, "�ڿ�,����"));
		list.add(new UserInfo(R.drawable.icon_10, "Grace", "Grace", "��", 95, "�ڿ�,����"));
		list.add(new UserInfo(R.drawable.icon_11, "Elenis", "Elenis", "��", 95, "�ڿ�,����"));
		list.add(new UserInfo(R.drawable.icon_12, "Crystals", "Crystals", "��", 95, "�ڿ�,����"));
		list.add(new UserInfo(R.drawable.icon_13, "Nancy", "Nancy", "��", 95, "�ڿ�,����"));
		list.add(new UserInfo(R.drawable.icon_14, "Niky", "Niky", "Ů", 95, "�ڿ�,����"));
		list.add(new UserInfo(R.drawable.icon_15, "dallon", "dallon", "Ů", 95, "�ڿ�,����"));
		list.add(new UserInfo(R.drawable.icon_16, "����", "����", "Ů", 95, "�ڿ�,����"));
		list.add(new UserInfo(R.drawable.icon_17, "oom", "oom", "Ů", 95, "�ڿ�,����"));
		list.add(new UserInfo(R.drawable.icon_18, "��ʦ��", "��ʦ��", "Ů", 95, "�ڿ�,����"));
		list.add(new UserInfo(R.drawable.icon_19, "Kevin", "Kevin", "Ů", 95, "�ڿ�,����"));
		list.add(new UserInfo(R.drawable.icon_20, "YOLANDA", "YOLANDA", "Ů", 95, "�ڿ�,����"));
		return list;
	}
	
	
}
