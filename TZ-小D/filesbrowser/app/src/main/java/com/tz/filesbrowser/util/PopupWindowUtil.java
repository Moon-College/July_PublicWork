package com.tz.filesbrowser.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.tz.filesbrowser.R;
import com.tz.filesbrowser.ui.MainActivity;

import java.util.ArrayList;

/**
 * Created by 屈发 on 2015/8/5.
 */
public class PopupWindowUtil {
    private static PopupWindow pop;
    /**
     * 显示PopupWindow
     *
     * @param context 上下文
     * @param v       PopupWindow 内显示的View
     * @param list    View 中显示的数据  List<String>
     */
    public static void showPopupWindow(final Context context, View v, final ArrayList<String> list) {
        View convertView = View.inflate(context, R.layout.lv_pop, null);
        ListView lv = (ListView) convertView.findViewById(R.id.lv_set_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.item_pop, list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    ((MainActivity) context).finish();
                }
            }
        });
        pop = new PopupWindow(convertView, 150, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pop.setBackgroundDrawable(new ColorDrawable());
        pop.showAsDropDown(v, 0, 0);
    }

    public static void closePopupWindow(){
        pop.dismiss();
    }

}
