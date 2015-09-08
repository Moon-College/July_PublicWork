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
 * ��΢�Ż���
 * @author �Խ���
 *
 */
public class MainActivity extends FragmentActivity implements OnPageChangeListener {

	private ViewPager vp;
	private FragmentPagerAdapter adapter;
	
	//�м���ʾ��ͼƬ
	private int[] datas=new int[]{
			R.drawable.china,
			R.drawable.korea,
			R.drawable.japan,
			R.drawable.america,
			R.drawable.india,
			R.drawable.england,
			R.drawable.france
	};
	
	//ͷ����ѡ��ť��Ӧ��ID
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
	private float fromXDelta=0;//��ɫˮƽ�߿�ʼX����Ϊ0

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//ͷ����ť��
		rg_top=(RadioGroup) findViewById(R.id.rg_top);
		//ͷ��ˮƽ������ͼ
		hsv = (HorizontalScrollView) findViewById(R.id.hsv_top);
		//��ɫ��ˮƽ�ߣ���ʾ��ǰ������һ��
		tv_scrollbar = (TextView) findViewById(R.id.tv_scrollbar);
		
		//����viewpager
		vp=(ViewPager)findViewById(R.id.viewpager);
		
		adapter=new MyPagerAdapter(getSupportFragmentManager());
		vp.setAdapter(adapter);
		
		//���ҳ��ı��¼�����
		vp.setOnPageChangeListener(this);
		
		//��Ӱ�ť��ı��¼�����
		rg_top.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				for(int i=0;i<rb_ids.length;i++){
					if(rb_ids[i]==checkedId){
						//���ͷ����ת����Ӧ��pagr
						vp.setCurrentItem(i);
					}
				}
			}
		});
	}

	//�Զ���������
	private class MyPagerAdapter extends FragmentPagerAdapter{

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Log.i("jzhao", "getItem��"+datas[position]);
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

	//�����ı�ͷ������������д�÷���
	@Override
	public void onPageScrolled(int position, float positionOffSet, int arg2) {
		float total=(position+positionOffSet)*(rg_top.getChildAt(0).getWidth());
		float left=(vp.getWidth()-rg_top.getChildAt(0).getWidth())/2;
		float scrollX=total-left;
		hsv.scrollTo((int)scrollX, 0);
		//�ƶ���ɫˮƽ��
		moveLine(position,positionOffSet);
	}
	
	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * �ƶ���ɫˮƽ��
	 * @param position
	 * @param positionOffSet
	 */
	private void moveLine(int position, float positionOffSet) {
		int[] location=new int[2];
		rg_top.getChildAt(position).getLocationInWindow(location);
		//��ȡ��ǰradiobutton����Ļ��ˮƽ����
		int x=location[0];
		Log.i("jzhao", "x:"+x+" positionOffSet:"+positionOffSet);
		TranslateAnimation translateAnimation=new TranslateAnimation(
				fromXDelta,
				//��ǰ��ʾ��radiobutton�ľ���x����+positionOffset*rb.getWidth();
				x+positionOffSet*rg_top.getChildAt(0).getWidth(), 
				0,
				0);
		translateAnimation.setDuration(40);
		translateAnimation.setFillAfter(true);
		tv_scrollbar.startAnimation(translateAnimation);
		fromXDelta=x+positionOffSet*rg_top.getChildAt(0).getWidth();
	}
}
