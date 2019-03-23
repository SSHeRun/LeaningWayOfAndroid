package com.example.adapterdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<HashMap<String,String>> persons = new ArrayList<>();
//        HashMap<String,String> person = new HashMap<>();
//        person.put("name","aaa");
//        person.put("age","18");
//        person.put("gender","man");
//        persons.add(person);
//
//        person = new HashMap<>();
//        person.put("name","bbb");
//        person.put("age","19");
//        person.put("gender","man");
//        persons.add(person);
//        person = new HashMap<>();
//        person.put("name","ccc");
//        person.put("age","20");
//        person.put("gender","man");
//        persons.add(person);
//        person = new HashMap<>();
//        person.put("name","ddd");
//        person.put("age","21");
//        person.put("gender","woman");
//        persons.add(person);
//
//        //简单适配器
//        //系统自带的布局文件
//        //SimpleAdapter simpleAdapter = new SimpleAdapter(this,persons,android.R.layout.simple_list_item_2,new String[]{"name","age"},new int[]{android.R.id.text1,android.R.id.text2});
//        //自己的布局文件
//        SimpleAdapter simpleAdapter = new SimpleAdapter(this,persons,R.layout.layout_person,new String[]{"name","age","gender"},new int[]{R.id.text1,R.id.text2,R.id.text3});

        ArrayList<Person> pensons = new ArrayList<>();
        pensons.add(new Person("aaa",18,true));
        pensons.add(new Person("bbb",19,false));
        pensons.add(new Person("ccc",20,true));
        pensons.add(new Person("ddd",21,true));
        pensons.add(new Person("eee",22,false));
        pensons.add(new Person("fff",23,true));
        pensons.add(new Person("ggg",24,false));
        pensons.add(new Person("aaa",18,true));
        pensons.add(new Person("bbb",19,false));
        pensons.add(new Person("ccc",20,true));
        pensons.add(new Person("ddd",21,true));
        pensons.add(new Person("eee",22,false));
        pensons.add(new Person("fff",23,true));
        pensons.add(new Person("ggg",24,false));
        pensons.add(new Person("aaa",18,true));
        pensons.add(new Person("bbb",19,false));
        pensons.add(new Person("ccc",20,true));
        pensons.add(new Person("ddd",21,true));
        pensons.add(new Person("eee",22,false));
        pensons.add(new Person("fff",23,true));
        pensons.add(new Person("ggg",24,false));
        pensons.add(new Person("aaa",18,true));
        pensons.add(new Person("bbb",19,false));
        pensons.add(new Person("ccc",20,true));
        pensons.add(new Person("ddd",21,true));
        pensons.add(new Person("eee",22,false));
        pensons.add(new Person("fff",23,true));
        pensons.add(new Person("ggg",24,false));

        PersonAdpter personAdpter = new PersonAdpter(this,pensons);

        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(personAdpter);
    }
}
