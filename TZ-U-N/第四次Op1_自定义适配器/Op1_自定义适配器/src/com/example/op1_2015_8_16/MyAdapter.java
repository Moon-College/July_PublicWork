package com.example.op1_2015_8_16;

import java.util.List;

import com.pujie_dome.Ctiy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
/**BaseAdapter
 * 常见的共同基类实现一个适配器,
 * 可以使用在两个列表视图(通过实现专业ListAdapter接口}
 * 和微调控制项(通过实现专业SpinnerAdapter接口。
 * 
 * */
public class MyAdapter extends BaseAdapter {

	private List<Ctiy>list;
	private Context context;
	
	public MyAdapter(List<Ctiy> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return  list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Ctiy ctiy =list.get(position);
		LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = layoutInflater.inflate(R.layout.list_view, null);
		TextView tv=(TextView) v.findViewById(R.id.tv);
		tv.setText(ctiy.getName());
		ImageView iv=(ImageView) v.findViewById(R.id.imag_1);
		iv.setImageResource(ctiy.getIcon());
		TextView tv_2=(TextView) v.findViewById(R.id.te_2);
		tv_2.setText(ctiy.getDeca());
		ImageView  imag=(ImageView) v.findViewById(R.id.imag_2);
		imag.setImageResource(ctiy.getIcon2());
		return v;
	}

}
