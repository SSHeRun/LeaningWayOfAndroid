package com.example.ryclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 准备数据
        //2. 派生一个适配器类
        //3. 绑定界面和adapter对象

        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("liguiyang",44,true));
        persons.add(new Person("gaoyuexiang",45,true));
        persons.add(new Person("fanxiangkui",40,false));
        persons.add(new Person("luyu",42,true));
        persons.add(new Person("liaoxuehua",40,false));
        persons.add(new Person("liguiyang",44,true));
        persons.add(new Person("gaoyuexiang",45,true));
        persons.add(new Person("fanxiangkui",40,false));
        persons.add(new Person("luyu",42,true));
        persons.add(new Person("liaoxuehua",40,false));
        persons.add(new Person("liguiyang",44,true));
        persons.add(new Person("gaoyuexiang",45,true));
        persons.add(new Person("fanxiangkui",40,false));
        persons.add(new Person("luyu",42,true));
        persons.add(new Person("liaoxuehua",40,false));
        persons.add(new Person("liguiyang",44,true));
        persons.add(new Person("gaoyuexiang",45,true));
        persons.add(new Person("fanxiangkui",40,false));
        persons.add(new Person("luyu",42,true));
        persons.add(new Person("liaoxuehua",40,false));
        persons.add(new Person("liguiyang",44,true));
        persons.add(new Person("gaoyuexiang",45,true));
        persons.add(new Person("fanxiangkui",40,false));
        persons.add(new Person("luyu",42,true));
        persons.add(new Person("liaoxuehua",40,false));
        persons.add(new Person("liguiyang",44,true));
        persons.add(new Person("gaoyuexiang",45,true));
        persons.add(new Person("fanxiangkui",40,false));
        persons.add(new Person("luyu",42,true));
        persons.add(new Person("liaoxuehua",40,false));

        PersonAdapter personAdapter = new PersonAdapter(persons);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        GridLayoutManager layoutManager = new GridLayoutManager(this,3);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(personAdapter);

    }
}
