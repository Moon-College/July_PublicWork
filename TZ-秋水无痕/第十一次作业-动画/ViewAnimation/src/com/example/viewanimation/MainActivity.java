
package com.example.viewanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView img;
//    private Button alpha;
//    private Button scale;
//    private Button translate;
//    private Button rotate;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        img = (ImageView) findViewById(R.id.img);
//        alpha = (Button) findViewById(R.id.alpha);
//        scale = (Button) findViewById(R.id.scale);
//        translate = (Button) findViewById(R.id.translate);
//        rotate = (Button) findViewById(R.id.rotate);
    }
    
    public void click(View v){
        Animation anim;
        switch (v.getId()) {
            case R.id.alpha:
                anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
                img.startAnimation(anim);
                break;
            case R.id.scale:
                anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
                img.startAnimation(anim);
                break;
            case R.id.translate:
                anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
                img.startAnimation(anim);
                break;
            case R.id.rotate:
                anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
                img.startAnimation(anim);
                break;
            default:
                break;
        }
    }

}
