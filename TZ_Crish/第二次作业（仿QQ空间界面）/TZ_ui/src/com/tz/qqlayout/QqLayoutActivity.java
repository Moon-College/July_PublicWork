package com.tz.qqlayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QqLayoutActivity extends Activity {
	private LinearLayout mOneLl;
	private LinearLayout mTwoLl;
	
	private List<Map<String, Object>> appList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qqzone);
		
		//初始化容器控件
		mOneLl = (LinearLayout) findViewById(R.id.ll_app_one);
		mTwoLl = (LinearLayout) findViewById(R.id.ll_app_two);
		mOneLl.setOrientation(LinearLayout.VERTICAL);
		mTwoLl.setOrientation(LinearLayout.VERTICAL);
		
		//模拟获取数据信息
		appList = loadData();
		
		initAppPlugin();
	}
	
	//初始化插件应用信息
	private void initAppPlugin() {
		if(appList == null) { //非空处理
			return ;
		}
		
		RelativeLayout appItemView; //定义一个相对布局容器
		for(int i = 0, len = appList.size(); i < len; i++) {
			appItemView = getAppItemRelativeLayout(i, appList.get(i));
			
			//定义一个分割线
			View divider = new View(this);
			RelativeLayout.LayoutParams dividerParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
			dividerParams.addRule(RelativeLayout.BELOW, appItemView.getId()); 
			divider.setLayoutParams(dividerParams);
			//设置分割线的颜色
			divider.setBackgroundColor(Color.parseColor("#CACACA")); 
			
			//把同一类型的应用一起显示
			if("1".equals(appList.get(i).get("app_type"))) {
				//把存放应用信息的控件放入容器中
				mOneLl.addView(appItemView);
				mOneLl.addView(divider); //加入一条分割线控件
			} else {
				//把存放应用信息的控件放入容器中
				mTwoLl.addView(appItemView);
				mTwoLl.addView(divider); //加入一条分割线控件
			}
			
			//设置事件监听
			appItemView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//String str = appList.get(appItemView.getId()).
					Toast.makeText(QqLayoutActivity.this, "该应用条目Id：=" + v.getId(), Toast.LENGTH_SHORT).show();
				}
			});
		}
		
	}
	
	//封装应用列表中的其中一个信息为相对布局控件
	private RelativeLayout getAppItemRelativeLayout(int id, Map<String, Object> map) {
		RelativeLayout appItemView = new RelativeLayout(this);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		appItemView.setLayoutParams(layoutParams);
		appItemView.setId(id); //设置ID
		
		//定义存放向前箭头符号的控件ImageView
		ImageView forwardIv = new ImageView(this);
		RelativeLayout.LayoutParams ivParams = new RelativeLayout.LayoutParams(60, 60);
		ivParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);  
		ivParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		ivParams.rightMargin = 20;
		forwardIv.setLayoutParams(ivParams);
        //设置图标
		forwardIv.setImageResource((int)map.get("forward_icon"));
		appItemView.addView(forwardIv);
		
		//定义存放向前箭头符号的控件ImageView
		ImageView appLogoIv = new ImageView(this);
		appLogoIv.setId(1);
		RelativeLayout.LayoutParams appLogoParams = new RelativeLayout.LayoutParams(100, 100);
		appLogoParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);  
		appLogoParams.leftMargin = 30;
		appLogoParams.topMargin = 20;
		appLogoParams.bottomMargin = 20;
		appLogoIv.setLayoutParams(appLogoParams);
		//设置图标
		appLogoIv.setImageResource((int)map.get("icon"));
		appItemView.addView(appLogoIv);
		
		//定义存放应用名称的控件TextView
		TextView appNameTv = new TextView(this);
		RelativeLayout.LayoutParams appNameParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		appNameParams.addRule(RelativeLayout.RIGHT_OF, appLogoIv.getId()); 
		appNameParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);    
		appNameParams.leftMargin = 35;
		appNameTv.setLayoutParams(appNameParams);
		//设置应用名称相关属性
		appNameTv.setText((String)map.get("app_name"));
		appNameTv.setTextColor(Color.BLACK);
		appNameTv.setTextSize(16f);
		appItemView.addView(appNameTv);
		
		return appItemView;
	}

	//模拟网络加载应用模块数据
	private List<Map<String, Object>> loadData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for(int i=0; i < 10; i++) {
			map = new HashMap<String, Object>();
			map.put("icon", R.drawable.fnj);
			map.put("app_name", "附近的群");
			map.put("forward_icon", R.drawable.eou);
			
			//设置类型。方便把同一类型放在一起显示
			if(i % 2 == 0) { 
				map.put("app_type", "1"); 
			} else {
				map.put("app_type", "2"); 
			}
			list.add(map);
		}
		
		return list;
	}
	
	
}
