package com.tz.qqlayout;

import com.tz.qqlayout.util.DensityUtil;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * �������֡�QQ���ֵ�java����ʵ��
 * 
 * @author zhao_jx
 * 
 */
public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// XMLʵ��QQ����,��ЩͼƬû���ҵ�
		setContentView(R.layout.main);
		// ע���ķ���initSearchLayout������ҵ1��java����ʵ����������
		// initSearchLayout();
		// ע����initQQLayout����������ʵ����QQ���֣�����ʱ�����⣬ֻ��д�����ˣ��������Բ��֣��������ͷ�������ݡ��ײ������ݵ��������һ����������
		// initQQLayout();
	}

	/**
	 * ��������
	 */
	private void initSearchLayout() {
		// һ��ˮƽ���Բ������һ�������һ����ť
		LinearLayout ll = new LinearLayout(this);
		// ���ò��ַ���
		ll.setOrientation(LinearLayout.HORIZONTAL);
		// �����丸����
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		ll.setLayoutParams(params);

		// ���һ���༭��
		EditText et = new EditText(this);
		et.setHint("������һ����ַ");
		LinearLayout.LayoutParams etParams = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1);
		et.setLayoutParams(etParams);
		ll.addView(et);

		// �ұ�һ��������ť
		Button btn = new Button(this);
		btn.setText("����");
		LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0);
		btn.setLayoutParams(btnParams);
		ll.addView(btn);

		setContentView(ll);
	}

	/**
	 * ��ʼ��QQ����
	 */
	private void initQQLayout() {
		// ID���������ֵ�ģ�飬�������Ӽ�ģ�飬��100���ٵ�������1��ʼ
		// ����һ����Բ���: ���飬ͷ�����ײ����м�һ��������ͼ
		RelativeLayout rl = new RelativeLayout(this);
		rl.setBackgroundColor(0xFFF8F8FF);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		rl.setLayoutParams(params);

		//���ͷ��
		rl.addView(initQQLayoutHead());
		
		//������ݲ���
		rl.addView(initQQLayoutContent());
				
		//��ӵײ�
		rl.addView(initQQLayoutBottom());
		setContentView(rl);
	}

	/**
	 * ��ʼ���м�����
	 * @return
	 */
	private View initQQLayoutContent() {
		ScrollView sv=new ScrollView(this);
		sv.setBackgroundColor(0xFFF0F0F0);
		RelativeLayout.LayoutParams svParams=new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		svParams.addRule(RelativeLayout.BELOW, 1);//1Ϊr1_top��ID
		sv.setLayoutParams(svParams);
		//ScrollView�ڷ�һ����Բ���
		RelativeLayout svRl=new RelativeLayout(this);
		LayoutParams svRlParams=new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		svRl.setLayoutParams(svRlParams);
		sv.addView(svRl);
		
		//��Բ��ֶ�������һ��������
		EditText et_search=new EditText(this);
		et_search.setId(21);
		et_search.setPadding(
				DensityUtil.dip2px(this, 5f), 
				DensityUtil.dip2px(this, 5f), 
				DensityUtil.dip2px(this, 5f), 
				DensityUtil.dip2px(this, 5f));
		//���ò��Զ���ý���
		et_search.setFocusable(true);
		et_search.setFocusableInTouchMode(true);
		et_search.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_textview_cart));
		et_search.setGravity(Gravity.CENTER);
		et_search.setHint("�¿�����ȹ");
		et_search.setTextSize(18f);
		RelativeLayout.LayoutParams et_searchParams=new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		et_searchParams.setMargins(
				DensityUtil.dip2px(this, 10f), 
				DensityUtil.dip2px(this, 5f), 
				DensityUtil.dip2px(this, 10f), 
				DensityUtil.dip2px(this, 5f));
		et_search.setLayoutParams(et_searchParams);
		svRl.addView(et_search);
		
		//����һ��ˮƽ�����Բ���
		LinearLayout ll_sub_dynamic=new LinearLayout(this);
		ll_sub_dynamic.setId(22);
		ll_sub_dynamic.setBackgroundColor(0xFFFFFFFF);
		ll_sub_dynamic.setOrientation(LinearLayout.HORIZONTAL);
		ll_sub_dynamic.setPadding(0, 0, 0, 0);
		RelativeLayout.LayoutParams ll_sub_dynamicParams=new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		ll_sub_dynamicParams.addRule(RelativeLayout.BELOW,21);// ������������
		ll_sub_dynamic.setLayoutParams(ll_sub_dynamicParams);
		svRl.addView(ll_sub_dynamic);
		
        //ˮƽ���Բ������ٷ�����button
		//���Ѷ�̬
		Button btn_sub_friend_dynamic=new Button(this);
		btn_sub_friend_dynamic.setId(221);
		btn_sub_friend_dynamic.setBackgroundColor(0xFFFFFFFF);
		btn_sub_friend_dynamic.setText("���Ѷ�̬");
		//���ð�ť��������ͼƬ
		btn_sub_friend_dynamic.setCompoundDrawablesWithIntrinsicBounds(
				null, 
				getResources().getDrawable(R.drawable.gns), 
				null, 
				null);
		
		LinearLayout.LayoutParams btn_sub_friend_dynamicParams=new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		btn_sub_friend_dynamic.setLayoutParams(btn_sub_friend_dynamicParams);
		ll_sub_dynamic.addView(btn_sub_friend_dynamic);
		//���Ѷ�̬
		Button btn_sub_nearby=new Button(this);
		btn_sub_nearby.setId(221);
		btn_sub_nearby.setBackgroundColor(0xFFFFFFFF);
		btn_sub_nearby.setText("����");
		//���ð�ť��������ͼƬ
		btn_sub_nearby.setCompoundDrawablesWithIntrinsicBounds(
				null, 
				getResources().getDrawable(R.drawable.eqc), 
				null, 
				null);
		
		LinearLayout.LayoutParams btn_sub_nearbyParams=new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		btn_sub_nearby.setLayoutParams(btn_sub_nearbyParams);
		ll_sub_dynamic.addView(btn_sub_nearby);
		//��Ȥ����
		Button btn_sub_interest_tribe=new Button(this);
		btn_sub_interest_tribe.setId(223);
		btn_sub_interest_tribe.setBackgroundColor(0xFFFFFFFF);
		btn_sub_interest_tribe.setText("��Ȥ����");
		//���ð�ť��������ͼƬ
		btn_sub_interest_tribe.setCompoundDrawablesWithIntrinsicBounds(
				null, 
				getResources().getDrawable(R.drawable.eqc), 
				null, 
				null);
		
		LinearLayout.LayoutParams btn_sub_interest_tribeParams=new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		btn_sub_interest_tribe.setLayoutParams(btn_sub_interest_tribeParams);
		ll_sub_dynamic.addView(btn_sub_interest_tribe);

		//��ʼ���Զ�����
		svRl.addView(initCustomPlugin());
		return sv;
	}
	
	
	/**
	 * ��ʼ���Զ�����
	 * @return
	 */
	private View initCustomPlugin() {
		
		LinearLayout ll_custom_plugin=new LinearLayout(this);
		ll_custom_plugin.setId(23);//�ڶ��Ӽ��ģ�����С�Ӽ�
		ll_custom_plugin.setOrientation(LinearLayout.VERTICAL);
		ll_custom_plugin.setBackgroundColor(0xFFFFFFFF);
		RelativeLayout.LayoutParams ll_custom_pluginParams=new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		ll_custom_pluginParams.addRule(RelativeLayout.BELOW, 22);
		ll_custom_pluginParams.setMargins(
				0, 
				DensityUtil.dip2px(this, 15f), 
				0, 
				0);
		ll_custom_plugin.setLayoutParams(ll_custom_pluginParams);
		//����»���
		TextView tv_underline=new TextView(this);
		tv_underline.setWidth(LayoutParams.FILL_PARENT);
		tv_underline.setHeight(DensityUtil.dip2px(this, 1f));
		tv_underline.setBackgroundColor(0xFFEBEBEB);
		ll_custom_plugin.addView(tv_underline);
		
		//����Ĳ��,ˮƽ����
		LinearLayout ll_custom_plugin_sub=new LinearLayout(this);
		ll_custom_plugin_sub.setOrientation(LinearLayout.HORIZONTAL);
		ll_custom_plugin_sub.setPadding(
				DensityUtil.dip2px(this, 10f), 
				DensityUtil.dip2px(this, 10f), 
				DensityUtil.dip2px(this, 10f),
				DensityUtil.dip2px(this, 10f));
		LinearLayout.LayoutParams ll_custom_plugin_subParams=new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		ll_custom_plugin_sub.setLayoutParams(ll_custom_plugin_subParams);
		ll_custom_plugin.addView(ll_custom_plugin_sub);
		
		//��Ӿ�����
		//���ͼ��
		ImageView ll_custom_plugin_sub_iv=new ImageView(this);
		ll_custom_plugin_sub_iv.setBackgroundResource(R.drawable.fnj);
		LinearLayout.LayoutParams ll_custom_plugin_sub_ivParams=new LinearLayout.LayoutParams(
				DensityUtil.dip2px(this, 30f), 
				DensityUtil.dip2px(this, 30f));
		ll_custom_plugin_sub_ivParams.setMargins(DensityUtil.dip2px(this, 5f), 0, 0, 0);
		ll_custom_plugin_sub_iv.setLayoutParams(ll_custom_plugin_sub_ivParams);
		ll_custom_plugin_sub.addView(ll_custom_plugin_sub_iv);
		//�������
		TextView ll_custom_plugin_sub_tv=new TextView(this);
		ll_custom_plugin_sub_tv.setText("��Ϸ");
		ll_custom_plugin_sub_tv.setTextColor(0xFF000000);
		ll_custom_plugin_sub_tv.setTextSize(17f);
		ll_custom_plugin_sub_tv.setGravity(Gravity.CENTER_VERTICAL);
		LinearLayout.LayoutParams ll_custom_plugin_sub_tvParams=new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, 
				LayoutParams.WRAP_CONTENT,
				1);
		ll_custom_plugin_sub_tvParams.setMargins(DensityUtil.dip2px(this, 15f), 0, 0, 0);
		ll_custom_plugin_sub_tv.setLayoutParams(ll_custom_plugin_sub_tvParams);
		ll_custom_plugin_sub.addView(ll_custom_plugin_sub_tv);
		//������Ҽ�ͷ
		ImageView ll_custom_plugin_sub_lastiv=new ImageView(this);
		ll_custom_plugin_sub_lastiv.setBackgroundResource(R.drawable.skin_icon_arrow_right_normal);
		LinearLayout.LayoutParams ll_custom_plugin_sub_lastivParams=new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, 
				LayoutParams.WRAP_CONTENT);
		ll_custom_plugin_sub_lastivParams.setMargins(DensityUtil.dip2px(this, 5f), 0, 0, 0);
		ll_custom_plugin_sub_lastivParams.gravity=Gravity.CENTER_VERTICAL;
		ll_custom_plugin_sub_lastiv.setBackgroundResource(R.drawable.skin_icon_arrow_right_normal);
		ll_custom_plugin_sub.addView(ll_custom_plugin_sub_lastiv);
		
		return ll_custom_plugin;
	}

	/**
	 * ��ʼ��ͷ��
	 */
	private View initQQLayoutHead() {
		// ͷ����һ���̶�����Բ���
		RelativeLayout topRl = new RelativeLayout(this);
		// ����ID�������沼������
		topRl.setId(1);
		topRl.setBackgroundColor(0xFF18B4ED);
		topRl.setPadding(DensityUtil.dip2px(this, 7f),
				DensityUtil.dip2px(this, 7f), DensityUtil.dip2px(this, 7f),
				DensityUtil.dip2px(this, 7f));
		RelativeLayout.LayoutParams topRlParams = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		// ����ڸ���������������
		topRlParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		topRl.setLayoutParams(topRlParams);
		

		// topRl�����ٷ�һ��ImageView������TextView
		// �û�ͷ������룬��ֱ����
		ImageView iv_top_usericon = new ImageView(this);
		iv_top_usericon.setId(11);
		iv_top_usericon.setImageResource(R.drawable.lnz);
		RelativeLayout.LayoutParams iv_top_usericonParams = new RelativeLayout.LayoutParams(
				DensityUtil.dip2px(this, 35f), DensityUtil.dip2px(this, 35f));
		iv_top_usericonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		iv_top_usericonParams.addRule(RelativeLayout.CENTER_VERTICAL);
		iv_top_usericon.setLayoutParams(iv_top_usericonParams);
		topRl.addView(iv_top_usericon);

		// ��̬���в�����
		TextView tv_top_dynamic = new TextView(this);
		tv_top_dynamic.setId(12);
		tv_top_dynamic.setText("��̬");
		tv_top_dynamic.setTextColor(0xFFFFFFFF);
		tv_top_dynamic.setTextSize(20);
		RelativeLayout.LayoutParams tv_top_dynamicParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		tv_top_dynamicParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		tv_top_dynamic.setLayoutParams(tv_top_dynamicParams);
		topRl.addView(tv_top_dynamic);

		// ����,�Ҷ��룬��ֱ����
		TextView tv_top_more = new TextView(this);
		tv_top_more.setId(13);
		tv_top_more.setText("����");
		tv_top_more.setTextColor(0xFFFFFFFF);
		tv_top_more.setTextSize(18);
		RelativeLayout.LayoutParams tv_top_moreParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		tv_top_moreParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		tv_top_moreParams.addRule(RelativeLayout.CENTER_VERTICAL);
		tv_top_more.setLayoutParams(tv_top_moreParams);
		topRl.addView(tv_top_more);
		return topRl;
	}
	
	/**
	 * ��ʼ���ײ�
	 */
	private View initQQLayoutBottom() {
//	        <ImageView
//	            android:id="@+id/iv_bottom_message"
//	            android:layout_width="60dp"
//	            android:layout_height="60dp"
//	            android:layout_alignParentLeft="true"
//	            android:layout_centerVertical="true"
//	            android:layout_marginLeft="15dp"
//	            android:background="@drawable/skin_tab_icon_conversation_normal" />
//
//	        <ImageView
//	            android:id="@+id/iv_bottom_contract"
//	            android:layout_width="60dp"
//	            android:layout_height="60dp"
//	            android:layout_centerInParent="true"
//	            android:src="@drawable/skin_tab_icon_contact_normal" />
//
//	        <ImageView
//	            android:id="@+id/iv_bottom_dynamic"
//	            android:layout_width="60dp"
//	            android:layout_height="60dp"
//	            android:layout_alignParentRight="true"
//	            android:layout_centerVertical="true"
//	            android:layout_marginRight="15dp"
//	            android:src="@drawable/skin_tab_icon_plugin_normal" />
//	    </RelativeLayout>
		
		
		// �ײ�һ���̶�����Բ���
		RelativeLayout bottomRl = new RelativeLayout(this);
		// ����ID�������沼������
		bottomRl.setId(3);
		bottomRl.setBackgroundColor(0xFFD4D4D4);
		bottomRl.setPadding(DensityUtil.dip2px(this, 5f),
				DensityUtil.dip2px(this, 5f), DensityUtil.dip2px(this, 5f),
				DensityUtil.dip2px(this, 5f));
		RelativeLayout.LayoutParams bottomRlParams = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		// ����ڸ��������ײ�����
		bottomRlParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		bottomRl.setLayoutParams(bottomRlParams);
		

		// bottomRl�����ٷ�����imageview
		// ��Ϣ������룬��ֱ����
		ImageView iv_bottom_message = new ImageView(this);
		iv_bottom_message.setId(11);
		iv_bottom_message.setImageResource(R.drawable.skin_tab_icon_conversation_normal);
		RelativeLayout.LayoutParams iv_bottom_messageParams = new RelativeLayout.LayoutParams(
				DensityUtil.dip2px(this, 60f), DensityUtil.dip2px(this, 60f));
		iv_bottom_messageParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		iv_bottom_messageParams.addRule(RelativeLayout.CENTER_VERTICAL);
		iv_bottom_messageParams.setMargins(DensityUtil.dip2px(this, 15f), 0, 0, 0);
		
		iv_bottom_message.setLayoutParams(iv_bottom_messageParams);
		bottomRl.addView(iv_bottom_message);

		// ��ϵ�ˣ��в�����
		ImageView iv_bottom_contract = new ImageView(this);
		iv_bottom_contract.setId(11);
		iv_bottom_contract.setImageResource(R.drawable.skin_tab_icon_contact_normal);
		RelativeLayout.LayoutParams iv_bottom_contractParams = new RelativeLayout.LayoutParams(
				DensityUtil.dip2px(this, 60f), DensityUtil.dip2px(this, 60f));
		iv_bottom_contractParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		iv_bottom_contract.setLayoutParams(iv_bottom_contractParams);
		bottomRl.addView(iv_bottom_contract);

		// ��̬,�Ҷ��룬��ֱ����
		ImageView iv_bottom_dynamic = new ImageView(this);
		iv_bottom_dynamic.setId(11);
		iv_bottom_dynamic
				.setImageResource(R.drawable.skin_tab_icon_plugin_normal);
		RelativeLayout.LayoutParams iv_bottom_dynamicParams = new RelativeLayout.LayoutParams(
				DensityUtil.dip2px(this, 60f), DensityUtil.dip2px(this, 60f));
		iv_bottom_dynamicParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		iv_bottom_dynamicParams.addRule(RelativeLayout.CENTER_VERTICAL);
		iv_bottom_dynamicParams.setMargins(0, 0, DensityUtil.dip2px(this, 15f), 0);
		iv_bottom_dynamic.setLayoutParams(iv_bottom_dynamicParams);
		bottomRl.addView(iv_bottom_dynamic);
		return bottomRl;
	}

}