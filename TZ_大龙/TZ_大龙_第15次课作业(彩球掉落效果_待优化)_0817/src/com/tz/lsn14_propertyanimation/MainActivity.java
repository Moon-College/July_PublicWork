package com.tz.lsn14_propertyanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ocean.anim.OceanMainActivity;
import com.tz.lsn15_propertyanimation.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void btnClick(View view) {
    	Intent intent = new Intent();
    	switch (view.getId()) {
    		case R.id.bt_ocean:
    			intent.setClass(this, OceanMainActivity.class);
    			startActivity(intent);
			break;
			case R.id.bt_objAnim:
				intent.setClass(this, ObjectAnimationActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_ArgbEvaluator:
				intent.setClass(this, BouncingBallsAnimationActivity.class);
				startActivity(intent);
				break;
			default:
				break;
		}
    }


  

}
