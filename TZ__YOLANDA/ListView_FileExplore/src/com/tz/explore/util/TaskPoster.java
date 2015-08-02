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

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;

import com.tz.explore.bean.Messenger;

/**
 * Created in Aug 2, 2015 9:49:29 PM
 * 
 * @author YOLANDA
 */
public class TaskPoster implements Runnable {

	private static ThreadPoolExecutor threadExecutor;

	private static HandlerPoster handlerPoster;

	private Messenger mMessenger;

	public static void initPoster() {
		threadExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		handlerPoster = new HandlerPoster(Looper.getMainLooper());
	}

	public TaskPoster(Messenger messenger) {
		mMessenger = messenger;
	}

	public void execute() {
		threadExecutor.execute(this);
	}

	@Override
	public void run() {
		Bitmap bitmap = getBitmapFromSdCard(mMessenger.getImgPath(), mMessenger.getWidth());
		mMessenger.setmBitmap(bitmap);
		handlerPoster.sendMessage(mMessenger.obtain());
	}

	public static Bitmap getBitmapFromSdCard(String filePath, int reqWidth) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (width > reqWidth) {
			inSampleSize = Math.round((float) width / (float) reqWidth);
		}
		options.inSampleSize = inSampleSize;

		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}

}
