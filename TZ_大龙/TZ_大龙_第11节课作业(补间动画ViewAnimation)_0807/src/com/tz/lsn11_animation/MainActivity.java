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
		Intent intent = null;
		switch (view.getId()) {
			case R.id.bt_demo:
				intent = new Intent(this, AnimationActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_alpha:
				intent = new Intent(this, AlphaAnimationActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_scale:
				intent = new Intent(this, ScaleAnimationActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_translate:
				intent = new Intent(this, TranslateAnimationActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_rotate:
				intent = new Intent(this, RotateAnimationActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_set:
				intent = new Intent(this, SetAnimationActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_shake:
				intent = new Intent(this, ShakeAnimationActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_msj:
				intent = new Intent(this, MSJWelcomeAnimationActivity.class);
				startActivity(intent);
				break;
			default:
				Toast.makeText(this, "还未注册调用入口", Toast.LENGTH_SHORT).show();
				break;
		}
	}

}
