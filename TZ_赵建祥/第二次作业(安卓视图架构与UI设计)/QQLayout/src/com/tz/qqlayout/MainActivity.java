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
 * 搜索布局、QQ布局的java代码实现
 * 
 * @author zhao_jx
 * 
 */
public class MainActivity extends Activity {

	//自定义插件
	private static List<QQPlugin> customPlugins=new ArrayList<QQPlugin>();
	//系统插件
	private static List<QQPlugin> systemPlugins=new ArrayList<QQPlugin>();
	
	static {
		//添加自定义插件
		QQPlugin gamePlugin=new QQPlugin();
		gamePlugin.setImageId(R.drawable.yx);
		gamePlugin.setName("游戏");
		gamePlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		customPlugins.add(gamePlugin);
		
		QQPlugin shopCartPlugin=new QQPlugin();
		shopCartPlugin.setImageId(R.drawable.gw);
		shopCartPlugin.setName("购物");
		shopCartPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		customPlugins.add(shopCartPlugin);
		
		QQPlugin readPlugin=new QQPlugin();
		readPlugin.setImageId(R.drawable.yd);
		readPlugin.setName("阅读");
		readPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		customPlugins.add(readPlugin);
		
		QQPlugin applicationPlugin=new QQPlugin();
		applicationPlugin.setImageId(R.drawable.yyb);
		applicationPlugin.setName("应用宝");
		applicationPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		customPlugins.add(applicationPlugin);
		
		//初始化系统插件
		QQPlugin nearPlugin=new QQPlugin();
		nearPlugin.setImageId(R.drawable.fnj);
		nearPlugin.setName("附近的群");
		nearPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		systemPlugins.add(nearPlugin);
		
		QQPlugin eatPlugin=new QQPlugin();
		eatPlugin.setImageId(R.drawable.chwl);
		eatPlugin.setName("吃喝玩乐");
		eatPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		systemPlugins.add(eatPlugin);
		
		QQPlugin sameCityPlugin=new QQPlugin();
		sameCityPlugin.setImageId(R.drawable.tcfw);
		sameCityPlugin.setName("同城服务");
		sameCityPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		systemPlugins.add(sameCityPlugin);
		
		QQPlugin newsPlugin=new QQPlugin();
		newsPlugin.setImageId(R.drawable.txxw);
		newsPlugin.setName("腾讯新闻");
		newsPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		systemPlugins.add(newsPlugin);
		
		QQPlugin playPlugin=new QQPlugin();
		playPlugin.setImageId(R.drawable.zbj);
		playPlugin.setName("直播间");
		playPlugin.setRedirectImageId(R.drawable.skin_icon_arrow_right_normal);
		systemPlugins.add(playPlugin);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// XML实现QQ布局,有些图片没有找到
		//setContentView(R.layout.main);
		
		// 注掉的方法initSearchLayout，是作业1、java代码实现搜索布局
		// initSearchLayout();
		// 注掉的initQQLayout方法，初步实现了QQ布局，最外层相对布局：里面包括头部、内容、底部，内容的最外层用一个滚动布局
		initQQLayout();
	}

	/**
	 * 搜索布局
	 */
	private void initSearchLayout() {
		// 一个水平线性布局里，放一个输入框、一个按钮
		LinearLayout ll = new LinearLayout(this);
		// 设置布局方向
		ll.setOrientation(LinearLayout.HORIZONTAL);
		// 宽高填充父容器
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		ll.setLayoutParams(params);

		// 左边一个编辑框
		EditText et = new EditText(this);
		et.setHint("请输入一个网址");
		LinearLayout.LayoutParams etParams = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1);
		et.setLayoutParams(etParams);
		ll.addView(et);

		// 右边一个搜索按钮
		Button btn = new Button(this);
		btn.setText("搜索");
		LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0);
		btn.setLayoutParams(btnParams);
		ll.addView(btn);

		setContentView(ll);
	}

	/**
	 * 初始化QQ布局
	 */
	private void initQQLayout() {
		// ID命名规则，兄弟模块，递增，子级模块，乘100，再递增，从1开始
		// 最外一个相对布局: 三块，头部、底部，中间一个滚动视图
		RelativeLayout rl = new RelativeLayout(this);
		rl.setBackgroundColor(0xFFF8F8FF);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		rl.setLayoutParams(params);

		//添加头部
		rl.addView(initQQLayoutHead());
		
		//添加内容部分
		rl.addView(initQQLayoutContent());
				
		//添加底部
		rl.addView(initQQLayoutBottom());
		setContentView(rl);
	}

	/**
	 * 初始化中间内容
	 * @return
	 */
	private View initQQLayoutContent() {
		ScrollView sv=new ScrollView(this);
		sv.setBackgroundColor(0xFFF0F0F0);
		RelativeLayout.LayoutParams svParams=new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		svParams.addRule(RelativeLayout.BELOW, 1);//1为r1_top的ID
		svParams.addRule(RelativeLayout.ABOVE, 3);//3为r1_bottom的ID
		sv.setLayoutParams(svParams);
		//ScrollView内放一个相对布局
		RelativeLayout svRl=new RelativeLayout(this);
		LayoutParams svRlParams=new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		svRl.setLayoutParams(svRlParams);
		sv.addView(svRl);
		
		//相对布局顶部，放一个搜索框：一个相对布局，然后用shape做出编辑框的效果，中间放个Button,再用drawableLeft加个放大镜
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
		//设置左边放大镜
		et_search_text.setCompoundDrawablesWithIntrinsicBounds(
				getResources().getDrawable(R.drawable.skin_searchbar_icon), 
				null, 
				null, 
				null);
		et_search_text.setGravity(Gravity.CENTER);
		et_search_text.setText("新款连衣裙");
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
		
		//接着一个水平的线性布局
		LinearLayout ll_sub_dynamic=new LinearLayout(this);
		ll_sub_dynamic.setId(22);
		ll_sub_dynamic.setBackgroundColor(0xFFFFFFFF);
		ll_sub_dynamic.setOrientation(LinearLayout.HORIZONTAL);
		ll_sub_dynamic.setPadding(0, 0, 0, 0);
		RelativeLayout.LayoutParams ll_sub_dynamicParams=new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		ll_sub_dynamicParams.addRule(RelativeLayout.BELOW,21);// 放在搜索框下
		ll_sub_dynamic.setLayoutParams(ll_sub_dynamicParams);
		svRl.addView(ll_sub_dynamic);
		
        //水平线性布局中再放三个button
		//好友动态
		Button btn_sub_friend_dynamic=new Button(this);
		btn_sub_friend_dynamic.setId(221);
		btn_sub_friend_dynamic.setBackgroundColor(0xFFFFFFFF);
		btn_sub_friend_dynamic.setText("好友动态");
		//设置按钮文字四周图片
		btn_sub_friend_dynamic.setCompoundDrawablesWithIntrinsicBounds(
				null, 
				getResources().getDrawable(R.drawable.gns), 
				null, 
				null);
		
		LinearLayout.LayoutParams btn_sub_friend_dynamicParams=new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		btn_sub_friend_dynamic.setLayoutParams(btn_sub_friend_dynamicParams);
		ll_sub_dynamic.addView(btn_sub_friend_dynamic);
		//好友动态
		Button btn_sub_nearby=new Button(this);
		btn_sub_nearby.setId(221);
		btn_sub_nearby.setBackgroundColor(0xFFFFFFFF);
		btn_sub_nearby.setText("附近");
		//设置按钮文字四周图片
		btn_sub_nearby.setCompoundDrawablesWithIntrinsicBounds(
				null, 
				getResources().getDrawable(R.drawable.eqc), 
				null, 
				null);
		
		LinearLayout.LayoutParams btn_sub_nearbyParams=new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		btn_sub_nearby.setLayoutParams(btn_sub_nearbyParams);
		ll_sub_dynamic.addView(btn_sub_nearby);
		//兴趣部落
		Button btn_sub_interest_tribe=new Button(this);
		btn_sub_interest_tribe.setId(223);
		btn_sub_interest_tribe.setBackgroundColor(0xFFFFFFFF);
		btn_sub_interest_tribe.setText("兴趣部落");
		//设置按钮文字四周图片
		btn_sub_interest_tribe.setCompoundDrawablesWithIntrinsicBounds(
				null, 
				getResources().getDrawable(R.drawable.xqbl), 
				null, 
				null);
		
		LinearLayout.LayoutParams btn_sub_interest_tribeParams=new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		btn_sub_interest_tribe.setLayoutParams(btn_sub_interest_tribeParams);
		ll_sub_dynamic.addView(btn_sub_interest_tribe);

		//初始化自定义插件
		svRl.addView(initCustomPlugin(customPlugins));
		
		//初始化系统插件
		svRl.addView(initSystemPlugin(systemPlugins));
		return sv;
	}
	
	
	/**
	 * 初始化自定义插件
	 * @param customPlugins 自定义插件列表
	 * @return
	 */
	private View initCustomPlugin(List<QQPlugin> customPlugins) {
		if(customPlugins!=null&&customPlugins.size()>0){
			LinearLayout ll_custom_plugin=new LinearLayout(this);
			ll_custom_plugin.setId(23);//第二子集的，第三小子集
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
			
			//循环添加插件
			boolean isFirst=true;
			for(QQPlugin qqPlugin:customPlugins){
				if(isFirst){
					//添加下划线
					TextView tv_underline=new TextView(this);
					tv_underline.setWidth(LayoutParams.FILL_PARENT);
					tv_underline.setHeight(DensityUtil.dip2px(this, 1f));
					tv_underline.setBackgroundColor(0xFFEBEBEB);
					ll_custom_plugin.addView(tv_underline);
					isFirst=false;
				}
				
				
				//具体的插件,水平布局
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
				
				//添加具体插件
				//插件图标
				ImageView ll_custom_plugin_sub_iv=new ImageView(this);
				ll_custom_plugin_sub_iv.setBackgroundResource(qqPlugin.getImageId());
				LinearLayout.LayoutParams ll_custom_plugin_sub_ivParams=new LinearLayout.LayoutParams(
						DensityUtil.dip2px(this, 30f), 
						DensityUtil.dip2px(this, 30f));
				ll_custom_plugin_sub_ivParams.setMargins(DensityUtil.dip2px(this, 5f), 0, 0, 0);
				ll_custom_plugin_sub_iv.setLayoutParams(ll_custom_plugin_sub_ivParams);
				ll_custom_plugin_sub.addView(ll_custom_plugin_sub_iv);
				//插件名称
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
				//插件向右箭头
				ImageView ll_custom_plugin_sub_lastiv=new ImageView(this);
				ll_custom_plugin_sub_lastiv.setBackgroundResource(qqPlugin.getRedirectImageId());
				LinearLayout.LayoutParams ll_custom_plugin_sub_lastivParams=new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, 
						LayoutParams.WRAP_CONTENT);
				ll_custom_plugin_sub_lastivParams.setMargins(DensityUtil.dip2px(this, 5f), 0, 0, 0);
				ll_custom_plugin_sub_lastivParams.gravity=Gravity.CENTER_VERTICAL;
				ll_custom_plugin_sub.addView(ll_custom_plugin_sub_lastiv);
				
				//每个插件底部有一条横线
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
	 * 初始化系统插件
	 * @param systemPlugins 系统插件列表
	 * @return
	 */
	private View initSystemPlugin(List<QQPlugin> systemPlugins) {
		if(systemPlugins!=null&&systemPlugins.size()>0){
			LinearLayout ll_system_plugin=new LinearLayout(this);
			ll_system_plugin.setId(24);//第二子集的，第三小子集
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
			
			//循环添加插件
			boolean isFirst=true;
			for(QQPlugin qqPlugin:systemPlugins){
				if(isFirst){
					//添加下划线
					TextView tv_underline=new TextView(this);
					tv_underline.setWidth(LayoutParams.FILL_PARENT);
					tv_underline.setHeight(DensityUtil.dip2px(this, 1f));
					tv_underline.setBackgroundColor(0xFFEBEBEB);
					ll_system_plugin.addView(tv_underline);
					isFirst=false;
				}
				
				
				//具体的插件,水平布局
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
				
				//添加具体插件
				//插件图标
				ImageView ll_system_plugin_sub_iv=new ImageView(this);
				ll_system_plugin_sub_iv.setBackgroundResource(qqPlugin.getImageId());
				LinearLayout.LayoutParams ll_system_plugin_sub_ivParams=new LinearLayout.LayoutParams(
						DensityUtil.dip2px(this, 30f), 
						DensityUtil.dip2px(this, 30f));
				ll_system_plugin_sub_ivParams.setMargins(DensityUtil.dip2px(this, 5f), 0, 0, 0);
				ll_system_plugin_sub_iv.setLayoutParams(ll_system_plugin_sub_ivParams);
				ll_system_plugin_sub.addView(ll_system_plugin_sub_iv);
				//插件名称
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
				//插件向右箭头
				ImageView ll_system_plugin_sub_lastiv=new ImageView(this);
				ll_system_plugin_sub_lastiv.setBackgroundResource(qqPlugin.getRedirectImageId());
				LinearLayout.LayoutParams ll_system_plugin_sub_lastivParams=new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, 
						LayoutParams.WRAP_CONTENT);
				ll_system_plugin_sub_lastivParams.setMargins(DensityUtil.dip2px(this, 5f), 0, 0, 0);
				ll_system_plugin_sub_lastivParams.gravity=Gravity.CENTER_VERTICAL;
				ll_system_plugin_sub.addView(ll_system_plugin_sub_lastiv);
				
				//每个插件底部有一条横线
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
	 * 初始化头部
	 */
	private View initQQLayoutHead() {
		// 头部个一个固定的相对布局
		RelativeLayout topRl = new RelativeLayout(this);
		// 设置ID，供下面布局引用
		topRl.setId(1);
		topRl.setBackgroundColor(0xFF18B4ED);
		topRl.setPadding(DensityUtil.dip2px(this, 7f),
				DensityUtil.dip2px(this, 7f), DensityUtil.dip2px(this, 7f),
				DensityUtil.dip2px(this, 7f));
		RelativeLayout.LayoutParams topRlParams = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		// 相对于父级容器顶部对齐
		topRlParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		topRl.setLayoutParams(topRlParams);
		

		// topRl里面再放一个ImageView、两个TextView
		// 用户头像，左对齐，垂直居中
		ImageView iv_top_usericon = new ImageView(this);
		iv_top_usericon.setId(11);
		iv_top_usericon.setImageResource(R.drawable.lnz);
		RelativeLayout.LayoutParams iv_top_usericonParams = new RelativeLayout.LayoutParams(
				DensityUtil.dip2px(this, 35f), DensityUtil.dip2px(this, 35f));
		iv_top_usericonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		iv_top_usericonParams.addRule(RelativeLayout.CENTER_VERTICAL);
		iv_top_usericon.setLayoutParams(iv_top_usericonParams);
		topRl.addView(iv_top_usericon);

		// 动态，中部对齐
		TextView tv_top_dynamic = new TextView(this);
		tv_top_dynamic.setId(12);
		tv_top_dynamic.setText("动态");
		tv_top_dynamic.setTextColor(0xFFFFFFFF);
		tv_top_dynamic.setTextSize(20);
		RelativeLayout.LayoutParams tv_top_dynamicParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		tv_top_dynamicParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		tv_top_dynamic.setLayoutParams(tv_top_dynamicParams);
		topRl.addView(tv_top_dynamic);

		// 更多,右对齐，垂直居中
		TextView tv_top_more = new TextView(this);
		tv_top_more.setId(13);
		tv_top_more.setText("更多");
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
	 * 初始化底部
	 */
	private View initQQLayoutBottom() {
		// 底部一个固定的相对布局
		RelativeLayout bottomRl = new RelativeLayout(this);
		// 设置ID，供下面布局引用
		bottomRl.setId(3);
		bottomRl.setBackgroundColor(0xFFD4D4D4);
		bottomRl.setPadding(DensityUtil.dip2px(this, 5f),
				DensityUtil.dip2px(this, 5f), DensityUtil.dip2px(this, 5f),
				DensityUtil.dip2px(this, 5f));
		RelativeLayout.LayoutParams bottomRlParams = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		// 相对于父级容器底部对齐
		bottomRlParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		bottomRl.setLayoutParams(bottomRlParams);
		

		// bottomRl里面再放三个ImageButton
		// 消息，左对齐，垂直居中
		ImageButton ib_bottom_message = new ImageButton(this);
		ib_bottom_message.setId(11);
		//设置选择器
		ib_bottom_message.setBackgroundResource(R.drawable.selector_message);
		//设置可以获取焦点
		ib_bottom_message.setFocusable(true);
		ib_bottom_message.setFocusableInTouchMode(true);
		RelativeLayout.LayoutParams ib_bottom_messageParams = new RelativeLayout.LayoutParams(
				DensityUtil.dip2px(this, 60f), DensityUtil.dip2px(this, 60f));
		ib_bottom_messageParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		ib_bottom_messageParams.addRule(RelativeLayout.CENTER_VERTICAL);
		ib_bottom_messageParams.setMargins(DensityUtil.dip2px(this, 15f), 0, 0, 0);
		
		ib_bottom_message.setLayoutParams(ib_bottom_messageParams);
		bottomRl.addView(ib_bottom_message);

		// 联系人，中部对齐
		ImageButton ib_bottom_contract = new ImageButton(this);
		ib_bottom_contract.setId(11);
		//设置选择器
		ib_bottom_contract.setBackgroundResource(R.drawable.selector_contract);
		//设置可以获取焦点
		ib_bottom_contract.setFocusable(true);
		ib_bottom_contract.setFocusableInTouchMode(true);
		RelativeLayout.LayoutParams ib_bottom_contractParams = new RelativeLayout.LayoutParams(
				DensityUtil.dip2px(this, 60f), DensityUtil.dip2px(this, 60f));
		ib_bottom_contractParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		ib_bottom_contract.setLayoutParams(ib_bottom_contractParams);
		bottomRl.addView(ib_bottom_contract);

		// 动态,右对齐，垂直居中
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