
package com.example.frameanimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    
    private ImageView img;
    private Button fly;
    private Button stop;
    private AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        img = (ImageView) findViewById(R.id.img);
        fly = (Button) findViewById(R.id.fly);
        stop = (Button) findViewById(R.id.stop);
    }
    
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        img.setBackgroundResource(R.drawable.anim_list);
        anim = (AnimationDrawable) img.getBackground();
        super.onWindowFocusChanged(hasFocus);
    }
    public void fly(View v){
        anim.start();
    }
    
    public void stop(View v){
        anim.stop();
    }
    
}
