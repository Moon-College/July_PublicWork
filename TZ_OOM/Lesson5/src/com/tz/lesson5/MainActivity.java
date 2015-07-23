package com.tz.lesson5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;


public class MainActivity extends FragmentActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
       
    }
   
    public void openListViewActivity(View v) {
    	startActivity(new Intent(this, ListViewActivity.class));
    }
    
    
    public void openGridViewActivity(View v) {
    	startActivity(new Intent(this, GridViewActivity.class));
    }
}
