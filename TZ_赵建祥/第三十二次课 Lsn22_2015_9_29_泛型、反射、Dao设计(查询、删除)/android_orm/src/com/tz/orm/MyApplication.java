package com.tz.orm;

import android.app.Application;

import com.tz.orm.util.Orm;
import com.tz.orm.util.TempletConfig;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		//应用启动时，初始化orm xml配置文件
		initOrm();
	}

	private void initOrm() {
		try {
			//先清除orms
			TempletConfig.orms.clear();
			//获取assert目录下所有文件
			String[] files=getAssets().list("");
			for (String fileName : files) {
				//找到后缀是.orm.xml的文件
				if(fileName.endsWith(".orm.xml")){
					//解析生成orm对象
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
