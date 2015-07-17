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

	// LinearLayout.LayoutParams����ʼ��ΪMATCH_PARENT, MATCH_PARENT
	public LinearLayout.LayoutParams layoutParamsMM = null;

	// LinearLayout.LayoutParams����ʼ��ΪWRAP_CONTENT, WRAP_CONTENT
	public LinearLayout.LayoutParams layoutParamsWW = null;
	// LinearLayout.LayoutParams����ʼ��ΪMATCH_PARENT, WRAP_CONTENT
	public LinearLayout.LayoutParams layoutParamsMW = null;

	// �����С dp
	private int textsize = 14;

	// �м�����ȫ���ŵ�scrollView
	private ScrollView scrollView;
	// scrollView��Ψһ�Ӳ���
	private LinearLayout cv;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
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

		// ��������
		contentView = new LinearLayout(this);
		// ��Ӳ�������
		contentView.setLayoutParams(layoutParamsMM);
		// ��ֱ����
		contentView.setOrientation(LinearLayout.VERTICAL);
		// ������ɫ
		contentView.setBackgroundResource(R.color.qq_search_background);
		// titleview ------------ ���ֳ�ʼ��
		init_title();

		init_scrollView();

		// �ײ� ----------------- ���ֳ�ʼ��
		init_Bottom();
	}

	private void init_scrollView()
	{
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 0);
		lp.weight = 1;
		scrollView = new ScrollView(this);
		scrollView.setLayoutParams(lp);
		//ȥ��scrollBar
		scrollView.setVerticalScrollBarEnabled(false);
		cv = new LinearLayout(this);
		cv.setLayoutParams(layoutParamsMM);
		cv.setOrientation(LinearLayout.VERTICAL);
		// searchview------------ ���ֳ�ʼ��
		init_searchLayout();
		// ���Ѷ�̬����������Ȥ����------ ���ֳ�ʼ��
		init_thrHeadLayout();
		// Body ----------------- ���ֳ�ʼ��
		init_body_item();

		scrollView.addView(cv);
		contentView.addView(scrollView);
	}

	/**
	 * @Title: init_title
	 * @Description: (ͷ������)
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
		// ����
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
		// ����
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
	 * @Description: (�������ֳ�ʼ��)
	 * @return void ��������
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

		// Բ�Ǿ���
		LinearLayout borderView = new LinearLayout(this);
		borderView.setLayoutParams(layoutParamsMM);
		borderView.setBackgroundResource(R.drawable.shape_qq_bordder);
		borderView.setGravity(Gravity.CENTER);

		// ����
		TextView tv_search = new TextView(this);
		tv_search.setLayoutParams(layoutParamsWW);
		// ��������ͼ��
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
		// ������ɫ
		lv_thrhead.setBackgroundResource(android.R.color.white);
		// ���Ѷ�̬
		lv_thrhead.addView(getThrHead(R.drawable.ezn, R.string.tt_qq_friends));
		// ����
		lv_thrhead.addView(getThrHead(R.drawable.eqc, R.string.tt_qq_near));
		// ��Ȥ����
		lv_thrhead.addView(getThrHead(R.drawable.iei, R.string.tt_qq_funning));

		cv.addView(lv_thrhead);
	}

	/**
	 * @Title: getThrHead
	 * @Description: (getThrHead ����)
	 * @param imageResouceId
	 *            ͼƬ��Դid
	 * @param textResouceId
	 *            ������Դid
	 * @return LinearLayout ����View
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
	 * ��ʼ�� body item
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
	 * @Description: (Item����)
	 * @param @param textResouceId ��ʾ������Դid
	 * @param @param topMargin ��߾� dp
	 * @return RelativeLayout ��������
	 */
	private RelativeLayout getBodyItemLayout(int imageResouceId,int textResouceId)
	{

		RelativeLayout item = new RelativeLayout(this);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				(int) ViewUtil.dip2px(this, 50f));
		item.setLayoutParams(lp);
		// ��ֱ����
		item.setGravity(Gravity.CENTER_VERTICAL);
		// ���ñ��� �������ײ����ߣ�
		item.setBackgroundResource(R.drawable.selector_item);
		// --------------------------------
		// ���icon ����
		// --------------------------------
		ImageView icon = new ImageView(this);
		RelativeLayout.LayoutParams lpl = new RelativeLayout.LayoutParams(
				(int) ViewUtil.dip2px(this, 25f), (int) ViewUtil.dip2px(this,
						25f));
		// ���������� ���
		lpl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		// lpl.addRule(RelativeLayout.CENTER_VERTICAL);
		// ����߾�
		lpl.leftMargin = (int) ViewUtil.dip2px(this, 20f);
		icon.setLayoutParams(lpl);
		// ͼƬ�������
		icon.setScaleType(ScaleType.FIT_XY);
		// ͼƬ��Դ
		icon.setImageResource(imageResouceId);
		// ����id
		icon.setId(R.id.qq_body_item_logo);

		// --------------------------------
		// ���ֲ���
		// --------------------------------
		TextView text = new TextView(this);
		RelativeLayout.LayoutParams lpt = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		// ͣ��Icon �ұ�
		lpt.addRule(RelativeLayout.RIGHT_OF, R.id.qq_body_item_logo);
		// ����߾�
		lpt.leftMargin = (int) ViewUtil.dip2px(this, 10f);
		text.setLayoutParams(lpt);
		// ��������
		text.setText(textResouceId);
		// ����������ɫ
		text.setTextColor(getResources().getColor(android.R.color.black));
		// ���������С
		text.setTextSize(textsize);
		// ����ID
		text.setId(R.id.qq_body_item_content);

		// --------------------------------
		// �ұ� ��ͷ ����
		// --------------------------------
		ImageView arrow = new ImageView(this);
		RelativeLayout.LayoutParams lpa = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		// �����������Ҳ�
		lpa.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		// ����߾�
		lpa.rightMargin = (int) ViewUtil.dip2px(this, 20f);
		arrow.setLayoutParams(lpa);
		// ͼƬ�������
		arrow.setScaleType(ScaleType.FIT_XY);
		// ͼƬ��Դ
		arrow.setImageResource(R.drawable.skin_icon_arrow_right_normal);

		// ���Icon
		item.addView(icon);
		// �������
		item.addView(text);
		// ��Ӽ�ͷ
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
