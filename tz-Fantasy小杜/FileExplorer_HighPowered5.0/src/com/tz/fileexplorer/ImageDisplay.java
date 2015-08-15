package com.tz.fileexplorer;
 
import com.tz.fileexplorer.R;
import com.tz.fileexplorer.imageloader.ImageLoader;

import android.R.integer;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

/**  
 * created at:2015年8月3日 下午3:08:07  
 * project name:FileExplorer_HighPowered  
 * @author Fantasy ado  
 * @version 1.0  
 * @since JDK 1.7  
 * File name:ImageDisplay.java  
 * description:  
 */

public class ImageDisplay extends Activity{
     private ImageView iv;
   
     
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	 setContentView(R.layout.image_display);
    	 iv = (ImageView) findViewById(R.id.iv_display);
     
    	String path =  getIntent().getStringExtra("path");
    	Toast.makeText(this, "path is :"+path, Toast.LENGTH_SHORT).show();
    	BitmapFactory.Options options = new BitmapFactory.Options();
    	options.inSampleSize = 1;
    	Bitmap bmp = BitmapFactory.decodeFile(path, options);
    	iv.setImageBitmap(bmp);
    }

}
