package com.tz.qqlayout;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tz.qqlayout.bean.QQPlugin;
import com.tz.qqlayout.util.DensityUtil;

/**
 * �������֡�QQ���ֵ�java����ʵ��
 * 
 * @author zhao_jx
 * 
 */
public class MainActivity extends Activity {

	//�Զ�����
	private static List<QQPlugin> customPlugins=new ArrayList<QQPlugin>();
	//ϵͳ���
	private static List<QQPlugin> systemPlugins=new ArrayList<QQPlugin>();
	
	static {
		//����Զ�����
		QQPlugin gamePlugin=new QQPlugin();
		gamePlugin.setImageId(R.drawable.yx);
		gamePlugin.setName("��Ϸ");
		gamePlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		customPlugins.add(gamePlugin);
		
		QQPlugin shopCartPlugin=new QQPlugin();
		shopCartPlugin.setImageId(R.drawable.gw);
		shopCartPlugin.setName("����");
		shopCartPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		customPlugins.add(shopCartPlugin);
		
		QQPlugin readPlugin=new QQPlugin();
		readPlugin.setImageId(R.drawable.yd);
		readPlugin.setName("�Ķ�");
		readPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		customPlugins.add(readPlugin);
		
		QQPlugin applicationPlugin=new QQPlugin();
		applicationPlugin.setImageId(R.drawable.yyb);
		applicationPlugin.setName("Ӧ�ñ�");
		applicationPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		customPlugins.add(applicationPlugin);
		
		//��ʼ��ϵͳ���
		QQPlugin nearPlugin=new QQPlugin();
		nearPlugin.setImageId(R.drawable.fnj);
		nearPlugin.setName("������Ⱥ");
		nearPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		systemPlugins.add(nearPlugin);
		
		QQPlugin eatPlugin=new QQPlugin();
		eatPlugin.setImageId(R.drawable.chwl);
		eatPlugin.setName("�Ժ�����");
		eatPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		systemPlugins.add(eatPlugin);
		
		QQPlugin sameCityPlugin=new QQPlugin();
		sameCityPlugin.setImageId(R.drawable.tcfw);
		sameCityPlugin.setName("ͬ�Ƿ���");
		sameCityPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		systemPlugins.add(sameCityPlugin);
		
		QQPlugin newsPlugin=new QQPlugin();
		newsPlugin.setImageId(R.drawable.txxw);
		newsPlugin.setName("��Ѷ����");
		newsPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		systemPlugins.add(newsPlugin);
		
		QQPlugin playPlugin=new QQPlugin();
		playPlugin.setImageId(R.drawable.zbj);
		playPlugin.setName("ֱ����");
		playPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		systemPlugins.add(playPlugin);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// XMLʵ��QQ����,��ЩͼƬû���ҵ�
		//setContentView(R.layout.main);
		
		// ע���ķ���initSearchLayout������ҵ1��java����ʵ����������
		// initSearchLayout();
		// ע����initQQLayout����������ʵ����QQ���֣��������Բ��֣��������ͷ�������ݡ��ײ������ݵ��������һ����������
		initQQLayout();
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
		svParams.addRule(RelativeLayout.ABOVE, 3);//3Ϊr1_bottom��ID
		sv.setLayoutParams(svParams);
		//ScrollView�ڷ�һ����Բ���
		RelativeLayout svRl=new RelativeLayout(this);
		LayoutParams svRlParams=new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		svRl.setLayoutParams(svRlParams);
		sv.addView(svRl);
		
		//��Բ��ֶ�������һ��������һ����Բ��֣�Ȼ����shape�����༭���Ч�����м�Ÿ�Button,����drawableLeft�Ӹ��Ŵ�
		RelativeLayout et_search=new RelativeLayout(this);
		et_search.setId(21);
		et_search.setBackgroundResource(R.drawable.shape_textview_cart);
		RelativeLayout.LayoutParams et_searchParams=new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		et_searchParams.setMargins(
				DensityUtil.dip2px(this, 7f), 
				DensityUtil.dip2px(this, 5f), 
				DensityUtil.dip2px(this, 7f), 
				DensityUtil.dip2px(this, 5f));
		et_search.setLayoutParams(et_searchParams);
		svRl.addView(et_search);
		
		Button et_search_text=new Button(this);
		//������߷Ŵ�
		et_search_text.setCompoundDrawablesWithIntrinsicBounds(
				getResources().getDrawable(R.drawable.skin_searchbar_icon), 
				null, 
				null, 
				null);
		et_search_text.setGravity(Gravity.CENTER);
		et_search_text.setText("�¿�����ȹ");
		et_search_text.setTextSize(14f);
		et_search_text.setTextColor(0xFFF0F0F0);
		et_search_text.setBackgroundColor(0xFFFFFFFF);
		et_search_text.setPadding(0, 0, 0, 0);
		RelativeLayout.LayoutParams et_search_textParams=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		et_search_textParams.setMargins(
				DensityUtil.dip2px(this, 0f), 
				DensityUtil.dip2px(this, 0f), 
				DensityUtil.dip2px(this, 0f), 
				DensityUtil.dip2px(this, 0f));
		et_search_textParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		et_search_text.setLayoutParams(et_search_textParams);
		et_search.addView(et_search_text);
		
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
				getResources().getDrawable(R.drawable.xqbl), 
				null, 
				null);
		
		LinearLayout.LayoutParams btn_sub_interest_tribeParams=new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		btn_sub_interest_tribe.setLayoutParams(btn_sub_interest_tribeParams);
		ll_sub_dynamic.addView(btn_sub_interest_tribe);

		//��ʼ���Զ�����
		svRl.addView(initCustomPlugin(customPlugins));
		
		//��ʼ��ϵͳ���
		svRl.addView(initSystemPlugin(systemPlugins));
		return sv;
	}
	
	
	/**
	 * ��ʼ���Զ�����
	 * @param customPlugins �Զ������б�
	 * @return
	 */
	private View initCustomPlugin(List<QQPlugin> customPlugins) {
		if(customPlugins!=null&&customPlugins.size()>0){
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
			
			//ѭ����Ӳ��
			boolean isFirst=true;
			for(QQPlugin qqPlugin:customPlugins){
				if(isFirst){
					//����»���
					TextView tv_underline=new TextView(this);
					tv_underline.setWidth(LayoutParams.FILL_PARENT);
					tv_underline.setHeight(DensityUtil.dip2px(this, 1f));
					tv_underline.setBackgroundColor(0xFFEBEBEB);
					ll_custom_plugin.addView(tv_underline);
					isFirst=false;
				}
				
				
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
				ll_custom_plugin_sub_iv.setBackgroundResource(qqPlugin.getImageId());
				LinearLayout.LayoutParams ll_custom_plugin_sub_ivParams=new LinearLayout.LayoutParams(
						DensityUtil.dip2px(this, 30f), 
						DensityUtil.dip2px(this, 30f));
				ll_custom_plugin_sub_ivParams.setMargins(DensityUtil.dip2px(this, 5f), 0, 0, 0);
				ll_custom_plugin_sub_iv.setLayoutParams(ll_custom_plugin_sub_ivParams);
				ll_custom_plugin_sub.addView(ll_custom_plugin_sub_iv);
				//�������
				TextView ll_custom_plugin_sub_tv=new TextView(this);
				ll_custom_plugin_sub_tv.setText(qqPlugin.getName());
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
				ll_custom_plugin_sub_lastiv.setBackgroundResource(qqPlugin.getRedirectImageId());
				LinearLayout.LayoutParams ll_custom_plugin_sub_lastivParams=new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, 
						LayoutParams.WRAP_CONTENT);
				ll_custom_plugin_sub_lastivParams.setMargins(DensityUtil.dip2px(this, 5f), 0, 0, 0);
				ll_custom_plugin_sub_lastivParams.gravity=Gravity.CENTER_VERTICAL;
				ll_custom_plugin_sub.addView(ll_custom_plugin_sub_lastiv);
				
				//ÿ������ײ���һ������
				TextView tv_underline=new TextView(this);
				tv_underline.setWidth(LayoutParams.FILL_PARENT);
				tv_underline.setHeight(DensityUtil.dip2px(this, 1f));
				tv_underline.setBackgroundColor(0xFFEBEBEB);
				ll_custom_plugin.addView(tv_underline);
				
			}
			return ll_custom_plugin;
		}
		return null;
	}

	/**
	 * ��ʼ��ϵͳ���
	 * @param systemPlugins ϵͳ����б�
	 * @return
	 */
	private View initSystemPlugin(List<QQPlugin> systemPlugins) {
		if(systemPlugins!=null&&systemPlugins.size()>0){
			LinearLayout ll_system_plugin=new LinearLayout(this);
			ll_system_plugin.setId(24);//�ڶ��Ӽ��ģ�����С�Ӽ�
			ll_system_plugin.setOrientation(LinearLayout.VERTICAL);
			ll_system_plugin.setBackgroundColor(0xFFFFFFFF);
			RelativeLayout.LayoutParams ll_system_pluginParams=new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			ll_system_pluginParams.addRule(RelativeLayout.BELOW, 23);
			ll_system_pluginParams.setMargins(
					0, 
					DensityUtil.dip2px(this, 15f), 
					0, 
					0);
			ll_system_plugin.setLayoutParams(ll_system_pluginParams);
			
			//ѭ����Ӳ��
			boolean isFirst=true;
			for(QQPlugin qqPlugin:systemPlugins){
				if(isFirst){
					//����»���
					TextView tv_underline=new TextView(this);
					tv_underline.setWidth(LayoutParams.FILL_PARENT);
					tv_underline.setHeight(DensityUtil.dip2px(this, 1f));
					tv_underline.setBackgroundColor(0xFFEBEBEB);
					ll_system_plugin.addView(tv_underline);
					isFirst=false;
				}
				
				
				//����Ĳ��,ˮƽ����
				LinearLayout ll_system_plugin_sub=new LinearLayout(this);
				ll_system_plugin_sub.setOrientation(LinearLayout.HORIZONTAL);
				ll_system_plugin_sub.setPadding(
						DensityUtil.dip2px(this, 10f), 
						DensityUtil.dip2px(this, 10f), 
						DensityUtil.dip2px(this, 10f),
						DensityUtil.dip2px(this, 10f));
				LinearLayout.LayoutParams ll_system_plugin_subParams=new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				ll_system_plugin_sub.setLayoutParams(ll_system_plugin_subParams);
				ll_system_plugin.addView(ll_system_plugin_sub);
				
				//��Ӿ�����
				//���ͼ��
				ImageView ll_system_plugin_sub_iv=new ImageView(this);
				ll_system_plugin_sub_iv.setBackgroundResource(qqPlugin.getImageId());
				LinearLayout.LayoutParams ll_system_plugin_sub_ivParams=new LinearLayout.LayoutParams(
						DensityUtil.dip2px(this, 30f), 
						DensityUtil.dip2px(this, 30f));
				ll_system_plugin_sub_ivParams.setMargins(DensityUtil.dip2px(this, 5f), 0, 0, 0);
				ll_system_plugin_sub_iv.setLayoutParams(ll_system_plugin_sub_ivParams);
				ll_system_plugin_sub.addView(ll_system_plugin_sub_iv);
				//�������
				TextView ll_system_plugin_sub_tv=new TextView(this);
				ll_system_plugin_sub_tv.setText(qqPlugin.getName());
				ll_system_plugin_sub_tv.setTextColor(0xFF000000);
				ll_system_plugin_sub_tv.setTextSize(17f);
				ll_system_plugin_sub_tv.setGravity(Gravity.CENTER_VERTICAL);
				LinearLayout.LayoutParams ll_system_plugin_sub_tvParams=new LinearLayout.LayoutParams(
						LayoutParams.FILL_PARENT, 
						LayoutParams.WRAP_CONTENT,
						1);
				ll_system_plugin_sub_tvParams.setMargins(DensityUtil.dip2px(this, 15f), 0, 0, 0);
				ll_system_plugin_sub_tv.setLayoutParams(ll_system_plugin_sub_tvParams);
				ll_system_plugin_sub.addView(ll_system_plugin_sub_tv);
				//������Ҽ�ͷ
				ImageView ll_system_plugin_sub_lastiv=new ImageView(this);
				ll_system_plugin_sub_lastiv.setBackgroundResource(qqPlugin.getRedirectImageId());
				LinearLayout.LayoutParams ll_system_plugin_sub_lastivParams=new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, 
						LayoutParams.WRAP_CONTENT);
				ll_system_plugin_sub_lastivParams.setMargins(DensityUtil.dip2px(this, 5f), 0, 0, 0);
				ll_system_plugin_sub_lastivParams.gravity=Gravity.CENTER_VERTICAL;
				ll_system_plugin_sub.addView(ll_system_plugin_sub_lastiv);
				
				//ÿ������ײ���һ������
				TextView tv_underline=new TextView(this);
				tv_underline.setWidth(LayoutParams.FILL_PARENT);
				tv_underline.setHeight(DensityUtil.dip2px(this, 1f));
				tv_underline.setBackgroundColor(0xFFEBEBEB);
				ll_system_plugin.addView(tv_underline);
				
			}
			return ll_system_plugin;
		}
		return null;
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
		

		// bottomRl�����ٷ�����ImageButton
		// ��Ϣ������룬��ֱ����
		ImageButton ib_bottom_message = new ImageButton(this);
		ib_bottom_message.setId(11);
		//����ѡ����
		ib_bottom_message.setBackgroundResource(R.drawable.selector_message);
		//���ÿ��Ի�ȡ����
		ib_bottom_message.setFocusable(true);
		ib_bottom_message.setFocusableInTouchMode(true);
		RelativeLayout.LayoutParams ib_bottom_messageParams = new RelativeLayout.LayoutParams(
				DensityUtil.dip2px(this, 60f), DensityUtil.dip2px(this, 60f));
		ib_bottom_messageParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		ib_bottom_messageParams.addRule(RelativeLayout.CENTER_VERTICAL);
		ib_bottom_messageParams.setMargins(DensityUtil.dip2px(this, 15f), 0, 0, 0);
		
		ib_bottom_message.setLayoutParams(ib_bottom_messageParams);
		bottomRl.addView(ib_bottom_message);

		// ��ϵ�ˣ��в�����
		ImageButton ib_bottom_contract = new ImageButton(this);
		ib_bottom_contract.setId(11);
		//����ѡ����
		ib_bottom_contract.setBackgroundResource(R.drawable.selector_contract);
		//���ÿ��Ի�ȡ����
		ib_bottom_contract.setFocusable(true);
		ib_bottom_contract.setFocusableInTouchMode(true);
		RelativeLayout.LayoutParams ib_bottom_contractParams = new RelativeLayout.LayoutParams(
				DensityUtil.dip2px(this, 60f), DensityUtil.dip2px(this, 60f));
		ib_bottom_contractParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		ib_bottom_contract.setLayoutParams(ib_bottom_contractParams);
		bottomRl.addView(ib_bottom_contract);

		// ��̬,�Ҷ��룬��ֱ����
		ImageButton ib_bottom_dynamic = new ImageButton(this);
		ib_bottom_dynamic.setId(11);
		ib_bottom_dynamic
				.setBackgroundResource(R.drawable.selector_dynamic);
		ib_bottom_dynamic.setFocusable(true);
		ib_bottom_dynamic.setFocusableInTouchMode(true);
		RelativeLayout.LayoutParams ib_bottom_dynamicParams = new RelativeLayout.LayoutParams(
				DensityUtil.dip2px(this, 60f), DensityUtil.dip2px(this, 60f));
		ib_bottom_dynamicParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		ib_bottom_dynamicParams.addRule(RelativeLayout.CENTER_VERTICAL);
		ib_bottom_dynamicParams.setMargins(0, 0, DensityUtil.dip2px(this, 15f), 0);
		ib_bottom_dynamic.setLayoutParams(ib_bottom_dynamicParams);
		bottomRl.addView(ib_bottom_dynamic);
		return bottomRl;
	}

}