package com.tz.layout;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QqZoneActivity extends FragmentActivity {

	private static final int ID_TABS = 10;
	private static final int ID_IMAGE = 11;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_qq_zone);
		
		setActionBar();
		
		initLayoutWithCode();
	}
	
	

	private void setActionBar() {
		TextView tvTitle = new TextView(this);
		tvTitle.setTextColor(Color.WHITE);
		tvTitle.setTextSize(14);
		tvTitle.setText("动态");
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER;
		tvTitle.setLayoutParams(params);
		
		ActionBar actionBar = getActionBar();
		
		actionBar.setCustomView(tvTitle);
		
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setBackgroundDrawable(new ActionBarDrawable(Color.parseColor("#25B6ED")));
	}

	
	class ActionBarDrawable extends ColorDrawable {
		public ActionBarDrawable(int color) {
			setColor(color);
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.qq_zone, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * 代码实现 而已
	 */
	private void initLayoutWithCode() {
		// 创建顶层父容器
		RelativeLayout.LayoutParams parentRelativeLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
																								 RelativeLayout.LayoutParams.MATCH_PARENT);
		RelativeLayout parentRelativeLayout = createRelativeLayout(parentRelativeLayoutParams);
		// 创建底部Tabs布局
		LinearLayout tabsLinearLayout = createButtomTabsLayout();
		// 为底部Tabs布局设置一个ID。后面需要用到。
		tabsLinearLayout.setId(ID_TABS);
		// 将底部Tabs布局添加到父容器中
		parentRelativeLayout.addView(tabsLinearLayout);
		
		
		// 创建一个外层线性布局参数
		RelativeLayout.LayoutParams linearParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 
																			   	   RelativeLayout.LayoutParams.MATCH_PARENT);
		// 设置外层线性布局摆放，相对底部Tabs布局	
		linearParams.addRule(RelativeLayout.ABOVE, ID_TABS);
		// 创建一个线性布局
		LinearLayout linearLayout = createLinearLayout(linearParams, LinearLayout.VERTICAL);
		linearLayout.setBackgroundColor(Color.parseColor("#F8F8F8"));
		// 创建搜索布局
		LinearLayout searchLinearLayout = createSearchLayout();
		// 将搜索布局添加到线性布局中
		linearLayout.addView(searchLinearLayout);
		
		// 创建一个顶部选项卡布局
		LinearLayout topTabsLinearLayout = createTopTabsLayout();
		linearLayout.addView(topTabsLinearLayout);
		
		/**
		 * 因为所有图标一样，所以只传入名称。。
		 */
		LinearLayout fristLayout = createList("游戏","购物","阅读");
		linearLayout.addView(fristLayout);
		
		LinearLayout secondLayout = createList("附近的群","吃喝玩乐","同城服务");
		linearLayout.addView(secondLayout);
		
		parentRelativeLayout.addView(linearLayout);
		// 将父容器添加到窗口
		setContentView(parentRelativeLayout);
	}
	
	/**
	 * 创建RelativeLayout容器
	 * @return RelativeLayout
	 */
	private RelativeLayout createRelativeLayout(RelativeLayout.LayoutParams params) {
		RelativeLayout relativeLayout = new RelativeLayout(this);
		relativeLayout.setLayoutParams(params);
		return relativeLayout;
	}
	
	/**
	 * 创建一个线性布局
	 * @param width 宽度
	 * @param height 高度
	 * @return LinearLayout
	 */
	private LinearLayout createLinearLayout(ViewGroup.LayoutParams params, int orientation) {
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(orientation);
		linearLayout.setLayoutParams(params);
		return linearLayout;
	}
	
	
	
	/**
	 * 创建底部Tabs 布局
	 * @return 
	 */
	private LinearLayout createButtomTabsLayout() {
		// 创建Tabs布局参数
		RelativeLayout.LayoutParams tabsParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
																			LinearLayout.LayoutParams.WRAP_CONTENT);
		// 设置 靠底部显示
		tabsParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		
		// 创建Tabs 布局窗口
		LinearLayout tabsLayout = createLinearLayout(tabsParams, LinearLayout.HORIZONTAL);
		tabsLayout.setBackgroundResource(R.drawable.linear_buttom_bg_shape);
		
		// 创建选项布局参数
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
																		 LinearLayout.LayoutParams.WRAP_CONTENT,1);
										
									
		// 创建消息 Tab
		ImageView messageImageView = createImageView(params,R.drawable.message_selector);
		// 创建联系人 Tab
		ImageView constactImageView = createImageView(params,R.drawable.constact_selector);
		// 创建动态  Tab
		ImageView dynamictImageView = createImageView(params,R.drawable.dynamic_selector);
		
		
		tabsLayout.addView(messageImageView);
		tabsLayout.addView(constactImageView);
		tabsLayout.addView(dynamictImageView);
		
		return tabsLayout;
	}
	
	/**
	 * 创建搜索布局
	 * @return
	 */
	private LinearLayout createSearchLayout() {
		// 创建Tabs布局参数
		RelativeLayout.LayoutParams searchParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
																				   LinearLayout.LayoutParams.WRAP_CONTENT);
		
		LinearLayout searchLinearLayout = createLinearLayout(searchParams, LinearLayout.HORIZONTAL);
		// 设置背景色
		searchLinearLayout.setBackgroundColor(Color.parseColor("#EBECED"));
		
		EditText editText = new EditText(this);
		editText.setBackgroundResource(R.drawable.edit_text_shape);
		
		LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
																			 LinearLayout.LayoutParams.WRAP_CONTENT);
		editTextParams.height = 45;
		editTextParams.setMargins(10, 10, 10, 10);
		editText.setLayoutParams(editTextParams);
		
		searchLinearLayout.addView(editText);
		return searchLinearLayout;
	}
	
	/**
	 * 创建 顶部选项卡布局
	 * @return
	 */
	private LinearLayout createTopTabsLayout() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
																		 LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout linearLayout = createLinearLayout(params, LinearLayout.HORIZONTAL);
		
		linearLayout.setPadding(0, 3, 0, 3);
		linearLayout.setBackgroundColor(Color.WHITE);
		LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1);
		TextView dynamictTextView = createTextView(textViewParams, "好友动态", R.drawable.igs);
		TextView nearTextView = createTextView(textViewParams, "附近", R.drawable.eqc);
		TextView hobbiesTextView = createTextView(textViewParams, "兴趣部落", R.drawable.iei);
		
		
		linearLayout.addView(dynamictTextView);
		linearLayout.addView(nearTextView);
		linearLayout.addView(hobbiesTextView);
		return linearLayout;
		
	}
	
	
	/**
	 * 创建列表 传Item名称
	 * @param itemNames item名称数组
	 * @return 返回一个items列表 View
	 */
	private LinearLayout createList(String... itemNames) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
																		 LinearLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin = 10;
		LinearLayout linearLayout = createLinearLayout(params, LinearLayout.VERTICAL);
		linearLayout.setBackgroundColor(Color.parseColor("#DEDFE0"));
		
		for(int i = 0; i < itemNames.length ; i++) {
			// 创建一个Item
			RelativeLayout itemLayout = createItem(itemNames[i]);
			LinearLayout.LayoutParams itemsParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			itemsParams.topMargin = 1;

			linearLayout.addView(itemLayout, itemsParams);
		}
		
		return linearLayout;
	}
	
	/**
	 * 创建一个Item
	 * @return
	 */
	private RelativeLayout createItem(String text) {
		RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
																						   RelativeLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout relativeLayout = createRelativeLayout(relativeLayoutParams);
		relativeLayout.setBackgroundColor(Color.WHITE);
	
		// 左边图标 
		RelativeLayout.LayoutParams logoParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
																				 RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		logoParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		logoParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		logoParams.setMargins(5, 0, 0, 0);
		ImageView logoImageView = createImageView(logoParams, R.drawable.eqc);
		logoImageView.setId(ID_IMAGE);
		relativeLayout.addView(logoImageView);
		
		// 文字
		RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
																					 RelativeLayout.LayoutParams.WRAP_CONTENT);
		/**
		 * Right_of 表示 ，在某个控件的右边，
		 * ALIGN_RIGHT 表示 和某个控件的右边对齐 。
		 */
		
		textViewParams.addRule(RelativeLayout.RIGHT_OF, logoImageView.getId());
		textViewParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		textViewParams.setMargins(5, 0, 0, 0);
		TextView textView = createTextView(textViewParams, text, -1);
		relativeLayout.addView(textView);
		
		// 右边图标 
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
																			 RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		params.setMargins(0, 0, 5, 0);
		
		ImageView imageView = createImageView(params, R.drawable.ie_arrow1_disable);
		relativeLayout.addView(imageView);
		
		return relativeLayout;
	}
	
	
	/**
	 * 创建ImageView
	 * @param params
	 * @param resId
	 * @return
	 */
	private ImageView createImageView(ViewGroup.LayoutParams params,int resId) {
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(resId);
		imageView.setLayoutParams(params);
		return imageView;
	}
	
	/**
	 * 创建一个TextView
	 * @param params 布局参数
	 * @param text 显示文本
	 * @param resId 图片资源Id
	 * @return TextView
	 */
	private TextView createTextView(ViewGroup.LayoutParams params,String text, int resId) {
		TextView textView = new TextView(this);
		textView.setTextSize(12);
		textView.setText(text);
		textView.setGravity(Gravity.CENTER);
		if(resId > 0) {
			Drawable topDrawable = getResources().getDrawable(resId);
			textView.setCompoundDrawablesWithIntrinsicBounds(null, topDrawable, null, null);
		}
		
		textView.setLayoutParams(params);
		return textView;
	}
}
