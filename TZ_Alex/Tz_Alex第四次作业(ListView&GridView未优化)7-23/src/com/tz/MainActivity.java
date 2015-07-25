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
		
		lv = (ListView) findViewById(R.id.lv_show);		//初始化listview控件
	
		initData();    	//初始化数据
	
		adapter = new MyAdapter(this, lv_info);
		
		lv.setAdapter(adapter);    //为listview绑定适配器
		
		lv.setOnItemLongClickListener(this);
	}
	
	/**
	 * 长按item删除该项
	 */
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		//获取该item的对象信息
		MemberInfo info = (MemberInfo) adapter.getItem(position);
		lv_info.remove(info);
		//刷新adapter
		adapter.notifyDataSetChanged();
		return true;
	}
	
	
	/**
	 * 给List结合赋值
	 */
	private void initData() {
		
		lv_info = new ArrayList<MemberInfo>();
		MemberInfo info1 = new MemberInfo("张飞","男","屌丝","八丈蛇矛",R.drawable.face1);
		MemberInfo info2 = new MemberInfo("曹操","男","土豪","长剑",R.drawable.face2);
		MemberInfo info3 = new MemberInfo("孙权","男","土豪","单刀",R.drawable.face3);
		MemberInfo info4 = new MemberInfo("吕布","男","猛男","方天画戟",R.drawable.face4);
		MemberInfo info5 = new MemberInfo("诸葛亮","男","小资","青纶羽扇",R.drawable.face5);
		MemberInfo info6 = new MemberInfo("貂蝉","女","女神","锦缎",R.drawable.face6);
		MemberInfo info7 = new MemberInfo("关羽","男","暖男","青龙偃月刀",R.drawable.face7);
		MemberInfo info8 = new MemberInfo("刘备","男","富二代","雌雄双股剑",R.drawable.face8);
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
