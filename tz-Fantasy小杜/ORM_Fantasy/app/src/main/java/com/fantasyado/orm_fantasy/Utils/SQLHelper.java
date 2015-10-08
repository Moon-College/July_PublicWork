package com.fantasyado.orm_fantasy.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/10/3 0003.
 */
public class SQLHelper extends SQLiteOpenHelper {

    private String dbName;
    private int dbVersion;
    private Context mContext;

    public SQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table User(_id integer primary key autoincrement," +
                "user_name varchar(20)," +
                "sex varchar(10)," +
                "age integer);";

         doBusiness(db,sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = null;
          if (newVersion==2&&newVersion>oldVersion){
             sql = "create table Singer(_id integer primary key autoincrement," +
                      "singer varchar(20)," +
                      "best_songs varchar(20)," +
                      "age integer);";

              Toast.makeText(mContext,"table Singer created !",Toast.LENGTH_LONG).show();
              doBusiness(db,sql);
          }



    }

    private void doBusiness(SQLiteDatabase db,String sql){
        try {
            db.beginTransaction();
            db.execSQL(sql);
            db.setTransactionSuccessful();
            Toast.makeText(mContext,"db created successfully!",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }
}
