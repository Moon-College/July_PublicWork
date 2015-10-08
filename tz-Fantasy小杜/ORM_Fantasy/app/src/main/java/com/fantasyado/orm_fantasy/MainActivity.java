package com.fantasyado.orm_fantasy;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fantasyado.orm_fantasy.Utils.Orm;
import com.fantasyado.orm_fantasy.Utils.SQLHelper;
import com.fantasyado.orm_fantasy.Utils.lvAdapter;
import com.fantasyado.orm_fantasy.Utils.ormTemplate;
import com.fantasyado.orm_fantasy.dao.SingerDao;
import com.fantasyado.orm_fantasy.dao.UserDao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private SQLiteDatabase db;
    private SQLHelper mHelper;
    private EditText etUserName;
    private Button btninsert;
    private Button btnQuery;
    private RadioGroup rg;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private EditText etsex;
    private EditText etage;
    private EditText etTableName;
    private EditText etDbVersion;
    private EditText[] inputs;
    private ArrayList<String> mdatas = new ArrayList<String>();
    private ListView mLv;
    private lvAdapter mAdapter;

    private int mDBversion = 1;
    private boolean dbChanged = true;
    private PopupWindow popupWindow;
    private SharedPreferences mSp;
    private SharedPreferences.Editor mEditor;
    private   Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            switch (msg.what){
                case 0x001 :
                    mAdapter = new lvAdapter(MainActivity.this, mdatas);
                    View view = View.inflate(MainActivity.this, R.layout.popupwindow_layout, null);
                    mLv = (ListView) view.findViewById(R.id.listView);
                    mLv.setAdapter(mAdapter);
                    popupWindow = new PopupWindow();
                     popupWindow.setWidth(scWidth*9/10);
                    popupWindow.setHeight(scHeigt/3);
                    popupWindow.setContentView(view);
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setFocusable(true);
                    popupWindow.showAsDropDown(btninsert, 5, 5);
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            mAdapter = null ;
                            mLv = null ;

                        }
                    });
                    break ;

                case 0x002 :
                    long result = (long) msg.obj;
                    Toast.makeText(MainActivity.this, "insert successfully !" + result, Toast.LENGTH_LONG).show();
                    break;
            }

        }
    };
    private int scWidth;
    private int scHeigt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
      scWidth =    getResources().getDisplayMetrics().widthPixels;
        scHeigt = getResources().getDisplayMetrics().heightPixels;


    }

    private void initialize() {

          mHelper = new SQLHelper(this, "user.db", null, 1);
          db = mHelper.getWritableDatabase();
        tv1 = (TextView) findViewById(R.id.tv_1);
        tv2 = (TextView) findViewById(R.id.tv_2);
        tv3 = (TextView) findViewById(R.id.tv_3);
        etUserName = (EditText) findViewById(R.id.et_UserName);
        etsex = (EditText) findViewById(R.id.et_sex);
        etage = (EditText) findViewById(R.id.et_age);
        etTableName = (EditText) findViewById(R.id.et_tableName);
        etTableName.setText("User");
        etDbVersion = (EditText) findViewById(R.id.et_version);
        btninsert = (Button) findViewById(R.id.btn_insert);
        btnQuery = (Button) findViewById(R.id.btn_query);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(this);
        mSp = getSharedPreferences("mDataBase",0);
         mEditor = mSp.edit();
        mDBversion = mSp.getInt("dbVersion",1);

        inputs = new EditText[]{
                etUserName,etsex,etage,etTableName,etDbVersion
        };

    }


//insert on click
    public void insert(View v) {
        if (etUserName.getText().toString() != null && etage.getText().toString() != null && etsex.getText().toString() != null
                && !TextUtils.isEmpty(etUserName.getText().toString()) && !TextUtils.isEmpty(etsex.getText().toString()) && !TextUtils.isEmpty(etage.getText().toString())
                && !TextUtils.isEmpty(etTableName.getText().toString())&&etTableName.getText().toString()!=null) {

            String name = etUserName.getText().toString();
            int age = Integer.parseInt(etage.getText().toString());
            String sex = etsex.getText().toString();
            String target = etTableName.getText().toString();
            if (!etDbVersion.getText().toString().equals("")&&etDbVersion.getText().toString()!=null){
                int dbVersion = Integer.parseInt(etDbVersion.getText().toString());
                mDBversion = mSp.getInt("dbVersion",1);
                if (dbVersion>mDBversion){
                    mHelper.onUpgrade(db,mDBversion,dbVersion);
                    mDBversion = dbVersion;
                    mEditor.putInt("dbVersion",mDBversion);
                    mEditor.commit();
                }
            }
            Orm orm = ormTemplate.OrmConfig.get(target + "_orm.xml");
            insertTask task = new insertTask();
            task.execute(orm,name,sex,age);

        }
    }

    //query on click
    public void query(View v) throws  Exception{

             mdatas.clear();
             if (etTableName.getText().toString()!=null&&!TextUtils.isEmpty(etTableName.getText().toString())) {
                 String target = etTableName.getText().toString();
                 if (target.contains("User")){
                     UserDao dao = new UserDao(mHelper,UserDao.class);
                     mdatas = dao.query("User");
                 }else if (target.contains("Singer")){
                     SingerDao dao = new SingerDao(mHelper,SingerDao.class);
                     mdatas = dao.query("Singer");
                 }
             }
             mHandler.sendEmptyMessage(0x001);





    }

    public void clear(View v){
        for (EditText et:inputs ) {
            et.setText(null);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_user :
                tv1.setText("UserName");
                tv2.setText("age");
                tv3.setText("gender");
                etUserName.setHint("UserName");
                etage.setHint("age");
                etsex.setHint("sex");
                etTableName.setText("User");
                break;
            case R.id.rb_singer :
                tv1.setText("SingerName");
                tv2.setText("age");
                tv3.setText("best_songs ever");
                etUserName.setHint("SingerName");
                etage.setHint("age");
                etsex.setHint("best_songs ever");
                etTableName.setText("Singer");
                break;
        }
    }




    // insert

    class insertTask extends AsyncTask<Object, Void, Long> {



        @Override
        protected Long doInBackground(Object... params) {
            long result = 0;
           Orm orm = (Orm) params[0];
            String name = (String) params[1];
            String sex = (String) params[2];
            int age = (int) params[3];
            if (orm!=null){
                try {
                    String  tableName = orm.getTableName();
                    String beanName = orm.getBeanName();
                    Class beanCls = Class.forName(beanName);
                    Constructor constructor = beanCls.getDeclaredConstructor(new Class[]{String.class, String.class, int.class});
                    Object bean = constructor.newInstance(name, sex, age);
                    Log.d("fantasy_log",bean.toString());
                    Class daoCls = Class.forName(orm.getDaoName());
                    Constructor daoConstructor = daoCls.getDeclaredConstructor(new Class[]{SQLHelper.class, Class.class});



                    Object dao = daoConstructor.newInstance(mHelper, daoCls);
                    Method method = daoCls.getMethod("insert", new Class[]{Object.class, String.class,Orm.class});
                  result = (long) method.invoke(dao,new Object[]{bean,tableName,orm});
                    Toast.makeText(MainActivity.this,"insert successfully",Toast.LENGTH_LONG).show();
                    dbChanged = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(MainActivity.this,"no orm Config file found !",Toast.LENGTH_LONG).show();
            }



            return  result;
        }

        @Override
        protected void onPostExecute(Long result) {
            super.onPostExecute(result);
            Message message = mHandler.obtainMessage(0x002);
            message.obj = result;
            mHandler.sendMessage(message);
            dbChanged = true ;
        }
    }

}


