//对文件进行操作的工具类
package com.hac.tz_homework5.util;

import android.util.Log;
import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by hp on 2015/7/28.
 */
public class FileUtil {
    //单位标识
    public static final int B = 0;
    public static final int KB = 1;
    public static final int MB = 2;
    public static final int GB = 3;

    //单位具体数值
    private static final double bound_kb = 1024.0;
    private static final double bound_mb = 1024.0*1024.0;
    private static final double bound_gb = 1024.0*1024.0*1024.0;

    /**
     * @param path 路径
     * @return 该路径下文件（夹）的大小（Bytes）
     */
    public static long getSize(String path) {
        long size = 0;
        File file = new File(path);
        if(file.isDirectory()) {
            File []files = file.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    //递归获得子文件（夹）大小
                    size += getSize(files[i].getAbsolutePath());
                    Log.i("size", "dir "+files[i].getAbsolutePath()+": "+String.valueOf(size));
                }
                else {
                    size += files[i].length();
                    Log.i("size", "file "+files[i].getAbsolutePath()+": "+String.valueOf(size));
                }

            }
        }
        else {
            size = file.length();
        }
        return size;
    }

    /**
     * @param file 需要计算大小的文件对象
     * @param isDir 是否是文件夹
     * @return 文件（夹）的大小（Bytes）
     */
    public static long getSize(File file, boolean isDir) {
        long size = 0;
        if(isDir) {
            File []files = file.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory())
                    size += getSize(files[i], true);
                else
                    size += files[i].length();
            }
        }
        else {
            size = file.length();
        }
        return size;
    }

    /**
     * 对大小进行格式化的方法
     * @param size 大小
     * @param type 指定单位类型，若为null则自动设置
     * @return 带有单位的大小字符串，如2.06MB
     */
    public static String getFormatedSize(long size, Integer type) {
        String sizeStr="";
        DecimalFormat df = new DecimalFormat("#.##");

        if(type != null) {
            switch (type) {
                case B:
                    sizeStr = String.valueOf(size)+"B";
                    break;
                case KB:
                    sizeStr = df.format(size/bound_kb)+"KB";
                    break;
                case MB:
                    sizeStr = df.format(size / bound_mb)+"MB";
                    break;
                case GB:
                    sizeStr = df.format(size / bound_gb)+"GB";
                    break;
            }
        }
        else {
            if(size<bound_kb) {
                sizeStr = String.valueOf(size)+"B";
            }
            else if(bound_kb<=size && size<bound_mb) {
                sizeStr = df.format(size / bound_kb)+"KB";
            }
            else if(bound_mb<=size && size<bound_gb) {
                sizeStr = df.format(size / bound_mb)+"MB";
            }
            else {
                sizeStr = df.format(size / bound_gb)+"GB";
            }
        }
        return sizeStr;
    }

    /**
     * 删除文件（夹）的方法
     * @param path 需要删除的路径
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        if(file.isDirectory()) {
            File []files = file.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteFile(files[i].getAbsolutePath());
                    Log.i("file", "del dir"+files[i].getAbsolutePath());
                    files[i].delete();
                }
                else {
                    files[i].delete();
                    Log.i("file", "del file"+files[i].getAbsolutePath());
                }
            }
        }
        file.delete();
    }
}
