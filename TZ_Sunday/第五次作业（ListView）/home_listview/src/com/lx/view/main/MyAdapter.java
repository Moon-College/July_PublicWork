 package com.lx.view.main;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.view.bean.Person;
/*
 * ͨ��LayoutInflater�����ز���
 * List<Person> ���ص�ÿһ����Ŀ�Ķ���
 * 
 * 
 * **/
public class  MyAdapter extends  BaseAdapter{
    List<Person>  data;
    private LayoutInflater inflater;
    private Boolean isMan;
    private Context context;
    public MyAdapter(Context context,List<Person> person){
    	 this.data = person;
    	 this.context = context;
    	 inflater = LayoutInflater.from(context);
    	 Log.i("INFo", "adapter" + data.size());
    }
	 
    //��������Ŀ������
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return  position;
	}
    //����ÿһ����Ŀ����ò
	public View getView(int position, View convertView, ViewGroup parent) {
       //��ȡ���� 
	     Person person = (Person) getItem(position);
		View view = inflater.inflate(R.layout.layout_iteam_person, null);
       ImageView img = (ImageView) view.findViewById(R.id.person);
       img.setImageResource(person.getImage());
       
       TextView person_name = (TextView) view.findViewById(R.id.person_name);
       person_name.setText(person.getName());
       
       
       TextView gender = (TextView) view.findViewById(R.id.person_gender);
       String isMan = person.getGender();
       if(isMan.equals("��")){
    	   gender.setText(person.getGender());
    	   gender.setBackgroundColor(Color.RED);
    	   
       }else{
    	   gender.setText(person.getGender());
    	   gender.setBackgroundColor(Color.BLUE);   
       }
      
       TextView person_like_ = (TextView) view.findViewById(R.id.person_like);
       person_like_.setText(person.getLike());
       TextView person_face = (TextView) view.findViewById(R.id.person_face);
       person_face.setText(String.valueOf(person.getFace()));
		return  view;
	}

}
