package com.qiangshijituan.zzq_jianbao.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qiangshijituan.zzq_jianbao.R;
import com.qiangshijituan.zzq_jianbao.activity.PersonalActivity;
import com.qiangshijituan.zzq_jianbao.activity.RegisterActivity;
import com.qiangshijituan.zzq_jianbao.bean.Login_Bean;
import com.qiangshijituan.zzq_jianbao.utils.OkHttpUtils;

import java.util.HashMap;

import static android.R.attr.name;
import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 赵自强 on 2016/12/8.
 */

public class LoginFragment extends BaseFragment {


    private TextView tv_zhuCe;
    private EditText et_usetname;
    private EditText et_password;
    private String username;
    private String password;
    private HashMap<String, String> map;
    private Button bt_dl;
    private SharedPreferences sharedPreferences;
    private CheckBox cb_login;
    private View view;


    @Override
    public View initView() {

        // 一个轻量级储存  在Fragment中我们多需要获取上下文
        sharedPreferences = getActivity().getSharedPreferences("YongHu", MODE_PRIVATE);

        // 查找控件
        view = LayoutInflater.from(getActivity()).inflate(R.layout.jianbao_loginfragmen, null);

        tv_zhuCe = (TextView) view.findViewById(R.id.tv_register_zc); // 注册
        et_usetname = (EditText) view.findViewById(R.id.et_zhanghao);
        et_password = (EditText) view.findViewById(R.id.et_mima);
        bt_dl = (Button) view.findViewById(R.id.bt_denglu_dl); // 登陆
        cb_login = (CheckBox) view.findViewById(R.id.cb_longin_cb); // CheckBoxd单选框
        username = et_usetname.getText().toString().trim();
        password = et_password.getText().toString().trim();

        initSharedPreferences();


        initDengLu();
        initData();
        return view;
    }


    @Override
    public void initData() {
        initButton();

    }

    private void initDengLu() {

        // /登陆按钮点击事件
        bt_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = et_usetname.getText().toString().trim();
                password = et_password.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getActivity(), "账号不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    initPost();
                }
            }
        });
      // 判断是否有值
        // 点击CheckBox  记住密码
        cb_login.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked) {//勾选时，存入EditText中的用户名密码

                            String strUserName = et_usetname.getText().toString();
                            String strPassWord = et_password.getText().toString();
                            sharedPreferences.edit().putString("setJudge", "yes").putString("strUserName", strUserName)
                                    .putString("strPassWord", strPassWord)
                                    .commit();
                            Toast.makeText(getActivity(), "记住用户名和密码", Toast.LENGTH_SHORT)
                                    .show();
                        } else {//不勾选，存入空String对象
                            sharedPreferences.edit().putString("setJudge", "no")
                                    .putString("strUserName", "")
                                    .putString("strPassWord", "")
                                    .commit();
                            Toast.makeText(getActivity(), "不记住用户名和密码", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
    }

    private void initSharedPreferences() {

        String setJudge = sharedPreferences.getString("setJudge", "no");
        String strUsername = sharedPreferences.getString("strUserName", "");
        String strPassword = sharedPreferences.getString("strPassWord", "");
        if (setJudge.equals("yes")) {
            cb_login.setChecked(true);
            et_usetname.setText(strUsername);
            et_password.setText(strPassword);
        } else {
            cb_login.setChecked(false);
            et_usetname.setText("");
            et_password.setText("");
        }
    }



    private void initPost() {
        OkHttpUtils.setGetEntiydata(new OkHttpUtils.EntiyData() {
            @Override
            public void getEntiy(Object o) {
                Login_Bean login_bean = (Login_Bean) o;
                String status = login_bean.getStatus();
                if (status.equals("200")) {
                    // 因为在Fragment中没有上下文  我们先需要获取上下文
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
                            bt_dl.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getActivity(), PersonalActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        OkHttpUtils.post(map, "http://192.168.4.188/Goods/" + "app/common/login.json", getActivity(), Login_Bean.class);
    }

    private void initButton() {


        tv_zhuCe.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }



    // FragmentTabHost
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取bundle
        Bundle bundle = getArguments();
        bundle.getInt("name");
        Log.e(TAG, "onCreate: " + name);
    }

}
