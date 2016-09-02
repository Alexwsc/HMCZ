package com.hmcz.wsc.mvcmvpmvvm.mvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hmcz.wsc.mvcmvpmvvm.R;
import com.hmcz.wsc.mvcmvpmvvm.bean.User;
import com.hmcz.wsc.mvcmvpmvvm.mvp.presenter.UserLoginPresenter;


/**
 * mvp模式拆分
 * m-----模型曾
 * v-----界面
 * p-----presenter
 */
/*
按照MVP模式拆分

 */
public class MVPActivity extends AppCompatActivity implements IUserLoginView {
    private EditText PassWordET, UserNameET;
    private ProgressDialog progressDialog;

    private UserLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserNameET = (EditText) findViewById(R.id.UserNameET);
        PassWordET = (EditText) findViewById(R.id.PassWordET);
        progressDialog = new ProgressDialog(this);

        presenter = new UserLoginPresenter(this);

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
        boolean isTrue = presenter.checkUserInfo(user);

        if (isTrue) {

            progressDialog.show();
            presenter.login(user);

        } else {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    //登陆成功
    public void success() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                Toast.makeText(MVPActivity.this, "欢迎回来" + UserNameET.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //登陆失败
    public void fail() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                Toast.makeText(MVPActivity.this, "用户名或密码输入有误", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
