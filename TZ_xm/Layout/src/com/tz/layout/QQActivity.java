package com.tz.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.tz.codelayout.R;

public class QQActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dynamic);
        
    }
    
}
