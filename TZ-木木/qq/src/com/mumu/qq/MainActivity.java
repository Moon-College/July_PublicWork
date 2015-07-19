package com.mumu.qq;

import java.lang.reflect.ParameterizedType;
import java.util.jar.Attributes.Name;
import java.util.zip.Inflater;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private RelativeLayout rLayout;
	
	static final int id1 = 1;
	static final int id2 = 2;
	
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//最外层大布局
		RelativeLayout generalLayout =  new RelativeLayout(this);
		generalLayout.setLayoutParams(new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT));
		generalLayout.setBackgroundColor(getResources().getColor(R.color.white));
		

		LinearLayout firstLayout = getFirstLayout();
		firstLayout.setId(id1);
		generalLayout.addView(firstLayout);
		
		LinearLayout secondLayout = getSecondLayout();
		RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)secondLayout.getLayoutParams();
		lp.addRule(RelativeLayout.BELOW, firstLayout.getId());
		generalLayout.addView(secondLayout);
		
		LinearLayout bottomLayout = getBottomLayout();
		generalLayout.addView(bottomLayout);
		
		setContentView(generalLayout);
	}
	
	/**
	 * "搜索" 和 "好友动态、附近、兴趣部落"布局
	 * @return
	 */
	private LinearLayout getFirstLayout() {
		String[] names = {"好友动态", "附近", "兴趣部落"};
		int[] picResId = {R.drawable.friend_update, R.drawable.near, R.drawable.interest_group};
		
		LinearLayout linearLayout = new LinearLayout(this);		
		linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT));
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setBackgroundColor(getResources().getColor(R.color.gray));
		
		//搜索框
		EditText editText = new EditText(this);
		LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		param.setMargins(10, 10, 10 ,10);
		editText.setLayoutParams(param);
		editText.setBackgroundColor(getResources().getColor(R.color.white));
		editText.setGravity(Gravity.CENTER);
		editText.setText("搜索");

		linearLayout.addView(editText);
		
		//功能性布局
		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT));
		((LinearLayout.LayoutParams)layout.getLayoutParams()).setMargins(0, 0, 0 ,20);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layout.setBackgroundColor(getResources().getColor(R.color.white));
		for(int i = 0; i < names.length; i++){
			LinearLayout ll = new LinearLayout(this);
			ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
					LinearLayout.LayoutParams.WRAP_CONTENT, 1));
			ll.setGravity(Gravity.CENTER_HORIZONTAL);
			ll.setOrientation(LinearLayout.VERTICAL);
			
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 
					LinearLayout.LayoutParams.WRAP_CONTENT));
			imageView.setImageResource(picResId[i]);
			ll.addView(imageView);
			
			TextView  tv = new TextView(this);
			LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
					LinearLayout.LayoutParams.MATCH_PARENT, 1); 
			tv.setLayoutParams(contentParams);
			tv.setText(names[i]);
			tv.setGravity(Gravity.CENTER_HORIZONTAL);
			ll.addView(tv);
			
			layout.addView(ll);
			
		}
		
		linearLayout.addView(layout);
		return linearLayout;
	}

	
	/**
	 * 游戏、购物等功能模块布局
	 * @return 该模块布局的LinearLayout
	 */
	private LinearLayout getSecondLayout(){
		String[] strings = {"音乐", "阅读", "喝咖啡"};
		int[] picResId = {R.drawable.music, R.drawable.reading, R.drawable.coffee};
		
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT));
		linearLayout.setOrientation(LinearLayout.VERTICAL);
	
		for (int index = 0; index < strings.length; index++) {
			LinearLayout ll = new LinearLayout(this);
			ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 50));
			ll.setPadding(10, 5, 10, 5);
			ll.setOrientation(LinearLayout.HORIZONTAL);
			
			ImageView imageView = new ImageView(this);
			//LinearLayout.LayoutParams picParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 
			//		LinearLayout.LayoutParams.MATCH_PARENT, 0); 
			LinearLayout.LayoutParams picParams = new LinearLayout.LayoutParams(40, 40, 0);
			imageView.setLayoutParams(picParams);
			imageView.setBackgroundResource(picResId[index]);
			ll.addView(imageView, 0);
			
			TextView  textView = new TextView(this);
			LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
					LinearLayout.LayoutParams.MATCH_PARENT, 1); 
			textView.setLayoutParams(contentParams);
			textView.setPadding(10, 0, 0, 0);
			textView.setText(strings[index]);
			ll.addView(textView, 1);
			
			ImageView nextImageView = new ImageView(this);
			LinearLayout.LayoutParams nextPicParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 
					LinearLayout.LayoutParams.MATCH_PARENT, 0); 
			nextImageView.setLayoutParams(nextPicParams);
			nextImageView.setBackgroundResource(R.drawable.next);
			ll.addView(nextImageView, 2);
			
			linearLayout.addView(ll, index);
		}
		
		return linearLayout;
	}

	/**
	 * 底部的“消息、联系人、动态”的布局
	 * @return
	 */
	private LinearLayout getBottomLayout(){
		int[] resArray = {R.drawable.skin_tab_icon_conversation_normal,
				R.drawable.skin_tab_icon_contact_normal,
				R.drawable.skin_tab_icon_plugin_selected};
		
		LinearLayout linearLayout = new LinearLayout(this);
		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		linearLayout.setLayoutParams(param);
		linearLayout.setBackgroundColor(getResources().getColor(R.color.gray));
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		
		for(int i = 0; i < resArray.length; i++ ){
			ImageView nextImageView = new ImageView(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 
					LinearLayout.LayoutParams.MATCH_PARENT, 1); 
			nextImageView.setLayoutParams(params);
			nextImageView.setBackgroundResource(resArray[i]);
			linearLayout.addView(nextImageView, i);
		}
		
		return linearLayout;
	}

}
