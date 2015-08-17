package com.ocean.anim.other;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tz.lsn15_propertyanimation.R;

public class OtherActivity extends Activity implements OnClickListener {

	private Button btn_test;
	private Button btn_two;
	private Button btn_one;
	private Button btn_three;
	private int width;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
		findView();
		setOnClick();
		width = getWindowManager().getDefaultDisplay().getWidth();
	}

	private void findView() {
		btn_test = (Button) findViewById(R.id.btn_test);
		btn_two = (Button) findViewById(R.id.btn_two);
		btn_one = (Button) findViewById(R.id.btn_one);
		btn_three = (Button) findViewById(R.id.btn_three);
	}

	private void setOnClick() {
		btn_test.setOnClickListener(this);
		btn_two.setOnClickListener(this);
		btn_one.setOnClickListener(this);
		btn_three.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_one:
			ViewWrapper warpper = new ViewWrapper(btn_one);
			OtherAnim.startAnim(warpper,btn_one.getWidth(),width - 30);
			break;
		case R.id.btn_test:
			OtherAnim.changeWidthByValue(btn_test, btn_test.getWidth(), width-30);
			break;
		case R.id.btn_two:
			ViewWrapper warpper2 = new ViewWrapper(btn_two);
			OtherAnim.startAnim(warpper2, btn_two.getWidth(), width-30, true);
			break;
		case R.id.btn_three:
			OtherAnim.startAnimAndDisappear(btn_three, 1.0f, 0.0f);
			break;
		default:
			break;
		}
	}
	
	public class ViewWrapper {  
	    private View mView;  
	  
	    public ViewWrapper(View view) {  
	        mView = view;  
	    }  
	  
	    public int getWidth() {  
	        return mView.getLayoutParams().width;  
	    }  
	  
	    public void setWidth(int width) {  
	        mView.getLayoutParams().width = width;  
	        mView.requestLayout();  
	    }  
	    
	    public View getView(){
	    	return mView;
	    }
	}
}
