package com.hmcz.wsc.mvcmvpmvvm.mvc;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hmcz.wsc.mvcmvpmvvm.R;
import com.hmcz.wsc.mvcmvpmvvm.bean.User;
import com.hmcz.wsc.mvcmvpmvvm.mvc.net.UserLoginNet;

/**
 * mvc模式拆分
 * m-----模型层
 * v-----界面
 * c-----控制层
 */
/*
按照MVC模式拆分
存在问题：
Activity中存在两部分内容：业务相关+界面相关
V中内容相对较少，而C中内容很多
解决方案：
1、如果将activity中的业务部分拆分对应------MVP
2、如果将activity中的界面相关部分拆分对应------MVVM
 */
public class MVCActivity extends AppCompatActivity {
    private EditText PassWordET, UserNameET;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserNameET = (EditText) findViewById(R.id.UserNameET);
        PassWordET = (EditText) findViewById(R.id.PassWordET);
        progressDialog = new ProgressDialog(this);

    }

    /**
     * 按钮点击
     *
     * @param view
     */
    public void login(View view) {

        final String username = UserNameET.getText().toString();
        final String password = PassWordET.getText().toString();

        final User user = new User(username, password);
        boolean isTrue = checkUserInfo(user);

        if (isTrue) {

            progressDialog.show();

            //模拟联网耗时
            new Thread() {
                @Override
                public void run() {
                    //网络模块 m层
                    UserLoginNet net=new UserLoginNet();
                    if (net.sendUserLoginInfo(user)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //登陆成功
                                progressDialog.dismiss();
                                Toast.makeText(MVCActivity.this, "欢迎回来" + username, Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //登陆成功
                                progressDialog.dismiss();
                                Toast.makeText(MVCActivity.this, "用户名或密码输入有误" , Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }.start();
        } else {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 4、判断用户输入
     *
     * @param user
     * @return
     */
    private boolean checkUserInfo(User user) {
        if (TextUtils.isEmpty(user.username) || TextUtils.isEmpty(user.password)) {
            return false;
        }
        return true;
    }
}
