package com.hacket.filemanager.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.hacket.filemanager.bean.FileInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengfansheng on 2015/7/26 0026.
 */
public class FileManagerUtil {

    public static List<FileInfo> getFiles(String path, boolean isRootDir) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }

        File root = new File(path);
        if (!root.exists() || !root.isDirectory()) {
            return null;
        }

        List<FileInfo> fileInfos = new ArrayList<>();

        // 返回上一层
        if (!isRootDir) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFilename("back");
            fileInfo.setFileurl(root.getParent());
            fileInfo.setIsFoolder(true);
            fileInfo.setIsImgType(false);
            fileInfo.setIsRootRir(isRootDir);
            fileInfos.add(fileInfo);
        }

        File[] files = root.listFiles();
        for (int i = 0; i < files.length; i++) {
            FileInfo info = new FileInfo();
            info.setFilename(files[i].getName());
            info.setFileurl(files[i].getAbsolutePath());
            info.setIsFoolder(files[i].isDirectory());
            boolean isImg = files[i].getName().endsWith("jpg") || files[i].getName().endsWith("JPG") || files[i].getName().endsWith("png") || files[i].getName().endsWith("PNG") || files[i].getName().endsWith("jpeg") || files[i].getName().endsWith("JPEG");
            info.setIsImgType(isImg);
            fileInfos.add(info);
        }

        return fileInfos;
    }

    public static Bitmap sacleBitmap(String imgPath, float reqWidth, float reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imgPath, options);
        float outWidth = options.outWidth;
        float outHeight = options.outHeight;
        float scaleSize = 1;
        float widthScale = scaleSize;
        float heigthScale = scaleSize;
        if (outWidth > reqWidth) {
            widthScale = outWidth / reqWidth;
        }
        if (outHeight > reqHeight) {
            heigthScale = outHeight / reqHeight;
        }
        scaleSize = Math.min(widthScale, heigthScale) > 1 ? Math.min(widthScale, heigthScale) : 1;
        options.inSampleSize = (int) scaleSize;
        Log.e("tag", "scaleSize=" + scaleSize);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(imgPath, options);
    }

    public static float dip2px(Context context, float dipValue) {
        return Math.round(context.getResources().getDisplayMetrics().density * dipValue);
    }

}