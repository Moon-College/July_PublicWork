package com.tz.lsn5_adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListAdapter;

public class GridViewActivity extends Activity {
	private GridView gv;
	private List<FoodInfo> list;
	private MyGridAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gridview);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
		gv = (GridView)findViewById(R.id.gv);
		
		list = getListItem();
		
		adapter =  new MyGridAdapter(this,list);
		
		gv.setAdapter(adapter);
	}

	private List<FoodInfo> getListItem() {
		// TODO Auto-generated method stub
		
		List<FoodInfo> list = new ArrayList<FoodInfo>();
		list.add(new FoodInfo("�����ļ���", R.drawable.food_1, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_1, "aaa"));
		list.add(new FoodInfo("�������ӳ�����", R.drawable.food_2, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_2, "bbb"));
		list.add(new FoodInfo("���Զ���", R.drawable.food_3, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_3, "ccc"));
		list.add(new FoodInfo("���𶹽�", R.drawable.food_4, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_4, "ddd"));
		list.add(new FoodInfo("��Ƥ������", R.drawable.food_5, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_5, "eee"));
		list.add(new FoodInfo("�ļ����ǻ����Է�", R.drawable.food_6, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_6, "fff"));
		list.add(new FoodInfo("��ĭ����˿", R.drawable.food_7, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_7, "ggg"));
		list.add(new FoodInfo("���ӳ�����", R.drawable.food_8, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_8, "hhh"));
		list.add(new FoodInfo("��Ʒ�ļ���", R.drawable.food_9, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_9, "iii"));
		list.add(new FoodInfo("�ļ���ţ��", R.drawable.food_10, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_10, "jjj"));
		
		return list;
	}
	
	
}
