package com.tz.volleylrucache.util;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.tz.volleylrucache.MyApplication;

public class LruRequestQueueManager{
	
	public static  RequestQueue requestQueue=Volley.newRequestQueue(MyApplication.application);
}
