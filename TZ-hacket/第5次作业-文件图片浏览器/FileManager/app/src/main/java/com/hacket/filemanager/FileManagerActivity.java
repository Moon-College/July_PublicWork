package com.hacket.filemanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.hacket.filemanager.adapter.FileManagerAdapter;
import com.hacket.filemanager.bean.FileInfo;
import com.hacket.filemanager.utils.FileManagerUtil;

import java.util.List;


public class FileManagerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView mLvFileManager;
    private FileManagerAdapter mFileManagerAdapter;
    private boolean isRootDir = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);

        initView();
        setListeners();
        initData(Environment.getExternalStorageDirectory().toString());

    }

    private void initView() {
        mLvFileManager = (ListView) findViewById(R.id.lv_filemanager);
    }

    private void setListeners() {
        mLvFileManager.setOnItemClickListener(this);
        mLvFileManager.setOnItemLongClickListener(this);
    }

    private void initData(String path) {
        new AsyncTask<String, Void, List<FileInfo>>() {

            @Override
            protected List<FileInfo> doInBackground(String... params) {
                if (TextUtils.isEmpty(params[0])) {
                    return null;
                }
                if (Environment.getExternalStorageDirectory().getAbsolutePath().equals(params[0])) {
                    isRootDir = true;
                    return FileManagerUtil.getFiles(params[0], true);
                }
                isRootDir = false;
                return FileManagerUtil.getFiles(params[0], false);
            }

            @Override
            protected void onPostExecute(List<FileInfo> fileInfos) {
                if (fileInfos != null && !fileInfos.isEmpty()) {
                    notifyData(fileInfos);
                }
            }
        }.execute(path);
    }

    private void notifyData(List<FileInfo> fileInfos) {
        if (mFileManagerAdapter == null) {
            mFileManagerAdapter = new FileManagerAdapter(getApplicationContext(), fileInfos);
            mLvFileManager.setAdapter(mFileManagerAdapter);
        } else {
            mFileManagerAdapter.setDatas(fileInfos);
            mFileManagerAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0 && mFileManagerAdapter.getItem(position).isRootRir()) {
            initData(mFileManagerAdapter.getItem(position).getFileurl());
            return;
        }

        FileInfo item = mFileManagerAdapter.getItem(position);
        if (item != null && item.isFoolder()) {
            mFileManagerAdapter.getDatas().clear();
            String fileurl = item.getFileurl();
            initData(fileurl);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        removeItem(parent, view, position);
        return true;
    }

    private void removeItem(AdapterView<?> parent, View view, final int position) {

        View contentView = View.inflate(getApplicationContext(), R.layout.popupwindow_view, null);
        int width = ViewGroup.LayoutParams.WRAP_CONTENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(contentView, width, height, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button open = (Button) contentView.findViewById(R.id.open);
        Button delete = (Button) contentView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInfo isRemove = mFileManagerAdapter.getDatas().remove(position);
                if (isRemove != null) {
                    Toast.makeText(getApplicationContext(), "remove:" + mFileManagerAdapter.getDatas().get(position).getFilename(), Toast.LENGTH_SHORT).show();
                    mFileManagerAdapter.notifyDataSetChanged();
                }
                popupWindow.dismiss();
            }
        });
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "open detail...", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        popupWindow.setContentView(contentView);
        int gravity = Gravity.TOP + Gravity.LEFT;
        int[] location = new int[2];// location[0]:x,location[1]:y
        view.getLocationInWindow(location);
        popupWindow.showAtLocation(parent, gravity, (int)FileManagerUtil.dip2px(getApplicationContext(),100), location[1]);
    }

    @Override
    public void onBackPressed() {
        if (mFileManagerAdapter != null && !isRootDir) {
            initData(mFileManagerAdapter.getDatas().get(0).getFileurl());
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("are you sure exit!");
            builder.setIcon(android.R.drawable.arrow_down_float);
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FileManagerActivity.super.onBackPressed();
                    dialog.dismiss();
                }
            });
            builder.show();
        }
    }


}