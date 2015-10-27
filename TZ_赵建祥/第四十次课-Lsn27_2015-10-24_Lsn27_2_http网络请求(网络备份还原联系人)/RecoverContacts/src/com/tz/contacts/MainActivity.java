package com.tz.contacts;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.tz.contacts.bean.Contact;
import com.tz.contacts.bean.JsonParams;
import com.tz.contacts.util.HttpCallBack;
import com.tz.contacts.util.HttpUtil;
import com.tz.contacts.util.JsonUtil;

public class MainActivity extends Activity {

    private ContentResolver cr;
	private Uri uri;
	private List<Contact> contacts;
	private ListView lv_contacts;
	private String backup_path="http://192.168.114.1:8080/wifidownload/backup.jsp";
	private String recover_path="http://192.168.114.1:8080/wifidownload/recover.jsp";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cr = getContentResolver();
        initData();
        
        lv_contacts=(ListView)findViewById(R.id.lv_contacts);
        
        ContactAdapter adapter=new ContactAdapter(contacts, this);
		lv_contacts.setAdapter(adapter);
        
    }

	private void initData() {
		
		contacts = new ArrayList<Contact>();
		uri = ContactsContract.AUTHORITY_URI;
		uri=Uri.withAppendedPath(uri, "raw_contacts");
		//获取联系人主表
		Cursor zbCursor=cr.query(uri, null, null, null, null);
		while(zbCursor.moveToNext()){
			Contact contact=new Contact();
			
			int zbId=zbCursor.getInt(zbCursor.getColumnIndex(ContactsContract.Contacts._ID));
			
			contact.setId(zbId);
			uri = ContactsContract.AUTHORITY_URI;
			uri=Uri.withAppendedPath(uri, "data");
			//根据主表ID查询子表
			Cursor mxCursor=cr.query(uri, null, "raw_contact_id=?", new String[]{zbId+""}, null);
			while(mxCursor.moveToNext()){
				String data1=mxCursor.getString(mxCursor.getColumnIndex("data1"));
				String mimetype=mxCursor.getString(mxCursor.getColumnIndex("mimetype"));
				if(mimetype.equals("vnd.android.cursor.item/email_v2")){
					contact.setEmail(data1);
				}
				if(mimetype.equals("vnd.android.cursor.item/phone_v2")){
					String data2=mxCursor.getString(mxCursor.getColumnIndex("data2"));
					if(data2.equals("2")){
						contact.setTel(data1);
					}
					if(data2.equals("1")){
						contact.setPhone(data1);
					}
				}
				
				if(mimetype.equals("vnd.android.cursor.item/name")){
					contact.setName(data1);
				}
			}
			contacts.add(contact);
			
		}
		
	}
	
	/**
	 * 备份联系人到远程服务器
	 * @param v
	 */
	public void backup(View v){
		try {
			//将联系人转换成JSON
			String jsonStr=new JsonUtil<List<Contact>>().parseListToJson(contacts);
			JsonParams params=new JsonParams();
			params.setJson(jsonStr);
			HttpUtil httpUtil=new HttpUtil<JsonParams>(this, backup_path, params, new HttpCallBack() {
				
				@Override
				public void success(String result) {
					Toast.makeText(MainActivity.this, result, 1).show();
					
					
				}
				
				@Override
				public void failed(String result) {
					Toast.makeText(MainActivity.this, result, 1).show();
					
				}
			});
			
			//post方式发送请求
			httpUtil.doAsyncPostByHttpURLConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 从远程服务器恢复联系人
	 * @param v
	 */
	public void recover(View v){
		try {
			HttpUtil httpUtil=new HttpUtil<JsonParams>(this, recover_path, null, new HttpCallBack() {
				
				@Override
				public void success(String result) {
					Toast.makeText(MainActivity.this, result, 1).show();
					
					try {
						JSONArray jsonArray=new JSONArray(result);
						
						/**************************/
						uri = ContactsContract.AUTHORITY_URI;
						uri=Uri.withAppendedPath(uri, "raw_contacts");
						//删除主表数据
						int zbdelCount=cr.delete(uri, null, null);
						
						uri = ContactsContract.AUTHORITY_URI;
						uri=Uri.withAppendedPath(uri, "data");
						//根据主表ID查询子表
						cr.delete(uri, null,null);
						/*******************************/
						for (int i=0;i<jsonArray.length();i++) {
							JSONObject jsonO=(JSONObject) jsonArray.get(i);
							int id=jsonO.getInt("id");
							String name=jsonO.getString("name");
							String email=jsonO.getString("email");
							String phone=jsonO.getString("phone");
							String tel=jsonO.getString("tel");
							/***********************/
							//插入主表
							uri=ContactsContract.AUTHORITY_URI;
							uri=Uri.withAppendedPath(uri, "raw_contacts");
							
							ContentValues values=new ContentValues();
							values.put(ContactsContract.Contacts._ID, id);
							cr.insert(uri, values);
							
							uri = ContactsContract.AUTHORITY_URI;
							uri=Uri.withAppendedPath(uri, "data");
							//插入邮箱
							ContentValues subvalues=new ContentValues();
							subvalues.put("raw_contact_id", id);
							subvalues.put("data1", email);
							subvalues.put("mimetype", "vnd.android.cursor.item/email_v2");
							cr.insert(uri, subvalues);
							//插入电话
							subvalues=new ContentValues();
							subvalues.put("raw_contact_id", id);
							subvalues.put("data1", tel);
							subvalues.put("data2", 2);
							subvalues.put("mimetype", "vnd.android.cursor.item/phone_v2");
							cr.insert(uri, subvalues);
							
							//插入手机
							subvalues=new ContentValues();
							subvalues.put("raw_contact_id", id);
							subvalues.put("data1", phone);
							subvalues.put("data2", 1);
							subvalues.put("mimetype", "vnd.android.cursor.item/phone_v2");
							cr.insert(uri, subvalues);
							
							//插入联系人名称
							subvalues=new ContentValues();
							subvalues.put("raw_contact_id", id);
							subvalues.put("data1", name);
							subvalues.put("mimetype", "vnd.android.cursor.item/name");
							cr.insert(uri, subvalues);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				@Override
				public void failed(String result) {
					Toast.makeText(MainActivity.this, result, 1).show();
				}
			});
			
			//post方式发送请求
			httpUtil.doAsyncGetByHttpURLConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
