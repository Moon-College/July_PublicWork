package com.tz.browse.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.tz.browse.base.BaseActivity;
import com.tz.filebrowse.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by 屈发 on 2015/8/4.
 */
public class TxtReadActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_txt_reader);
        hideTitlebarLeft();
        setBarTitle("文本浏览器");
        initData();
    }


    private void initData() {
        TextView tv_txt = (TextView) findViewById(R.id.tv_txt);
        Intent intent = getIntent();
        String path = intent.getData().getPath();
        File f = new File(path);

        try {
            FileInputStream in = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);

            }
            String str = new String(out.toByteArray());
            tv_txt.setText(str);
            in.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
