package com.tz.tt;

import com.tz.tt.util.ViewUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class QQActivity extends Activity {

	private LinearLayout contentView;

	// LinearLayout.LayoutParams，初始化为MATCH_PARENT, MATCH_PARENT
	public LinearLayout.LayoutParams layoutParamsMM = null;

	// LinearLayout.LayoutParams，初始化为WRAP_CONTENT, WRAP_CONTENT
	public LinearLayout.LayoutParams layoutParamsWW = null;
	// LinearLayout.LayoutParams，初始化为MATCH_PARENT, WRAP_CONTENT
	public LinearLayout.LayoutParams layoutParamsMW = null;

	// 字体大小 dp
	private int textsize = 14;

	// 中间内容全部放到scrollView
	private ScrollView scrollView;
	// scrollView的唯一子布局
	private LinearLayout cv;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		initView();
		setContentView(contentView);

	}

	private void initView()
	{

		layoutParamsMM = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		layoutParamsWW = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		layoutParamsMW = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);

		// 容器布局
		contentView = new LinearLayout(this);
		// 添加布局属性
		contentView.setLayoutParams(layoutParamsMM);
		// 垂直布局
		contentView.setOrientation(LinearLayout.VERTICAL);
		// 背景颜色
		contentView.setBackgroundResource(R.color.qq_search_background);
		// titleview ------------ 布局初始化
		init_title();

		init_scrollView();

		// 底部 ----------------- 布局初始化
		init_Bottom();
	}

	private void init_scrollView()
	{
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 0);
		lp.weight = 1;
		scrollView = new ScrollView(this);
		scrollView.setLayoutParams(lp);
		//去掉scrollBar
		scrollView.setVerticalScrollBarEnabled(false);
		cv = new LinearLayout(this);
		cv.setLayoutParams(layoutParamsMM);
		cv.setOrientation(LinearLayout.VERTICAL);
		// searchview------------ 布局初始化
		init_searchLayout();
		// 好友动态，附近，兴趣部落------ 布局初始化
		init_thrHeadLayout();
		// Body ----------------- 布局初始化
		init_body_item();

		scrollView.addView(cv);
		contentView.addView(scrollView);
	}

	/**
	 * @Title: init_title
	 * @Description: (头部布局)
	 */
	private void init_title()
	{
		// content
		RelativeLayout titleView = new RelativeLayout(this);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				(int) ViewUtil.dip2px(this, 50f));
		titleView.setLayoutParams(lp);
		titleView.setBackgroundResource(R.color.qq_title_tbackground);

		RelativeLayout.LayoutParams lpb = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lpb.addRule(RelativeLayout.CENTER_IN_PARENT);
		// 标题
		TextView tv_title = new TextView(this);
		tv_title.setLayoutParams(lpb);
		tv_title.setText(R.string.tt_main_action);
		tv_title.setTextColor(getResources().getColor(android.R.color.white));
		tv_title.setTextSize(16);

		titleView.addView(tv_title);

		RelativeLayout.LayoutParams lpm = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lpm.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		lpm.addRule(RelativeLayout.CENTER_VERTICAL);
		lpm.rightMargin = (int) ViewUtil.dip2px(this, 10f);
		// 标题
		TextView tv_more = new TextView(this);
		tv_more.setLayoutParams(lpm);
		tv_more.setText(R.string.tt_main_more);
		tv_more.setTextColor(getResources().getColor(android.R.color.white));
		tv_more.setTextSize(16);
		titleView.addView(tv_more);

		contentView.addView(titleView);
	}

	/***
	 * @Title: init_searchLayout
	 * @Description: (搜索布局初始化)
	 * @return void 返回类型
	 */
	private void init_searchLayout()
	{
		// content
		LinearLayout searchView = new LinearLayout(this);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) ViewUtil.dip2px(this, 50f));
		searchView.setLayoutParams(lp);
		searchView.setBackgroundResource(R.color.qq_search_background);
		searchView.setGravity(Gravity.CENTER);
		searchView.setPadding((int) ViewUtil.dip2px(this, 15f),
				(int) ViewUtil.dip2px(this, 10f),
				(int) ViewUtil.dip2px(this, 15f),
				(int) ViewUtil.dip2px(this, 10f));

		// 圆角矩形
		LinearLayout borderView = new LinearLayout(this);
		borderView.setLayoutParams(layoutParamsMM);
		borderView.setBackgroundResource(R.drawable.shape_qq_bordder);
		borderView.setGravity(Gravity.CENTER);

		// 搜索
		TextView tv_search = new TextView(this);
		tv_search.setLayoutParams(layoutParamsWW);
		// 设置搜索图标
		tv_search.setCompoundDrawablesWithIntrinsicBounds(
				R.drawable.skin_searchbar_icon, 0, 0, 0);
		tv_search.setText(R.string.tt_qq_search);
		tv_search.setTextColor(getResources().getColor(R.color.qq_search_text));
		tv_search.setTextSize(14);

		borderView.addView(tv_search);
		searchView.addView(borderView);
		cv.addView(searchView);
	}

	private void init_thrHeadLayout()
	{
		// content
		LinearLayout lv_thrhead = new LinearLayout(this);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) ViewUtil.dip2px(this, 90f));
		lv_thrhead.setLayoutParams(lp);
		// 背景颜色
		lv_thrhead.setBackgroundResource(android.R.color.white);
		// 好友动态
		lv_thrhead.addView(getThrHead(R.drawable.ezn, R.string.tt_qq_friends));
		// 附近
		lv_thrhead.addView(getThrHead(R.drawable.eqc, R.string.tt_qq_near));
		// 兴趣部落
		lv_thrhead.addView(getThrHead(R.drawable.iei, R.string.tt_qq_funning));

		cv.addView(lv_thrhead);
	}

	/**
	 * @Title: getThrHead
	 * @Description: (getThrHead 布局)
	 * @param imageResouceId
	 *            图片资源id
	 * @param textResouceId
	 *            文字资源id
	 * @return LinearLayout 返回View
	 */
	public LinearLayout getThrHead(int imageResouceId, int textResouceId)
	{
		LinearLayout lv_friends = new LinearLayout(this);
		LayoutParams lpf = new LayoutParams(0, LayoutParams.MATCH_PARENT);
		lpf.weight = 1;
		lv_friends.setLayoutParams(lpf);
		lv_friends.setOrientation(LinearLayout.VERTICAL);
		lv_friends.setGravity(Gravity.CENTER);

		ImageView topIcon = new ImageView(this);
		LayoutParams lpt = new LayoutParams((int) ViewUtil.dip2px(this, 40f),
				(int) ViewUtil.dip2px(this, 40f));
		topIcon.setLayoutParams(lpt);
		topIcon.setScaleType(ScaleType.FIT_XY);
		topIcon.setImageResource(imageResouceId);

		TextView bottonText = new TextView(this);
		bottonText.setLayoutParams(layoutParamsWW);
		bottonText.setText(textResouceId);
		bottonText.setTextColor(getResources().getColor(android.R.color.black));
		bottonText.setTextSize(textsize);

		lv_friends.addView(topIcon);
		lv_friends.addView(bottonText);
		return lv_friends;
	}

	/**
	 * 初始化 body item
	 */
	private void init_body_item()
	{
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) ViewUtil.dip2px(this, 20f));
		View view1 = new View(this);
		view1.setLayoutParams(lp);
		view1.setBackgroundResource(android.R.color.transparent);
		cv.addView(view1);
		cv.addView(getBodyItemLayout(R.drawable.fnj,R.string.tt_qq_game));
		cv.addView(getBodyItemLayout(R.drawable.fnj,R.string.tt_qq_shop));
		cv.addView(getBodyItemLayout(R.drawable.fnj,R.string.tt_qq_app));
		View view2 = new View(this);
		view2.setLayoutParams(lp);
		view2.setBackgroundResource(android.R.color.transparent);
		cv.addView(view2);
		cv.addView(getBodyItemLayout(R.drawable.fnj,R.string.tt_qq_neargroup));
		cv.addView(getBodyItemLayout(R.drawable.fnj,R.string.tt_qq_funning));
		cv.addView(getBodyItemLayout(R.drawable.fnj,R.string.tt_qq_samecity));
	}

	/**
	 * 
	 * @Title: getBodyItemLayout
	 * @Description: (Item布局)
	 * @param @param textResouceId 显示文字资源id
	 * @param @param topMargin 外边距 dp
	 * @return RelativeLayout 返回类型
	 */
	private RelativeLayout getBodyItemLayout(int imageResouceId,int textResouceId)
	{

		RelativeLayout item = new RelativeLayout(this);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				(int) ViewUtil.dip2px(this, 50f));
		item.setLayoutParams(lp);
		// 垂直居中
		item.setGravity(Gravity.CENTER_VERTICAL);
		// 设置背景 （包括底部的线）
		item.setBackgroundResource(R.drawable.selector_item);
		// --------------------------------
		// 左边icon 布局
		// --------------------------------
		ImageView icon = new ImageView(this);
		RelativeLayout.LayoutParams lpl = new RelativeLayout.LayoutParams(
				(int) ViewUtil.dip2px(this, 25f), (int) ViewUtil.dip2px(this,
						25f));
		// 悬靠父布局 左侧
		lpl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		// lpl.addRule(RelativeLayout.CENTER_VERTICAL);
		// 左外边距
		lpl.leftMargin = (int) ViewUtil.dip2px(this, 20f);
		icon.setLayoutParams(lpl);
		// 图片填充类型
		icon.setScaleType(ScaleType.FIT_XY);
		// 图片资源
		icon.setImageResource(imageResouceId);
		// 设置id
		icon.setId(R.id.qq_body_item_logo);

		// --------------------------------
		// 文字布局
		// --------------------------------
		TextView text = new TextView(this);
		RelativeLayout.LayoutParams lpt = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		// 停靠Icon 右边
		lpt.addRule(RelativeLayout.RIGHT_OF, R.id.qq_body_item_logo);
		// 左外边距
		lpt.leftMargin = (int) ViewUtil.dip2px(this, 10f);
		text.setLayoutParams(lpt);
		// 设置文字
		text.setText(textResouceId);
		// 设置字体颜色
		text.setTextColor(getResources().getColor(android.R.color.black));
		// 设置字体大小
		text.setTextSize(textsize);
		// 设置ID
		text.setId(R.id.qq_body_item_content);

		// --------------------------------
		// 右边 箭头 布局
		// --------------------------------
		ImageView arrow = new ImageView(this);
		RelativeLayout.LayoutParams lpa = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		// 悬靠父布局右侧
		lpa.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		// 右外边距
		lpa.rightMargin = (int) ViewUtil.dip2px(this, 20f);
		arrow.setLayoutParams(lpa);
		// 图片填充类型
		arrow.setScaleType(ScaleType.FIT_XY);
		// 图片资源
		arrow.setImageResource(R.drawable.skin_icon_arrow_right_normal);

		// 添加Icon
		item.addView(icon);
		// 添加文字
		item.addView(text);
		// 添加箭头
		item.addView(arrow);

		return item;
	}

	private void init_Bottom()
	{
		LinearLayout bottomView = new LinearLayout(this);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) ViewUtil.dip2px(this, 48f));
		bottomView.setLayoutParams(lp);
		bottomView.setBackgroundResource(R.drawable.line_bottom_top);
		bottomView.addView(getButtomItem(R.drawable.qq_profilecard_icon_msg_nor,
				R.string.tt_main_msg));
		bottomView.addView(getButtomItem(R.drawable.exz,
				R.string.tt_main_linksper));
		bottomView.addView(getButtomItem(R.drawable.fcd,
				R.string.tt_main_action));
		contentView.addView(bottomView);
	}

	private LinearLayout getButtomItem(int imageResouceId, int textResouceId)
	{
		LinearLayout lv_bottom = new LinearLayout(this);
		LayoutParams lpf = new LayoutParams(0, LayoutParams.MATCH_PARENT);
		lpf.weight = 1;
		lv_bottom.setLayoutParams(lpf);
		lv_bottom.setOrientation(LinearLayout.VERTICAL);
		lv_bottom.setGravity(Gravity.CENTER);

		ImageView topIcon = new ImageView(this);
		LayoutParams lpt = new LayoutParams((int) ViewUtil.dip2px(this, 20f),
				(int) ViewUtil.dip2px(this, 20f));
		topIcon.setLayoutParams(lpt);
		topIcon.setScaleType(ScaleType.FIT_XY);
		topIcon.setImageResource(imageResouceId);

		TextView bottonText = new TextView(this);
		bottonText.setLayoutParams(layoutParamsWW);
		bottonText.setText(textResouceId);
		bottonText.setTextColor(getResources().getColor(android.R.color.black));
		bottonText.setTextSize(12);

		lv_bottom.addView(topIcon);
		lv_bottom.addView(bottonText);

		return lv_bottom;
	}
}
