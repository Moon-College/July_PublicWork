package com.tz.lsn5_adapter.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.tz.lsn5_adapter.R;
import com.tz.lsn5_adapter.adapter.MyGridAdapter;
import com.tz.lsn5_adapter.object.FoodInfo;

public class GridViewActivity extends BaseActivity {
	private GridView gv;
	private List<FoodInfo> list;
	private MyGridAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//setContentView(R.layout.activity_gridview);
		setContentLayout(R.layout.activity_gridview);
		
		hideLeftBtn();
		hideRightBtn();
		setTitle("����ר��");
		
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
		gv = (GridView)findViewById(R.id.gv);
		
		list = getListItem();
		
		System.out.println(list.toString());
		
		adapter =  new MyGridAdapter(this, list);
		
		gv.setAdapter(adapter);
		
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				FoodInfo foodInfo = (FoodInfo) adapter.getItem(position);
				Toast.makeText(GridViewActivity.this, foodInfo.getUserName() + " �����ҵĲˣ�", Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	private List<FoodInfo> getListItem() {
		// TODO Auto-generated method stub
		
		List<FoodInfo> list = new ArrayList<FoodInfo>();
		list.add(new FoodInfo("�����ļ���", R.drawable.food_1, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_1, "Ricky"));
		list.add(new FoodInfo("�������ӳ�����", R.drawable.food_2, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_2, "Grace"));
		list.add(new FoodInfo("���Զ���", R.drawable.food_3, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_3, "Dallon"));
		list.add(new FoodInfo("���𶹽�", R.drawable.food_4, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_4, "Danny"));
		list.add(new FoodInfo("��Ƥ������", R.drawable.food_5, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_5, "Moon"));
		list.add(new FoodInfo("�ļ����ǻ����Է�", R.drawable.food_6, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_6, "Alex"));
		list.add(new FoodInfo("��ĭ����˿", R.drawable.food_7, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_7, "Elaine"));
		list.add(new FoodInfo("���ӳ�����", R.drawable.food_8, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_8, "Cystle"));
		list.add(new FoodInfo("��Ʒ�ļ���", R.drawable.food_9, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_9, "yoyo"));
		list.add(new FoodInfo("�ļ���ţ��", R.drawable.food_10, 10, "�ļ����׳ƶ��ǣ����ԲԲ������ˮ�ۣ�ͷ�������������ϲ�����ǲ����˼Ҳ����ϵĳ����߲�֮һ", R.drawable.user_10, "Linda"));
		
		return list;
	}
	
	
}
