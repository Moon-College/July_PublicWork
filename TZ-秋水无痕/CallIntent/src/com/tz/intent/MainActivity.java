package com.tz.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
    private Button btn_call;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
    }
    /**
     * @author kangpeng qq:568374687
     * 初始化控件与监听事件
     */
	private void initView() {
		btn_call = (Button) findViewById(R.id.btn_call);
		btn_call.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+110));
		startActivity(intent);
	}
}