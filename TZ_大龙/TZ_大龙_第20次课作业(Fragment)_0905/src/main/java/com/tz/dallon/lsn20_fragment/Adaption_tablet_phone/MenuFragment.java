package com.tz.dallon.lsn20_fragment.Adaption_tablet_phone;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.tz.dallon.lsn20_fragment.R;


public class MenuFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView lv;
    private ListAdapter adapter;
    private String[] datas = new String[]{"QQ", "微信"};
    private boolean isTablet = false;
    Fragment f;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_menu, null);
        lv = (ListView) view.findViewById(R.id.lv);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, datas);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(isTablet) {
            //平板,右边展示
           if(position == 0) {
                f = new QQFragment();
            } else if(position == 1) {
                f = new WeiXinFragment();
            }

            getFragmentManager().beginTransaction().replace(R.id.fl_content,f).commit();
        } else {
            //手机设备 跳转Activity
            Intent intent = null;
            if(position == 0) {
                intent = new Intent(getActivity(),QQActivity.class);
            } else if(position == 1) {
                intent = new Intent(getActivity(), WeiXinActivity.class);
            }

            startActivity(intent);

        }
    }

    //需要等父Activity创建完才能判断
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //判断是否平板方法一
        if (isTablet(getActivity())) {
            isTablet = true;
        } else {
            isTablet = false;
        }

        //判断是否平板方法二: 推荐用二方法
//        if(getActivity().findViewById(R.id.fl_content) != null){
//            //是平板
//            isTablet = true;
//        }else{
//            //是手机
//            isTablet = false;
//        }
    }

    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     *
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
