package com.fantasyado.drawanimation;

import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.PrivateCredentialPermission;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;


public class MainActivity extends ActionBarActivity {

	
	 private Splash splash;
 
 
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.activity_main);
        
        splash = (Splash) findViewById(R.id.splashView);
        
        
    }
 
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
   
 
    }
 
}
