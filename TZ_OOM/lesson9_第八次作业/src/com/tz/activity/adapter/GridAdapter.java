package com.tz.activity.adapter;

import java.util.List;
import java.util.Map;

import com.tz.activity.R;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
	
	private Context mContext;
	private int mResourceId;
	private List<Map<String, Object>> mDataSource;
	private String[] mFrom;
	private int[] mTo;
	
	private LayoutInflater mInflater;
	/**
	 * 
	 * @param context 上下文环境
	 * @param layoutResId 
	 * @param dataSource
	 * @param form
	 * @param to
	 */
	public GridAdapter(Context context, int layoutResId, List<Map<String, Object>> dataSource, String[] form, int[] to) {
		this.mContext = context;
		this.mResourceId = layoutResId;
		this.mDataSource = dataSource;
		this.mFrom = form;
		this.mTo = to;
		this.mInflater = LayoutInflater.from(context);
		
	}
	
	@Override
	public int getCount() {
		return this.mDataSource.size();
	}

	@Override
	public Map<String,Object> getItem(int position) {
		return this.mDataSource.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if(convertView == null) {
			view = this.mInflater.inflate(mResourceId, null);
			
		}else {
			view = convertView;
		}
		
		bindView(position, view);
		
		
		return view;
	}

	private void bindView(int position, View view) {
		final Map<String,Object> dataSet = mDataSource.get(position);
        if (dataSet == null) {
            return;
        }
		
		 final String[] from = mFrom;
	     final int[] to = mTo;
	     final int count = to.length;
		for(int i = 0 ; i < count ; i++) {
			 final View v = view.findViewById(to[i]);
			 if (v != null) {
	                final Object data = dataSet.get(from[i]);
	                String text = data == null ? "" : data.toString();
	                if (text == null) {
	                    text = "";
	                }

	               // boolean bound = false;
	                /*if (binder != null) {
	                    bound = binder.setViewValue(v, data, text);
	                }*/

	                if (v instanceof Checkable) {
                        if (data instanceof Boolean) {
                            ((Checkable) v).setChecked((Boolean) data);
                        } else if (v instanceof TextView) {
                            setViewText((TextView) v, text);
                        } else {
                            throw new IllegalStateException(v.getClass().getName() +
                                    " should be bound to a Boolean, not a " +
                                    (data == null ? "<unknown type>" : data.getClass()));
                        }
                    } else if (v instanceof TextView) {
                        setViewText((TextView) v, text);
                    } else if (v instanceof ImageView) {
                        if (data instanceof Integer) {
                            setViewImage((ImageView) v, (Integer) data);                            
                        } else {
                            setViewImage((ImageView) v, text);
                        }
                    } else {
                        throw new IllegalStateException(v.getClass().getName() + " is not a " +
                                " view that can be bounds by this SimpleAdapter");
                    } 
	            }
			 
		}
	}

	
	public void setViewText(TextView v, String text) {
        v.setText(text);
    }

	public void setViewImage(ImageView v, int value) {
        v.setImageResource(value);
    }

	
	 public void setViewImage(ImageView v, String value) {
	        try {
	            v.setImageResource(Integer.parseInt(value));
	        } catch (NumberFormatException nfe) {
	            v.setImageURI(Uri.parse(value));
	        }
	    }
}
