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
 * 隐藏Activity的标题栏，添加自己标题 栏
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
	 * 初始化View
	 */
	private void initView() {
		btnLeft = (ImageView)findViewById(R.id.btn_left);
		btnRight = (ImageView)findViewById(R.id.btn_right);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		container = (LinearLayout) findViewById(R.id.container);
	}
	
	/**
	 * 设置布局内容
	 * @param resId 布局资源ID
	 */
	protected View setContent(int resId) {
		if(resId <= 0) {
			throw new RuntimeException("布局资源Id不能为0");
		}
		View view = getLayoutInflater().inflate(resId, null);
		setContent(view);
		return view;
	}
	
	/**
	 * 设置布局内容
	 * @param view 布局View 
	 */
	protected void setContent(View view) {
		if(view == null) {
			throw new RuntimeException("布局View不能为空");
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
	 * 设置左边按钮图片
	 * @param resId 图片资源Id
	 */
	protected void setLeftButtonImage(int resId) {
		if(btnLeft != null) {
			if(resId > 0) {
				btnLeft.setImageResource(resId);
			}
		}
	}
	
	/**
	 * 设置右边按钮图片
	 * @param resId 图片资源Id
	 */
	protected void setRightButtonImage(int resId) {
		if(btnRight != null) {
			if(resId > 0) {
				btnRight.setImageResource(resId);
			}
		}
	}
	
	/**
	 * 设置左边按钮背景色
	 * @param color 颜色
	 */
	protected void setLeftButtonBackgroundColor(int color) {
		if(btnLeft != null) {
			if(color > 0) {
				btnLeft.setBackgroundColor(color);
			}
		}
	}
	
	/**
	 * 设置右边按钮背景色
	 * @param color 颜色
	 */
	protected void setRightButtonBackgroundColor(int color) {
		if(btnRight != null) {
			if(color > 0) {
				btnRight.setBackgroundColor(color);
			}
		}
	}
	/**
	 * 设置左边按钮背景色
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
	 * 设置右边按钮背景色
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
	 * 设置标题
	 * @param title 文本
	 */
	protected void setTitleText(String title) {
		if(tvTitle != null) {
			tvTitle.setText(title);
		}
	}
	
	/**
	 * 设置标题 
	 * @param resId 标题资源Id
	 */
	protected void setTitleText(int resId) {
		if(tvTitle != null) {
			if(resId > 0) {
				tvTitle.setText(resId);
			}
		}
	}
	
	/**
	 * 设置标题背景
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
	 * 设置标题背景
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
	 * 设置标题背景
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
	 * 设置标题字体大小 
	 * @param fontSize 字体大小
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
	 * 显示Toast
	 * @param message 消息
 	 * @param duration 显示时间 
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
				Toast.makeText(BaseActivity.this, "点击右边按钮", Toast.LENGTH_SHORT).show();
			}
		}
		
	};

}
