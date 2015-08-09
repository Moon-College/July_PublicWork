
package com.tz.volumecontrolerview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {
    
    VolumeView volumeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volumeView = (VolumeView) findViewById(R.id.volumeView);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            volumeView.manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            volumeView.manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
        }
        //获取系统当前媒体音量
        volumeView.setVolume(volumeView.manager.getStreamVolume(AudioManager.STREAM_RING));
        volumeView.invalidate();//重新调用ondraw方法进行重绘
//        return super.onKeyDown(keyCode, event);
        return true;
    }

    
}
