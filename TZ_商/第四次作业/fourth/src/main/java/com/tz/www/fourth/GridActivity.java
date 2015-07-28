package com.tz.www.fourth;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GridActivity extends BaseActivity {
    //输入框
    private AutoCompleteTextView atvSearch;
    private GridView gvList;
    private String[] mString = {"aa", "bb", "cc", "ab", "ac", "bc", "ca"};
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> data;
    private int[] icons = {
            R.mipmap.face1,
            R.mipmap.face2,
            R.mipmap.face3,
            R.mipmap.face4,
            R.mipmap.face5,
            R.mipmap.face6,
            R.mipmap.face7,
            R.mipmap.face8
    };


    @Override
    public void initView() {
        setContentView(R.layout.activity_grid_view);
        atvSearch = (AutoCompleteTextView) findViewById(R.id.atv_search);
        gvList = (GridView) findViewById(R.id.gv_list);


    }

    @Override
    public void initData() {
        //设置第一个字开始搜索
        atvSearch.setThreshold(1);
        data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 8; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("number",i+1);
            map.put("name", "貂蝉" + i);
            map.put("phone", "133333" + i);
            map.put("icon", icons[i]);
            data.add(map);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mString);
        atvSearch.setAdapter(arrayAdapter);

        simpleAdapter  = new SimpleAdapter(this, data, R.layout.gridview_item, new String[]{"name", "icon"}, new int[]{R.id.tv_name, R.id.icon});
        gvList.setAdapter(simpleAdapter);
        //单击每个条目
        gvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map map = (Map) simpleAdapter.getItem(position);
                String name = (String) map.get("name");
                Toast.makeText(GridActivity.this,name,Toast.LENGTH_SHORT).show();
            }
        });
        //长点击每个条目
        gvList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                data.remove(position);
                simpleAdapter.notifyDataSetChanged();
                Toast.makeText(GridActivity.this, position + 1 + "号选手被删除", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

}
