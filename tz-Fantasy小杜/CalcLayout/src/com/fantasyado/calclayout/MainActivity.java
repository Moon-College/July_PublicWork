package com.fantasyado.calclayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout.LayoutParams;


public class MainActivity extends Activity {
    private GridView gv;
    private ArrayAdapter<String> adapter;
    private String[] 	datas = {
			"x^2", 	"x^y", 	"sin", 	"cos", 	"tan", 	
			"¡Ì", 	"10^x", "log", 	"Exp", 	"Mod", 	
			"top", 	"CE", 	"C", 	"backspace", "/", 	
			"pi", 	"7", 	"8", 	"9", 	"*", 	
			"n!", 	"4", 	"5", 	"6", 	"-",
			"+-", 	"1", 	"2", 	"3", 	"+",
			"(", 	")", 	"0", 	".", 	"="
		};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Calculator");
        this.setTitleColor(Color.parseColor("#e3e3e3"));
        initView();
        intData();
    }
	private void intData() {
		// TODO Auto-generated method stub
		 adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.button, datas);
		 gv.setAdapter(adapter);
		 
			
			
		}
	
		
 
	private void initView() {
		// TODO Auto-generated method stub
		gv = (GridView) findViewById(R.id.gridview);		
		
	}

 
}
