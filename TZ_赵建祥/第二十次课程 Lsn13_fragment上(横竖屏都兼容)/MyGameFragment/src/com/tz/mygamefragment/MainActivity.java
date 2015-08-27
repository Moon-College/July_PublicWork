package com.tz.mygamefragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import com.tz.mygamefragment.fragment.LeftFragment;
import com.tz.mygamefragment.fragment.RightFragment;

/**
 * 横竖屏切换，动态加载fragment
 * @author 赵建祥
 *
 */
public class MainActivity extends Activity {

	private LeftFragment leftFragment;
	private FragmentManager fm;
	private RightFragment rightFragment;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("jzhao","onCreate..");
        loadFragments();
    }

    /**
     * 加载左右两边碎片
     */
    private void loadFragments() {
		fm = getFragmentManager();
		//开启事务---在事务里面可以批量处理fragment
		FragmentTransaction ft = fm.beginTransaction();
		leftFragment = new LeftFragment();
		Display display = getWindowManager().getDefaultDisplay();
		if (display.getWidth() > display.getHeight()) {
			//告诉左边fragment，当前是手机被水平了
			Bundle bundle=new Bundle();
			bundle.putBoolean("isHorizontal", true);
			
			//设置下布局
			leftFragment.setArguments(bundle);
			rightFragment = new RightFragment();
			rightFragment.setArguments(bundle);
			Toast.makeText(this, "横屏", Toast.LENGTH_SHORT).show();
			if(fm.findFragmentByTag("left")==null){
				ft.add(R.id.fr_content, leftFragment,"left");//添加fragment
			}else{
				ft.replace(R.id.fr_content, leftFragment,"left");///添加fragment
			}
			
			if(fm.findFragmentByTag("right")==null){
				ft.add(R.id.fr_content, rightFragment,"right");//添加fragment
			}else{
				ft.replace(R.id.fr_content, rightFragment,"right");//添加fragment
			}
			
		}else{
			Toast.makeText(this, "竖屏", Toast.LENGTH_SHORT).show();
			if(fm.findFragmentByTag("left")==null){
				ft.add(R.id.fr_content, leftFragment,"left");//添加fragment
			}else{
				ft.replace(R.id.fr_content, leftFragment,"left");///添加fragment
			}
		}
		ft.commit();
	}
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	//横竖屏切换时，将left\right从容器中移除
    	FragmentTransaction ft = fm.beginTransaction();
    	if(fm.findFragmentByTag("left")!=null){
			ft.replace(R.id.fr_content, leftFragment,"left");
		}
		
    	if(fm.findFragmentByTag("right")!=null){
			ft.replace(R.id.fr_content, rightFragment,"right");
		}
		
		ft.commit();
    }

}
