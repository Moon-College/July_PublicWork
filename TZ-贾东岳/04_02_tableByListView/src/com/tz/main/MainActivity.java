package com.tz.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tz.first.R;
import com.tz.fourth.second.ItemInfo;
import com.tz.fourth.second.TableAdapter;

/**
 * 第四节课第二个作业，模拟一个tableLayout，并给其每列不同的背景颜色.
 * 
 * @author JDY
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simulate_table);
		// 设置表格标题的背景颜色
		ViewGroup tableTitle = (ViewGroup) findViewById(R.id.table_title);
		tableTitle.setBackgroundColor(getResources().getColor(R.color.wheat));

		List<ItemInfo> list = new ArrayList<ItemInfo>();
		list.add(new ItemInfo("第一列", "第二列", "第三列"));
		list.add(new ItemInfo("第一列", "第二列", "第三列"));
		list.add(new ItemInfo("第一列", "第二列", "第三列"));
		list.add(new ItemInfo("第一列", "第二列", "第三列"));

		ListView tableListView = (ListView) findViewById(R.id.list);

		TableAdapter adapter = new TableAdapter(this, list);
		tableListView.setAdapter(adapter);
	}

}
