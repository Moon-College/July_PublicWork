package com.tz.contacts.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class HttpUtil<T> {
	public static final String TAG=HttpUtil.class.getName();
	
	private Context context;
	private String path;//�����ַ
	private T params;//����
	private String paramsStr;//����ƴ�Ӻ���ַ���
	private HttpCallBack callBack;//�ص�����
	public HttpUtil(Context context,String path,T params,HttpCallBack callBack){
		this.context=context;
		this.path=path;
		this.params=params;
		this.callBack=callBack;
		
		paramsStr=createParamsStr(params);
		Log.i(TAG, "paramsStr:"+paramsStr);
	}
	
	/**
	 * ����������ת���ַ��� T��֧�ּ̳�
	 * @param params
	 * @return
	 */
	public String createParamsStr(T params) {
		if(params==null){
			return "";
		}
		String result = "";
		try {
			Class tClazz=params.getClass();
			//��ȡ�����ж��������
			Field[] fields=tClazz.getDeclaredFields();
			if(fields!=null&&fields.length>0){
				result="";
				for (Field field : fields) {
					//�������Կ��Է���
					field.setAccessible(true);
					//��ȡ����ֵ
					Object value=field.get(params);
					if(value!=null&&!"".equals(value.toString())){
						String key=field.getName();
						result+=key+"="+value.toString()+"&";
					}
				}
				if(!result.equals("")){
					result=result.substring(0, result.length()-1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * �����첽����ִ��get��ʽ��ȡ������Դ
	 * @throws Exception
	 */
	public void doAsyncGetByHttpURLConnection() throws Exception {
		MyHttpTast tast=new MyHttpTast(context,"GET");
		tast.execute();
	}
	
	/**
	 * �����첽����ִ��post��ʽ��ȡ������Դ
	 * @throws Exception
	 */
	public void doAsyncPostByHttpURLConnection() throws Exception {
		MyHttpTast tast=new MyHttpTast(context,"POST");
		tast.execute();
	}
	
	/**
	 * get��ʽ��ȡ������Դ
	 * 
	 * @param path
	 *            URL·��
	 * @return
	 * @throws Exception
	 */
	private String doGetByHttpURLConnection(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// �������ӳ�ʱʱ��
		connection.setConnectTimeout(50000);
		// ��������ʽ
		connection.setRequestMethod("GET");
		
		connection.connect();
		// ��ȡ��Ӧ��
		int code = connection.getResponseCode();
		// �ܹ�������ȡ������Դ
		if (code == 200) {
			InputStream is=connection.getInputStream();
			byte[] buffer=new byte[1024];
			int len=0;
			//StringBuffer sb=new StringBuffer();
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			while((len=is.read(buffer))!=-1){
				//sb.append(new String(buffer,0,len));
				baos.write(buffer,0,len);
			}
			return baos.toString();
		}
		return null;
	}
	
	/**
	 * Post��ʽ��ȡ������Դ
	 * 
	 * @param path
	 *            URL·��
	 * @return
	 * @throws Exception
	 */
	public static String doPostByHttpURLConnection(String path,String paramStr) throws Exception {
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// �������ӳ�ʱʱ��
		connection.setConnectTimeout(5000);
		// ��������ʽ
		connection.setRequestMethod("POST");
		//����POST�������
		if(paramStr!=null&&!paramStr.equals("")){
			Log.i(TAG, "paramStr="+paramStr);
			PrintWriter pw=new PrintWriter(connection.getOutputStream());
			pw.write(paramStr);
			pw.flush();
		}
		// ��ȡ��Ӧ��
		int code = connection.getResponseCode();
		// �ܹ�������ȡ������Դ
		if (code == 200) {
			InputStream is = connection.getInputStream();
			byte[] buffer=new byte[1024];
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			int len=0;
			while((len=is.read(buffer))!=-1){
				baos.write(buffer, 0, len);
			}
			return baos.toString();
		}
		return null;
	}
	
class MyHttpTast extends AsyncTask<Void, Void, String>{
		
		Context context;
		String requestMethod;
		public MyHttpTast(Context context,String requestMethod){
			this.context=context;
			this.requestMethod=requestMethod;
		}
		
		private ProgressDialog dialog;

		/**
		 * ִ��ǰ
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(context);
			dialog.show();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			Log.i(TAG, "result:"+result);
			if(result!=null){
				callBack.success(result);
			}else{
				callBack.failed("�������������");
			}
		}

		@Override
		protected String doInBackground(Void... params) {
			String getResult=null;
			try {
				if("GET".equals(requestMethod)){
					getResult = doGetByHttpURLConnection(path+"?"+paramsStr);
				} else {
					getResult = doPostByHttpURLConnection(path,paramsStr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return getResult;
		}
		
	}

}
