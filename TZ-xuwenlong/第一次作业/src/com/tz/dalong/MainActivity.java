package com.tz.dalong;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	
	private Button iphone,sms,log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        iphone=(Button) findViewById(R.id.iphone);
        sms=(Button) findViewById(R.id.sms);
        log=(Button) findViewById(R.id.log);
        iphone.setOnClickListener(this);
        sms.setOnClickListener(this);
        log.setOnClickListener(this);
    
        
    
    
    }
    
    /**
     * @deprecated dsgsd
     * @author ÐìÎÄÁú
     * @category sdg
     * 
     * 
     */
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId()){
		case R.id.iphone:
			
			Intent intent_iphone=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+111111));
			startActivity(intent_iphone);
			break;
		case R.id.sms:
			Intent intent_sms=new Intent(Intent.ACTION_VIEW,Uri.parse("smsto:"+111111));
			intent_sms.putExtra("sms_body", "ÐìÎÄÁú");
			startActivity(intent_sms);
			
			break;
		case R.id.log:
			break;
		
		
		}
	}
    
    
    
    
    
    
    
    
    
    


   

}
