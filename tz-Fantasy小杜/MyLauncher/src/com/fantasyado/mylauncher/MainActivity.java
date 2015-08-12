package com.fantasyado.mylauncher;

 
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;

import com.fantasyado.mylauncher.adapter.LauncherAdapter;


public class MainActivity extends Activity 
 implements OnItemClickListener, OnItemLongClickListener      
{

    private GridView gv;
    private List<ResolveInfo> activities;
	private LauncherAdapter adapter;
 
	private String pkgName;
 

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        gv = (GridView)findViewById(R.id.gv_container);
 
        
        PackageManager pm = getPackageManager();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        activities = pm.queryIntentActivities(intent, 0);
         adapter = new LauncherAdapter(activities, MainActivity.this);
        gv.setAdapter(adapter);
         gv.setOnItemClickListener(this);
        gv.setOnItemLongClickListener(this);
        
    
    }
 
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		ResolveInfo info = activities.get(position);
	 String pkgName = info.activityInfo.applicationInfo.packageName;
	 String clsName = info.activityInfo.name;
	 Intent intent = new Intent();
	 intent.setClassName(pkgName, clsName);
	 startActivity(intent);
 
		
	}
 
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
 
	     ResolveInfo info = activities.get(position);
	  pkgName = info.activityInfo.packageName;
 
//  	  selectedItem = view ;
//	   selectedItem.setDrawingCacheEnabled(true);
//	   drawingCache = selectedItem.getDrawingCache();
		 DeleteApps(pkgName);
		 adapter.notifyDataSetChanged();
		 activities.remove(position);
		 gv.invalidate();
		  
	     
 		return true;
	}

	 
      
    private void DeleteApps(String pkgName ) {
    	   Uri uri = Uri.parse("package:"+pkgName);
    	   Intent intent = new Intent();
    	   intent.setAction(Intent.ACTION_DELETE);
    	   intent.setData(uri);
    	   startActivity(intent);
    	  
    }

	 
  
 
}
