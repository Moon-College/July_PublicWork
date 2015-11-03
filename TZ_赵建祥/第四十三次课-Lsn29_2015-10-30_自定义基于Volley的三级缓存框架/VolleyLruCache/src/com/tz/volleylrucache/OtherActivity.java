package com.tz.volleylrucache;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.tz.volleylrucache.util.ImageLoadManager;

public class OtherActivity extends Activity {

    private ImageView iv_lruimage;
    
    private static final String IMAGE_URL="http://www.iyi8.com/uploadfile/2015/0510/20150510124655627.jpg";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        
        //º”‘ÿÕº∆¨
        loadImage();
    }

	/**
	 * º”‘ÿÕº∆¨
	 */
	private void loadImage() {
		
		ImageLoadManager.loadImageByVolleyAndLru(IMAGE_URL, new ImageListener() {
			
			@Override
			public void onErrorResponse(VolleyError arg0) {
				// Õº∆¨º”‘ÿ ß∞‹ π”√ƒ¨»œÕº∆¨
				iv_lruimage.setImageResource(R.drawable.ic_launcher);
			}
			
			@Override
			public void onResponse(ImageContainer container, boolean arg1) {
				//	Õº∆¨º”‘ÿ≥…π¶
				iv_lruimage.setImageBitmap(container.getBitmap());
			}
		});
		
	}

	private void initView() {
		iv_lruimage = (ImageView) findViewById(R.id.iv_lruimage);
	}

    
}
