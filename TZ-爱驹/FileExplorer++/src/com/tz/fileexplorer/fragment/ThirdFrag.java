package com.tz.fileexplorer.fragment;

import com.tz.fileexplorer.MainActivity;
import com.tz.fileexplorer.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ThirdFrag extends Fragment {

	protected static final int RESULT_OK = 1;
	private ImageView iv;
	private ViewGroup viewGroup;
	private TextView tv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		viewGroup = (ViewGroup) inflater.inflate(R.layout.third_fragment, null);
		tv = (TextView) viewGroup.findViewById(R.id.tv);
		iv = (ImageView) viewGroup.findViewById(R.id.iv);
		tv.setText("打开照相机拍照即可在此画面显示相片");
		return viewGroup;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		((MainActivity) getActivity()).getMoreButton().setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						switch (v.getId()) {

						case R.id.btn_more:
							Intent intent = new Intent(
									MediaStore.ACTION_IMAGE_CAPTURE);
							startActivityForResult(intent, RESULT_OK);
							break;
						}
					}
				});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case RESULT_OK:
			switch (resultCode) {
			case Activity.RESULT_OK:

				Bundle bundle = data.getExtras();
				Bitmap bm = (Bitmap) bundle.get("data");
				viewGroup.removeView(tv);
				iv.setImageBitmap(bm);
				break;
			}
			break;
		}
	}
}
