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
 * �б��г�������PopupWindow
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
		// ���õ����Ŀ�Ⱥ͸߶�
		setWidth(width);
		setHeight(height);
		// ���õ����Ĳ��ֽ���
		setContentView(LayoutInflater.from(context).inflate(R.layout.popup,
				null));
		init();
	}

	/**
	 * ��ʼ��
	 */
	private void init() {
		// ���ñ�����ɫ
		setBackgroundDrawable(new ColorDrawable());
		// ���Ի�ý���
		setFocusable(true);
		// �����������ر�
		setOutsideTouchable(true);

		initView();
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		btn_detele = (Button) getContentView().findViewById(R.id.btn_delete);
		btn_details = (Button) getContentView().findViewById(R.id.btn_details);
	}

	/**
	 * ��ʾpopupWindow������Ϊ��ť��Ӽ���
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
		// �õ�x,y������
		int[] location = new int[2];
		v.getLocationInWindow(location);
		showAtLocation(v, Gravity.TOP | Gravity.LEFT ,location[0]+100, location[1] + 10);
	}
	
	public void setClick(OnPopupClickListener click) {
		this.click = click;
	}

	/**
	 * �Զ���ӿڣ�ʵ��ɾ���Ͳ鿴���鹦��
	 */
	public interface OnPopupClickListener {
		/**
		 * ɾ���ӿ�
		 * 
		 * @param v
		 *            �ؼ�
		 * @param position
		 *            listview�е�item
		 */
		public void delete(View v, int position);

		/**
		 * �鿴����ӿ�
		 * 
		 * @param v
		 *            �ؼ�
		 * @param position
		 *            listview�е�item
		 */
		public void details(View v, int position);
	}
}
