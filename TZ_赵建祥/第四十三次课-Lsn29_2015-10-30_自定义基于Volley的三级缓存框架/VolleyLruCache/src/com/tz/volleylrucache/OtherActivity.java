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
        
        //����ͼƬ
        loadImage();
    }

	/**
	 * ����ͼƬ
	 */
	private void loadImage() {
		
		ImageLoadManager.loadImageByVolleyAndLru(IMAGE_URL, new ImageListener() {
			
			@Override
			public void onErrorResponse(VolleyError arg0) {
				// ͼƬ����ʧ��ʹ��Ĭ��ͼƬ
				iv_lruimage.setImageResource(R.drawable.ic_launcher);
			}
			
			@Override
			public void onResponse(ImageContainer container, boolean arg1) {
				//	ͼƬ���سɹ�
				iv_lruimage.setImageBitmap(container.getBitmap());
			}
		});
		
	}

	private void initView() {
		iv_lruimage = (ImageView) findViewById(R.id.iv_lruimage);
	}

    
}
