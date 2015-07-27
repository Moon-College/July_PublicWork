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
 * ����VIPͬѧ�б�
 * @author �Խ���
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
     * ��ʼ�� ����
     */
    private void intData() {
		JulyVip vip1=new JulyVip();
		vip1.setFaceImgId(R.drawable.andy);
		vip1.setNetName("andy");
		vip1.setSex("��");
		vip1.setFaceScore("85");
		vip1.setHobby("����");
		data.add(vip1);
		JulyVip vip2=new JulyVip();
		vip2.setFaceImgId(R.drawable.bg);
		vip2.setNetName("����");
		vip2.setSex("��");
		vip2.setFaceScore("86");
		vip2.setHobby("����");
		data.add(vip2);
		JulyVip vip3=new JulyVip();
		vip3.setFaceImgId(R.drawable.cz);
		vip3.setNetName("����");
		vip3.setSex("��");
		vip3.setFaceScore("87");
		vip3.setHobby("���� ����");
		data.add(vip3);
		JulyVip vip4=new JulyVip();
		vip4.setFaceImgId(R.drawable.grace);
		vip4.setNetName("grace");
		vip4.setSex("Ů");
		vip4.setFaceScore("88");
		vip4.setHobby("����");
		data.add(vip4);
		JulyVip vip5=new JulyVip();
		vip5.setFaceImgId(R.drawable.me);
		vip5.setNetName("�Խ���");
		vip5.setSex("��");
		vip5.setFaceScore("65");
		vip5.setHobby("���� ����");
		data.add(vip5);
		JulyVip vip6=new JulyVip();
		vip6.setFaceImgId(R.drawable.ricky);
		vip6.setNetName("����");
		vip6.setSex("��");
		vip6.setFaceScore("89");
		vip6.setHobby("���� ����");
		data.add(vip6);
		JulyVip vip7=new JulyVip();
		vip7.setFaceImgId(R.drawable.shang);
		vip7.setNetName("��");
		vip7.setSex("��");
		vip7.setFaceScore("88");
		vip7.setHobby("����");
		data.add(vip7);
		JulyVip vip8=new JulyVip();
		vip8.setFaceImgId(R.drawable.xbl);
		vip8.setNetName("С����");
		vip8.setSex("��");
		vip8.setFaceScore("89");
		vip8.setHobby("����");
		data.add(vip8);
		JulyVip vip9=new JulyVip();
		vip9.setFaceImgId(R.drawable.xlb);
		vip9.setNetName("С����");
		vip9.setSex("��");
		vip9.setFaceScore("91");
		vip9.setHobby("����");
		data.add(vip9);
		JulyVip vip10=new JulyVip();
		vip10.setFaceImgId(R.drawable.yy);
		vip10.setNetName("����");
		vip10.setSex("��");
		vip10.setFaceScore("93");
		vip10.setHobby("����");
		data.add(vip10);
		JulyVip vip11=new JulyVip();
		vip11.setFaceImgId(R.drawable.yyls);
		vip11.setNetName("������ʦ");
		vip11.setSex("Ů");
		vip11.setFaceScore("95");
		vip11.setHobby("����");
		data.add(vip11);
	}

	/**
     * ��ʼ���ؼ�
     */
	private void initView() {
		lv_vip=(ListView) findViewById(R.id.lv_vip);
		//��ʼ��������
		adapter=new VipAdapter(this, data);
		lv_vip.setAdapter(adapter);
		
		//�����Ŀ��ɾ����Ŀ
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