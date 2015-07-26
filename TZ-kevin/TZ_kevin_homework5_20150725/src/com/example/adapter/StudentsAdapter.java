package com.example.adapter;

import java.util.List;

import com.example.entity.Students;
import com.example.tz_kevin_homework5_20150724.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 
 * @author kevin.li
 * @version 1.0.0
 * @create 20150724
 * @function 学生信息适配器
 */
public class StudentsAdapter extends BaseAdapter {
	// 上下文
	private Context mContext;
	// 数据源
	private List<Students> mData;

	public StudentsAdapter(Context mContext, List<Students> mData) {
		this.mContext = mContext;
		this.mData = mData;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null; // 持有者
		Students students = mData.get(position); // 学生对象
		if (null == convertView) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.activity_ss_item, null);
			holder.item = (RelativeLayout) convertView
					.findViewById(R.id.item_rl_container);
			holder.imvHead = (ImageView) convertView
					.findViewById(R.id.item_imv_head);
			holder.tvName = (TextView) convertView
					.findViewById(R.id.item_tv_screenName);
			holder.tvInfo = (TextView) convertView
					.findViewById(R.id.item_tv_info);
			holder.tvFaceScore = (TextView) convertView
					.findViewById(R.id.item_tv_faceScore);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 数据的设置 展示
		holder.imvHead.setImageResource(students.getHead());
		holder.tvName.setText(students.getScreenNames());
		String sex = "1".equals(students.getSex()) ? "男" : "女";
		holder.tvInfo.setText("性别  " + sex + "\n" + "爱好  "
				+ students.getHobby());
		holder.tvFaceScore.setText("颜值：" + students.getFaceScore());

		if ("男".equals(sex)) {
			holder.item.setBackgroundColor(Color.parseColor("#0A61BF"));
		} else {
			holder.item.setBackgroundColor(Color.parseColor("#D44C4D"));
		}

		return convertView;
	}

	/**
	 * @author kevin.li
	 * @function 持有者
	 */
	class ViewHolder {
		public RelativeLayout item;
		public ImageView imvHead;
		public TextView tvName;
		public TextView tvInfo;
		public TextView tvFaceScore;

	}

}
