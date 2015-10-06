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
	 * ��������¼�
	 * @param v
	 */
	public void insert(View v) {
		try {
			String beanClsName=et_bean.getText().toString();
			String dbversion=et_dbversion.getText().toString();
			//��������ȡ��Ӧbean��orm
			Orm orm=TempletConfig.orms.get(beanClsName+".orm.xml");
			if(orm!=null){
				String beanDao=orm.getBeanDao();
				//��ȡ��Ӧdao��
				Class daoCls=Class.forName(beanDao);
				//����dao���췽��
				Constructor daoConstructor=daoCls.getConstructor(new Class[]{MySqliteOpenHelper.class});
				Object dao=daoConstructor.newInstance(new MySqliteOpenHelper(this, "myorm.db", null, Integer.parseInt(dbversion)));
				//���ö�Ӧ��insert����
				Method insertMethod=daoCls.getMethod("insert", new Class[]{Object.class});
				
				//������Ҫ����Ķ���
				Class beanCls=Class.forName(orm.getBeanCls());
				Constructor beanConstructor=beanCls.getConstructor(null);
				Object bean=beanConstructor.newInstance(null);
				Object result=insertMethod.invoke(dao, bean);
				Toast.makeText(this, "����ɹ�:"+result, 0).show();
			} else {
				Toast.makeText(this, "û�ж�Ӧ��ʵ����", 0).show();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �����ѯ�¼�
	 * @param v
	 */
	public void query(View v) {
		try {
			String beanClsName=et_bean.getText().toString();
			String dbversion=et_dbversion.getText().toString();
			//��������ȡ��Ӧbean��orm
			Orm orm=TempletConfig.orms.get(beanClsName+".orm.xml");
			if(orm!=null){
				String beanDao=orm.getBeanDao();
				//��ȡ��Ӧdao��
				Class daoCls=Class.forName(beanDao);
				//����dao���췽��
				Constructor daoConstructor=daoCls.getConstructor(new Class[]{MySqliteOpenHelper.class});
				Object dao=daoConstructor.newInstance(new MySqliteOpenHelper(this, "myorm.db", null, Integer.parseInt(dbversion)));
				//���ö�Ӧ��insert����
				Method insertMethod=daoCls.getMethod("queryAll", null);
				
				//������Ҫ����Ķ���
				data=(List)insertMethod.invoke(dao, null);
				Toast.makeText(this, "��ѯ��:"+data.size()+"����¼", 0).show();
				
				//����ѯ�ļ�¼��ʾ��listView��
				adapter=new BeanAdapter(data, this);
				lv_beans.setAdapter(adapter);
				//��listView���item��ӵ���¼�
				lv_beans.setOnItemLongClickListener(this);
				
			} else {
				Toast.makeText(this, "û�ж�Ӧ��ʵ����", 0).show();
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
				//��������ȡ��Ӧbean��orm
				Orm orm=TempletConfig.orms.get(beanClsName+".orm.xml");
				if(orm!=null){
					String beanDao=orm.getBeanDao();
					//��ȡ��Ӧdao��
					Class daoCls=Class.forName(beanDao);
					//����dao���췽��
					Constructor daoConstructor=daoCls.getConstructor(new Class[]{MySqliteOpenHelper.class});
					Object dao=daoConstructor.newInstance(new MySqliteOpenHelper(this, "myorm.db", null, Integer.parseInt(dbversion)));
					//���ö�Ӧ��insert����
					Method deleteMethod=daoCls.getMethod("delete", new Class[]{Object.class});
					
					//������Ҫ����Ķ���
					int result=(Integer)deleteMethod.invoke(dao, t);
					if(result>0){
						//ɾ���ɹ���ˢ�½���
						data.remove(t);
						adapter.notifyDataSetChanged();
					} else {
						Toast.makeText(this, "ɾ��ʧ��", 0).show();
					}
				} else {
					Toast.makeText(this, "û�ж�Ӧ��ʵ����", 0).show();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
