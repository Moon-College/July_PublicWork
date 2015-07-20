package com.tz.layout;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QqZoneActivity extends FragmentActivity {

	private static final int ID_TABS = 10;
	private static final int ID_IMAGE = 11;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_qq_zone);
		
		setActionBar();
		
		initLayoutWithCode();
	}
	
	

	private void setActionBar() {
		TextView tvTitle = new TextView(this);
		tvTitle.setTextColor(Color.WHITE);
		tvTitle.setTextSize(14);
		tvTitle.setText("��̬");
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER;
		tvTitle.setLayoutParams(params);
		
		ActionBar actionBar = getActionBar();
		
		actionBar.setCustomView(tvTitle);
		
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setBackgroundDrawable(new ActionBarDrawable(Color.parseColor("#25B6ED")));
	}

	
	class ActionBarDrawable extends ColorDrawable {
		public ActionBarDrawable(int color) {
			setColor(color);
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.qq_zone, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * ����ʵ�� ����
	 */
	private void initLayoutWithCode() {
		// �������㸸����
		RelativeLayout.LayoutParams parentRelativeLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
																								 RelativeLayout.LayoutParams.MATCH_PARENT);
		RelativeLayout parentRelativeLayout = createRelativeLayout(parentRelativeLayoutParams);
		// �����ײ�Tabs����
		LinearLayout tabsLinearLayout = createButtomTabsLayout();
		// Ϊ�ײ�Tabs��������һ��ID��������Ҫ�õ���
		tabsLinearLayout.setId(ID_TABS);
		// ���ײ�Tabs������ӵ���������
		parentRelativeLayout.addView(tabsLinearLayout);
		
		
		// ����һ��������Բ��ֲ���
		RelativeLayout.LayoutParams linearParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 
																			   	   RelativeLayout.LayoutParams.MATCH_PARENT);
		// ����������Բ��ְڷţ���Եײ�Tabs����	
		linearParams.addRule(RelativeLayout.ABOVE, ID_TABS);
		// ����һ�����Բ���
		LinearLayout linearLayout = createLinearLayout(linearParams, LinearLayout.VERTICAL);
		linearLayout.setBackgroundColor(Color.parseColor("#F8F8F8"));
		// ������������
		LinearLayout searchLinearLayout = createSearchLayout();
		// ������������ӵ����Բ�����
		linearLayout.addView(searchLinearLayout);
		
		// ����һ������ѡ�����
		LinearLayout topTabsLinearLayout = createTopTabsLayout();
		linearLayout.addView(topTabsLinearLayout);
		
		/**
		 * ��Ϊ����ͼ��һ��������ֻ�������ơ���
		 */
		LinearLayout fristLayout = createList("��Ϸ","����","�Ķ�");
		linearLayout.addView(fristLayout);
		
		LinearLayout secondLayout = createList("������Ⱥ","�Ժ�����","ͬ�Ƿ���");
		linearLayout.addView(secondLayout);
		
		parentRelativeLayout.addView(linearLayout);
		// ����������ӵ�����
		setContentView(parentRelativeLayout);
	}
	
	/**
	 * ����RelativeLayout����
	 * @return RelativeLayout
	 */
	private RelativeLayout createRelativeLayout(RelativeLayout.LayoutParams params) {
		RelativeLayout relativeLayout = new RelativeLayout(this);
		relativeLayout.setLayoutParams(params);
		return relativeLayout;
	}
	
	/**
	 * ����һ�����Բ���
	 * @param width ���
	 * @param height �߶�
	 * @return LinearLayout
	 */
	private LinearLayout createLinearLayout(ViewGroup.LayoutParams params, int orientation) {
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(orientation);
		linearLayout.setLayoutParams(params);
		return linearLayout;
	}
	
	
	
	/**
	 * �����ײ�Tabs ����
	 * @return 
	 */
	private LinearLayout createButtomTabsLayout() {
		// ����Tabs���ֲ���
		RelativeLayout.LayoutParams tabsParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
																			LinearLayout.LayoutParams.WRAP_CONTENT);
		// ���� ���ײ���ʾ
		tabsParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		
		// ����Tabs ���ִ���
		LinearLayout tabsLayout = createLinearLayout(tabsParams, LinearLayout.HORIZONTAL);
		tabsLayout.setBackgroundResource(R.drawable.linear_buttom_bg_shape);
		
		// ����ѡ��ֲ���
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
																		 LinearLayout.LayoutParams.WRAP_CONTENT,1);
										
									
		// ������Ϣ Tab
		ImageView messageImageView = createImageView(params,R.drawable.message_selector);
		// ������ϵ�� Tab
		ImageView constactImageView = createImageView(params,R.drawable.constact_selector);
		// ������̬  Tab
		ImageView dynamictImageView = createImageView(params,R.drawable.dynamic_selector);
		
		
		tabsLayout.addView(messageImageView);
		tabsLayout.addView(constactImageView);
		tabsLayout.addView(dynamictImageView);
		
		return tabsLayout;
	}
	
	/**
	 * ������������
	 * @return
	 */
	private LinearLayout createSearchLayout() {
		// ����Tabs���ֲ���
		RelativeLayout.LayoutParams searchParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
																				   LinearLayout.LayoutParams.WRAP_CONTENT);
		
		LinearLayout searchLinearLayout = createLinearLayout(searchParams, LinearLayout.HORIZONTAL);
		// ���ñ���ɫ
		searchLinearLayout.setBackgroundColor(Color.parseColor("#EBECED"));
		
		EditText editText = new EditText(this);
		editText.setBackgroundResource(R.drawable.edit_text_shape);
		
		LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
																			 LinearLayout.LayoutParams.WRAP_CONTENT);
		editTextParams.height = 45;
		editTextParams.setMargins(10, 10, 10, 10);
		editText.setLayoutParams(editTextParams);
		
		searchLinearLayout.addView(editText);
		return searchLinearLayout;
	}
	
	/**
	 * ���� ����ѡ�����
	 * @return
	 */
	private LinearLayout createTopTabsLayout() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
																		 LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout linearLayout = createLinearLayout(params, LinearLayout.HORIZONTAL);
		
		linearLayout.setPadding(0, 3, 0, 3);
		linearLayout.setBackgroundColor(Color.WHITE);
		LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1);
		TextView dynamictTextView = createTextView(textViewParams, "���Ѷ�̬", R.drawable.igs);
		TextView nearTextView = createTextView(textViewParams, "����", R.drawable.eqc);
		TextView hobbiesTextView = createTextView(textViewParams, "��Ȥ����", R.drawable.iei);
		
		
		linearLayout.addView(dynamictTextView);
		linearLayout.addView(nearTextView);
		linearLayout.addView(hobbiesTextView);
		return linearLayout;
		
	}
	
	
	/**
	 * �����б� ��Item����
	 * @param itemNames item��������
	 * @return ����һ��items�б� View
	 */
	private LinearLayout createList(String... itemNames) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
																		 LinearLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin = 10;
		LinearLayout linearLayout = createLinearLayout(params, LinearLayout.VERTICAL);
		linearLayout.setBackgroundColor(Color.parseColor("#DEDFE0"));
		
		for(int i = 0; i < itemNames.length ; i++) {
			// ����һ��Item
			RelativeLayout itemLayout = createItem(itemNames[i]);
			LinearLayout.LayoutParams itemsParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			itemsParams.topMargin = 1;

			linearLayout.addView(itemLayout, itemsParams);
		}
		
		return linearLayout;
	}
	
	/**
	 * ����һ��Item
	 * @return
	 */
	private RelativeLayout createItem(String text) {
		RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
																						   RelativeLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout relativeLayout = createRelativeLayout(relativeLayoutParams);
		relativeLayout.setBackgroundColor(Color.WHITE);
	
		// ���ͼ�� 
		RelativeLayout.LayoutParams logoParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
																				 RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		logoParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		logoParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		logoParams.setMargins(5, 0, 0, 0);
		ImageView logoImageView = createImageView(logoParams, R.drawable.eqc);
		logoImageView.setId(ID_IMAGE);
		relativeLayout.addView(logoImageView);
		
		// ����
		RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
																					 RelativeLayout.LayoutParams.WRAP_CONTENT);
		/**
		 * Right_of ��ʾ ����ĳ���ؼ����ұߣ�
		 * ALIGN_RIGHT ��ʾ ��ĳ���ؼ����ұ߶��� ��
		 */
		
		textViewParams.addRule(RelativeLayout.RIGHT_OF, logoImageView.getId());
		textViewParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		textViewParams.setMargins(5, 0, 0, 0);
		TextView textView = createTextView(textViewParams, text, -1);
		relativeLayout.addView(textView);
		
		// �ұ�ͼ�� 
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
																			 RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		params.setMargins(0, 0, 5, 0);
		
		ImageView imageView = createImageView(params, R.drawable.ie_arrow1_disable);
		relativeLayout.addView(imageView);
		
		return relativeLayout;
	}
	
	
	/**
	 * ����ImageView
	 * @param params
	 * @param resId
	 * @return
	 */
	private ImageView createImageView(ViewGroup.LayoutParams params,int resId) {
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(resId);
		imageView.setLayoutParams(params);
		return imageView;
	}
	
	/**
	 * ����һ��TextView
	 * @param params ���ֲ���
	 * @param text ��ʾ�ı�
	 * @param resId ͼƬ��ԴId
	 * @return TextView
	 */
	private TextView createTextView(ViewGroup.LayoutParams params,String text, int resId) {
		TextView textView = new TextView(this);
		textView.setTextSize(12);
		textView.setText(text);
		textView.setGravity(Gravity.CENTER);
		if(resId > 0) {
			Drawable topDrawable = getResources().getDrawable(resId);
			textView.setCompoundDrawablesWithIntrinsicBounds(null, topDrawable, null, null);
		}
		
		textView.setLayoutParams(params);
		return textView;
	}
}
