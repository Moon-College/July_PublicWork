package com.rex.tz_7_fileexplorer.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

import com.rex.tz_7_fileexplorer.R;
import com.rex.tz_7_fileexplorer.adapter.FileExploreAdapter;
import com.rex.tz_7_fileexplorer.data.FileInfo;
import com.rex.tz_7_fileexplorer.util.ExplorerFileUtil;

public class Home_Activity extends Activity implements
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener
{

    ListView lv_files = null;
    List<FileInfo> fileInfos = new ArrayList<FileInfo>(0);
    private TextView tv_currpath;
    private FileExploreAdapter adapter;
    private File sdCdRootFile;
    private View popupView;
    private int height = 120;
    private int width = 400;
    private PopupWindow popupWindow;
    private Button btn_scan_detail;
    private Button btn_delete_item;

    private int possition = -1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tv_currpath = (TextView) findViewById(R.id.tv_currpath);
        lv_files = (ListView) findViewById(R.id.lv_files);

        initData();
        adapter = new FileExploreAdapter(this, fileInfos);

        lv_files.setAdapter(adapter);

        lv_files.setOnItemClickListener(this);

        popupView = View
                .inflate(Home_Activity.this, R.layout.popupwindow, null);
        
        btn_scan_detail = (Button) popupView.findViewById(R.id.btn_scan_detail);
        btn_delete_item = (Button) popupView.findViewById(R.id.btn_delete_item);
        
        btn_scan_detail.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                if(-1 != possition)
                {
                    popupWindow.dismiss();
                    
                    Toast.makeText(Home_Activity.this, fileInfos.get(possition).toString(), Toast.LENGTH_LONG).show();
                    possition = -1;
                }
            }
        });
        
        btn_delete_item.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                if(-1 != possition)
                {
                    popupWindow.dismiss();
                    fileInfos.remove(possition);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(Home_Activity.this, "删除item：\n"+fileInfos.get(possition).toString(), Toast.LENGTH_LONG).show();
                    possition = -1;
                }
            }
        });
        
        lv_files.setOnItemLongClickListener(this);

        // View contentView = View.inflate(this, R.layout.custom_popupwindow,
        // null);
        // GlobalPopupWindow popupWindow = new GlobalPopupWindow(this,
        // contentView, WindowManager.LayoutParams.WRAP_CONTENT,
        // WindowManager.LayoutParams.WRAP_CONTENT);
        // popupWindow.showGlobalView();
        // popupWindow.setOnClickLis();
        // popupWindow.setOnTouchLis();
    }

    private void initData()
    {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
        {
            sdCdRootFile = Environment.getExternalStorageDirectory();
            tv_currpath.setText(ExplorerFileUtil.getAbsPath(sdCdRootFile));

            ExplorerFileUtil.explorer(this, fileInfos, sdCdRootFile);

        }
        else
        {
            tv_currpath.setText("没有SD卡");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id)
    {

        FileInfo fileInfo = fileInfos.get(position);
        String path = fileInfo.getWholePath();

        // 点击“返回”或者是普通item，逻辑都是得到这个item里面存储的path，然后把他的子文件全部列举出来
        // 只有“返回”sd卡根目录的时候，不能再返回了，所以这里不再添加“返回”item
        if (!fileInfo.isFile())
        {
            fileInfos.clear();

            // file为要打开展示的目录
            File file = new File(path);

            // 只要这个file不是sd卡的根目录，都应该加上“返回”item
            if (!file.equals(sdCdRootFile))
            {
                // 返回到哪里？返回到path所在的这个目录，这个目录包含了path，所以是他的父目录
                FileInfo tempInfo = new FileInfo("返回:"
                        + file.getParentFile().getAbsolutePath(),
                        ExplorerFileUtil.getAbsPath(file.getParentFile()),
                        false, false);
                fileInfos.add(tempInfo);
            }

            tv_currpath.setText(ExplorerFileUtil.getAbsPath(file));

            ExplorerFileUtil.explorer(Home_Activity.this, fileInfos, file);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, final View view,
            int position, long id)
    {
        possition = position;
        
        popupWindow = new PopupWindow(popupView, width, height);
        int x = view.getLeft() + view.getWidth() / 2 - width / 2;
        int y = view.getTop();

        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setFocusable(true);

        view.setBackgroundColor(Color.GRAY);
        popupWindow.showAtLocation(view, Gravity.LEFT | Gravity.TOP, x, y);

        popupWindow.setOnDismissListener(new OnDismissListener()
        {

            @Override
            public void onDismiss()
            {
                if (null != view)
                {
                    view.setBackgroundColor(Color.WHITE);
                }
            }
        });

        return true;
    }
}
