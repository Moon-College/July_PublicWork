package com.tz.lsn11_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
    }
	
	public void clickBtn(View view) {
		switch (view.getId()) {
			case R.id.bt_demo:
				Intent intent = new Intent(this, AnimationActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_alpha:
				Intent intent2 = new Intent(this, AlphaAnimationActivity.class);
				startActivity(intent2);
				break;
			case R.id.bt_scale:
				Intent intent3 = new Intent(this, ScaleAnimationActivity.class);
				startActivity(intent3);
				break;
			case R.id.bt_translate:
				Intent intent4 = new Intent(this, TranslateAnimationActivity.class);
				startActivity(intent4);
				break;
			case R.id.bt_rotate:
				Intent intent5 = new Intent(this, RotateAnimationActivity.class);
				startActivity(intent5);
				break;
			case R.id.bt_set:
				Intent intent6 = new Intent(this, SetAnimationActivity.class);
				startActivity(intent6);
				break;
			default:
				Toast.makeText(this, "还未注册调用入口", Toast.LENGTH_SHORT).show();
				break;
		}
	}

}
