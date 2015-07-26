package com.hacket.filemanager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hacket.filemanager.adapter.FileManagerAdapter;
import com.hacket.filemanager.bean.FileInfo;
import com.hacket.filemanager.utils.FileManagerUtil;

import java.util.List;


public class FileManagerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

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
    public void onBackPressed() {
        if (mFileManagerAdapter != null) {
            if (isRootDir) {
                super.onBackPressed();
            } else {
                initData(mFileManagerAdapter.getDatas().get(0).getFileurl());
            }
        } else {
            super.onBackPressed();
        }

    }
}