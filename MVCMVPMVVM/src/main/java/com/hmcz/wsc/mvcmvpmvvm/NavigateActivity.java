package com.hmcz.wsc.mvcmvpmvvm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hmcz.wsc.mvcmvpmvvm.mvc.MVCActivity;
import com.hmcz.wsc.mvcmvpmvvm.mvp.MVPActivity;
import com.hmcz.wsc.mvcmvpmvvm.mvvm.MVVMActivity;

public class NavigateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.button:
                startActivity(new Intent(NavigateActivity.this, MainActivity.class));
                break;
            case R.id.button2://MVC
                startActivity(new Intent(NavigateActivity.this, MVCActivity.class));
                break;
            case R.id.button3://MVP
                startActivity(new Intent(NavigateActivity.this, MVPActivity.class));
                break;
            case R.id.button4://MVVM
                startActivity(new Intent(NavigateActivity.this, MVVMNavigateActivity.class));
                Toast.makeText(this,"点击了",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
