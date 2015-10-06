package com.tz.orm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import com.tz.orm.adapter.BeanAdapter;
import com.tz.orm.util.MySqliteOpenHelper;
import com.tz.orm.util.Orm;
import com.tz.orm.util.TempletConfig;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemLongClickListener {

	private EditText et_dbversion;
	private EditText et_bean;
	private ListView lv_beans;
	
	private List data=null;
	private BeanAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_dbversion=(EditText) findViewById(R.id.et_dbversion);
		et_bean=(EditText)findViewById(R.id.et_bean);
		lv_beans=(ListView)findViewById(R.id.lv_beans);
	}

	/**
	 * 点击插入事件
	 * @param v
	 */
	public void insert(View v) {
		try {
			String beanClsName=et_bean.getText().toString();
			String dbversion=et_dbversion.getText().toString();
			//从配置中取对应bean的orm
			Orm orm=TempletConfig.orms.get(beanClsName+".orm.xml");
			if(orm!=null){
				String beanDao=orm.getBeanDao();
				//获取对应dao类
				Class daoCls=Class.forName(beanDao);
				//调用dao构造方法
				Constructor daoConstructor=daoCls.getConstructor(new Class[]{MySqliteOpenHelper.class});
				Object dao=daoConstructor.newInstance(new MySqliteOpenHelper(this, "myorm.db", null, Integer.parseInt(dbversion)));
				//调用对应的insert方法
				Method insertMethod=daoCls.getMethod("insert", new Class[]{Object.class});
				
				//构造需要插入的对象
				Class beanCls=Class.forName(orm.getBeanCls());
				Constructor beanConstructor=beanCls.getConstructor(null);
				Object bean=beanConstructor.newInstance(null);
				Object result=insertMethod.invoke(dao, bean);
				Toast.makeText(this, "插入成功:"+result, 0).show();
			} else {
				Toast.makeText(this, "没有对应的实体类", 0).show();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 点击查询事件
	 * @param v
	 */
	public void query(View v) {
		try {
			String beanClsName=et_bean.getText().toString();
			String dbversion=et_dbversion.getText().toString();
			//从配置中取对应bean的orm
			Orm orm=TempletConfig.orms.get(beanClsName+".orm.xml");
			if(orm!=null){
				String beanDao=orm.getBeanDao();
				//获取对应dao类
				Class daoCls=Class.forName(beanDao);
				//调用dao构造方法
				Constructor daoConstructor=daoCls.getConstructor(new Class[]{MySqliteOpenHelper.class});
				Object dao=daoConstructor.newInstance(new MySqliteOpenHelper(this, "myorm.db", null, Integer.parseInt(dbversion)));
				//调用对应的insert方法
				Method insertMethod=daoCls.getMethod("queryAll", null);
				
				//构造需要插入的对象
				data=(List)insertMethod.invoke(dao, null);
				Toast.makeText(this, "查询到:"+data.size()+"条记录", 0).show();
				
				//将查询的记录显示在listView里
				adapter=new BeanAdapter(data, this);
				lv_beans.setAdapter(adapter);
				//给listView里的item添加点击事件
				lv_beans.setOnItemLongClickListener(this);
				
			} else {
				Toast.makeText(this, "没有对应的实体类", 0).show();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		if(data!=null){
			Object t=data.get(position);
			try {
				String beanClsName=et_bean.getText().toString();
				String dbversion=et_dbversion.getText().toString();
				//从配置中取对应bean的orm
				Orm orm=TempletConfig.orms.get(beanClsName+".orm.xml");
				if(orm!=null){
					String beanDao=orm.getBeanDao();
					//获取对应dao类
					Class daoCls=Class.forName(beanDao);
					//调用dao构造方法
					Constructor daoConstructor=daoCls.getConstructor(new Class[]{MySqliteOpenHelper.class});
					Object dao=daoConstructor.newInstance(new MySqliteOpenHelper(this, "myorm.db", null, Integer.parseInt(dbversion)));
					//调用对应的insert方法
					Method deleteMethod=daoCls.getMethod("delete", new Class[]{Object.class});
					
					//构造需要插入的对象
					int result=(Integer)deleteMethod.invoke(dao, t);
					if(result>0){
						//删除成功，刷新界面
						data.remove(t);
						adapter.notifyDataSetChanged();
					} else {
						Toast.makeText(this, "删除失败", 0).show();
					}
				} else {
					Toast.makeText(this, "没有对应的实体类", 0).show();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
