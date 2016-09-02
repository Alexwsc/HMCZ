package com.hmcz.wsc.mvcmvpmvvm.mvvm;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.hmcz.wsc.mvcmvpmvvm.R;
import com.hmcz.wsc.mvcmvpmvvm.bean.User;
import com.hmcz.wsc.mvcmvpmvvm.databinding.ActivityMvvmtestBinding;
import com.hmcz.wsc.mvcmvpmvvm.mvvm.net.UserLoginNet;


public class MVVMTestActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmtestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvmtest);
        user=new User();
        UserEvent event=new UserEvent(user);
        binding.setEvent(event);
        progressDialog = new ProgressDialog(this);
    }
    /**
     * 按钮点击
     *
     * @param view
     */
    public void login(View view) {


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
                                Toast.makeText(MVVMTestActivity.this, "欢迎回来" + user.getUsername(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //登陆成功
                                progressDialog.dismiss();
                                Toast.makeText(MVVMTestActivity.this, "登陆失败" , Toast.LENGTH_SHORT).show();
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
