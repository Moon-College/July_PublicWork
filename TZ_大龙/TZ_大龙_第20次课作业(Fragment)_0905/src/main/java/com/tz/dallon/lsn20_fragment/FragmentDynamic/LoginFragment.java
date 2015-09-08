package com.tz.dallon.lsn20_fragment.FragmentDynamic;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tz.dallon.lsn20_fragment.R;


public class LoginFragment extends Fragment {
    EditText et_userName;
    EditText et_pwd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //渲染和加载布局的方法
        View view = inflater.inflate(R.layout.fragment_dynamic_login, null);
        et_userName = (EditText) view.findViewById(R.id.et_userName);
        et_pwd = (EditText) view.findViewById(R.id.et_pwd);
        Button bt = (Button) view.findViewById(R.id.bt_submit);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = et_userName.getText().toString();
                String passWord = et_pwd.getText().toString();
                String msg = null;
                if (TextUtils.isEmpty(userName)) {
                    msg = "用户不能为空";
                } else {
                    msg = "登陆成功";

                    SharedPreferences sp = getActivity().getSharedPreferences("main", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username", userName);
                    editor.putString("password", passWord);
                    editor.commit();
                    getActivity().finish();
                }

                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

}
