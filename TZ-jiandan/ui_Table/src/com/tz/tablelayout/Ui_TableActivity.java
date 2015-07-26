package com.tz.tablelayout;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ui_TableActivity extends Activity {
    /** Called when the activity is first created. */
	Button btn ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        //setContentView(R.layout.activity_framelayout);	
        //setContentView(R.layout.table_layout);
        setContentView(R.layout.activity_tablelayout);
        
        btn = (Button) findViewById(R.id.button1);
    }
    
    public void GetTable(View v) {
    	Intent intent = new Intent();
		intent.setClass(Ui_TableActivity.this, tablelayout_java.class);
		startActivity(intent);
	}
}