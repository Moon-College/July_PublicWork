package com.tz.gridview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;
import android.widget.SimpleAdapter;

/**
 * 好豆菜谱
 * @author 赵建祥
 *
 */
public class MainActivity extends Activity {
    
	private GridView gv_menucontent;
	private List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
	private SimpleAdapter adapter;
	private String[] from=new String[]{
			"img",//菜谱图片
			"imgState",//菜谱图片上说明
			"presentation",//简介
			"face",//作者头像
			"author"//作者名字
	};
	
	private int [] to=new int[]{
			R.id.iv_img,//菜谱图片
			R.id.tv_imgState,//菜谱图片上说明
			R.id.tv_presentation,//简介
			R.id.iv_face,//作者头像
			R.id.tv_author//作者名字
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        initData();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
		Map<String,Object> menu0=new HashMap<String,Object>();
		menu0.put(from[0], R.drawable.food0);
		menu0.put(from[1], "22道菜");
		menu0.put(from[2], "牛肉这样做才好吃");
		menu0.put(from[3], R.drawable.face0);
		menu0.put(from[4], "author0");
		data.add(menu0);
		
		Map<String,Object> menu1=new HashMap<String,Object>();
		menu1.put(from[0], R.drawable.food1);
		menu1.put(from[1], "23道菜");
		menu1.put(from[2], "给烟民的良心菜");
		menu1.put(from[3], R.drawable.face1);
		menu1.put(from[4], "author1");
		data.add(menu1);
		
		Map<String,Object> menu2=new HashMap<String,Object>();
		menu2.put(from[0], R.drawable.food2);
		menu2.put(from[1], "24道菜");
		menu2.put(from[2], "给烟民的良心菜");
		menu2.put(from[3], R.drawable.face2);
		menu2.put(from[4], "author2");
		data.add(menu2);
		
		Map<String,Object> menu3=new HashMap<String,Object>();
		menu3.put(from[0], R.drawable.food3);
		menu3.put(from[1], "25道菜");
		menu3.put(from[2], "给烟民的良心菜");
		menu3.put(from[3], R.drawable.face3);
		menu3.put(from[4], "author3");
		data.add(menu3);
		
		Map<String,Object> menu4=new HashMap<String,Object>();
		menu4.put(from[0], R.drawable.food4);
		menu4.put(from[1], "25道菜");
		menu4.put(from[2], "给烟民的良心菜");
		menu4.put(from[3], R.drawable.face4);
		menu4.put(from[4], "author2");
		data.add(menu4);
		
		Map<String,Object> menu5=new HashMap<String,Object>();
		menu5.put(from[0], R.drawable.food5);
		menu5.put(from[1], "26道菜");
		menu5.put(from[2], "给烟民的良心菜");
		menu5.put(from[3], R.drawable.face5);
		menu5.put(from[4], "author5");
		data.add(menu5);
		
		Map<String,Object> menu6=new HashMap<String,Object>();
		menu6.put(from[0], R.drawable.food6);
		menu6.put(from[1], "27道菜");
		menu6.put(from[2], "给烟民的良心菜");
		menu6.put(from[3], R.drawable.face6);
		menu6.put(from[4], "author6");
		data.add(menu6);
		
		Map<String,Object> menu7=new HashMap<String,Object>();
		menu7.put(from[0], R.drawable.food7);
		menu7.put(from[1], "29道菜");
		menu7.put(from[2], "给烟民的良心菜");
		menu7.put(from[3], R.drawable.face7);
		menu7.put(from[4], "author7");
		data.add(menu7);
	}

	/**
     * 初始化控件
     */
	private void initView() {
		gv_menucontent=(GridView) findViewById(R.id.gv_menucontent);
		adapter=new SimpleAdapter(this, data, R.layout.grid_item, from, to);
		gv_menucontent.setAdapter(adapter);
	}
}