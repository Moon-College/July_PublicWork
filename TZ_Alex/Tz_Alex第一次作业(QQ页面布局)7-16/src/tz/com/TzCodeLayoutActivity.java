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

		// ���붯̬����
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);
		// �༭��
		EditText et = new EditText(this);
		LinearLayout.LayoutParams et_lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		et.setLayoutParams(et_lp);
		// ��ť
		Button btn = new Button(this);
		LinearLayout.LayoutParams btn_lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		btn.setLayoutParams(btn_lp);
		//ͼƬ����
		ImageView iv = new ImageView(this);
		LinearLayout.LayoutParams iv_lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		iv.setLayoutParams(iv_lp);
		// ���༭�������linearlayout��
		ll.addView(et);
		ll.addView(btn);
		ll.addView(iv);
	}

}
