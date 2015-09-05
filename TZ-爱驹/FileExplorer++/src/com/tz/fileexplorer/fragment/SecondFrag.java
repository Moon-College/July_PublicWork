package com.tz.fileexplorer.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class SecondFrag extends Fragment {

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView tv = new TextView(getActivity());
		tv.setText("this is the secondfragment");
		WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		tv.setText("屏幕分辨率为 宽\t"+outMetrics.widthPixels+"\t高\t"+outMetrics.heightPixels);
		return tv;
	}
}