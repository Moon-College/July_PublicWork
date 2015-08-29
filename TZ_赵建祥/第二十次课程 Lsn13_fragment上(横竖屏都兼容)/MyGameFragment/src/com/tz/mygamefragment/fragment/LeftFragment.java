package com.tz.mygamefragment.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.tz.mygamefragment.R;

public class LeftFragment extends Fragment {

	public boolean isHorizontal=false;
	
	public LeftFragment() {
		super();
	}
	
	@Override
	public void setArguments(Bundle bundle) {
		// TODO Auto-generated method stub
		super.setArguments(bundle);
		this.isHorizontal=bundle.getBoolean("isHorizontal");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.left_fragment, null);
		if(isHorizontal){
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(400, LayoutParams.FILL_PARENT);
			view.setLayoutParams(params);
		}else{
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			view.setLayoutParams(params);
		}
		return view;
	}
	
	/**
	 * 
	 */
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
	}
	
}
