/**
 * Copyright © YOLANDA. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tz.explore.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.explore.R;
import com.tz.explore.bean.Item;
import com.tz.explore.util.ImageLoader;

/**
 * Created in Aug 2, 2015 4:38:07 PM
 * 
 * @author YOLANDA
 */
public class MainAdapter extends BaseAdapter {

	private Context mContext;

	private List<Item> list;

	public MainAdapter(Context context, List<Item> items) {
		this.mContext = context;
		this.list = items;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Item getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item, parent, false);
			holder.imageView = (ImageView) convertView.findViewById(R.id.iv_icon);
			holder.textView = (TextView) convertView.findViewById(R.id.tv_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Item item = getItem(position);
		Bitmap icon = item.getIcon();
		if (icon == null) {
			ImageLoader.getInstance().loadImage(holder.imageView, item.getPath());
		} else {
			holder.imageView.setImageBitmap(icon);
		}
		holder.textView.setText(item.getName());
		return convertView;
	}

	class ViewHolder {
		private ImageView imageView;
		private TextView textView;
	}

}
