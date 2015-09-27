package com.tz.pullxml;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String XML_STR = "<ArrayOfString><string>hello</string><string>123456</string></ArrayOfString>";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		List<String> list=parseXml(XML_STR);
		for(String str:list){
			Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
		}
	}

	public List<String> parseXml(String xml) {
		List<String> list=new ArrayList<String>();
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xml));
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String nodeName = parser.getName();
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if(nodeName!=null&&nodeName.equals("string")){
						String value=parser.nextText();
						list.add(value);
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				default:
					break;

				}
				eventType=parser.next();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
