package com.fantasyado.orm_fantasy;

import android.app.Application;
import android.util.Log;

import com.fantasyado.orm_fantasy.Utils.Orm;
import com.fantasyado.orm_fantasy.Utils.ormTemplate;

import java.io.InputStream;

/**
 * Created by Administrator on 2015/10/3 0003.
 */
public class MyApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        readOrmConfig();


    }

    private void readOrmConfig() {

        try{

            final String[] files  = getAssets().list("");

            new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < files.length; i++) {
                        try {
                            if (files[i].endsWith("_orm.xml")) {
                                InputStream in = getAssets().open(files[i]);
                                Orm orm = ormTemplate.parseOrm(in);
                                Log.d("fantasy_log", orm.toString());

                                ormTemplate.OrmConfig.put(files[i], orm);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                    Log.d("fantasy_log", ormTemplate.OrmConfig.size() + ",size");


                }
            }.start();
        }catch(Exception e){
            e.printStackTrace();
        }



    }
}
