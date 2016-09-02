package com.hmcz.wsc.mvcmvpmvvm.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hmcz.wsc.mvcmvpmvvm.BR;

/**
 * 作者：wangsc on 2016/8/31 08:33.
 * 邮箱：wsc030119@163.com
 */
public class User extends BaseObservable {
    public String username;
    public String password;

    public User() {
    }

    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }
    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
