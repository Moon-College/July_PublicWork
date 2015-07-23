package com.example.qq;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class MainActivity extends Activity implements OnQueryTextListener {

	private SearchView search_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initListener();
		initSetting();
	}

	/**
	 * 
	 * 此方法描述的是：初始化控件
	 * @最后修改人： studio initView void
	 * @author: studio 
	 */
	private void initView() {
		search_view = (SearchView) this.findViewById(R.id.search_view);

	}

	/**
	 * 此方法描述的是：初始化监听
	 * @最后修改人： studio initListener void
	 * @author: studio 
	 */
	private void initListener() {

	}

	/**
	 *此方法描述的是  初始化控件的设置 
	 * @author: Sky @最后修改人： Sky
	 * @最后修改日期:2015年7月21日 上午12:36:52
	 * @version: 2.0
	 * initSetting void
	 */
	public void initSetting() {
		// 设置该SearchView默认是否自动缩小为图标
		search_view.setIconifiedByDefault(false);
		// 为该SearchView组件设置事件监听器
		search_view.setOnQueryTextListener(this);
		// 设置该SearchView显示搜索按钮
		search_view.setSubmitButtonEnabled(true);
		// 设置该SearchView内默认显示的提示文本
		search_view.setQueryHint("查找");
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		return false;
	}

}
