package tz.holyligt.fileexplorer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by xiong on 2015/7/27.
 */
public class ImageLoader {

    private static ImageLoader imageLoader;
    private final LruCache<String, Bitmap> menmorcache;
    private int id;

    private ImageLoader(){
        int maxMemory= (int) Runtime.getRuntime().maxMemory();
        int cachesize= maxMemory/8;
         menmorcache=new LruCache<String,Bitmap>(cachesize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public void placeholder(int id){
        this.id=id;
    }

    public void  addBitmapToMemoryCache(String key,Bitmap bitmap){
        if (getBitmapFromMemoryCache(key)==null){
            menmorcache.put(key,bitmap);
        }
    }

    public Bitmap getBitmapFromMemoryCache(String key) {
        return menmorcache.get(key);
    }


    public static Bitmap decodeSampledBitmapFromResoure(String pathname,int regWidth){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(pathname,options);
        options.inSampleSize=caluateInSamplSize(options,regWidth);
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeFile(pathname,options);
    }

    private static int caluateInSamplSize(BitmapFactory.Options options, int regWidth) {
        int samplesie=1;
        final  int width=options.outWidth;
        if (width>regWidth){
            final int withdRadio= (int) Math.round((width/regWidth*1.0));
            samplesie=withdRadio;
        }
        return samplesie;
    }


    public static ImageLoader getInstance(){
        if (imageLoader==null) {
            imageLoader = new ImageLoader();
        }
        return imageLoader;
    }

    public void loadImage(ImageView imageView,String path ){


             ImageLoadTask task=new ImageLoadTask(imageView,path);
             task.execute(path);

    }
    class ImageLoadTask extends AsyncTask<String,Void,Bitmap>{


        private  String path;
        private ImageView imageView;
       public ImageLoadTask(ImageView imageView,String path){
           this.imageView=imageView;
           this.path=path;
           imageView.setTag(path);
           if (id!=0){
               imageView.setImageResource(id);
           }
       }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap=null;
            if (params[0]!=null){
                String path=params[0];
                bitmap=LoadImage(path);
            }
            return bitmap;
        }


        private Bitmap LoadImage(String mimageurl) {
            Bitmap bitmap = ImageLoader.getInstance().getBitmapFromMemoryCache(mimageurl);
            if (bitmap != null) {
                return bitmap;
            }

            if (bitmap == null) {
                bitmap = ImageLoader.decodeSampledBitmapFromResoure(mimageurl, 100);
                if (bitmap != null) {
                    ImageLoader.getInstance().addBitmapToMemoryCache(mimageurl, bitmap);
                    return bitmap;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap!=null&&imageView.getTag()!=null&&imageView.getTag().equals(path)) {

                imageView.setImageBitmap(bitmap);
                imageView.setScaleType(ImageView.ScaleType.CENTER);
            }
        }
    }



}
