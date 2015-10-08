package com.fantasyado.orm_fantasy.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.fantasyado.orm_fantasy.Bean.Item;
import com.fantasyado.orm_fantasy.Bean.Key;
import com.fantasyado.orm_fantasy.Utils.Orm;
import com.fantasyado.orm_fantasy.Utils.SQLHelper;
import com.fantasyado.orm_fantasy.Utils.ormTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/10/3 0003.
 */
public class BaseDao<T> {

    private SQLHelper mHelper;
    private Class cls;
    private  SQLiteDatabase mDB;
    private ArrayList<Object> mDatas = new ArrayList<Object>();

    public BaseDao(SQLHelper mHelper, Class cls) {
        this.mHelper = mHelper;
        this.cls = cls;
        mDB = mHelper.getWritableDatabase();
    }

    public  long insert(Object bean,String table,Orm orm){

        ContentValues values  = new ContentValues();
         Class beanCls = bean.getClass();
       ArrayList<Item> items =  orm.getItem();
        for (Item item:items ) {
            String type = item.getType();
            try {
                Method put = values.getClass().getMethod("put", new Class[]{String.class, Class.forName(type)});
                Field field = beanCls.getDeclaredField(item.getProperty());
                field.setAccessible(true);
               Object value =  field.get(bean);
                put.invoke(values,item.getColumn(),value);
            }  catch (Exception e) {
                e.printStackTrace();
            }
        }



       long result =  mDB.insert(table,null,values);
        return result;
    }

    public    ArrayList<Object> query(String table) throws Exception{

        queryTask task = new queryTask();
        task.execute(table);
        return mDatas;
    }

    class  queryTask extends AsyncTask<String,Void,ArrayList<Object>>{
        ArrayList<Object> datas = new ArrayList<Object>();
        @Override
        protected ArrayList doInBackground(String... params)  {

            try{
                String table = params[0];
                Cursor cursor = mDB.query(table, null, null, null, null, null, null);
                Orm orm = ormTemplate.OrmConfig.get(table + "_orm.xml");
                Class beanCls = Class.forName(orm.getBeanName());

                ArrayList<Item> items = orm.getItem();
                while(cursor.moveToNext()){
                    Object bean = beanCls.newInstance();
                    Key key = orm.getKey();
                    Field kfield = beanCls.getDeclaredField(key.getProperty());
                    kfield.setAccessible(true);
                    kfield.set(bean,cursor.getInt(cursor.getColumnIndex(key.getColumn())));

                    for (Item item:items) {
                        String type =   item.getType();
                        String property = item.getProperty();
                        Field field = beanCls.getDeclaredField(property);
                        field.setAccessible(true);

                        if (type.contains("String")){
                            String value = cursor.getString(cursor.getColumnIndex(item.getColumn()));
                            field.set(bean,value);
                        }else if (type.contains("Integer")){
                            int value = cursor.getInt(cursor.getColumnIndex(item.getColumn()));
                            field.set(bean,value);
                        }
                    }
                    mDatas.add(bean.toString());
                }


            }catch (Exception e){
                e.printStackTrace();
            }

            return mDatas;
        }


    }

}
