package com.hmcz.wsc.mvcmvpmvvm.mvvm;

import android.text.Editable;
import android.text.TextWatcher;

import com.hmcz.wsc.mvcmvpmvvm.bean.User;

/**
 * 作者：wangsc on 2016/9/1 00:04.
 * 邮箱：wsc030119@163.com
 */
public class UserEvent {

    private User user;

    public UserEvent(User user) {
        this.user = user;
    }

    public TextWatcher nameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            user.setUsername(editable.toString());
        }
    };
    public TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            user.setPassword(editable.toString());
        }
    };
}
