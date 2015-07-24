package com.lx.view.main;

import java.util.ArrayList;
import java.util.List;

import com.lx.view.bean.Person;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends Activity {
     private ListView mListView;
     private List<Person> datas;
	 private int [] imges = new int []{ 
		  R.drawable.face1,
		  R.drawable.face2,
		  R.drawable.face3,
		  R.drawable.face4,
		  R.drawable.face5,
		  R.drawable.face6,
		  R.drawable.face7,
		  R.drawable.face8,
		  R.drawable.ic_launcher,
		  R.drawable.ic_launcher,
		  R.drawable.ic_launcher,
			 
			 
	 };
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      mListView = (ListView) findViewById(R.id.listview_people);
     
      setPersonData();
      
      setListViewAdapter();
      
    }
	private void setListViewAdapter() {
       MyAdapter adapter = new MyAdapter(this, datas);
       mListView.setAdapter(adapter);
	}
	private void setPersonData() {
	  datas  = new ArrayList<Person>();
	  Resources resources = getResources();
	  String [] names = resources.getStringArray(R.array.names);
	  String [] genders = resources.getStringArray(R.array.genders);
	  String [] likes = resources.getStringArray(R.array.likes);
	  int []  faces = resources.getIntArray(R.array.faces); 
	  Log.i("INFO", "nameLength" + names.length);
	  for (int i = 0; i < names.length; i++) {
		 Person persons  = new  Person();
		 persons.setImage(imges[i]);
		 persons.setName(names[i]);
		 persons.setGender(genders[i]);
         persons.setFace(faces[i]);
         persons.setLike(likes[i]);
        datas.add(persons);
	   }
	  Log.i("INFO", "PeronLength :" + datas.size());
	} 
}