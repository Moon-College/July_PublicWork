package com.tz.fourth.second;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tz.auxiliary.MyColor;
import com.tz.first.R;

/**第四次课第二个作业用到的，重定义getView,为ListView绑定数据。*/
public class TableAdapter extends BaseAdapter {
	private List<ItemInfo> list;
	private LayoutInflater inflater;

//	private static final int TYPE_ITEM = 0;
//	private static final int TYPE_SEPARATOR = 1;
//	private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

	public TableAdapter(Context context, List<ItemInfo> list) {
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemInfo fi = (ItemInfo) this.getItem(position);
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_items_ll, null);
			viewHolder.field1 = (TextView) convertView
					.findViewById(R.id.field_1_tv);
			viewHolder.field2 = (TextView) convertView
					.findViewById(R.id.field_2_tv);
			viewHolder.field3 = (TextView) convertView
					.findViewById(R.id.field_3_tv);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.field1.setText(fi.getField1());
		viewHolder.field2.setText(fi.getField2());
		viewHolder.field3.setText(fi.getField3());
		viewHolder.field1.setBackgroundColor(MyColor
				.getColorByHexadecimal(R.color.saddlebrown));
		viewHolder.field2
				.setBackgroundColor(MyColor.getColorByR(R.color.orange));
		viewHolder.field3.setBackgroundColor(MyColor
				.getColorByR(R.color.green));
		return convertView;
	}

	public static class ViewHolder {
		public TextView field1;
		public TextView field2;
		public TextView field3;

	}
}
