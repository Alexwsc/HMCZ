package com.hmcz.wsc.mvcmvpmvvm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hmcz.wsc.mvcmvpmvvm.mvvm.MVVMActivity;
import com.hmcz.wsc.mvcmvpmvvm.mvvm.MVVMTestActivity;

public class MVVMNavigateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvmnavigate);
    }

    public void MVVMClick(View view){
        switch (view.getId()){
            case R.id.button5://MVVM基本示例
                startActivity(new Intent(this, MVVMActivity.class));
                break;
            case R.id.button6://MVVM项目改造
                startActivity(new Intent(this, MVVMTestActivity.class));
                break;

        }
    }
}
