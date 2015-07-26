package com.tz.fourth.second;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tz.auxiliary.MyColor;
import com.tz.first.R;
import com.tz.first.R.color;
import com.tz.main.MyApplication;

/** 第四次课第二个作业用到的，重定义getView,为ListView绑定数据。 */
public class StudentsAdapter extends BaseAdapter {
	private List<StudentsInfo> list;
	private LayoutInflater inflater;

	public StudentsAdapter(Context context, List<StudentsInfo> list) {
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public void addItem(final StudentsInfo item) {
		list.add(item);
		notifyDataSetChanged();
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
		StudentsInfo fi = (StudentsInfo) this.getItem(position);
		StudentHolder studentHolder;
		if (convertView == null) {
			studentHolder = new StudentHolder();
			convertView = inflater.inflate(R.layout.student_info_ll, null);
			studentHolder.item_ll = (LinearLayout) convertView
					.findViewById(R.id.stuinfo_ll);
			studentHolder.touxiang_iv = (ImageView) convertView
					.findViewById(R.id.touxiang_iv);
			studentHolder.wangming_tv = (TextView) convertView
					.findViewById(R.id.xingming_tv);
			studentHolder.xingbie_tv = (TextView) convertView
					.findViewById(R.id.xingbie_tv);
			studentHolder.yanzhi_tv = (TextView) convertView
					.findViewById(R.id.yanzhi_tv);
			studentHolder.aihao_tv = (TextView) convertView
					.findViewById(R.id.aihao_tv);
			convertView.setTag(studentHolder);

		} else {
			studentHolder = (StudentHolder) convertView.getTag();
		}
		studentHolder.touxiang_iv.setBackgroundResource(fi.getTouXiangR());
		studentHolder.wangming_tv.setText("网名:" + fi.getWangMing());
		studentHolder.xingbie_tv.setText("性别:" + fi.getXingBie());
		studentHolder.yanzhi_tv.setText("颜值:" + fi.getYanZhi());
		studentHolder.aihao_tv.setText("爱好:" + fi.getAiHao());
		// 设置背景色
		if ("女".equals(fi.getXingBie())) {
			studentHolder.item_ll.setBackgroundColor(Color
					.parseColor("#ffcc0000"));
		} else if ("男".equals(fi.getXingBie())) {
			studentHolder.item_ll.setBackgroundColor(Color
					.parseColor("#ff6699ff"));
		} else {
			studentHolder.item_ll.setBackgroundColor(Color.BLACK);
		}
		return convertView;
	}

	public static class StudentHolder {
		public LinearLayout item_ll;
		public ImageView touxiang_iv;
		public TextView wangming_tv;
		public TextView xingbie_tv;
		public TextView yanzhi_tv;
		public TextView aihao_tv;
	}

	public OnItemLongClickListener longListener = new OnItemLongClickListener() {
		@Override
		public boolean onItemLongClick(android.widget.AdapterView<?> parent,
				View view, int position, long id) {
			DeleteDialog(position);
			return false;
		};
	};
	private Context context;

	private void DeleteDialog(int position) {
		AlertDialog.Builder builder = new Builder(context);

		builder.setMessage("确定删除该同学信息？");
		builder.setTitle("提示");
		final int pos = position;
		final List<StudentsInfo> stuList = list;
		builder.setNeutralButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				stuList.remove(pos);
				notifyDataSetChanged();
			}
		});

		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.create().show();
	}
}
