package com.hmcz.wsc.mvcmvpmvvm.mvp.presenter;

import android.text.TextUtils;

import com.hmcz.wsc.mvcmvpmvvm.bean.User;
import com.hmcz.wsc.mvcmvpmvvm.mvp.IUserLoginView;
import com.hmcz.wsc.mvcmvpmvvm.mvp.net.UserLoginNet;

/**
 * 作者：wangsc on 2016/8/31 21:57.
 * 邮箱：wsc030119@163.com
 * 业务相关代码
 */
public class UserLoginPresenter {

    //Activity和Fragment都可以做界面处理，通用性不好
    //如何通用则：放置参数为通用的比如（抽象类或者接口），实际开发中接口更通用
    private IUserLoginView view;

    public UserLoginPresenter(IUserLoginView view) {
        this.view = view;
    }

    /**
     * 4、判断用户输入
     *
     * @param user
     * @return
     */
    public boolean checkUserInfo(User user) {
        if (TextUtils.isEmpty(user.username) || TextUtils.isEmpty(user.password)) {
            return false;
        }
        return true;
    }

    /**
     * 登陆业务
     *
     * @param user
     */
    public void login(final User user) {
        //模拟联网耗时
        new Thread() {
            @Override
            public void run() {
                //网络模块 m层
                UserLoginNet net = new UserLoginNet();
                if (net.sendUserLoginInfo(user)) {
                    //登陆成功
                    view.success();
                } else {
                    //登陆失败
                    view.fail();
                }
            }
        }.start();
    }
}
