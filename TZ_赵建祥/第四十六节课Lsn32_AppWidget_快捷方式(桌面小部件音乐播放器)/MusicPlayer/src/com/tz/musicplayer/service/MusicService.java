package com.tz.musicplayer.service;

import java.io.File;
import java.io.IOException;

import com.tz.music.inter.IMusic;
import com.tz.music.inter.bean.Music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service implements OnCompletionListener {

	public static final String ROOT_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/Download/";
	public static final String TAG = "MusicService";
	private MediaPlayer mp;

	private String[] musicArray = null;

	// 音乐文件索引
	private int musicIndex = -1;
	private MusicBinder binder;
	
	//播放模式
	public static int musicPlayModel=0;
	
	public static final int PLAYMODEL_ORDER=0;//顺序
	public static final int PLAYMODEL_RANDOM=1;//随机
	public static final int PLAYMODEL_LOOP=2;//循环
	public static final int PLAYMODEL_SINGLE=3;//单曲
	
	public static final int PLAYDIRECT_PREVIOUS=0;//上一首
	public static final int PLAYDIRECT_NEXT=1;//下一首
	
	

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i(TAG, "onCreate");
		getDownLoadMusic();
		mp = new MediaPlayer();
		mp.setOnCompletionListener(this);
		if (musicIndex >= 0 && musicIndex < musicArray.length) {
			// 重置
			mp.reset();
			// 准备资源
			String path = ROOT_PATH + musicArray[musicIndex];
			Log.i(TAG, "music path=" + path);
			try {
				mp.setDataSource(path);
				mp.prepare();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取下载的音乐
	 */
	private void getDownLoadMusic() {
		if (musicArray != null) {
			musicArray = null;
		}
		File rootFile = new File(ROOT_PATH);
		if (rootFile != null && rootFile.isDirectory()) {
			musicArray = rootFile.list();
			if (musicArray != null && musicArray.length > 0) {
				// 默认第一首哥
				musicIndex = 0;
			}
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onBind");
		binder = new MusicBinder();
		return binder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.i(TAG, "onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy");
		if (mp.isPlaying()) {
			mp.stop();
		}
	}

	public class MusicBinder extends  IMusic.Stub{

		/**
		 * 播放
		 */
		public Music play() {
			Music music=new Music();
			if (mp != null) {
				if (!mp.isPlaying()) {
					mp.start();
				}
			}
			music.setTitle("本草纲目");
			music.setSinger("周杰伦");
			return music;
		}

		/**
		 * 停止，再点击播放时重新播放
		 */
		public void stop() {
			if (mp != null) {
				if (mp.isPlaying()) {
					mp.stop();
					try {
						mp.prepare();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mp.seekTo(0);
				}
			}
		}

		/**
		 * 暂停，再点击播放时接着播放
		 */
		public void pause() {
			if (mp.isPlaying()) {
				mp.pause();
			}
		}
		/**
		 * 下一首
		 * @param direction   方向  0上一首 1下一首
		 */
		public void next(int direction) {
			Log.i(TAG, "musicPlayModel:"+musicPlayModel);
			switch (musicPlayModel) {
			case 0:
				//0 顺序
				if (mp != null) {
					int tempIndex=(direction==0)?musicIndex-1:musicIndex+1;
					if (tempIndex >= 0 && tempIndex < musicArray.length) {
						musicIndex=tempIndex;
						beginPlay();
					}
					
				}
				break;
			case 1:
				//随机播放 与方向无关
				if (mp != null) {
					musicIndex=(int)(Math.random()*musicArray.length);
					beginPlay();
				}
				break;
			case 2:
				//2循环 
				if (mp != null) {
					if(direction==0){
						//上一首
						musicIndex--;
						if (musicIndex <0) {
							musicIndex = musicArray.length-1;
						}
					}else {
						//下一首
						musicIndex++;
						if (musicIndex >= musicArray.length) {
							musicIndex = 0;
						}
					}
					beginPlay();
				}
				break;
			case 3:
				binder.play();
				break;

			default:
				break;
			}
		}
		
		/**
		 * 准备播放
		 */
		private void beginPlay(){
			if (musicIndex >= 0 && musicIndex < musicArray.length) {
				// 重置
				mp.reset();
				// 准备资源
				String path = ROOT_PATH + musicArray[musicIndex];
				Log.i(TAG, "music path=" + path);
				try {
					mp.setDataSource(path);
					mp.prepare();
					mp.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		public void setPlayModel(int playModel){
			musicPlayModel=playModel;;
		}
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		Log.i(TAG, "onCompletion");
		binder.next(1);
	}

}
