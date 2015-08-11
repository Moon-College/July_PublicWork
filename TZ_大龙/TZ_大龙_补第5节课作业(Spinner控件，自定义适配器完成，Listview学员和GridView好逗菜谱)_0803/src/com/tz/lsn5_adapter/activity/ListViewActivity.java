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
//				R.layout.activity_listview_item, //布局资源
//				new String[]{"icon","nickName","sex","faceValue","hobby"}, 
//				new int[]{R.id.icon,R.id.nickName,R.id.sex,R.id.faceValue,R.id.hobby});
		
		adapter = new MyListAdapter(this, list);
		
		lv.setAdapter(adapter);
		
		//单击条目事件
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				UserInfo userInfo = (UserInfo) adapter.getItem(position);
				Toast.makeText(ListViewActivity.this, "您点击了：" + userInfo.getNickname(), Toast.LENGTH_SHORT).show();
			}
		});
		
		//双击条目事件
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final UserInfo userInfo = (UserInfo) adapter.getItem(position);
				
				AlertDialog.Builder builder =  new AlertDialog.Builder(ListViewActivity.this);
				builder.setTitle("温馨提示(重要)");
				builder.setMessage("您确实要删除用户 【 " + userInfo.getNickname() + "】的信息吗？");
				builder.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if(deleteUser(userInfo)) {
							adapter.notifyDataSetChanged();
							Toast.makeText(ListViewActivity.this, "删除成功！", Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(ListViewActivity.this, "删除失败！", Toast.LENGTH_LONG).show();
						}
					}

				});
				builder.setNegativeButton("点错了", null);
				builder.show();
				
				return false;
			}
		});
	}
	
	//删除用户
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
	 * 初始化用户信息并生成 list
	 * @return
	 */
	private List<UserInfo> getListItem() {
		// TODO Auto-generated method stub
		List<UserInfo> list = new ArrayList<UserInfo>();
		
		list.add(new UserInfo(R.drawable.icon_1, "罗鼎", "鼎哥", "男", 95, "挖坑,唱歌"));
		list.add(new UserInfo(R.drawable.icon_2, "张予涵", "娇妖", "女", 85, "跳舞"));
		list.add(new UserInfo(R.drawable.icon_3, "刘楠", "花木兰", "女", 65, "剑术"));
		list.add(new UserInfo(R.drawable.icon_4, "张波", "波歌", "男", 85, "看美女"));
		list.add(new UserInfo(R.drawable.icon_5, "张紫涵", "张紫涵", "女", 92, "打羽毛球"));
		list.add(new UserInfo(R.drawable.icon_6, "Danny", "Danny", "男", 95, "挖坑"));
		list.add(new UserInfo(R.drawable.icon_7, "罗鼎", "Ricky", "男", 95, "麦霸"));
		list.add(new UserInfo(R.drawable.icon_7, "周礼", "Alex", "女", 95, "敲代码"));
		list.add(new UserInfo(R.drawable.icon_8, "Jason", "Jason", "男", 95, "挖坑,唱歌"));
		list.add(new UserInfo(R.drawable.icon_9, "David", "David", "男", 95, "挖坑,唱歌"));
		list.add(new UserInfo(R.drawable.icon_10, "Grace", "Grace", "男", 95, "挖坑,唱歌"));
		list.add(new UserInfo(R.drawable.icon_11, "Elenis", "Elenis", "男", 95, "挖坑,唱歌"));
		list.add(new UserInfo(R.drawable.icon_12, "Crystals", "Crystals", "男", 95, "挖坑,唱歌"));
		list.add(new UserInfo(R.drawable.icon_13, "Nancy", "Nancy", "男", 95, "挖坑,唱歌"));
		list.add(new UserInfo(R.drawable.icon_14, "Niky", "Niky", "女", 95, "挖坑,唱歌"));
		list.add(new UserInfo(R.drawable.icon_15, "dallon", "dallon", "女", 95, "挖坑,唱歌"));
		list.add(new UserInfo(R.drawable.icon_16, "波哥", "波哥", "女", 95, "挖坑,唱歌"));
		list.add(new UserInfo(R.drawable.icon_17, "oom", "oom", "女", 95, "挖坑,唱歌"));
		list.add(new UserInfo(R.drawable.icon_18, "大师兄", "大师兄", "女", 95, "挖坑,唱歌"));
		list.add(new UserInfo(R.drawable.icon_19, "Kevin", "Kevin", "女", 95, "挖坑,唱歌"));
		list.add(new UserInfo(R.drawable.icon_20, "YOLANDA", "YOLANDA", "女", 95, "挖坑,唱歌"));
		return list;
	}
	
	
}
