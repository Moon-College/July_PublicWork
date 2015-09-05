package com.tz.dallon.lsn20_fragment.FragmentDynamic;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tz.dallon.lsn20_fragment.R;


public class ShoppingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dynamic_shopping,null);

        return view;
    }
}
