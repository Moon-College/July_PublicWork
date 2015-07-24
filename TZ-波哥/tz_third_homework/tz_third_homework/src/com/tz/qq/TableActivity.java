package com.tz.qq;

import java.util.ArrayList;
import java.util.List;

import com.tz.qq.QqActivity.OnMyClickListener;

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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableActivity extends Activity {

	private List<ImageView> images;
	private List<TextView> tViews;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout ll = new LinearLayout(this);
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		ll.setBackgroundColor(Color.parseColor("#EBECED"));
		ll.setOrientation(LinearLayout.VERTICAL);
		for (int i = 0; i < 15; i++) {
			LinearLayout ll_buttom = setBottomLayout();
			ll.addView(ll_buttom);
		}
		setContentView(ll);

	}

	/**
	 * 设置最下面的布局
	 * 
	 * @return
	 */
	private LinearLayout setBottomLayout() {

		LinearLayout ll_buttom = new LinearLayout(this);
		MarginLayoutParams padLayoutParams1 = new MarginLayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		padLayoutParams1.setMargins(0, Tools.DPtoPX(this, 20), 0, 0);
		ll_buttom.setLayoutParams(new LayoutParams(padLayoutParams1));
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
			ll_buttom.addView(ll_home);
		}
		return ll_buttom;
	}

}
