package com.tz.mygamefragment.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tz.mygamefragment.R;

public class RightFragment extends Fragment {

	private boolean isHorizontal=false;
	
	@Override
	public void setArguments(Bundle dundle) {
		// TODO Auto-generated method stub
		super.setArguments(dundle);
		isHorizontal=dundle.getBoolean("isHorizontal");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.right_fragment, null);
		return view;
	}
}
