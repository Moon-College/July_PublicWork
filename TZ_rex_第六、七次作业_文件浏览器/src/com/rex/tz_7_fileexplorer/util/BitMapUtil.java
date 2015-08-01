package com.rex.tz_7_fileexplorer.util;

import java.util.concurrent.ExecutionException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.util.Log;

public class BitMapUtil
{
    private static Bitmap compressBitmap(String wholePath, int reqWidth)
    {
        Options opts = new Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(wholePath, opts);
        int width = opts.outWidth;

        int inSampleSize = 1;
        if (width > reqWidth)
        {
            inSampleSize = Math.round((float) width / reqWidth);
        }

        opts.inSampleSize = inSampleSize;
        opts.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(wholePath, opts);
    }

    public static Bitmap getCompressedBitmap(String wholePath, int reqWidth)
    {
        AsyncTask<String, Void, Bitmap> asyncTask = new AsyncTask<String, Void, Bitmap>()
        {

            @Override
            protected Bitmap doInBackground(String... params)
            {
                String wholePath = params[0];
                int reqWidth = Integer.valueOf(params[1]);

                return compressBitmap(wholePath, reqWidth);
            }
        };

        Bitmap bitmap = null;

        try
        {
            bitmap = asyncTask.execute(wholePath, reqWidth + "").get();
        }
        catch (InterruptedException | ExecutionException e)
        {
            Log.i("getCompressedBitmap()", e.toString());
        }

        return bitmap;
    }

}
