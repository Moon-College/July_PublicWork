package com.tz.lesson5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tz.lesson5.adapter.ListViewAdapter;
import com.tz.lesson5.bean.Person;

public class ListViewActivity extends Activity implements OnItemClickListener{

	private ListView mListView;
    private ListViewAdapter adapter;
    private List<Person> persons;
    
    private String[] names = new String[] {
    	"�ŷ�",	
    	"�ܲ�",	
    	"��Ȩ",	
    	"����",	
    	"�����",	
    	"����",	
    	"����",	
    	"����",	
    	"����ɽ",	
    	"��֪��"
    };
    
    private String[] hobbys = new String[] {
    		"д����",
    		"����Ӱ",
    		"�ܲ�",
    		"����",
    		"����",
    		"������",
    		"����",
    		"������",
    		"������"
    };
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
       
        persons = initData();
        mListView = (ListView) findViewById(R.id.lv);
        // ���ض���
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_item_in);
        // ���ö���
        mListView.setLayoutAnimation(new LayoutAnimationController(animation));
        mListView.setOnItemClickListener(this);
        adapter = new ListViewAdapter(this, persons);
        
        mListView.setAdapter(adapter);
    }
    /**
     * ��ʼ������
     * @return 
     */
    private List<Person> initData() {
    	List<Person> persons = new ArrayList<Person>();
    	Random random = new Random();
    	for(int i=0;i<names.length;i++) {
    		Person person = new Person();
    		person.setFace(getResources().getIdentifier("face"+(i+1), "drawable", getPackageName()));
    		person.setNickName(names[i]);
    		person.setSex(random.nextInt(2));
    		person.setFaceScore((float)Math.ceil(Math.random() * 5));
    		person.setHobby(getHobby(random.nextInt(8) + 1,random));
    		persons.add(person);
    	}
    	
    	return persons;
    }
    
    /**
     * ���ø��������������� 
     * @param number
     * @return
     */
    private String getHobby(int number,Random random) {
    	StringBuffer buffer = new StringBuffer();
    	for(int i = 0; i< number; i++) {
    		String hobby = hobbys[random.nextInt(9)];
    		int index = buffer.indexOf(hobby);
    		if(index == -1) {
    			buffer.append(hobby).append("��");
    		}
    	}
    	buffer.deleteCharAt(buffer.length() - 1);
    	return buffer.toString();
    }
    
    /**
     * ListView Item �����¼� 
     * ����ĳ��Item ɾ��
     */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		final Person person = adapter.getItem(position);
		if(person != null && persons.contains(person)) {
			// ���ض��� 
			 Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_item_out);
			// ���ö���������
			 animation.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {}
				
				@Override
				public void onAnimationRepeat(Animation animation) {}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					persons.remove(person);
					adapter.notifyDataSetChanged();
					animation.cancel();
				}
			});
			 view.setAnimation(animation);
		}
	}

	
}
