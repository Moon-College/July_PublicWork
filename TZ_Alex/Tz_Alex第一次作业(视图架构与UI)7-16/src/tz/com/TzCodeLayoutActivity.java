package tz.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TzCodeLayoutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tz_code_layout);

		// 代码动态布局
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);
		// 编辑框
		EditText et = new EditText(this);
		LinearLayout.LayoutParams et_lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		et.setLayoutParams(et_lp);
		// 按钮
		Button btn = new Button(this);
		LinearLayout.LayoutParams btn_lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		btn.setLayoutParams(btn_lp);
		//图片容器
		ImageView iv = new ImageView(this);
		LinearLayout.LayoutParams iv_lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		iv.setLayoutParams(iv_lp);
		// 将编辑框添加至linearlayout中
		ll.addView(et);
		ll.addView(btn);
		ll.addView(iv);
	}

}
