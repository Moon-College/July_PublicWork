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
 * ���������ṩ�ߣ�����ϵ�˽���������ɾ�������ݡ���ԭ�����ݡ���ԭ��������������
 * 
 * @author zhao_jx
 * 
 */
public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private ContentResolver cr;// �����ṩ��
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

		// ��ʼ���ؼ�
		initView();
		// ��ʼ������
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
		// ��ȡ��ϵ������
		Cursor zbCursor = cr.query(uri, null, "deleted=?",
				new String[] { "0" }, null);
		while (zbCursor.moveToNext()) {
			Contact contact = new Contact();

			int zbId = zbCursor.getInt(zbCursor
					.getColumnIndex(ContactsContract.Contacts._ID));

			contact.setId(zbId);
			uri = ContactsContract.AUTHORITY_URI;
			uri = Uri.withAppendedPath(uri, "data");
			// ��������ID��ѯ�ӱ�
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
		// ����ɾ����Ӧ��ϵ��
		lv_contacts.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// ɾ����ϵ��
				int delCount = deleteContact(contacts.get(position));
				if (delCount > 0) {
					Toast.makeText(MainActivity.this, "ɾ���ɹ�", 1).show();
					contacts.remove(position);
					adapter.notifyDataSetChanged();
				} else {
					Toast.makeText(MainActivity.this, "ɾ��ʧ��", 1).show();
				}
				return false;
			}
		});

		// �����ϵ�˽�����Ϣ��ʾ������ı༭����
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
	 * ɾ����ϵ��
	 * 
	 * @param contact
	 *            Ҫɾ������ϵ��
	 * @return ɾ��������
	 */
	protected int deleteContact(Contact contact) {
		uri = ContactsContract.AUTHORITY_URI;
		uri = Uri.withAppendedPath(uri, "raw_contacts");
		// ɾ������
		cr.delete(uri, ContactsContract.Contacts._ID + "=?",
				new String[] { contact.getId() + "" });

		uri = ContactsContract.AUTHORITY_URI;
		uri = Uri.withAppendedPath(uri, "data");
		// ɾ���ӱ�
		int delCount = cr.delete(uri, "raw_contact_id=?",
				new String[] { contact.getId() + "" });
		return delCount;
	}

	/**
	 * ������ϵ��
	 * 
	 * @param v
	 */
	public void insertContact(View v) {
		final String name = et_name.getText().toString();
		final String email = et_email.getText().toString();
		final String phone = et_phone.getText().toString();
		final String tel = et_tel.getText().toString();

		// ��ѯ��û�ж�Ӧ��ϵ��
		uri = ContactsContract.AUTHORITY_URI;
		uri = Uri.withAppendedPath(uri, "raw_contacts");
		Cursor nameCursor = cr.query(uri, null, "deleted=? and display_name=?",
				new String[] { 0 + "", name }, null);
		Log.i(TAG, "nameCursor:" + nameCursor);
		if (nameCursor != null && nameCursor.getCount() > 0) {

			nameCursor.moveToNext();
			// ��ȡ����ID
			final int zbid = nameCursor.getInt(nameCursor
					.getColumnIndex(ContactsContract.Contacts._ID));
			AlertDialog.Builder builder = new Builder(this);
			builder.setTitle("����");
			builder.setMessage("�Ѵ��ڸ���ϵ���Ƿ����");
			builder.setPositiveButton("ȷ��",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							// ����������ϵ��
							Contact contact = updateContact(new Contact(zbid,
									name, email, tel, phone));
							for (int i = 0; i < contacts.size(); i++) {
								Contact t = contacts.get(i);
								// �����б����ݡ���ˢ��
								if (t.getName() != null
										&& t.getName()
												.equals(contact.getName())) {
									contacts.set(i, contact);
									adapter.notifyDataSetChanged();
									break;
								}
							}
							Toast.makeText(MainActivity.this, "���³ɹ�", 1).show();
						}
					});

			builder.setNegativeButton("ȡ��",
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
				Toast.makeText(this, "����ɹ�", 1).show();
			}
		}
	}

	/**
	 * ������ϵ�˵�Զ�̷�����
	 * 
	 * @param v
	 */
	public void backup(View v) {
		try {
			// ����ϵ��ת����JSON
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

			// post��ʽ��������
			httpUtil.doAsyncPostByHttpURLConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��Զ�̷������ָ���ϵ��
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
							Toast.makeText(MainActivity.this, "�ָ��ɹ�", 1).show();
						}

						@Override
						public void failed(String result) {
							Toast.makeText(MainActivity.this, result, 1).show();
						}
					});

			// post��ʽ��������
			httpUtil.doAsyncGetByHttpURLConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �ָ���ϵ�ˣ��ȸ��¡��ٲ���
	 * 
	 * @param json
	 */
	protected void recoverContacts(String json) {
		try {
			JSONArray jsonArray = new JSONArray(json);

			/**************************/
			// uri = ContactsContract.AUTHORITY_URI;
			// uri=Uri.withAppendedPath(uri, "raw_contacts");
			// //ɾ����������
			// int zbdelCount=cr.delete(uri, null, null);
			//
			// uri = ContactsContract.AUTHORITY_URI;
			// uri=Uri.withAppendedPath(uri, "data");
			// //��������IDɾ���ӱ�
			// cr.delete(uri, null,null);
			/*******************************/
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonO = (JSONObject) jsonArray.get(i);
				int id = jsonO.getInt("id");
				String name = jsonO.getString("name");
				String email = jsonO.getString("email");
				String phone = jsonO.getString("phone");
				String tel = jsonO.getString("tel");
				// ����������ѯ���ֻ�����ϵ��
				uri = ContactsContract.AUTHORITY_URI;
				uri = Uri.withAppendedPath(uri, "raw_contacts");

				// ��ѯ��û�ж�Ӧ��ϵ��
				Cursor nameCursor = cr.query(uri, null,
						"deleted=? and display_name=?", new String[] { "0",
								name }, null);
				if (nameCursor != null&&nameCursor.getCount()>0) {
					nameCursor.moveToNext();
					// ��ȡ����ID
					int zbid = nameCursor.getInt(nameCursor
							.getColumnIndex(ContactsContract.Contacts._ID));
					// ����������ϵ��
					Contact contact=updateContact(new Contact(zbid, name, email, tel, phone));
					
					// �����б����ݡ���ˢ��
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
					// ������ϵ��
					Contact contact=addContact(new Contact(id, name, email, tel, phone));
					//����list�б�
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
	 * ����������ϵ��
	 * 
	 * @param contact
	 */
	private Contact updateContact(Contact contact) {
		int zbid = contact.getId();

		uri = ContactsContract.AUTHORITY_URI;
		uri = Uri.withAppendedPath(uri, "data");
		// ��������
		ContentValues subvalues = new ContentValues();
		subvalues.put("data1", contact.getEmail());
		int emailResult = cr.update(uri, subvalues,
				"raw_contact_id=? and mimetype=?", new String[] { zbid + "",
						"vnd.android.cursor.item/email_v2" });
		Log.i(TAG, "emailResult:" + emailResult);
		// ����˵��û�ж�Ӧ���ݣ���ô�Ͳ���
		if (emailResult == 0) {
			subvalues.put("raw_contact_id", zbid);
			subvalues.put("mimetype", "vnd.android.cursor.item/email_v2");
			cr.insert(uri, subvalues);
		}
		// ����绰
		subvalues = new ContentValues();
		subvalues.put("data1", contact.getTel());
		int telResult = cr
				.update(uri, subvalues,
						"raw_contact_id=? and mimetype=? and data2=?",
						new String[] { zbid + "",
								"vnd.android.cursor.item/phone_v2", 1 + "" });
		Log.i(TAG, "telResult:" + telResult);
		// ����˵��û�ж�Ӧ���ݣ���ô�Ͳ���
		if (telResult == 0) {
			subvalues.put("raw_contact_id", zbid);
			subvalues.put("mimetype", "vnd.android.cursor.item/phone_v2");
			subvalues.put("data2", 1);
			cr.insert(uri, subvalues);
		}
		// �����ֻ�
		subvalues = new ContentValues();
		subvalues.put("data1", contact.getPhone());
		int phoneResult = cr
				.update(uri, subvalues,
						"raw_contact_id=? and mimetype=? and data2=?",
						new String[] { zbid + "",
								"vnd.android.cursor.item/phone_v2", 2 + "" });
		Log.i(TAG, "phoneResult:" + phoneResult);
		// ����˵��û�ж�Ӧ���ݣ���ô�Ͳ���
		if (phoneResult == 0) {
			subvalues.put("raw_contact_id", zbid);
			subvalues.put("mimetype", "vnd.android.cursor.item/phone_v2");
			subvalues.put("data2", 2);
			cr.insert(uri, subvalues);
		}
		// ������ϵ������
		subvalues = new ContentValues();
		subvalues.put("data1", contact.getName());
		int nameResult = cr.update(uri, subvalues,
				"raw_contact_id=? and mimetype=?", new String[] { zbid + "",
						"vnd.android.cursor.item/name" });
		Log.i(TAG, "nameResult:" + nameResult);
		// ����˵��û�ж�Ӧ���ݣ���ô�Ͳ���
		if (nameResult == 0) {
			subvalues.put("raw_contact_id", zbid);
			subvalues.put("mimetype", "vnd.android.cursor.item/name");
			cr.insert(uri, subvalues);
		}
		return contact;
	}

	private Contact addContact(Contact contact) {
		// ��������
		uri = ContactsContract.AUTHORITY_URI;
		uri = Uri.withAppendedPath(uri, "raw_contacts");

		ContentValues values = new ContentValues();
		uri = cr.insert(uri, values);
		// ��ȡ����������ID
		long zbid = ContentUris.parseId(uri);
		contact.setId((int) zbid);

		uri = ContactsContract.AUTHORITY_URI;
		uri = Uri.withAppendedPath(uri, "data");
		// ��������
		ContentValues subvalues = new ContentValues();
		subvalues.put("raw_contact_id", zbid);
		subvalues.put("data1", contact.getEmail());
		subvalues.put("mimetype", "vnd.android.cursor.item/email_v2");
		cr.insert(uri, subvalues);
		// ����绰
		subvalues = new ContentValues();
		subvalues.put("raw_contact_id", zbid);
		subvalues.put("data1", contact.getTel());
		subvalues.put("data2", 1);
		subvalues.put("mimetype", "vnd.android.cursor.item/phone_v2");
		cr.insert(uri, subvalues);

		// �����ֻ�
		subvalues = new ContentValues();
		subvalues.put("raw_contact_id", zbid);
		subvalues.put("data1", contact.getPhone());
		subvalues.put("data2", 2);
		subvalues.put("mimetype", "vnd.android.cursor.item/phone_v2");
		cr.insert(uri, subvalues);

		// ������ϵ������
		subvalues = new ContentValues();
		subvalues.put("raw_contact_id", zbid);
		subvalues.put("data1", contact.getName());
		subvalues.put("mimetype", "vnd.android.cursor.item/name");
		cr.insert(uri, subvalues);

		return contact;
	}
}
