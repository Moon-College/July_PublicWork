package com.tz.fileexplorer.view;

import com.tz.fileexplorer.R;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MyPopupWindow extends PopupWindow implements OnClickListener{
	private TextView mDetailTv;
	private TextView mReNameTv;
	private TextView mDelTv;
	private Context mContext;
	
	public MyPopupWindow(Context context) {
		super(View.inflate(context, R.layout.popupwindow, null), 
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		this.mContext = context;
		
		//设置属性
		this.setOutsideTouchable(true);
		this.setTouchable(true);
		this.setBackgroundDrawable(new BitmapDrawable());
		
		View contentView = View.inflate(context, R.layout.popupwindow, null);
		mDetailTv = (TextView) contentView.findViewById(R.id.tv_detail);
		mReNameTv = (TextView) contentView.findViewById(R.id.tv_rename);
		mDelTv = (TextView) contentView.findViewById(R.id.tv_delitem);
		mDetailTv.setOnClickListener(this); 
		mReNameTv.setOnClickListener(this);
		mDelTv.setOnClickListener(this);
		this.setContentView(contentView); //要加上这一行，否则没点击效果
	}

	@Override
	public void onClick(View v) {
		PopWindosOnclickInterface pwoi = (PopWindosOnclickInterface) mContext;
		switch (v.getId()) {
			case R.id.tv_detail:
				pwoi.detailOnclick();
				break;
			case R.id.tv_rename:
				pwoi.reNameOnclick();
				break;
			case R.id.tv_delitem:
				pwoi.delOnclick();
				break;
			default:
				break;
		}
	}
	
	public interface PopWindosOnclickInterface {
		void detailOnclick();
		void reNameOnclick();
		void delOnclick();
	}
	
}
