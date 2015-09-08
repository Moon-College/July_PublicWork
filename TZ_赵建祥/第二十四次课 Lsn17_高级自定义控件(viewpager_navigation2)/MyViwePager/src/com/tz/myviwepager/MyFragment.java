package com.tz.myviwepager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
			Bundle arguments = getArguments();
			int dataid=arguments.getInt("DATAID");
			ImageView iv=new ImageView(getActivity());
			iv.setImageResource(dataid);
			Log.i("jzhao", "onCreateView£º"+dataid);
			return iv;
	}
}
