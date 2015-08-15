package com.example.administrator.myapplication;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.myapplication.adapter.AppGridViewAdapter;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private GridView mGridView;
    private PackageManager packageManager;
    private List<ResolveInfo> infos;
    private List<PackageInfo> installedPackages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (GridView) findViewById(R.id.gv);

        initData();
        setOnclick();
    }

    /**
     * 添加点击事件
     */
    private void setOnclick() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PackageInfo info = installedPackages.get(position);
                Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(info.packageName);
                if (launchIntentForPackage==null){
                    Toast.makeText(MainActivity.this, "此乃系统应用", Toast.LENGTH_SHORT).show();
                    return ;
                }
                startActivity(launchIntentForPackage);
            }
        });
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PackageInfo info = installedPackages.get(position);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:" + info.packageName));
                startActivity(intent);
                return true;
            }
        });
    }

    private void initData() {
        packageManager = getPackageManager();
        Executors.newFixedThreadPool(1).execute(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_DEFAULT);
//                infos = packageManager.queryIntentActivities(intent,PackageManager.GET_INTENT_FILTERS);
                installedPackages = packageManager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
                final AppGridViewAdapter adapter = new AppGridViewAdapter(MainActivity.this, installedPackages);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mGridView.setAdapter(adapter);
                    }
                });
            }
        });
    }


}
