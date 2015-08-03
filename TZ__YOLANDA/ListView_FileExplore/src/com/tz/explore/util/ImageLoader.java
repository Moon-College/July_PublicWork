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
package com.tz.explore.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.tz.explore.bean.Messenger;
import com.tz.explore.bean.Messenger.ImageCallback;

/**
 * Created in Aug 2, 2015 4:54:56 PM
 * 
 * @author YOLANDA
 */
public class ImageLoader implements ImageCallback {

	private static ImageLoader _ImageLoader;

	private LruCache<String, Bitmap> cache;

	private ImageLoader() {
		TaskPoster.initPoster();
		int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);

		cache = new LruCache<String, Bitmap>(maxSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight() / 1024;
			}
		};
	}

	public static ImageLoader getInstance() {
		if (_ImageLoader == null) {
			_ImageLoader = new ImageLoader();
		}
		return _ImageLoader;
	}

	public void loadImage(ImageView imageView, String path) {
		Bitmap bitmap = cache.get(path);
		if (bitmap == null) {
			Messenger messenger = new Messenger(imageView, path, imageView.getWidth(), this);
			TaskPoster poster = new TaskPoster(messenger);
			poster.execute();
		} else {
			imageView.setImageBitmap(bitmap);
		}
	}

	@Override
	public void callback(Messenger messenger) {
		cache.put(messenger.getImgPath(), messenger.getmBitmap());
	}

}
