package com.study.contactadapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.autotipphonenumber.R;
import com.study.remind.Contact;

public class ContactAdapter extends BaseAdapter implements Filterable{
	
	private Context context;
	private ArrayList<Contact> Datas;
	private LayoutInflater inflater;
	
	public ContactAdapter(Context context,ArrayList<Contact> Datas){
		this.context=context;
		this.Datas=Datas;	
		//����inflater�����ַ�ʽ
		//inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return Datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View root = inflater.inflate(R.layout.contcat,null);
		
		TextView name=(TextView) root.findViewById(R.id.name);
		TextView phoneNum=(TextView) root.findViewById(R.id.phoneNum);
		
		name.setText(Datas.get(position).getName());
		phoneNum.setText(Datas.get(position).getPhoneNum());
		return root;
	}

	//ʵ��Filterabale�ӿ�
	@Override
	public Filter getFilter() {
		return new ContactFilter();
	}
	
	private class ContactFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			
			FilterResults results = new FilterResults();
			ArrayList<Contact> filterResult=new ArrayList<Contact>();
			
			
			//����ĸ����Ϲ�������
			for(int i=0;i<Datas.size();i++){
				if(Datas.get(i).getName().startsWith((String) constraint) 
						|| Datas.get(i).getPhoneNum().startsWith((String) constraint) ){
					filterResult.add(Datas.get(i));
				}
			}
			

            results.values = filterResult;
            results.count = filterResult.size();
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {

            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
			
		}
		
	}

}