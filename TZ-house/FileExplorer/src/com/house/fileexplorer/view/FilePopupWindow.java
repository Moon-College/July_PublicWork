package com.house.fileexplorer.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.house.fileexplorer.R;

/**
 * 列表中长按弹出PopupWindow
 */
public class FilePopupWindow extends PopupWindow {

	private Context context;
	private OnPopupClickListener click;
	private Button btn_detele,btn_details;

	public FilePopupWindow(Context context) {
		this(context,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
	}

	public FilePopupWindow(Context context, int width, int height) {
		this.context = context;
		// 设置弹窗的宽度和高度
		setWidth(width);
		setHeight(height);
		// 设置弹窗的布局界面
		setContentView(LayoutInflater.from(context).inflate(R.layout.popup,
				null));
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		// 设置背景颜色
		setBackgroundDrawable(new ColorDrawable());
		// 可以获得焦点
		setFocusable(true);
		// 点击外面区域关闭
		setOutsideTouchable(true);

		initView();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		btn_detele = (Button) getContentView().findViewById(R.id.btn_delete);
		btn_details = (Button) getContentView().findViewById(R.id.btn_details);
	}

	/**
	 * 显示popupWindow，并且为按钮添加监听
	 * @param v
	 * @param position
	 */
	public void showPopup(final View v, final int position){
		btn_details.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				click.details(v, position);
			}
		});
		
		btn_detele.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				click.delete(v, position);
			}
		});
		// 得到x,y的坐标
		int[] location = new int[2];
		v.getLocationInWindow(location);
		showAtLocation(v, Gravity.TOP | Gravity.LEFT ,location[0]+100, location[1] + 10);
	}
	
	public void setClick(OnPopupClickListener click) {
		this.click = click;
	}

	/**
	 * 自定义接口，实现删除和查看详情功能
	 */
	public interface OnPopupClickListener {
		/**
		 * 删除接口
		 * 
		 * @param v
		 *            控件
		 * @param position
		 *            listview中的item
		 */
		public void delete(View v, int position);

		/**
		 * 查看详情接口
		 * 
		 * @param v
		 *            控件
		 * @param position
		 *            listview中的item
		 */
		public void details(View v, int position);
	}
}
