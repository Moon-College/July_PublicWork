package com.tz.qq;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QqActivity extends Activity {

	private List<ImageView> images;
	private List<TextView> tViews;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		images = new ArrayList<ImageView>();
		tViews = new ArrayList<TextView>();

		RelativeLayout rl = new RelativeLayout(this);
		rl.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		rl.setBackgroundColor(Color.parseColor("#EBECED"));

		RelativeLayout rl_top = new RelativeLayout(this);
		rl_top.setId(5);
		rl_top.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		rl_top.setBackgroundColor(Color.parseColor("#18B4ED"));

		setTopLayOut(rl_top);

		EditText et_search = setMiddleFfirst();

		LinearLayout ll_function = setMiddleSecond();

		LinearLayout ll_item = setMiddleThridLayout();

		LinearLayout ll_buttom = setBottomLayout();

		addview(rl, rl_top, et_search, ll_function, ll_item, ll_buttom);

		setContentView(rl);
	}

	private void addview(RelativeLayout rl, RelativeLayout rl_top,
			EditText et_search, LinearLayout ll_function, LinearLayout ll_item,
			LinearLayout ll_buttom) {
		rl.addView(ll_buttom);
		rl.addView(ll_item);
		rl.addView(ll_function);
		rl.addView(et_search);
		rl.addView(rl_top);
	}

	/**
	 * 上面搜索布局的实现
	 * 
	 * @return
	 */
	private EditText setMiddleFfirst() {
		EditText et_search = new EditText(this);
		et_search.setId(6);
		RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		et_search.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				Tools.DPtoPX(this, 40)));
		params2.setMargins(Tools.DPtoPX(this, 10), Tools.DPtoPX(this, 10),
				Tools.DPtoPX(this, 10), Tools.DPtoPX(this, 10));
		params2.addRule(RelativeLayout.BELOW, 5);
		params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
		et_search.setLayoutParams(params2);
		et_search.setBackgroundResource(R.drawable.roundrectangle);
		et_search.setGravity(Gravity.CENTER);
		et_search.setHint("自拍");
		return et_search;
	}

	/**
	 * 好友动态、附近、兴趣部落
	 * 
	 * @return
	 */
	private LinearLayout setMiddleSecond() {
		LinearLayout ll_function = new LinearLayout(this);
		ll_function.setId(8);
		RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params3.addRule(RelativeLayout.BELOW, 6);
		ll_function.setLayoutParams(params3);
		ll_function.setBackgroundColor(Color.WHITE);
		ll_function.setOrientation(LinearLayout.HORIZONTAL);

		for (int i = 0; i < 3; i++) {
			RelativeLayout rl1 = new RelativeLayout(this);
			rl1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, 1.0f));
			rl1.setPadding(Tools.DPtoPX(this, 10), Tools.DPtoPX(this, 10),
					Tools.DPtoPX(this, 10), Tools.DPtoPX(this, 10));

			ImageView img_notice = new ImageView(this);

			TextView tv1 = new TextView(this);

			img_notice.setId(7);
			RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(
					Tools.DPtoPX(this, 40), Tools.DPtoPX(this, 40));
			params4.addRule(RelativeLayout.CENTER_HORIZONTAL);
			img_notice.setLayoutParams(params4);
			if (i == 0) {
				img_notice.setImageResource(R.drawable.fes);
				tv1.setText("好友动态");
			} else if (i == 1) {
				img_notice.setImageResource(R.drawable.eqc);
				tv1.setText("附近");
			} else {
				img_notice.setImageResource(R.drawable.iei);
				tv1.setText("兴趣部落");
			}
			rl1.addView(img_notice, 0);

			RelativeLayout.LayoutParams params5 = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params5.setMargins(0, Tools.DPtoPX(this, 5), 0, 0);
			params5.addRule(RelativeLayout.BELOW, 7);
			params5.addRule(RelativeLayout.CENTER_HORIZONTAL);
			tv1.setLayoutParams(params5);
			tv1.setTextColor(Color.BLACK);
			// 设置字体加粗
			TextPaint tp = tv1.getPaint();
			tp.setFakeBoldText(true);
			rl1.addView(tv1, 1);
			ll_function.addView(rl1);
		}
		return ll_function;
	}

	/**
	 * 头部布局实现
	 * 
	 * @param rl_top
	 */

	private void setTopLayOut(RelativeLayout rl_top) {
		// 动态
		TextView tv_dongtai = new TextView(this);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		tv_dongtai.setLayoutParams(params);
		tv_dongtai.setPadding(Tools.DPtoPX(this, 10), Tools.DPtoPX(this, 10),
				Tools.DPtoPX(this, 10), Tools.DPtoPX(this, 10));
		tv_dongtai.setText("动态");
		tv_dongtai.setTextColor(Color.WHITE);
		tv_dongtai.setTextSize(16);
		rl_top.addView(tv_dongtai, 0);

		// 更多
		TextView tv_gengduo = new TextView(this);
		RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params1.setMargins(0, 0, Tools.DPtoPX(this, 10), 0);
		params1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params1.addRule(RelativeLayout.CENTER_VERTICAL);
		tv_gengduo.setLayoutParams(params1);
		tv_gengduo.setText("更多");
		tv_gengduo.setTextColor(Color.WHITE);
		tv_gengduo.setTextSize(16);
		rl_top.addView(tv_gengduo, 1);
	}

	/**
	 * 设置中间布局
	 * 
	 * @return
	 */
	private LinearLayout setMiddleThridLayout() {
		LinearLayout ll_item = new LinearLayout(this);
		RelativeLayout.LayoutParams params6 = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params6.setMargins(0, Tools.DPtoPX(this, 25), 0, 0);
		params6.addRule(RelativeLayout.BELOW, 8);
		ll_item.setLayoutParams(params6);
		ll_item.setOrientation(LinearLayout.VERTICAL);

		for (int i = 0; i < 6; i++) {
			// 相对布局
			RelativeLayout rl_item = new RelativeLayout(this);
			MarginLayoutParams padLayoutParams = new MarginLayoutParams(
					LayoutParams.MATCH_PARENT, Tools.DPtoPX(this, 50));
			if (i == 3) {
				padLayoutParams.setMargins(0, Tools.DPtoPX(this, 25), 0, 0);
			}
			rl_item.setLayoutParams(new LayoutParams(padLayoutParams));
			rl_item.setBackgroundResource(R.drawable.selector_background);
			rl_item.setClickable(true);

			// 最上面线
			ImageView iv_line1 = new ImageView(this);
			iv_line1.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, Tools.DPtoPX(this, 1)));
			iv_line1.setBackgroundColor(Color.parseColor("#CCCCCC"));
			rl_item.addView(iv_line1, 0);

			// 最左边的图片
			ImageView img1 = new ImageView(this);
			// 描述文字
			TextView tv_youxi = new TextView(this);
			img1.setId(10);
			RelativeLayout.LayoutParams params7 = new RelativeLayout.LayoutParams(
					Tools.DPtoPX(this, 40), Tools.DPtoPX(this, 40));
			params7.setMargins(Tools.DPtoPX(this, 10), 0, 0, 0);
			params7.addRule(RelativeLayout.CENTER_VERTICAL);
			img1.setLayoutParams(params7);
			if (i == 0) {
				img1.setBackgroundResource(R.drawable.yx);
				tv_youxi.setText("游戏");
			} else if (i == 1) {
				img1.setBackgroundResource(R.drawable.gw);
				tv_youxi.setText("购物");
			} else if (i == 2) {
				img1.setBackgroundResource(R.drawable.yyb);
				tv_youxi.setText("应用宝");
			} else if (i == 3) {
				img1.setBackgroundResource(R.drawable.fjdq);
				tv_youxi.setText("附近的群");
			} else if (i == 4) {
				img1.setBackgroundResource(R.drawable.chwl);
				tv_youxi.setText("吃喝玩乐");
			} else if (i == 5) {
				img1.setBackgroundResource(R.drawable.tcfw);
				tv_youxi.setText("同城服务");
			}
			rl_item.addView(img1, 1);
			RelativeLayout.LayoutParams params8 = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params8.setMargins(Tools.DPtoPX(this, 10), 0, 0, 0);
			params8.addRule(RelativeLayout.CENTER_VERTICAL);
			params8.addRule(RelativeLayout.RIGHT_OF, 10);
			tv_youxi.setLayoutParams(params8);
			tv_youxi.setTextColor(Color.BLACK);
			// 设置字体加粗
			TextPaint tp = tv_youxi.getPaint();
			tp.setFakeBoldText(true);
			rl_item.addView(tv_youxi, 2);

			// 最右边的箭头
			ImageView iv_go = new ImageView(this);
			RelativeLayout.LayoutParams params9 = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params9.setMargins(0, 0, Tools.DPtoPX(this, 10), 0);
			params9.addRule(RelativeLayout.CENTER_VERTICAL);
			params9.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			iv_go.setLayoutParams(params9);
			iv_go.setBackgroundResource(R.drawable.go);
			rl_item.addView(iv_go, 3);
			ll_item.addView(rl_item);
		}
		return ll_item;
	}

	/**
	 * 设置最下面的布局
	 * 
	 * @return
	 */
	private LinearLayout setBottomLayout() {

		LinearLayout ll_buttom = new LinearLayout(this);
		RelativeLayout.LayoutParams params10 = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params10.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		ll_buttom.setLayoutParams(params10);
		ll_buttom.setOrientation(LinearLayout.HORIZONTAL);

		for (int j = 0; j < 3; j++) {
			LinearLayout ll_home = new LinearLayout(this);
			ll_home.setLayoutParams(new LinearLayout.LayoutParams(0,
					LayoutParams.WRAP_CONTENT, 1.0f));
			ll_home.setBackgroundColor(Color.WHITE);
			ll_home.setGravity(Gravity.CENTER_HORIZONTAL);
			ll_home.setOrientation(LinearLayout.VERTICAL);
			ll_home.setPadding(0, Tools.DPtoPX(this, 5), 0,
					Tools.DPtoPX(this, 3));

			ImageView img_home = new ImageView(this);

			TextView tv_home = new TextView(this);

			img_home.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			if (j == 0) {
				img_home.setBackgroundResource(R.drawable.home_press);
				tv_home.setText("首页");
			} else if (j == 1) {
				img_home.setBackgroundResource(R.drawable.invest);
				tv_home.setText("投资");
			} else {
				img_home.setBackgroundResource(R.drawable.borrow);
				tv_home.setText("借款");
			}
			ll_home.addView(img_home, 0);

			MarginLayoutParams padLayoutParams = new MarginLayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			padLayoutParams.setMargins(0, Tools.DPtoPX(this, 3), 0, 0);
			tv_home.setLayoutParams(new LinearLayout.LayoutParams(
					padLayoutParams));
			tv_home.setTextColor(Color.parseColor("#F16236"));
			tv_home.setTextSize(12);
			ll_home.addView(tv_home, 1);
			images.add(img_home);
			tViews.add(tv_home);
			ll_buttom.addView(ll_home);
			ll_home.setOnClickListener(new OnMyClickListener(j));
		}
		return ll_buttom;
	}

	public class OnMyClickListener implements OnClickListener {
		private int position;

		public OnMyClickListener(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			if (position == 0) {
				images.get(0).setBackgroundResource(R.drawable.home_press);
				images.get(1).setBackgroundResource(R.drawable.invest);
				images.get(2).setBackgroundResource(R.drawable.borrow);
				tViews.get(0).setTextColor(0xfff16236);
				tViews.get(1).setTextColor(0xff919191);
				tViews.get(2).setTextColor(0xff919191);
			} else if (position == 1) {
				images.get(0).setBackgroundResource(R.drawable.home);
				images.get(1).setBackgroundResource(R.drawable.invest_press);
				images.get(2).setBackgroundResource(R.drawable.borrow);
				tViews.get(0).setTextColor(0xff919191);
				tViews.get(1).setTextColor(0xfff16236);
				tViews.get(2).setTextColor(0xff919191);
			} else {
				images.get(0).setBackgroundResource(R.drawable.home);
				images.get(1).setBackgroundResource(R.drawable.invest);
				images.get(2).setBackgroundResource(R.drawable.borrow_press);
				tViews.get(0).setTextColor(0xff919191);
				tViews.get(1).setTextColor(0xff919191);
				tViews.get(2).setTextColor(0xfff16236);
			}
		}
	}

}
