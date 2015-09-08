package com.tz.myviwepager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * 仿微信滑动
 * @author 赵建祥
 *
 */
public class MainActivity extends FragmentActivity implements OnPageChangeListener {

	private ViewPager vp;
	private FragmentPagerAdapter adapter;
	
	//中间显示的图片
	private int[] datas=new int[]{
			R.drawable.china,
			R.drawable.korea,
			R.drawable.japan,
			R.drawable.america,
			R.drawable.india,
			R.drawable.england,
			R.drawable.france
	};
	
	//头部单选按钮对应的ID
	private int[] rb_ids=new int[]{
			R.id.rb_china,
			R.id.rb_korea,
			R.id.rb_japan,
			R.id.rb_america,
			R.id.rb_india,
			R.id.rb_england,
			R.id.rb_france
	};
	
	private RadioGroup rg_top;
	private HorizontalScrollView hsv;
	private TextView tv_scrollbar;
	private float fromXDelta=0;//红色水平线开始X坐标为0

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//头部按钮组
		rg_top=(RadioGroup) findViewById(R.id.rg_top);
		//头部水平滚动视图
		hsv = (HorizontalScrollView) findViewById(R.id.hsv_top);
		//红色的水平线，表示当前滑到哪一个
		tv_scrollbar = (TextView) findViewById(R.id.tv_scrollbar);
		
		//内容viewpager
		vp=(ViewPager)findViewById(R.id.viewpager);
		
		adapter=new MyPagerAdapter(getSupportFragmentManager());
		vp.setAdapter(adapter);
		
		//添加页面改变事件监听
		vp.setOnPageChangeListener(this);
		
		//添加按钮组改变事件监听
		rg_top.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				for(int i=0;i<rb_ids.length;i++){
					if(rb_ids[i]==checkedId){
						//点击头部跳转到对应的pagr
						vp.setCurrentItem(i);
					}
				}
			}
		});
	}

	//自定义适配器
	private class MyPagerAdapter extends FragmentPagerAdapter{

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Log.i("jzhao", "getItem："+datas[position]);
			MyFragment fragment=new MyFragment();
			Bundle bundle=new Bundle();
			bundle.putInt("DATAID", datas[position]);
			fragment.setArguments(bundle);
			return fragment;
		}

		@Override
		public int getCount() {
			return datas.length;
		}
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	//滑动改变头部滚动，需重写该方法
	@Override
	public void onPageScrolled(int position, float positionOffSet, int arg2) {
		float total=(position+positionOffSet)*(rg_top.getChildAt(0).getWidth());
		float left=(vp.getWidth()-rg_top.getChildAt(0).getWidth())/2;
		float scrollX=total-left;
		hsv.scrollTo((int)scrollX, 0);
		//移动红色水平线
		moveLine(position,positionOffSet);
	}
	
	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 移动红色水平线
	 * @param position
	 * @param positionOffSet
	 */
	private void moveLine(int position, float positionOffSet) {
		int[] location=new int[2];
		rg_top.getChildAt(position).getLocationInWindow(location);
		//获取当前radiobutton在屏幕的水平坐标
		int x=location[0];
		Log.i("jzhao", "x:"+x+" positionOffSet:"+positionOffSet);
		TranslateAnimation translateAnimation=new TranslateAnimation(
				fromXDelta,
				//当前显示的radiobutton的绝对x坐标+positionOffset*rb.getWidth();
				x+positionOffSet*rg_top.getChildAt(0).getWidth(), 
				0,
				0);
		translateAnimation.setDuration(40);
		translateAnimation.setFillAfter(true);
		tv_scrollbar.startAnimation(translateAnimation);
		fromXDelta=x+positionOffSet*rg_top.getChildAt(0).getWidth();
	}
}
