package com.tz.customlistview;

import java.util.ArrayList;
import java.util.List;

import com.tz.customlistview.adapter.VipAdapter;
import com.tz.customlistview.bean.JulyVip;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 七月VIP同学列表
 * @author 赵建祥
 *
 */
public class MainActivity extends Activity {
	
	private ListView lv_vip;
	private List<JulyVip> data=new ArrayList<JulyVip>();
	private VipAdapter adapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        intData();
        initView();
    }

    /**
     * 初始化 数据
     */
    private void intData() {
		JulyVip vip1=new JulyVip();
		vip1.setFaceImgId(R.drawable.andy);
		vip1.setNetName("andy");
		vip1.setSex("男");
		vip1.setFaceScore("85");
		vip1.setHobby("篮球");
		data.add(vip1);
		JulyVip vip2=new JulyVip();
		vip2.setFaceImgId(R.drawable.bg);
		vip2.setNetName("波哥");
		vip2.setSex("男");
		vip2.setFaceScore("86");
		vip2.setHobby("足球");
		data.add(vip2);
		JulyVip vip3=new JulyVip();
		vip3.setFaceImgId(R.drawable.cz);
		vip3.setNetName("城主");
		vip3.setSex("男");
		vip3.setFaceScore("87");
		vip3.setHobby("足球 篮球");
		data.add(vip3);
		JulyVip vip4=new JulyVip();
		vip4.setFaceImgId(R.drawable.grace);
		vip4.setNetName("grace");
		vip4.setSex("女");
		vip4.setFaceScore("88");
		vip4.setHobby("排球");
		data.add(vip4);
		JulyVip vip5=new JulyVip();
		vip5.setFaceImgId(R.drawable.me);
		vip5.setNetName("赵建祥");
		vip5.setSex("男");
		vip5.setFaceScore("65");
		vip5.setHobby("足球 排球");
		data.add(vip5);
		JulyVip vip6=new JulyVip();
		vip6.setFaceImgId(R.drawable.ricky);
		vip6.setNetName("鼎哥");
		vip6.setSex("男");
		vip6.setFaceScore("89");
		vip6.setHobby("足球 上网");
		data.add(vip6);
		JulyVip vip7=new JulyVip();
		vip7.setFaceImgId(R.drawable.shang);
		vip7.setNetName("商");
		vip7.setSex("男");
		vip7.setFaceScore("88");
		vip7.setHobby("足球");
		data.add(vip7);
		JulyVip vip8=new JulyVip();
		vip8.setFaceImgId(R.drawable.xbl);
		vip8.setNetName("小白龙");
		vip8.setSex("男");
		vip8.setFaceScore("89");
		vip8.setHobby("足球");
		data.add(vip8);
		JulyVip vip9=new JulyVip();
		vip9.setFaceImgId(R.drawable.xlb);
		vip9.setNetName("小笼包");
		vip9.setSex("男");
		vip9.setFaceScore("91");
		vip9.setHobby("足球");
		data.add(vip9);
		JulyVip vip10=new JulyVip();
		vip10.setFaceImgId(R.drawable.yy);
		vip10.setNetName("云烟");
		vip10.setSex("男");
		vip10.setFaceScore("93");
		vip10.setHobby("足球");
		data.add(vip10);
		JulyVip vip11=new JulyVip();
		vip11.setFaceImgId(R.drawable.yyls);
		vip11.setNetName("瑶瑶老师");
		vip11.setSex("女");
		vip11.setFaceScore("95");
		vip11.setHobby("足球");
		data.add(vip11);
	}

	/**
     * 初始化控件
     */
	private void initView() {
		lv_vip=(ListView) findViewById(R.id.lv_vip);
		//初始化适配器
		adapter=new VipAdapter(this, data);
		lv_vip.setAdapter(adapter);
		
		//点击条目，删除条目
		lv_vip.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				data.remove(position);
				adapter.notifyDataSetChanged();
			}
		});
	}
}