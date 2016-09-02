package com.hmcz.wsc.mvcmvpmvvm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hmcz.wsc.mvcmvpmvvm.bean.User;

/**
 * 通过一个一个登录功能，按照不同的模式（MVC\MVP\MVVM）拆分，棱知道各个模式的优劣
 */
public class MainActivity extends AppCompatActivity {

    /*
    流程：
    1、界面展示
    2、用户的输入
    3、按钮点击
    4、判断用户输入
    5、显示滚动条
    6、一系列耗时工作
    7、隐藏
    8、提示用户

    第一种情况：MainActivity中处理所有的代码
     */

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

        User user = new User(username, password);
        boolean isTrue = checkUserInfo(user);

        if (isTrue) {

            progressDialog.show();

            //模拟联网耗时
            new Thread() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    if ("13888888888".equals(username) && "123456".equals(password)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //登陆成功
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "欢迎回来" + username, Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //登陆成功
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "登陆失败" , Toast.LENGTH_SHORT).show();
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
