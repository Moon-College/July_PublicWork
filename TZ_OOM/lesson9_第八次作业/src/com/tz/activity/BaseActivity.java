package com.tz.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * ����Activity�ı�����������Լ����� ��
 * 
 *
 */
public abstract class BaseActivity extends FragmentActivity {

	private ImageView btnLeft;
	private ImageView btnRight;
	private TextView tvTitle;
	private LinearLayout container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_base);
		initView();
		
		btnLeft.setOnClickListener(defaultOnListener);
		btnRight.setOnClickListener(defaultOnListener);
	}
	
	
	
	/**
	 * ��ʼ��View
	 */
	private void initView() {
		btnLeft = (ImageView)findViewById(R.id.btn_left);
		btnRight = (ImageView)findViewById(R.id.btn_right);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		container = (LinearLayout) findViewById(R.id.container);
	}
	
	/**
	 * ���ò�������
	 * @param resId ������ԴID
	 */
	protected View setContent(int resId) {
		if(resId <= 0) {
			throw new RuntimeException("������ԴId����Ϊ0");
		}
		View view = getLayoutInflater().inflate(resId, null);
		setContent(view);
		return view;
	}
	
	/**
	 * ���ò�������
	 * @param view ����View 
	 */
	protected void setContent(View view) {
		if(view == null) {
			throw new RuntimeException("����View����Ϊ��");
		}
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
																		LinearLayout.LayoutParams.MATCH_PARENT, 
																		LinearLayout.LayoutParams.MATCH_PARENT);
		view.setLayoutParams(params);
		
		if(container != null) {
			container.addView(view);
		}
	}

	/**
	 * ������߰�ťͼƬ
	 * @param resId ͼƬ��ԴId
	 */
	protected void setLeftButtonImage(int resId) {
		if(btnLeft != null) {
			if(resId > 0) {
				btnLeft.setImageResource(resId);
			}
		}
	}
	
	/**
	 * �����ұ߰�ťͼƬ
	 * @param resId ͼƬ��ԴId
	 */
	protected void setRightButtonImage(int resId) {
		if(btnRight != null) {
			if(resId > 0) {
				btnRight.setImageResource(resId);
			}
		}
	}
	
	/**
	 * ������߰�ť����ɫ
	 * @param color ��ɫ
	 */
	protected void setLeftButtonBackgroundColor(int color) {
		if(btnLeft != null) {
			if(color > 0) {
				btnLeft.setBackgroundColor(color);
			}
		}
	}
	
	/**
	 * �����ұ߰�ť����ɫ
	 * @param color ��ɫ
	 */
	protected void setRightButtonBackgroundColor(int color) {
		if(btnRight != null) {
			if(color > 0) {
				btnRight.setBackgroundColor(color);
			}
		}
	}
	/**
	 * ������߰�ť����ɫ
	 * @param d drawable
	 */
	protected void setLeftButtonBackgroundDrawable(Drawable d) {
		if(btnLeft != null) {
			if(d != null) {
				btnLeft.setBackgroundDrawable(d);
			}
		}
	}
	
	/**
	 * �����ұ߰�ť����ɫ
	 * @param d drawable
	 */
	protected void setRightButtonBackgroundDrawable(Drawable d) {
		if(btnRight != null) {
			if(d != null) {
				btnRight.setBackgroundDrawable(d);
			}
		}
	}
	
	protected void setLeftButtonBackgroundResource(int resId) {
		if(btnLeft != null) {
			if(resId > 0) {
				btnLeft.setBackgroundResource(resId);
			}
		}
	}
	
	protected void setRightButtonBackgroundResource(int resId) {
		if(btnRight != null) {
			if(resId > 0) {
				btnRight.setBackgroundResource(resId);
			}
		}
	}
	
	protected void setTitleTextColor(int color) {
		if(tvTitle != null) {
			if(color > 0) {
				tvTitle.setTextColor(color);
			}
		}
	}
	
	/**
	 * ���ñ���
	 * @param title �ı�
	 */
	protected void setTitleText(String title) {
		if(tvTitle != null) {
			tvTitle.setText(title);
		}
	}
	
	/**
	 * ���ñ��� 
	 * @param resId ������ԴId
	 */
	protected void setTitleText(int resId) {
		if(tvTitle != null) {
			if(resId > 0) {
				tvTitle.setText(resId);
			}
		}
	}
	
	/**
	 * ���ñ��ⱳ��
	 * @param color
	 */
	protected void setTitleBackgroundColor(int color) {
		if(tvTitle != null) {
			if(color > 0) {
				tvTitle.setBackgroundColor(color);
			}
		}
	}
	/**
	 * ���ñ��ⱳ��
	 * @param drawable
	 */
	protected void setTitleBackgroundDrawable(Drawable drawable) {
		if(tvTitle != null) {
			if(drawable != null) {
				tvTitle.setBackgroundDrawable(drawable);
			}
		}
	}
	/**
	 * ���ñ��ⱳ��
	 * @param resId
	 */
	protected void setTitleBackgroundResource(int resId) {
		if(tvTitle != null) {
			if(resId > 0) {
				tvTitle.setBackgroundResource(resId);
			}
		}
	}
	
	/**
	 * ���ñ��������С 
	 * @param fontSize �����С
	 */
	protected void setTitleFontSize(float fontSize) {
		if(tvTitle != null) {
			tvTitle.setTextSize(fontSize);
		}
	}
	
	
	protected void setTitleFontSize(int unit, float fontSize) {
		if(tvTitle != null) {
			tvTitle.setTextSize(unit,fontSize);
		}
	}


	protected void setLeftButtonOnClickListener(OnClickListener onClickListener) {
		if(btnLeft != null) {
			if(onClickListener != null) {
				btnLeft.setOnClickListener(onClickListener);
			}
		}
	}
	
	protected void setRightButtonOnClickListener(OnClickListener onClickListener) {
		if(btnRight != null) {
			if(onClickListener != null) {
				btnRight.setOnClickListener(onClickListener);
			}
		}
	}
	
	/**
	 * ��ʾToast
	 * @param message ��Ϣ
 	 * @param duration ��ʾʱ�� 
	 */
	protected void showToast(String message, int duration) {
		Toast.makeText(this, message, duration).show();
	}
	
	
	OnClickListener defaultOnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.btn_left) {
				finish();
			}
			else if(v.getId() == R.id.btn_right) {
				Toast.makeText(BaseActivity.this, "����ұ߰�ť", Toast.LENGTH_SHORT).show();
			}
		}
		
	};

}
