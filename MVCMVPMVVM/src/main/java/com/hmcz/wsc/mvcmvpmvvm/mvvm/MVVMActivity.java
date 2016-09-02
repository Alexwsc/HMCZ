package com.hmcz.wsc.mvcmvpmvvm.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hmcz.wsc.mvcmvpmvvm.R;
import com.hmcz.wsc.mvcmvpmvvm.bean.User;
import com.hmcz.wsc.mvcmvpmvvm.databinding.ActivityMvvmBinding;

/**
 * MVVM模式包含三个部分
 * ---Model代表你的业务逻辑
 * ---View显示内容
 * ---ViewMoedel将前面两者联系在一起的对象
 * <p/>
 * 主要处理Model和View层
 * 第三部分可以使用google官方提供的一个工具（Android Data Binding）自动地将View和一个对象field绑定，
 * 当field更新的时候，framework将收到通知，然后view自动更新
 * <p/>
 * Data Binding官方原生支持MVVM模型可以让我们在不改变既有代码框架的前提下，非常容易地使用这些新特性
 * <p/>
 * google官网：https://developer.android.com/topic/libraries/data-binding/index.html
 * module的build.gradle中android中配置
 * <p/>
 * dataBinding {
 * enabled = true
 * }
 * <p/>
 * 配置后该module的结构会发生变化，主要是intermediates结构多了一些系统生成的文件夹
 */
public class MVVMActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        user = new User("jack", "123456");
        binding.setUser(user);
        UserEvent event=new UserEvent(user);
        binding.setEvent(event);


        //当field更新的时候，framework将收到通知，然后view自动更新
        //想要实现在子线程中修改field的时候把界面也修改了，这样就可以不用考虑子线程不可更改UI了，
        //可以使用Data Binding提供的几种方案，具体看以上链接中
        //Observable objects, observable fields, and observable collections
        /**
         * 官方原文
         * Any plain old Java object (POJO) may be used for data binding, but modifying a POJO
         * will not cause the UI to update. The real power of data binding can be used by giving
         * your data objects the ability to notify when data changes. There are three different data
         * change notification mechanisms, Observable objects, observable fields, and observable collections.
         */
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                user.setUsername("alex-wsc");
            }
        }.start();
    }

    //view中的界面改变如何通知module

    //以下代码运行得知，edittext内容改变，弹的吐司还是原先的内容，并没有显示最新的edittext的值，、
    //需要添加edittext的文本内容变化监听addTextChangeListener,具体可见google官网Event Handling和Listener Bindings
    public void Test(View view) {
        Toast.makeText(MVVMActivity.this, "name:" + user.getUsername(), Toast.LENGTH_SHORT).show();
    }
}
