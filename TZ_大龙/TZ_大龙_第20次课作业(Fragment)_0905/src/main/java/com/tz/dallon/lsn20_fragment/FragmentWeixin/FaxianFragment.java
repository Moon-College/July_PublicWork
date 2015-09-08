package com.tz.dallon.lsn20_fragment.FragmentWeixin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FaxianFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        tv.setText("我是发现");
        return tv;
    }
}
