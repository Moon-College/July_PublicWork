package com.example.listview;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QqAdapter extends BaseAdapter {
    private Context context;
	private List<QQ> qqs;
	private LayoutInflater inflater;
	public QqAdapter(Context context,List<QQ> qqs){
		this.context=context;
		this.inflater=inflater.from(context);
		this.qqs=qqs;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return qqs==null ? 0 :qqs.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return  qqs.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		if(convertView==null){
			convertView=inflater.inflate(R.layout.qq_item_layout, null);
			holder=new ViewHolder();
			holder.iv_head=(ImageView) convertView.findViewById(R.id.iv_head);
			holder.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_face=(TextView) convertView.findViewById(R.id.tv_face);
			holder.tv_hobby=(TextView) convertView.findViewById(R.id.tv_hobby);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		QQ qq=qqs.get(position);
		holder.tv_name.setText(qq.getName());
		holder.tv_face.setText("颜值："+qq.getFace());
		holder.tv_hobby.setText("爱好："+qq.getHobby());
		int sex=qq.getSex();
		if(sex==1){
			holder.iv_head.setImageResource(getHeadImage()[sex-1]);
			convertView.setBackgroundColor(Color.parseColor("#ffffff"));
		}else if(sex==2){
			holder.iv_head.setImageResource(getHeadImage()[sex-1]);
			convertView.setBackgroundColor(Color.parseColor("#A0A0A0"));
		}
		return convertView;
	}
	class ViewHolder{
		private ImageView iv_head;
		private TextView tv_name,tv_face,tv_hobby;
	}
	
	private int[] getHeadImage(){
		int [] Heads={R.drawable.boy,R.drawable.girl};
		return Heads;
	}
}
