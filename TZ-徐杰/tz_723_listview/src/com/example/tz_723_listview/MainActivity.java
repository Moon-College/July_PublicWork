package com.example.tz_723_listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Administrator ���ֵĴ������г����棬�����ݲ���ʾ��ԭ�򡣳�ʼ������ʱ����û�н�����ӵ�list�С�����
 */
public class MainActivity extends Activity {
	ListView listView;
	List<HashMap<String, Object>> list;
	Context context;
	ListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		initView();
		initData();
		initAdapter();
		initLinstener();

	}

	/**
	 * ������
	 */
	private void initView() {
		listView = (ListView) findViewById(R.id.lv_show);
	}

	/**
	 * ����������
	 */
	private void initAdapter() {
		adapter = new ListViewAdapter();
		listView.setAdapter(adapter);

	}

	/**
	 * ��������
	 */
	private void initData() {
		list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map;
		for (int i = 0; i < 20; i++) {
			map = new HashMap<String, Object>();
			map.put("age", i);
			map.put("name", "����" + i);
			if (i % 2 == 0) {
				map.put("sex", "��");
			} else {
				map.put("sex", "Ů");
			}
			map.put("imgview", R.drawable.ic_launcher);
			list.add(map);
		}

	}

	/**
	 * ����
	 */
	private void initLinstener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				HashMap<String, Object> map = list.get(arg2);
				Toast.makeText(context, (String)map.get("sex")+"\n"+map.get("age")+"\n"+(String)map.get("name"), 1).show();
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				createDialog(arg2);
				
				return true;
			}
		});
	}
    /**
     * ����dialog��������
     * @param arg2
     */
	protected void createDialog(final int arg2) {
		AlertDialog.Builder dialog=	new AlertDialog.Builder(context).setTitle("�Ƿ�ɾ����" + arg2  + "��")
				.setPositiveButton("��", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						list.remove(arg2);
						adapter.notifyDataSetChanged();
					}
				}).setNegativeButton("��", new  OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
					}
				});
		dialog.show();
		
	}

	class ListViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			HashMap<String, Object> map = list.get(position);
			Log.e("ssss", (String) map.get("name"));
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(
						R.layout.listview_item, null);
				holder.imageView = (ImageView) convertView
						.findViewById(R.id.img_view);
				holder.tv_age = (TextView) convertView
						.findViewById(R.id.tv_age);
				holder.tv_name = (TextView) convertView
						.findViewById(R.id.tv_name);
				holder.tv_sex = (TextView) convertView
						.findViewById(R.id.tv_sex);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.imageView.setImageDrawable(getResources().getDrawable(
					(Integer) map.get("imgview")));
			holder.tv_age.setText(map.get("age") + "");
			holder.tv_name.setText((CharSequence) map.get("name"));
			holder.tv_sex.setText((CharSequence) map.get("sex"));
			if (((CharSequence) map.get("sex")).equals("��")) {
				holder.tv_sex.setTextColor(Color.RED);
			} else {
				holder.tv_sex.setTextColor(Color.BLACK);
			}

			// convertView = LayoutInflater.from(context).inflate(
			// R.layout.listview_item, null);
			// ImageView imageView = (ImageView) convertView
			// .findViewById(R.id.img_view);
			// TextView tv_age = (TextView)
			// convertView.findViewById(R.id.tv_age);
			// TextView tv_name = (TextView) convertView
			// .findViewById(R.id.tv_name);
			// TextView tv_sex = (TextView)
			// convertView.findViewById(R.id.tv_sex);
			// imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
			// tv_age.setText("18");
			// tv_name.setText("����");
			// tv_sex.setText("��");
			return convertView;
		}

	}

	class ViewHolder {
		ImageView imageView;
		TextView tv_age;
		TextView tv_name;
		TextView tv_sex;

	}
}
