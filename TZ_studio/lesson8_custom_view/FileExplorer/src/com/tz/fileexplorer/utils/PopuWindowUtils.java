package com.tz.fileexplorer.utils;

import java.io.File;

import com.tz.fileexplorer.R;
import com.tz.fileexplorer.bean.FileBean;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PopuWindowUtils {
	private static PopuWindowUtils  instance;
	private  Context ctx;
	
	private PopuWindowUtils(){}
	
	private  PopuWindowUtils(Context context) {
		super();
		this.ctx=context;
	}


	public static PopuWindowUtils newInstance(Context context){
		if (instance==null) {
			instance=new PopuWindowUtils(context);
		}
		return instance;
	}
	
	
	/**
	 * 创建一个PopupWindow
	 * @param height PopupWindow 高度
	 * @param filebean  
	 * @return
	 */
	public  PopupWindow getPopupWindow(int height,FileBean fileBean) {
		PopupWindow popupWindow = new PopupWindow(ctx);
		View view = createContentView(popupWindow,fileBean);
		popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
		popupWindow.setHeight(height);
		popupWindow.setContentView(view);
		popupWindow.setBackgroundDrawable(ctx.getResources().getDrawable(R.drawable.local_popup_bg));
		popupWindow.setFocusable(true);
		return popupWindow;
	}
	
	/**
	 * 
	 * 此方法描述的是：创建一个popuwindow的view
	 * @author:  studio
	 * @最后修改人： studio
	 * createContentView
	 * @param popupWindow
	 * @param filebean
	 * @return View
	 */
	private  View createContentView(final PopupWindow popupWindow, final FileBean filebean) {
		View view = View.inflate(ctx,R.layout.popup_window_view, null);
		TextView tvDetail = (TextView) view.findViewById(R.id.tv_detail);
		TextView tvDelete = (TextView) view.findViewById(R.id.tv_delete);
		TextView tvOpen = (TextView) view.findViewById(R.id.tv_open);
		
		if(!filebean.isDir()) {
			tvOpen.setVisibility(View.INVISIBLE);
		}else {
			tvOpen.setVisibility(View.VISIBLE);
		}
		
		// 详细
		tvDetail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}			
				iOperaPopWindow.opearDetail(filebean);
			}
		});
		
		// 删除
		tvDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
				iOperaPopWindow.opearDel(filebean);
			}
		});
		
		//　点击进入 
		tvOpen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
				iOperaPopWindow.opearInto(filebean);
			}
		});	
		return view;
	}
		
	IOperaPopWindow iOperaPopWindow;
	public void setOpera(IOperaPopWindow iOperaPopWindow){
		this.iOperaPopWindow=iOperaPopWindow;
	}
	
	
	public interface IOperaPopWindow{
		//详情操作
		void opearDetail(FileBean filebean);
		//删除操作
		void opearDel(FileBean filebean);
		//打开操作
		void opearInto(FileBean filebean);
	}

}
