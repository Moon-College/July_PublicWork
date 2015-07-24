package com.ws.task.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener {

	private List<Bean> data;
	private ListView lv;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		lv = (ListView) findViewById(R.id.lv);
		initData();
		//������
		adapter = new MyAdapter(this, data);
		lv.setAdapter(adapter);
		//lisetView�¼�
		lv.setOnItemClickListener(this);
	}
	//����
	private void initData() {
		int[] haed = { R.drawable.ahri, R.drawable.akali, R.drawable.alistar,
				R.drawable.amumu, R.drawable.anivia, R.drawable.annie,
				R.drawable.ashe, R.drawable.blitzcrank, R.drawable.brand,
				R.drawable.caitlyn };
		String[] name = { "Grace��ʦ", "Crystal��ʦ", "Rrick��ʦ", "������ʦ", "Danny��ʦ", "������ʦ", "Elaine��ʦ",
				"��Į��ʦ", "Alex��ʦ", "YSEO��ʦ" };
		boolean[] sex = { true, true, false, true, false, false, true, false,
				true, false };
		String[] hobby = { "����", "����", "����", "����", "����", "����", "����", "����",
				"����", "����" };
//		int[] number = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 };
		data = new ArrayList<Bean>();
		int b = name.length;
		//ѭ����ֵ
		for(int i=0;i<b;i++){
			Bean hero = new Bean();
			hero.setHead(haed[i]);
			hero.setName(name[i]);
			//�ж� trueΪŮ  falseΪ��
			hero.setSex((sex[i])==true? "Ů":"��");
			hero.setHobby(hobby[i]);
//			hero.setNumber(number[i]);
			//���뵽���ݵ���
			data.add(hero);
		}
	}
	
	
	//item�ĵ���¼�
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
		//����һ��ȷ��ɾ���ĶԻ���
		new AlertDialog.Builder(MainActivity.this).setTitle("ע��").setMessage("ȷ��Ҫɾ����")
		.setIcon(R.drawable.ic_launcher).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//ɾ������Ŀ������
				data.remove(arg2);
				//ˢ��listview
				adapter.notifyDataSetChanged();
			}
		}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		}).show();//��ʾ����
		
	}

}
