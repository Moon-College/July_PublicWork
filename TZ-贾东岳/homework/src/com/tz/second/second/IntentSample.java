package com.tz.second.second;

import android.content.Intent;
import android.net.Uri;

import com.tz.main.MyApplication;

/**
 * 第二次课第二个作业，intent的使用. 此类下面注释代码块中有常见的intent的用法
 * 
 * @author JDY
 * 
 */
public class IntentSample {

	/**
	 * 调用intent呼叫指定的电话号码。
	 * 要使用这个必须在配置文件中加入<uses-permission android:name="android.permission.CALL_PHONE"/>.
	 * @param telNum 想要呼叫的电话号码.
	 */
	public void makeAPhoneCall(String telNum) {
		// 呼叫指定的电话号码。
		//Uri.parse("tel:10086"))这里获得的是电话号码。
		Uri uri1 = Uri.parse("tel:"+telNum);
		Intent intent = new Intent(Intent.ACTION_DIAL, uri1);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK ); 
		MyApplication.appContext().startActivity(intent);
	}
	/**
	 * 调用拨号面板：
	 */
	public void callDialPanel(String telNum) { 
		// 这里创建了一个电话号码输入界面的意图，Uri.parse("tel:10086"))这里获得的是电话号码。
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+telNum));
		// 这里设置启动Flag为新任务。
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// 这里就可以开启任务了。
		MyApplication.appContext().startActivity(intent);
	}

	// /**
	// * 显示网页
	// */
	// public void displayPage(){
	// Uri uri = Uri.parse("http://www.google.com");
	// Intent it = new Intent(Intent.ACTION_VIEW, uri);
	// MyApplication.appContext().startActivity(it);
	// }
	// // 显示地图:
	// Uri uri = Uri.parse("geo:38.899533,-77.036476");
	// Intent it = new Intent(Intent.Action_VIEW, uri);
	// startActivity(it);
	//
	// // 路径规划:
	// Uri uri = Uri
	// .parse("http://maps.google.com/maps?f=d&saddr=startLat%20startLng&daddr=endLat%20endLng&hl=en");
	// Intent it = new Intent(Intent.ACTION_VIEW, URI);
	// startActivity(it);
	//
	// // 拨打电话:
	// // 调用拨号面板：
	// Uri uri = Uri.parse("tel:xxxxxx");
	// Intent it = new Intent(Intent.ACTION_DIAL, uri);
	// startActivity(it);
	// // 呼叫指定的电话号码。
	// Uri uri = Uri.parse("tel.xxxxxx");
	// Intent it = new Intent(Intent.ACTION_CALL, uri);
	// // 要使用这个必须在配置文件中加入<uses-permission android:name="android.permission.CALL_PHONE"/>
	//
	// // 发送SMS/MMS
	// // 调用发送短信的程序
	//
	// Intent it = new Intent(Intent.ACTION_VIEW);
	// it.putExtra("sms_body", "The SMS text");
	// it.setType("vnd.android-dir/mms-sms");
	// startActivity(it);
	//
	// // 发送短信
	// Uri uri = Uri.parse("smsto:0800000123");
	// Intent it = new Intent(Intent.ACTION_SENDTO, uri);
	// it.putExtra("sms_body", "The SMS text");
	// startActivity(it);
	//
	// // 发送彩信
	// Uri uri = Uri.parse("content://media/external/images/media/23");
	// Intent it = new Intent(Intent.ACTION_SEND);
	// it.putExtra("sms_body", "some text");
	// it.putExtra(Intent.EXTRA_STREAM, uri);
	// it.setType("image/png");
	// startActivity(it);
	//
	// // 发送Email
	// Uri uri = Uri.parse("mailto:xxx@abc.com");
	// Intent it = new Intent(Intent.ACTION_SENDTO, uri);
	// startActivity(it);
	// Intent it = new Intent(Intent.ACTION_SEND);
	// it.putExtra(Intent.EXTRA_EMAIL, "me@abc.com");
	// it.putExtra(Intent.EXTRA_TEXT, "The email body text");
	// it.setType("text/plain");
	// startActivity(Intent.createChooser(it, "Choose Email Client"));
	// Intent it = new Intent(Intent.ACTION_SEND);
	// String[] tos = { "me@abc.com" };
	// String[] ccs = { "you@abc.com" };
	// it.putExtra(Intent.EXTRA_EMAIL, tos);
	// it.putExtra(Intent.EXTRA_CC, ccs);
	// it.putExtra(Intent.EXTRA_TEXT, "The email body text");
	// it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
	// it.setType("message/rfc822");
	// startActivity(Intent.createChooser(it, "Choose Email Client"));
	//
	// // 添加附件
	// Intent it = new Intent(Intent.ACTION_SEND);
	// it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
	// it.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/mysong.mp3");
	// sendIntent.setType("audio/mp3");
	// startActivity(Intent.createChooser(it, "Choose Email Client"));
	//
	// // 播放多媒体
	// Intent it = new Intent(Intent.ACTION_VIEW);
	// Uri uri = Uri.parse("file:///sdcard/song.mp3");
	// it.setDataAndType(uri, "audio/mp3");
	// startActivity(it);
	// Uri uri = Uri.withAppendedPath(
	// MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
	// Intent it = new Intent(Intent.ACTION_VIEW, uri);
	// startActivity(it);
	//
	// // Uninstall 程序
	// Uri uri = Uri.fromParts("package", strPackageName, null);
	// Intent it = new Intent(Intent.ACTION_DELETE, uri);
	// startActivity(it);
	//
	// // Install　程序
	// Uri installUri = Uri.fromParts("package", "xxx", null);
	// returnIt = new Intent(Intent.ACTION_PACKAGE_ADDED, installUri);
	//
	// 打开另一程序
	// Intent i = new Intent();
	// ComponentName cn = new ComponentName("com.drip.android2",
	// "com.drip.android2.AndroidSearch");
	// i.setComponent(cn);
	// i.setAction("android.intent.action.MAIN");
	// startActivityForResult(i, RESULT_OK);
	//
	// 打开联系人列表
	// <1>
	// Intent i = new Intent();
	// i.setAction(Intent.ACTION_GET_CONTENT);
	// i.setType("vnd.android.cursor.item/phone");
	// startActivityForResult(i, REQUEST_TEXT);
	//
	// <2>
	// Uri uri = Uri.parse("content://contacts/people");
	// Intent it = new Intent(Intent.ACTION_PICK, uri);
	// startActivityForResult(it, REQUEST_TEXT);
	// 打开录音机
	// Intent mi = new Intent(Media.RECORD_SOUND_ACTION);
	// startActivity(mi);
	//
	// 从gallery选取图片
	// Inten t i = new Intent();
	// i.setType("image/*");
	// i.setAction(Intent.ACTION_GET_CONTENT);
	// startActivityForResult(i, 11);
	//
	// 打开照相机
	// <1>Intent i = new Intent(Intent.ACTION_CAMERA_BUTTON, null);
	// this.sendBroadcast(i);
	// <2>long dateTaken = System.currentTimeMillis();
	// String name = createName(dateTaken) + ".jpg";
	// fileName = folder + name;
	// ContentValues values = new ContentValues();
	// values.put(Images.Media.TITLE, fileName);
	// values.put("_data", fileName);
	// values.put(Images.Media.PICASA_ID, fileName);
	// values.put(Images.Media.DISPLAY_NAME, fileName);
	// values.put(Images.Media.DESCRIPTION, fileName);
	// values.put(Images.ImageColumns.BUCKET_DISPLAY_NAME, fileName);
	// Uri photoUri = getContentResolver().insert(
	// MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
	//
	// Intent inttPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	// inttPhoto.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
	// startActivityForResult(inttPhoto, 10);
	//
	// 播放多媒体
	// Intent it = new Intent(Intent.ACTION_VIEW);
	// Uri uri = Uri.parse("file:///sdcard/song.mp3");
	// it.setDataAndType(uri, "audio/mp3");
	// startActivity(it);
	//
	// 或者
	// Uri uri =
	// Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
	// Intent it = new Intent(Intent.ACTION_VIEW, uri);
	// startActivity(it);
	//
	// 或者
	// Uri playUri = Uri.parse("file:///sdcard/download/everything.mp3");
	// returnIt = new Intent(Intent.ACTION_VIEW, playUri);
	// 发送Email
	// Uri uri = Uri.parse("mailto:xxx@abc.com");
	// Intent it = new Intent(Intent.ACTION_SENDTO, uri);
	// startActivity(it);
	//
	// 或者:
	// Intent intent = new Intent(Intent.ACTION_SEND);
	// intent.putExtra(Intent.EXTRA_EMAIL, address);
	// intent.putExtra(Intent.EXTRA_SUBJECT, filename);
	// intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + filename)); ;
	// intent.setType("text/csv");
	// startActivity(Intent.createChooser(intent, "EMail File"));
	//
	// 或者:
	// Intent it = new Intent(Intent.ACTION_SEND);
	// it.putExtra(Intent.EXTRA_EMAIL, "me@abc.com");
	// it.putExtra(Intent.EXTRA_TEXT, "The email body text");
	// it.setType("text/plain");
	// startActivity(Intent.createChooser(it, "Choose Email Client"));
	//
	// 或者:
	// Intent it=new Intent(Intent.ACTION_SEND);
	// String[] tos={"me@abc.com"};
	// String[] ccs={"you@abc.com"};
	// it.putExtra(Intent.EXTRA_EMAIL, tos);
	// it.putExtra(Intent.EXTRA_CC, ccs);
	// it.putExtra(Intent.EXTRA_TEXT, "The email body text");
	// it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
	// it.setType("message/rfc822");
	// startActivity(Intent.createChooser(it, "Choose Email Client"));
	//
	// 或者:
	// Intent it = new Intent(Intent.ACTION_SEND);
	// it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
	// it.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/mysong.mp3");
	// sendIntent.setType("audio/mp3");
	// startActivity(Intent.createChooser(it, "Choose Email Client"));
	//
	// 后台发送短信
	// 注册权限
	// <uses-permission android:name="android.permission.SEND_SMS" />
	// 代码实现 :
	// // 获取信息内容
	// String message ;
	// // 移动运营商允许每次发送的字节数据有限，我们可以使用Android给我们提供 的短信工具。
	// if (message != null) {
	// SmsManager sms = SmsManager.getDefault();
	// // 如果短信没有超过限制长度，则返回一个长度的List。
	// List<String> texts = sms.divideMessage(message);
	// for (String text : texts) {
	// sms.sendTextMessage(“这里是接收者电话号码”, “这里是发送者电话号码”, “这里是短信内容”, null, null);
	// }
	// }
	// //说明
	// sms.sendTextMessage(destinationAddress, scAddress, text, sentIntent,
	// deliveryIntent)：
	// destinationAddress:接收方的手机号码
	// scAddress:发送方的手机号码
	// text:信息内容
	// sentIntent:发送是否成功的回执，
	// DeliveryIntent:接收是否成功的回执，
	//
	// 从google搜索内容
	// Intent intent = new Intent();
	// intent.setAction(Intent.ACTION_WEB_SEARCH);
	// intent.putExtra(SearchManager.QUERY,"searchString")
	// startActivity(intent);
	// // APK 安装
	// Intent intent =new Intenet();
	// String filepath="/SDCard/XXX.apk";
	//
	// intent.setDataAndType(Uri.parse("file://" +
	// filepath),"application/vnd.android.package-archive");
	// starActivity(intent);
	//
	// // Activity之间的传递
	// Intent intent=new Intent();
	// intent.setClass(MainActivity.this, SecondActivity.class);
	// startActivity(intent);
}
