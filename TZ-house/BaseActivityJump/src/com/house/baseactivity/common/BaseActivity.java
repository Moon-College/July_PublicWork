package com.house.baseactivity.common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.house.baseactivity.R;

/**
 * Activity�Ļ���
 */
public abstract class BaseActivity extends Activity implements OnClickListener{
	
	private Button btn_left;
	private Button btn_right;
	private TextView tv_title;
	private LinearLayout ll_content;
	
	protected Context context;
	protected View contentView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		setContentView(R.layout.activity_base);
		context = this;
		initView();
	}

	/**
	 * ��ʼ��UI�ؼ�
	 */
	private void initView() {
		btn_left = (Button) findViewById(R.id.btn_left);
		btn_right = (Button) findViewById(R.id.btn_right);
		tv_title = (TextView) findViewById(R.id.tv_title);
		
		ll_content = (LinearLayout) findViewById(R.id.ll_content);
		
		btn_left.setOnClickListener(this);
		btn_right.setOnClickListener(this);
	}
	
	/**
	 * ����������Ӳ����ļ�
	 * @param resId �����ļ�id
	 */
	protected void setContentLayout(int resId){
		View contentView = View.inflate(getApplicationContext(), resId, null); 
		setContentLayout(contentView);
	}
	
	/**
	 * ����������Ӳ���
	 * @param contentView 
	 */
	protected void setContentLayout(View contentView){
		this.contentView = contentView;
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		contentView.setLayoutParams(params);
		if(ll_content!=null){
			ll_content.addView(contentView);
		}
	}
	
	/**
	 * �õ�View
	 * @return
	 */
	protected View getContentView(){
		return contentView;
	}
	
	/**
	 * ���ñ���
	 */
	protected void setTitleForString(String title){
		if (tv_title != null) {
			tv_title.setText(title);
		}
	}
	
	/**
	 * ���ñ���
	 */
	protected void setTitleForInt(int title){
		if (tv_title != null) {
			tv_title.setText(getResources().getString(title));
		}
	}
	
	/**
	 * ������ఴť�ļ����¼�
	 */
	protected void setLeftButtonOnClickListener(){
		
	}
	
	/**
	 * �����Ҳఴť�ļ����¼�
	 */
	protected void setRightButtonOnClickListener(){
		
	}
	
	/**
	 * ������ఴť
	 */
	protected void hideLeftButton(){
		if (btn_left != null) {
			btn_left.setVisibility(View.INVISIBLE);
		}
	}
	
	/**
	 * �����Ҳఴť
	 */
	protected void hideRightButton(){
		if (btn_right != null) {
			btn_right.setVisibility(View.INVISIBLE);
		}
	}
	
	/**
	 * ������ఴť�ı���ͼƬ
	 * @param ͼƬ��id
	 */
	protected void setLeftButtonBackground(int id){
		if (btn_left != null) {
			btn_left.setBackgroundResource(id);
		}
	}
	
	/**
	 * ������ఴť������
	 * @param text
	 */
	protected void setLeftButtonText(String text){
		if (btn_left != null) {
			btn_left.setText(text);
		}
	}
	
	/**
	 * ������ఴť�ı���ͼƬ
	 * @param ͼƬ��id
	 */
	protected void setRightButtonBackground(int id){
		if (btn_right != null) {
			btn_right.setBackgroundResource(id);
		}
	}
	
	/**
	 * ������ఴť������
	 * @param text
	 */
	protected void setRightButtonText(String text){
		if (btn_right != null) {
			btn_right.setText(text);
		}
	}
	
	/**
	 * Ϊ��ť��Ӽ���
	 */
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_left:
			setLeftButtonOnClickListener();
			break;
		case R.id.btn_right:
			setRightButtonOnClickListener();
			break;
		}
	}
}
