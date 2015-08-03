package com.rex.tz_7_fileexplorer.view;

import com.rex.tz_7_fileexplorer.R;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class GlobalPopupWindow
{
    Context context;
    View contentView;
    int width;
    int height;
    private WindowManager manager;
    private IBinder token;
    private WindowManager.LayoutParams params;
    private Button btn_add;
    private Button btn_delete;
    private Button btn_modify;

    public GlobalPopupWindow(Context context, View contentView, int width,
            int height)
    {
        super();
        this.context = context;
        this.contentView = contentView;
        this.width = width;
        this.height = height;
        token = contentView.getWindowToken();
        manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        
        btn_add = (Button) contentView.findViewById(R.id.btn_add);
        btn_delete = (Button) contentView.findViewById(R.id.btn_delete);
        btn_modify = (Button) contentView.findViewById(R.id.btn_modify);
    }
    
    public void setOnClickLis()
    {
        OnClickListener clickListener =  new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context, "点我干嘛", Toast.LENGTH_LONG).show();
            }
        };
        btn_add.setOnClickListener(clickListener);
        btn_delete.setOnClickListener(clickListener);
        btn_modify.setOnClickListener(clickListener);
    }
    
    public void setOnTouchLis()
    {
        
        OnTouchListener onTouchListener =  new OnTouchListener()
        {
            
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                int x = (int) event.getRawX();
                int y = (int) event.getRawY();
                params.x = x- (width/2);
                params.y = y- (height/2);
                manager.updateViewLayout(contentView, params);
                return true;
            }
        };
        
        btn_add.setOnTouchListener(onTouchListener);
        btn_delete.setOnTouchListener(onTouchListener);
        btn_modify.setOnTouchListener(onTouchListener);
    }

    public void showGlobalView()
    {
        params = createPopupLayout(token);
        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 0;
        
        manager.addView(contentView, params);
    }

    private WindowManager.LayoutParams createPopupLayout(IBinder token)
    {
        WindowManager.LayoutParams p = new WindowManager.LayoutParams();
        p.gravity = Gravity.LEFT | Gravity.TOP;
        p.width = this.width;
        p.height = this.height;
        p.format = PixelFormat.OPAQUE;

        // 设置悬浮窗口不可以获得焦点
        p.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        p.type = WindowManager.LayoutParams.TYPE_PHONE;
        p.token = token;
        p.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED;
        p.setTitle("PopupWindow:" + Integer.toHexString(hashCode()));

        return p;
    }

}
