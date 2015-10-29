package com.tz.recovercontacts;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tz.recovercontacts.bean.Contact;
import com.tz.recovercontacts.bean.JsonParams;
import com.tz.recovercontacts.util.HttpCallBack;
import com.tz.recovercontacts.util.HttpUtil;
import com.tz.recovercontacts.util.JsonUtil;

/**
 * 利用内容提供者，对联系人进行新增、删除、备份、还原。备份、还原利用网络来进行
 * 
 * @author zhao_jx
 * 
 */
public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private ContentResolver cr;// 内容提供者
	private Uri uri;
	private List<Contact> contacts;
	private ListView lv_contacts;
	private String backup_path = "http://192.168.136.5:8080/httpserver/backup.jsp";
	private String recover_path = "http://192.168.136.5:8080/httpserver/recoverServlet";
	private EditText et_name;
	private EditText et_email;
	private EditText et_phone;
	private EditText et_tel;
	private ContactAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 初始化控件
		initView();
		// 初始化数据
		initData();
	}

	private void initView() {
		et_name = (EditText) findViewById(R.id.et_name);
		et_email = (EditText) findViewById(R.id.et_email);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_tel = (EditText) findViewById(R.id.et_tel);
		lv_contacts = (ListView) findViewById(R.id.lv_contacts);
	}

	private void initData() {
		cr = getContentResolver();
		contacts = new ArrayList<Contact>();
		uri = ContactsContract.AUTHORITY_URI;
		uri = Uri.withAppendedPath(uri, "raw_contacts");
		// 获取联系人主表
		Cursor zbCursor = cr.query(uri, null, "deleted=?",
				new String[] { "0" }, null);
		while (zbCursor.moveToNext()) {
			Contact contact = new Contact();

			int zbId = zbCursor.getInt(zbCursor
					.getColumnIndex(ContactsContract.Contacts._ID));

			contact.setId(zbId);
			uri = ContactsContract.AUTHORITY_URI;
			uri = Uri.withAppendedPath(uri, "data");
			// 根据主表ID查询子表
			Cursor mxCursor = cr.query(uri, null, "raw_contact_id=?",
					new String[] { zbId + "" }, null);
			if (mxCursor != null) {
				while (mxCursor.moveToNext()) {
					String data1 = mxCursor.getString(mxCursor
							.getColumnIndex("data1"));
					String mimetype = mxCursor.getString(mxCursor
							.getColumnIndex("mimetype"));
					if (mimetype.equals("vnd.android.cursor.item/email_v2")) {
						contact.setEmail(data1);
					}
					if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
						String data2 = mxCursor.getString(mxCursor
								.getColumnIndex("data2"));
						Log.i(TAG, "data1:" + data1 + " data2:" + data2);
						if (data2.equals("1")) {
							contact.setTel(data1);
						}
						if (data2.equals("2")) {
							contact.setPhone(data1);
						}
					}

					if (mimetype.equals("vnd.android.cursor.item/name")) {
						contact.setName(data1);
					}
				}
				contacts.add(contact);
			}
		}

		adapter = new ContactAdapter(contacts, this);
		lv_contacts.setAdapter(adapter);
		// 长按删除对应联系人
		lv_contacts.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 删除联系人
				int delCount = deleteContact(contacts.get(position));
				if (delCount > 0) {
					Toast.makeText(MainActivity.this, "删除成功", 1).show();
					contacts.remove(position);
					adapter.notifyDataSetChanged();
				} else {
					Toast.makeText(MainActivity.this, "删除失败", 1).show();
				}
				return false;
			}
		});

		// 点击联系人将其信息显示到上面的编辑框中
		lv_contacts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Contact contact = contacts.get(position);
				et_name.setText(contact.getName());
				et_email.setText(contact.getEmail());
				et_phone.setText(contact.getPhone());
				et_tel.setText(contact.getTel());
			}
		});
	}

	/**
	 * 删除联系人
	 * 
	 * @param contact
	 *            要删除的联系人
	 * @return 删除的数量
	 */
	protected int deleteContact(Contact contact) {
		uri = ContactsContract.AUTHORITY_URI;
		uri = Uri.withAppendedPath(uri, "raw_contacts");
		// 删除主表
		cr.delete(uri, ContactsContract.Contacts._ID + "=?",
				new String[] { contact.getId() + "" });

		uri = ContactsContract.AUTHORITY_URI;
		uri = Uri.withAppendedPath(uri, "data");
		// 删除子表
		int delCount = cr.delete(uri, "raw_contact_id=?",
				new String[] { contact.getId() + "" });
		return delCount;
	}

	/**
	 * 插入联系人
	 * 
	 * @param v
	 */
	public void insertContact(View v) {
		final String name = et_name.getText().toString();
		final String email = et_email.getText().toString();
		final String phone = et_phone.getText().toString();
		final String tel = et_tel.getText().toString();

		// 查询有没有对应联系人
		uri = ContactsContract.AUTHORITY_URI;
		uri = Uri.withAppendedPath(uri, "raw_contacts");
		Cursor nameCursor = cr.query(uri, null, "deleted=? and display_name=?",
				new String[] { 0 + "", name }, null);
		Log.i(TAG, "nameCursor:" + nameCursor);
		if (nameCursor != null && nameCursor.getCount() > 0) {

			nameCursor.moveToNext();
			// 获取主表ID
			final int zbid = nameCursor.getInt(nameCursor
					.getColumnIndex(ContactsContract.Contacts._ID));
			AlertDialog.Builder builder = new Builder(this);
			builder.setTitle("提醒");
			builder.setMessage("已存在该联系人是否更新");
			builder.setPositiveButton("确认",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							// 更新现在联系人
							Contact contact = updateContact(new Contact(zbid,
									name, email, tel, phone));
							for (int i = 0; i < contacts.size(); i++) {
								Contact t = contacts.get(i);
								// 更新列表数据、并刷新
								if (t.getName() != null
										&& t.getName()
												.equals(contact.getName())) {
									contacts.set(i, contact);
									adapter.notifyDataSetChanged();
									break;
								}
							}
							Toast.makeText(MainActivity.this, "更新成功", 1).show();
						}
					});

			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			builder.show();
		} else {
			Contact contact = addContact(new Contact(null, name, email, tel,
					phone));
			if (contact != null) {
				contacts.add(contact);
				adapter.notifyDataSetChanged();
				Toast.makeText(this, "插入成功", 1).show();
			}
		}
	}

	/**
	 * 备份联系人到远程服务器
	 * 
	 * @param v
	 */
	public void backup(View v) {
		try {
			// 将联系人转换成JSON
			String jsonStr = new JsonUtil<List<Contact>>()
					.parseListToJson(contacts);
			JsonParams params = new JsonParams();
			params.setJson(jsonStr);
			HttpUtil httpUtil = new HttpUtil<JsonParams>(this, backup_path,
					params, new HttpCallBack() {

						@Override
						public void success(String result) {
							Toast.makeText(MainActivity.this, result, 1).show();
						}

						@Override
						public void failed(String result) {
							Toast.makeText(MainActivity.this, result, 1).show();
						}
					});

			// post方式发送请求
			httpUtil.doAsyncPostByHttpURLConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 从远程服务器恢复联系人
	 * 
	 * @param v
	 */
	public void recover(View v) {
		try {
			HttpUtil httpUtil = new HttpUtil<JsonParams>(this, recover_path,
					null, new HttpCallBack() {

						@Override
						public void success(String result) {
							Toast.makeText(MainActivity.this, result, 1).show();
							recoverContacts(result);
							Toast.makeText(MainActivity.this, "恢复成功", 1).show();
						}

						@Override
						public void failed(String result) {
							Toast.makeText(MainActivity.this, result, 1).show();
						}
					});

			// post方式发送请求
			httpUtil.doAsyncGetByHttpURLConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 恢复联系人，先更新、再插入
	 * 
	 * @param json
	 */
	protected void recoverContacts(String json) {
		try {
			JSONArray jsonArray = new JSONArray(json);

			/**************************/
			// uri = ContactsContract.AUTHORITY_URI;
			// uri=Uri.withAppendedPath(uri, "raw_contacts");
			// //删除主表数据
			// int zbdelCount=cr.delete(uri, null, null);
			//
			// uri = ContactsContract.AUTHORITY_URI;
			// uri=Uri.withAppendedPath(uri, "data");
			// //根据主表ID删除子表
			// cr.delete(uri, null,null);
			/*******************************/
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonO = (JSONObject) jsonArray.get(i);
				int id = jsonO.getInt("id");
				String name = jsonO.getString("name");
				String email = jsonO.getString("email");
				String phone = jsonO.getString("phone");
				String tel = jsonO.getString("tel");
				// 根据姓名查询，手机中联系人
				uri = ContactsContract.AUTHORITY_URI;
				uri = Uri.withAppendedPath(uri, "raw_contacts");

				// 查询有没有对应联系人
				Cursor nameCursor = cr.query(uri, null,
						"deleted=? and display_name=?", new String[] { "0",
								name }, null);
				if (nameCursor != null&&nameCursor.getCount()>0) {
					nameCursor.moveToNext();
					// 获取主表ID
					int zbid = nameCursor.getInt(nameCursor
							.getColumnIndex(ContactsContract.Contacts._ID));
					// 更新现在联系人
					Contact contact=updateContact(new Contact(zbid, name, email, tel, phone));
					
					// 更新列表数据、并刷新
					for (int j = 0; j < contacts.size(); j++) {
						Contact t = contacts.get(j);
						if (t.getName() != null
								&& t.getName()
										.equals(contact.getName())) {
							contacts.set(j, contact);
							break;
						}
					}
				} else {
					// 增加联系人
					Contact contact=addContact(new Contact(id, name, email, tel, phone));
					//加入list列表
					contacts.add(contact);
				}
			}
			adapter.notifyDataSetChanged();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 更新现有联系人
	 * 
	 * @param contact
	 */
	private Contact updateContact(Contact contact) {
		int zbid = contact.getId();

		uri = ContactsContract.AUTHORITY_URI;
		uri = Uri.withAppendedPath(uri, "data");
		// 插入邮箱
		ContentValues subvalues = new ContentValues();
		subvalues.put("data1", contact.getEmail());
		int emailResult = cr.update(uri, subvalues,
				"raw_contact_id=? and mimetype=?", new String[] { zbid + "",
						"vnd.android.cursor.item/email_v2" });
		Log.i(TAG, "emailResult:" + emailResult);
		// 等于说明没有对应数据，那么就插入
		if (emailResult == 0) {
			subvalues.put("raw_contact_id", zbid);
			subvalues.put("mimetype", "vnd.android.cursor.item/email_v2");
			cr.insert(uri, subvalues);
		}
		// 插入电话
		subvalues = new ContentValues();
		subvalues.put("data1", contact.getTel());
		int telResult = cr
				.update(uri, subvalues,
						"raw_contact_id=? and mimetype=? and data2=?",
						new String[] { zbid + "",
								"vnd.android.cursor.item/phone_v2", 1 + "" });
		Log.i(TAG, "telResult:" + telResult);
		// 等于说明没有对应数据，那么就插入
		if (telResult == 0) {
			subvalues.put("raw_contact_id", zbid);
			subvalues.put("mimetype", "vnd.android.cursor.item/phone_v2");
			subvalues.put("data2", 1);
			cr.insert(uri, subvalues);
		}
		// 插入手机
		subvalues = new ContentValues();
		subvalues.put("data1", contact.getPhone());
		int phoneResult = cr
				.update(uri, subvalues,
						"raw_contact_id=? and mimetype=? and data2=?",
						new String[] { zbid + "",
								"vnd.android.cursor.item/phone_v2", 2 + "" });
		Log.i(TAG, "phoneResult:" + phoneResult);
		// 等于说明没有对应数据，那么就插入
		if (phoneResult == 0) {
			subvalues.put("raw_contact_id", zbid);
			subvalues.put("mimetype", "vnd.android.cursor.item/phone_v2");
			subvalues.put("data2", 2);
			cr.insert(uri, subvalues);
		}
		// 插入联系人名称
		subvalues = new ContentValues();
		subvalues.put("data1", contact.getName());
		int nameResult = cr.update(uri, subvalues,
				"raw_contact_id=? and mimetype=?", new String[] { zbid + "",
						"vnd.android.cursor.item/name" });
		Log.i(TAG, "nameResult:" + nameResult);
		// 等于说明没有对应数据，那么就插入
		if (nameResult == 0) {
			subvalues.put("raw_contact_id", zbid);
			subvalues.put("mimetype", "vnd.android.cursor.item/name");
			cr.insert(uri, subvalues);
		}
		return contact;
	}

	private Contact addContact(Contact contact) {
		// 插入主表
		uri = ContactsContract.AUTHORITY_URI;
		uri = Uri.withAppendedPath(uri, "raw_contacts");

		ContentValues values = new ContentValues();
		uri = cr.insert(uri, values);
		// 获取新增的主表ID
		long zbid = ContentUris.parseId(uri);
		contact.setId((int) zbid);

		uri = ContactsContract.AUTHORITY_URI;
		uri = Uri.withAppendedPath(uri, "data");
		// 插入邮箱
		ContentValues subvalues = new ContentValues();
		subvalues.put("raw_contact_id", zbid);
		subvalues.put("data1", contact.getEmail());
		subvalues.put("mimetype", "vnd.android.cursor.item/email_v2");
		cr.insert(uri, subvalues);
		// 插入电话
		subvalues = new ContentValues();
		subvalues.put("raw_contact_id", zbid);
		subvalues.put("data1", contact.getTel());
		subvalues.put("data2", 1);
		subvalues.put("mimetype", "vnd.android.cursor.item/phone_v2");
		cr.insert(uri, subvalues);

		// 插入手机
		subvalues = new ContentValues();
		subvalues.put("raw_contact_id", zbid);
		subvalues.put("data1", contact.getPhone());
		subvalues.put("data2", 2);
		subvalues.put("mimetype", "vnd.android.cursor.item/phone_v2");
		cr.insert(uri, subvalues);

		// 插入联系人名称
		subvalues = new ContentValues();
		subvalues.put("raw_contact_id", zbid);
		subvalues.put("data1", contact.getName());
		subvalues.put("mimetype", "vnd.android.cursor.item/name");
		cr.insert(uri, subvalues);

		return contact;
	}
}
