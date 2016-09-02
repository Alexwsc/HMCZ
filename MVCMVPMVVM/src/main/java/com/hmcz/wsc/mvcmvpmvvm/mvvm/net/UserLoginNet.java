package com.hmcz.wsc.mvcmvpmvvm.mvvm.net;

import android.os.SystemClock;

import com.hmcz.wsc.mvcmvpmvvm.bean.User;

/**
 * 作者：wangsc on 2016/8/31 10:24.
 * 邮箱：wsc030119@163.com
 */
public class UserLoginNet {

    /**
     * 发送用户输入数据
     *
     * @param user
     * @return
     */
    public boolean sendUserLoginInfo(User user) {

        SystemClock.sleep(2000);
        if ("13888888888".equals(user.username) && "123456".equals(user.password)) {

            //登陆成功
            return true;
        } else {
            //登陆失败
            return false;
        }

    }
}
