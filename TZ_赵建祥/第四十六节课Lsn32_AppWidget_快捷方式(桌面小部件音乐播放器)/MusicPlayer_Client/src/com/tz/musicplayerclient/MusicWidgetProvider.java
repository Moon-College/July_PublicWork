package com.tz.musicplayerclient;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViews.RemoteView;

public class MusicWidgetProvider extends AppWidgetProvider {
	
	public static final String TAG=MusicWidgetProvider.class.getSimpleName();
	
	public static final int FLAG_TOGGLEPLAY=1;//播放或暂停按钮
	public static final int FLAG_NEXT=2;//下一首
	
	private static MusicWidgetProvider musicWidgetProvider;

	public  static MusicWidgetProvider getInstance(){
		if(musicWidgetProvider==null)
			musicWidgetProvider=new MusicWidgetProvider();
		return musicWidgetProvider;
	}

	@SuppressLint("NewApi") @Override
	public void onAppWidgetOptionsChanged(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId,
			Bundle newOptions) {
		// TODO Auto-generated method stub
		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId,
				newOptions);
		Log.i(TAG, "onAppWidgetOptionsChanged");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
		Log.i(TAG, "onReceive");
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.i(TAG, "onUpdate");
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Log.i(TAG, "onDeleted");
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		Log.i(TAG, "onEnabled");
		//初始化按件
		initView(context);
	}

	private void initView(Context context) {
		
		AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_music);
		
		Intent playIntent=new Intent(context, MusicPlayWidgetService.class);
		//给播放添加点击事件
		PendingIntent playPendingIntent=PendingIntent.getService(context, 0, playIntent, 0);
		views.setOnClickPendingIntent(R.id.bt_play, playPendingIntent);
		
		Intent stopIntent=new Intent(context, MusicStopWidgetService.class);
		//给停止添加点击事件
		PendingIntent stopPendingIntent=PendingIntent.getService(context, 0, stopIntent, 0);
		views.setOnClickPendingIntent(R.id.bt_stop, stopPendingIntent);
		
		//给前一首添加点击事件
		Intent previousIntent=new Intent(context, MusicPreviousWidgetService.class);
		PendingIntent previousPendingIntent=PendingIntent.getService(context, 0, previousIntent, 0);
		views.setOnClickPendingIntent(R.id.bt_previous, previousPendingIntent);
		
		//给下一首添加点击事件
		Intent nextIntent=new Intent(context, MusicNextWidgetService.class);
		PendingIntent nextPendingIntent=PendingIntent.getService(context, 0, nextIntent, 0);
		views.setOnClickPendingIntent(R.id.bt_next, nextPendingIntent);
				
		ComponentName componentName = new ComponentName(context, this.getClass());
		widgetManager.updateAppWidget(componentName, views);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
		Log.i(TAG, "onDisabled");
	}
	
	/**
	 * 更新状态
	 * @param state
	 */
	public void update(Context context,String state){
		AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_music);
		views.setTextViewText(R.id.tv_state, state);
		ComponentName componentName = new ComponentName(context, this.getClass());
		widgetManager.updateAppWidget(componentName, views);
	}

	
}
