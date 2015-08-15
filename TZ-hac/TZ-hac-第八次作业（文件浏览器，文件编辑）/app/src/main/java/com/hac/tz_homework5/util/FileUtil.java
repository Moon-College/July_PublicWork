//对文件进行操作的工具类
package com.hac.tz_homework5.util;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

    private static final String[][] MIME_MapTable={
            //{后缀名，MIME类型}
            {".3gp",    "video/3gpp"},
            {".apk",    "application/vnd.android.package-archive"},
            {".asf",    "video/x-ms-asf"},
            {".avi",    "video/x-msvideo"},
            {".bin",    "application/octet-stream"},
            {".bmp",    "image/bmp"},
            {".c",  "text/plain"},
            {".class",  "application/octet-stream"},
            {".conf",   "text/plain"},
            {".cpp",    "text/plain"},
            {".doc",    "application/msword"},
            {".docx",   "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls",    "application/vnd.ms-excel"},
            {".xlsx",   "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe",    "application/octet-stream"},
            {".gif",    "image/gif"},
            {".gtar",   "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h",  "text/plain"},
            {".htm",    "text/html"},
            {".html",   "text/html"},
            {".jar",    "application/java-archive"},
            {".java",   "text/plain"},
            {".jpeg",   "image/jpeg"},
            {".jpg",    "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log",    "text/plain"},
            {".m3u",    "audio/x-mpegurl"},
            {".m4a",    "audio/mp4a-latm"},
            {".m4b",    "audio/mp4a-latm"},
            {".m4p",    "audio/mp4a-latm"},
            {".m4u",    "video/vnd.mpegurl"},
            {".m4v",    "video/x-m4v"},
            {".mov",    "video/quicktime"},
            {".mp2",    "audio/x-mpeg"},
            {".mp3",    "audio/x-mpeg"},
            {".mp4",    "video/mp4"},
            {".mpc",    "application/vnd.mpohun.certificate"},
            {".mpe",    "video/mpeg"},
            {".mpeg",   "video/mpeg"},
            {".mpg",    "video/mpeg"},
            {".mpg4",   "video/mp4"},
            {".mpga",   "audio/mpeg"},
            {".msg",    "application/vnd.ms-outlook"},
            {".ogg",    "audio/ogg"},
            {".pdf",    "application/pdf"},
            {".png",    "image/png"},
            {".pps",    "application/vnd.ms-powerpoint"},
            {".ppt",    "application/vnd.ms-powerpoint"},
            {".pptx",   "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop",   "text/plain"},
            {".rc", "text/plain"},
            {".rmvb",   "audio/x-pn-realaudio"},
            {".rtf",    "application/rtf"},
            {".sh", "text/plain"},
            {".tar",    "application/x-tar"},
            {".tgz",    "application/x-compressed"},
            {".txt",    "text/plain"},
            {".wav",    "audio/x-wav"},
            {".wma",    "audio/x-ms-wma"},
            {".wmv",    "audio/x-ms-wmv"},
            {".wps",    "application/vnd.ms-works"},
            {".xml",    "text/plain"},
            {".z",  "application/x-compress"},
            {".zip",    "application/x-zip-compressed"},
            {"",        "*/*"}
    };

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

    public static boolean writeFile(Context context, String path, String content, boolean create) {
        File file = new File(path);
        FileOutputStream fos;

        try {
            if(!file.exists() && create) {
                file.createNewFile();
            }
            if(file.isDirectory()) {
                return false;
            }
            fos = new FileOutputStream(path);
            byte []bytes = content.getBytes();
            fos.write(bytes);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String readFile(String path){
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
            return "read file failed";
        FileInputStream fis= null;
        StringBuffer sb = null;
        try {
            fis = new FileInputStream(file);
            byte[] buf = new byte[1024];
            sb=new StringBuffer();
            while((fis.read(buf))!=-1){
                sb.append(new String(buf));
                buf=new byte[1024];//重新生成，避免和上次读取的数据重复
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getMIMEType(File file) {

        String type="*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if(dotIndex < 0){
            return type;
        }
        /* 获取文件的后缀名*/
        String end=fName.substring(dotIndex,fName.length()).toLowerCase();
        if(end=="")return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for(int i=0;i<MIME_MapTable.length;i++){ //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if(end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }
}
