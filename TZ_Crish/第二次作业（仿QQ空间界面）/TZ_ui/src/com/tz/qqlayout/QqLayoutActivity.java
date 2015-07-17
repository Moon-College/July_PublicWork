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
		
		//��ʼ�������ؼ�
		mOneLl = (LinearLayout) findViewById(R.id.ll_app_one);
		mTwoLl = (LinearLayout) findViewById(R.id.ll_app_two);
		mOneLl.setOrientation(LinearLayout.VERTICAL);
		mTwoLl.setOrientation(LinearLayout.VERTICAL);
		
		//ģ���ȡ������Ϣ
		appList = loadData();
		
		initAppPlugin();
	}
	
	//��ʼ�����Ӧ����Ϣ
	private void initAppPlugin() {
		if(appList == null) { //�ǿմ���
			return ;
		}
		
		RelativeLayout appItemView; //����һ����Բ�������
		for(int i = 0, len = appList.size(); i < len; i++) {
			appItemView = getAppItemRelativeLayout(i, appList.get(i));
			
			//����һ���ָ���
			View divider = new View(this);
			RelativeLayout.LayoutParams dividerParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
			dividerParams.addRule(RelativeLayout.BELOW, appItemView.getId()); 
			divider.setLayoutParams(dividerParams);
			//���÷ָ��ߵ���ɫ
			divider.setBackgroundColor(Color.parseColor("#CACACA")); 
			
			//��ͬһ���͵�Ӧ��һ����ʾ
			if("1".equals(appList.get(i).get("app_type"))) {
				//�Ѵ��Ӧ����Ϣ�Ŀؼ�����������
				mOneLl.addView(appItemView);
				mOneLl.addView(divider); //����һ���ָ��߿ؼ�
			} else {
				//�Ѵ��Ӧ����Ϣ�Ŀؼ�����������
				mTwoLl.addView(appItemView);
				mTwoLl.addView(divider); //����һ���ָ��߿ؼ�
			}
			
			//�����¼�����
			appItemView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//String str = appList.get(appItemView.getId()).
					Toast.makeText(QqLayoutActivity.this, "��Ӧ����ĿId��=" + v.getId(), Toast.LENGTH_SHORT).show();
				}
			});
		}
		
	}
	
	//��װӦ���б��е�����һ����ϢΪ��Բ��ֿؼ�
	private RelativeLayout getAppItemRelativeLayout(int id, Map<String, Object> map) {
		RelativeLayout appItemView = new RelativeLayout(this);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		appItemView.setLayoutParams(layoutParams);
		appItemView.setId(id); //����ID
		
		//��������ǰ��ͷ���ŵĿؼ�ImageView
		ImageView forwardIv = new ImageView(this);
		RelativeLayout.LayoutParams ivParams = new RelativeLayout.LayoutParams(60, 60);
		ivParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);  
		ivParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		ivParams.rightMargin = 20;
		forwardIv.setLayoutParams(ivParams);
        //����ͼ��
		forwardIv.setImageResource((int)map.get("forward_icon"));
		appItemView.addView(forwardIv);
		
		//��������ǰ��ͷ���ŵĿؼ�ImageView
		ImageView appLogoIv = new ImageView(this);
		appLogoIv.setId(1);
		RelativeLayout.LayoutParams appLogoParams = new RelativeLayout.LayoutParams(100, 100);
		appLogoParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);  
		appLogoParams.leftMargin = 30;
		appLogoParams.topMargin = 20;
		appLogoParams.bottomMargin = 20;
		appLogoIv.setLayoutParams(appLogoParams);
		//����ͼ��
		appLogoIv.setImageResource((int)map.get("icon"));
		appItemView.addView(appLogoIv);
		
		//������Ӧ�����ƵĿؼ�TextView
		TextView appNameTv = new TextView(this);
		RelativeLayout.LayoutParams appNameParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		appNameParams.addRule(RelativeLayout.RIGHT_OF, appLogoIv.getId()); 
		appNameParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);    
		appNameParams.leftMargin = 35;
		appNameTv.setLayoutParams(appNameParams);
		//����Ӧ�������������
		appNameTv.setText((String)map.get("app_name"));
		appNameTv.setTextColor(Color.BLACK);
		appNameTv.setTextSize(16f);
		appItemView.addView(appNameTv);
		
		return appItemView;
	}

	//ģ���������Ӧ��ģ������
	private List<Map<String, Object>> loadData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for(int i=0; i < 10; i++) {
			map = new HashMap<String, Object>();
			map.put("icon", R.drawable.fnj);
			map.put("app_name", "������Ⱥ");
			map.put("forward_icon", R.drawable.eou);
			
			//�������͡������ͬһ���ͷ���һ����ʾ
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
