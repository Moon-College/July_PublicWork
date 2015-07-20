package com.tz.fourth.second;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tz.first.R;

/**第四节课第二个作业，模拟一个tableLayout，并给其每列不同的背景颜色.
 * @author Administrator
 *
 */
public class SimulateTableActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simulate_table);
		//设置表格标题的背景颜色
				ViewGroup tableTitle = (ViewGroup) findViewById(R.id.table_title);
				tableTitle.setBackgroundColor(getResources().getColor(R.color.cornflowerblue));
				
				List<ItemInfo> list = new ArrayList<ItemInfo>();
				list.add(new ItemInfo("第一列", "第二列", "第三列"));
				list.add(new ItemInfo("第一列", "第二列", "第三列"));
				list.add(new ItemInfo("第一列", "第二列", "第三列"));
				list.add(new ItemInfo("第一列", "第二列", "第三列"));
				
				ListView tableListView = (ListView) findViewById(R.id.list);
			
				TableAdapter adapter = new TableAdapter(this, list);
				tableListView.setAdapter(adapter);
	}
	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		
		return super.onCreateView(name, context, attrs);
	}
	
}
