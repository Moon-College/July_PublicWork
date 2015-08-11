package com.hac.tz_homework5.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.hac.tz_homework5.GlobalData;

/**
 * Created by KoaliTech on 15/3/29.
 */
public class SharedPreferenceImpl {

    Context context;


    public SharedPreferenceImpl(Context context) {
        this.context = context;
    }

    public void set(String key, String value){
        if (" ".equals(value)||"null".equals(value)){
            return;
        }
        //实例化SharedPreferences对象（第一步）
        SharedPreferences mySharedPreferences = context.getSharedPreferences(GlobalData.SHAREDPREFERENCE_NAME, Context.MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象（第二步）
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        //用putString的方法保存数据
        editor.putString(key, value);
        //提交当前数据
        editor.commit();
    }

    public String get(String key,String defaultString) {
        SharedPreferences sharedPreferences= context.getSharedPreferences(GlobalData.SHAREDPREFERENCE_NAME,Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        String value =sharedPreferences.getString(key, defaultString);
        return value;
    }
}
