package com.tz.contacts;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tz.contacts.bean.Contact;

public class ContactAdapter extends BaseAdapter {

	private List<Contact> contacts;
	private Context context;
	
	public ContactAdapter(List<Contact> contacts, Context context) {
		super();
		this.contacts = contacts;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return contacts.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return contacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Contact contact=contacts.get(position);
		View v=LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
		TextView contactTv=(TextView) v.findViewById(android.R.id.text1);
		contactTv.setText(contact.toString());
		return v;
	}

}
