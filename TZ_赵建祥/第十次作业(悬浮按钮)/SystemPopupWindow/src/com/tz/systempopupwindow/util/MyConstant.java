package com.tz.systempopupwindow.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;

/**
 * 常量类
 * @author 赵建祥 QQ:451966221 DATE:2015/7/9
 */
public class MyConstant {

	public static final String MY_TAG="jzhao";
	//内置SD卡绝对路径
	public static final String INNER_SD_ABSOLUTE_PATH=Environment.getExternalStorageDirectory().getAbsolutePath();
	//外置所有SD卡绝对路径
	public static final List<String> EXT_SD_ABSOLUTE_PATH=getExtSDCardPath();
	
	 /**
     * 获取外置SD卡路径
     * @return  应该就一条记录或空
     */
    public static List<String> getExtSDCardPath()
    {
        List<String> lResult = new ArrayList<String>();
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("extSdCard"))
                {
                    String [] arr = line.split(" ");
                    String path = arr[1];
                    File file = new File(path);
                    if (file.isDirectory())
                    {
                        lResult.add(path);
                    }
                }
            }
            isr.close();
        } catch (Exception e) {
        }
        return lResult;
    }
}
