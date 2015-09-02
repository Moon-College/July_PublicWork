package com.tz.lsn15_propertyanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.ocean.anim.OceanMainActivity;
import com.tz.lsn15_propertyanimation.CalculateGridLayout.CalculateGridLayoutActivity;
import com.tz.lsn15_propertyanimation.CoolSafari.CoolSafariAnimationActivity;
import com.tz.lsn15_propertyanimation.GifEngine.GifEngineActivity;
import com.tz.lsn15_propertyanimation.LayoutAnimation.LayoutAnimationActivity;
import com.tz.lsn15_propertyanimation.ObjectAnimation.ObjectAnimationActivity;
import com.tz.lsn15_propertyanimation.bouncingball.BouncingBallsActivity;
import com.tz.lsn15_propertyanimation.interpolator.InterpolatorActivity;

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
			case R.id.bt_bouncingBalls:
				intent.setClass(this, BouncingBallsActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_coolSafari:
				intent.setClass(this, CoolSafariAnimationActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_interpolator:
				intent.setClass(this, InterpolatorActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_layoutAnimation:
				intent.setClass(this, LayoutAnimationActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_calc:
				intent.setClass(this, CalculateGridLayoutActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_gif:
				intent.setClass(this, GifEngineActivity.class);
				startActivity(intent);
				break;
			default:
				break;
		}
    }

	public void test(View v) {
		Message msg = new Message();
	}


  

}
