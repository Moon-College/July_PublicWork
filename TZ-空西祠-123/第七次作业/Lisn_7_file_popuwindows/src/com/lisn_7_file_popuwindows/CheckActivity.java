package com.lisn_7_file_popuwindows;

import java.io.File;
import java.math.BigDecimal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CheckActivity extends Activity {
	private TextView txt1,txt2;
      @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.check);
    	
    	initdate();
    }

	private void initdate() {
           txt1=(TextView) findViewById(R.id.textView2);
           txt2=(TextView) findViewById(R.id.textView3);
           Intent intent=getIntent();
           String name=intent.getStringExtra("name");
           txt1.setText(name);
           System.out.println("----"+name);
          String path=intent.getStringExtra("path");
          // System.out.println(path);
           //txt2.setText(name);
           File file=new File(path); 
           try {
        	  txt2.setText(getCacheSize(file));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
		
	}
	  public static String getFormatSize(double size) {  
	        double kiloByte = size / 1024;  
	        if (kiloByte < 1) {  
	            return size + "Byte";  
	        }  
	  
	        double megaByte = kiloByte / 1024;  
	        if (megaByte < 1) {  
	            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));  
	            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)  
	                    .toPlainString() + "KB";  
	        }  
	  
	        double gigaByte = megaByte / 1024;  
	        if (gigaByte < 1) {  
	            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));  
	            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)  
	                    .toPlainString() + "MB";  
	        }  
	  
	        double teraBytes = gigaByte / 1024;  
	        if (teraBytes < 1) {  
	            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));  
	            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)  
	                    .toPlainString() + "GB";  
	        }  
	        BigDecimal result4 = new BigDecimal(teraBytes);  
	        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()  
	                + "TB";  
	    }  
	      
	      
	    public static String getCacheSize(File file) throws Exception{  
	        return getFormatSize(getFolderSize(file));  
	    }
	    public static long getFolderSize(File file) throws Exception {  
	        long size = 0;  
	       
	        try {  
	            File[] fileList = file.listFiles();  
	            for (int i = 0; i < fileList.length; i++) {  
	                // 如果下面还有文件  
	                if (fileList[i].isDirectory()) {  
	                    size = size + getFolderSize(fileList[i]);  
	                } else {  
	                    size = size + fileList[i].length();  
	                }  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return size;  
	    }  
}
