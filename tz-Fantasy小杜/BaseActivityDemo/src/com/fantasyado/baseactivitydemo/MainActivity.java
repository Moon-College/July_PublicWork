package com.fantasyado.baseactivitydemo;

 
 

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
 
 


public class MainActivity extends BaseActivity {

    private View myLayout;
	private TextView tv;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("MainActivity");
     //  removeDefaultContent();
        myLayout = View.inflate(this,R.layout.activity_main,null);
        addMylayout(myLayout);
        tv = (TextView)myLayout.findViewById(R.id.textView1);
        
    }

  
}
