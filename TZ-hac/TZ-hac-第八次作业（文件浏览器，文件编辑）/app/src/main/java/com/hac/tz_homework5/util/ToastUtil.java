package com.hac.tz_homework5.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hp on 2015/8/9.
 */
public class ToastUtil {
    public static void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
