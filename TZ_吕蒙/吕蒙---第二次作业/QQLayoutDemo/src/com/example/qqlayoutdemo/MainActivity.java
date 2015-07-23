package com.example.qqlayoutdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.my.pixtodp.DensityUtil;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	

	
	//View��Idֵ
	final int Root=0,Edit=1,LinearHor1=2,LinearVer1=3,LinearHor2=5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.relative_layout);
		
		//��ȡcolor�е���ɫֵ
		Resources resource=this.getBaseContext().getResources();//this.getResources();
		int gray=resource.getColor(R.color.gray);  
		int white=resource.getColor(R.color.white);
		
		/**
		 * ����root���ڵ�
		 */
		RelativeLayout root=new RelativeLayout(this);				  			
		root.setBackgroundColor(gray);
		
		LayoutParams rootParamas=new LayoutParams(
				LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT);	
		root.setId(Root);
		root.setLayoutParams(rootParamas);
		
		
		/**
		 * ����root���ڵ��ӿؼ�1--EditText
		 */		
		EditText edit=new EditText(this);
		RelativeLayout.LayoutParams eidtParams=new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
		RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		 //margin��Ҫ���ؼ���ͬ����
		eidtParams.setMargins(
				DensityUtil.dip2px(this, 10),
				DensityUtil.dip2px(this, 10),
				DensityUtil.dip2px(this, 5),
				0);
		edit.setLayoutParams(eidtParams);
		
		Drawable round_edittext=resource.getDrawable(R.drawable.round_edittext);
		edit.setBackgroundDrawable(round_edittext);
				
		edit.setHint("����");  
		//edit.setTextSize(TypedValue.COMPLEX_UNIT_DIP,10); //�������ִ�СΪ10dp
		edit.setTextSize(DensityUtil.dip2px(this, 10)); //�������ִ�СΪ10dp
		edit.setGravity(Gravity.CENTER);   //����GravityΪ����
		edit.setId(Edit); //����ID
		
		root.addView(edit);
		
		
		/**
		 * ����root���ڵ��ӿؼ�LinearHor1--ˮƽLineatLayout
		 */
		LinearLayout linear_horizotal_1=new LinearLayout(this);
		RelativeLayout.LayoutParams linear_horizotal_1_Params=new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		//����topmargin=5dp
		linear_horizotal_1_Params.setMargins(0, DensityUtil.dip2px(this, 5), 0, 0);	
		//����λ����edit�ؼ�����
		linear_horizotal_1_Params.addRule(RelativeLayout.BELOW,Edit);
		linear_horizotal_1.setLayoutParams(linear_horizotal_1_Params);
		
		linear_horizotal_1.setOrientation(LinearLayout.HORIZONTAL);
		linear_horizotal_1.setBackgroundColor(white);
		linear_horizotal_1.setId(LinearHor1);
		
		root.addView(linear_horizotal_1);
		
		
		//����LinearHor1�ӽڵ�:button1
		Button button1=new Button(this);
		LinearLayout.LayoutParams button1params =new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		
		
		button1.setLayoutParams(button1params);
		
		button1.setBackgroundColor(white);
		button1.setTextSize(DensityUtil.dip2px(this, 10));
		
		/**
		 * ����root���ڵ��ӿؼ�3--��ֱLinearLayout
		 */
		
		LinearLayout linear_vertical_1=new LinearLayout(this);
		RelativeLayout.LayoutParams linear_vertical_1_params=new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		//����topmargin=15dp
		linear_vertical_1_params.setMargins(0, DensityUtil.dip2px(this, 15), 0, 0);	
		//����λ����LinearHor1�ؼ�����
		linear_vertical_1_params.addRule(RelativeLayout.BELOW,LinearHor1);
		linear_vertical_1.setLayoutParams(linear_vertical_1_params);
		
		linear_vertical_1.setOrientation(LinearLayout.VERTICAL);
		linear_vertical_1.setBackgroundColor(white);
		linear_vertical_1.setId(LinearVer1);
		
		root.addView(linear_vertical_1);
		
		/**
		 * ����root���ڵ��ӿؼ�LinearHor2--ˮƽLinearLayout
		 */
		LinearLayout linear_horizotal_2=new LinearLayout(this);
		RelativeLayout.LayoutParams linear_horizotal_2_Params=new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		//��������ײ�
		linear_horizotal_2_Params.addRule(RelativeLayout.BELOW);

		linear_horizotal_2.setLayoutParams(linear_horizotal_2_Params);
		
		linear_horizotal_2.setOrientation(LinearLayout.HORIZONTAL);
		linear_horizotal_2.setBackgroundColor(white);
		linear_horizotal_2.setId(LinearHor2);
		
		root.addView(linear_horizotal_2);
		
		//����LinearHor2��������button
		
		

		
		setContentView(root);
		
		
			
	}
	

	
	
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
