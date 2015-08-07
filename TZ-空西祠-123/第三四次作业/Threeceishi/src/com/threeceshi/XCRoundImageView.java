
package com.threeceshi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class XCRoundImageView extends ImageView {
	Paint paint = new Paint();
	public XCRoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	 protected void onDraw(Canvas canvas) {  
		  
	        Drawable drawable = getDrawable(); 
	        if (null != drawable) {  
	            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();  
	            Bitmap b = getCircleBitmap(bitmap, 14); 
	            final Rect rectSrc = new Rect(0, 0, b.getWidth(), b.getHeight()); final Rect rectDest = new Rect(0,0,getWidth(),getHeight());
	            paint.reset();  
	            canvas.drawBitmap(b, rectSrc, rectDest, paint);  
	        } else { 
	        	super.onDraw(canvas);  
	        }  
	    } /** * ��ȡԲ��ͼƬ����
	     * @param bitmap
	     * @param pixels
	     * @return Bitmap
	     * @author caizhiming */ 
	 private Bitmap getCircleBitmap(Bitmap bitmap, int pixels) {  
	        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),  
	                bitmap.getHeight(), Config.ARGB_8888);  
	        Canvas canvas = new Canvas(output); final int color = 0xff424242; final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());  
	        paint.setAntiAlias(true);  
	        canvas.drawARGB(0, 0, 0, 0);  
	        paint.setColor(color); int x = bitmap.getWidth(); 
	        
	        canvas.drawCircle(x / 2, x / 2, x / 2, paint);  
	        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
	        canvas.drawBitmap(bitmap, rect, rect, paint); return output;  
	        
	        
	    }  
	
	
}