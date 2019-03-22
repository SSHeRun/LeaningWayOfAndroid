package com.example.adapterdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    //private  static final String[] name ={"aaa","bbb","ccc","ddd"};
    //可以定义死的数组
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //从资源文件中获取数组
        String[] name = getResources().getStringArray(R.array.names);
        //准备一个适配器adpter,（数据-adpter-目标组件）
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.layout_person,name);
        //自己的布局文件
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,name);
        //系统自带的布局文件
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);
    }
}
