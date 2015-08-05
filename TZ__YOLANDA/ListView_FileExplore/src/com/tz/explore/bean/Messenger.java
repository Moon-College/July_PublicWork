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
package com.tz.explore.bean;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.os.Message;
import android.widget.ImageView;

/**
 * Created in Aug 2, 2015 9:52:53 PM
 * 
 * @author YOLANDA
 */
public class Messenger implements Serializable {

	private static final long serialVersionUID = 1L;

	private ImageView mImageView;

	private String imgPath;

	private Bitmap mBitmap;

	private int width;

	private ImageCallback callback;

	public Messenger() {
		super();
	}

	public Messenger(ImageView mImageView, String imgPath, int width, ImageCallback callback) {
		super();
		this.mImageView = mImageView;
		this.imgPath = imgPath;
		this.width = width;
		this.callback = callback;
	}

	public ImageView getmImageView() {
		return mImageView;
	}

	public void setmImageView(ImageView mImageView) {
		this.mImageView = mImageView;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Bitmap getmBitmap() {
		return mBitmap;
	}

	public void setmBitmap(Bitmap mBitmap) {
		this.mBitmap = mBitmap;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public ImageCallback getCallback() {
		return callback;
	}

	public void setCallback(ImageCallback callback) {
		this.callback = callback;
	}

	public Message obtain() {
		Message message = Message.obtain();
		message.obj = this;
		return message;
	}

	public void callback() {
		this.mImageView.setImageBitmap(mBitmap);
		if (callback != null) {
			callback.callback(this);
		}
	}

	public interface ImageCallback {
		public void callback(Messenger messenger);
	}

}
