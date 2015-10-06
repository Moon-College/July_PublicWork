package com.tz.orm;

import android.app.Application;

import com.tz.orm.util.Orm;
import com.tz.orm.util.TempletConfig;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		//Ӧ������ʱ����ʼ��orm xml�����ļ�
		initOrm();
	}

	private void initOrm() {
		try {
			//�����orms
			TempletConfig.orms.clear();
			//��ȡassertĿ¼�������ļ�
			String[] files=getAssets().list("");
			for (String fileName : files) {
				//�ҵ���׺��.orm.xml���ļ�
				if(fileName.endsWith(".orm.xml")){
					//��������orm����
					Orm orm=TempletConfig.parseXmlToOrm(getAssets().open(fileName));
					TempletConfig.orms.put(fileName, orm);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
