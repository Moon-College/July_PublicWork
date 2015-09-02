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
 * �������л�����̬����fragment
 * @author �Խ���
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
     * ��������������Ƭ
     */
    private void loadFragments() {
		fm = getFragmentManager();
		//��������---���������������������fragment
		FragmentTransaction ft = fm.beginTransaction();
		leftFragment = new LeftFragment();
		Display display = getWindowManager().getDefaultDisplay();
		if (display.getWidth() > display.getHeight()) {
			//�������fragment����ǰ���ֻ���ˮƽ��
			Bundle bundle=new Bundle();
			bundle.putBoolean("isHorizontal", true);
			
			//�����²���
			leftFragment.setArguments(bundle);
			rightFragment = new RightFragment();
			rightFragment.setArguments(bundle);
			Toast.makeText(this, "����", Toast.LENGTH_SHORT).show();
			if(fm.findFragmentByTag("left")==null){
				ft.add(R.id.fr_content, leftFragment,"left");//���fragment
			}else{
				ft.replace(R.id.fr_content, leftFragment,"left");///���fragment
			}
			
			if(fm.findFragmentByTag("right")==null){
				ft.add(R.id.fr_content, rightFragment,"right");//���fragment
			}else{
				ft.replace(R.id.fr_content, rightFragment,"right");//���fragment
			}
			
		}else{
			Toast.makeText(this, "����", Toast.LENGTH_SHORT).show();
			if(fm.findFragmentByTag("left")==null){
				ft.add(R.id.fr_content, leftFragment,"left");//���fragment
			}else{
				ft.replace(R.id.fr_content, leftFragment,"left");///���fragment
			}
		}
		ft.commit();
	}
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	//�������л�ʱ����left\right���������Ƴ�
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
