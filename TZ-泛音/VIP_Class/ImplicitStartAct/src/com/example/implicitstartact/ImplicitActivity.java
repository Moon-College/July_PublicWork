package com.example.implicitstartact;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ImplicitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_implicit);
		((Button)findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(ImplicitActivity.this, "Use setAction to statrt one activity", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setAction("someActionName");
				startActivity(intent);
			}
		});
		
          ((Button)findViewById(R.id.button2)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(ImplicitActivity.this, "Use someAction but Category is myway1 to statrt one activity", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setAction("someActionName");
				intent.addCategory("way1");
//				intent.setAction("com.example.implicitstartact.TargetActivity");
				startActivity(intent);
			}
		});
          
          ((Button)findViewById(R.id.button3)).setOnClickListener(new OnClickListener() {
  			
  			@Override
  			public void onClick(View v) {
  				Toast.makeText(ImplicitActivity.this, "Use someAction but Category is myway2 to statrt one activity", Toast.LENGTH_SHORT).show();
  				Intent intent = new Intent();
  				intent.setAction("someActionName");
  				intent.addCategory("way2");
//  				intent.setAction("com.example.implicitstartact.TargetActivity");
  				startActivity(intent);
  			}
  		});
	}



}
