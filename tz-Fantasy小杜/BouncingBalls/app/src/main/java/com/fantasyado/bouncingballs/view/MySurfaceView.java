package com.fantasyado.bouncingballs.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Administrator on 2015/8/16.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mHolder;
    private boolean hasSurface;
    private float cx;
    private float cy;
    private boolean pointChanged;
    private Paint mPaint;
    private  Canvas mCanvas;


    private int[] bgclors = {
            Color.WHITE, Color.YELLOW,
              Color.CYAN
    };
    int[] paintColor={
            Color.RED, Color.BLUE,Color.CYAN,
            Color.YELLOW,Color.BLUE,
            Color.BLACK,Color.GREEN

    };
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };





    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this);
           mPaint = new Paint();
    }

    public MySurfaceView(Context context) {
        this(context, null);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        hasSurface = true;
        bgTwinkle();





    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        bgAnimator.end();
        mHolder.removeCallback(this);


    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:



            case MotionEvent.ACTION_MOVE:
                if (hasSurface) {

                    ShapeHolder newBall = addBall(event.getX(), event.getY());



                    // Bouncing animation with squash and stretch
                    float startY = newBall.getY();
                    float endY = getHeight() - 50f;
                    float h = (float) getHeight();
                    float eventY = event.getY();
                    int duration = (int) (500 * ((h - eventY) / h));
                    ValueAnimator bounceAnim = ObjectAnimator.ofFloat(newBall, "y", startY, endY);
                    bounceAnim.setDuration(duration);
                    bounceAnim.setInterpolator(new AccelerateInterpolator());
                    ValueAnimator squashAnim1 = ObjectAnimator.ofFloat(newBall, "x", newBall.getX(),
                            newBall.getX() - 25f);
                    squashAnim1.setDuration(duration / 4);
                    squashAnim1.setRepeatCount(1);
                    squashAnim1.setRepeatMode(ValueAnimator.REVERSE);
                    squashAnim1.setInterpolator(new DecelerateInterpolator());
                    ValueAnimator squashAnim2 = ObjectAnimator.ofFloat(newBall, "width", newBall.getWidth(),
                            newBall.getWidth() + 50);
                    squashAnim2.setDuration(duration / 4);
                    squashAnim2.setRepeatCount(1);
                    squashAnim2.setRepeatMode(ValueAnimator.REVERSE);
                    squashAnim2.setInterpolator(new DecelerateInterpolator());
                    ValueAnimator stretchAnim1 = ObjectAnimator.ofFloat(newBall, "y", endY,
                            endY + 25f);
                    stretchAnim1.setDuration(duration / 4);
                    stretchAnim1.setRepeatCount(1);
                    stretchAnim1.setInterpolator(new DecelerateInterpolator());
                    stretchAnim1.setRepeatMode(ValueAnimator.REVERSE);
                    ValueAnimator stretchAnim2 = ObjectAnimator.ofFloat(newBall, "height",
                            newBall.getHeight(), newBall.getHeight() - 25);
                    stretchAnim2.setDuration(duration / 4);
                    stretchAnim2.setRepeatCount(1);
                    stretchAnim2.setInterpolator(new DecelerateInterpolator());
                    stretchAnim2.setRepeatMode(ValueAnimator.REVERSE);
                    ValueAnimator bounceBackAnim = ObjectAnimator.ofFloat(newBall, "y", endY,
                            startY);
                    bounceBackAnim.setDuration(duration);
                    bounceBackAnim.setInterpolator(new DecelerateInterpolator());
                    // Sequence the down/squash&stretch/up animations
                    AnimatorSet bouncer = new AnimatorSet();
                    bouncer.play(bounceAnim).before(squashAnim1);
                    bouncer.play(squashAnim1).with(squashAnim2);
                    bouncer.play(squashAnim1).with(stretchAnim1);
                    bouncer.play(squashAnim1).with(stretchAnim2);
                    bouncer.play(bounceBackAnim).after(stretchAnim2);

                    // Fading animation - remove the ball when the animation is done
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(newBall, "alpha", 1f, 0f);
                    fadeAnim.setDuration(250);
                    fadeAnim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            ShapeHolder.balls.remove(((ObjectAnimator) animation).getTarget());

                        }
                    });

                    // Sequence the two animations to play one after the other
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.play(bouncer).before(fadeAnim);

                    // Start the animation
                    animatorSet.start();

                    return true;
                }

            default:

                break;

        }


        return super.onTouchEvent(event);
    }
    private  ValueAnimator bgAnimator;
    private void bgTwinkle() {
        bgAnimator = ObjectAnimator.ofInt(this, "backgroundColor", bgclors);
        bgAnimator.setDuration(5000);
        bgAnimator.setEvaluator(new ArgbEvaluator());
        bgAnimator.setRepeatCount(ValueAnimator.INFINITE);
        bgAnimator.setRepeatMode(ValueAnimator.REVERSE);
        bgAnimator.start();

    }


    @Override
    protected void onDraw(  Canvas canvas) {

        for (int i = 0; i < ShapeHolder.balls.size(); ++i) {

            ShapeHolder shapeHolder = ShapeHolder.balls.get(i);
             canvas.save();
             canvas.translate(shapeHolder.getX(), shapeHolder.getY());
            shapeHolder.getShape().draw(canvas);
             canvas.restore();

        }

    }


    private ShapeHolder addBall(float x, float y) {
        OvalShape circle = new OvalShape();
        circle.resize(50f, 50f);
        ShapeDrawable drawable = new ShapeDrawable(circle);
        ShapeHolder shapeHolder = new ShapeHolder(drawable);
        shapeHolder.setX(x - 25f);
        shapeHolder.setY(y - 25f);
        int red = (int)(Math.random() * 255);
        int green = (int)(Math.random() * 255);
        int blue = (int)(Math.random() * 255);
        int color = 0xff000000 | red << 16 | green << 8 | blue;
        Paint paint = drawable.getPaint(); //new Paint(Paint.ANTI_ALIAS_FLAG);
        int darkColor = 0xff000000 | red/4 << 16 | green/4 << 8 | blue/4;
        RadialGradient gradient = new RadialGradient(37.5f, 12.5f,
                50f, color, darkColor, Shader.TileMode.CLAMP);
        paint.setShader(gradient);
        shapeHolder.setPaint(paint);
        shapeHolder.balls.add(shapeHolder);
        return shapeHolder;
    }

}
