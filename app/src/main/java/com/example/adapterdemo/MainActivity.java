package com.example.adapterdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] names = {"auto","apple","android","banana","bit","bigger","cat","common","commit","dsafsd","dfsdf","dfjsf"};

//        ArrayAdapter<String>  arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_2,names);

//        ArrayList<HashMap<String,String>> persons = new ArrayList<>();
//        HashMap<String,String> p1 = new HashMap<>();
//        p1.put("name","liguiyang");
//        p1.put("age","don't tell you");
//        p1.put("gender","man");
//        persons.add(p1);
//
//        p1 = new HashMap<>();
//        p1.put("name","gaoyuexiang");
//        p1.put("age","45");
//        p1.put("gender","man");
//        persons.add(p1);
//
//        p1 = new HashMap<>();
//        p1.put("name","fanxiangkui");
//        p1.put("age","40");
//        p1.put("gender","girl");
//        persons.add(p1);
//
//        p1 = new HashMap<>();
//        p1.put("name","luyu");
//        p1.put("age","40");
//        p1.put("gender","woman");
//        persons.add(p1);
//
//
//
//
//        SimpleAdapter simpleAdapter = new SimpleAdapter(this,persons,R.layout.layout_person,new String[]{"name","age"},new int[]{R.id.textView_name,R.id.textView_other});

        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("liguiyang",44,"man"));
        persons.add(new Person("gaoyeuxiang",45,"girl"));
        persons.add(new Person("fanxiangkui",40,"girl"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("liguiyang",44,"man"));
        persons.add(new Person("gaoyeuxiang",45,"girl"));
        persons.add(new Person("fanxiangkui",40,"girl"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("liguiyang",44,"man"));
        persons.add(new Person("gaoyeuxiang",45,"girl"));
        persons.add(new Person("fanxiangkui",40,"girl"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("liguiyang",44,"man"));
        persons.add(new Person("gaoyeuxiang",45,"girl"));
        persons.add(new Person("fanxiangkui",40,"girl"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("liguiyang",44,"man"));
        persons.add(new Person("gaoyeuxiang",45,"girl"));
        persons.add(new Person("fanxiangkui",40,"girl"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("liguiyang",44,"man"));
        persons.add(new Person("gaoyeuxiang",45,"girl"));
        persons.add(new Person("fanxiangkui",40,"girl"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("liguiyang",44,"man"));
        persons.add(new Person("gaoyeuxiang",45,"girl"));
        persons.add(new Person("fanxiangkui",40,"girl"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("liguiyang",44,"man"));
        persons.add(new Person("gaoyeuxiang",45,"girl"));
        persons.add(new Person("fanxiangkui",40,"girl"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("liguiyang",44,"man"));
        persons.add(new Person("gaoyeuxiang",45,"girl"));
        persons.add(new Person("fanxiangkui",40,"girl"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));
        persons.add(new Person("luyu",42,"man"));

        PersonAdapter personAdapter = new PersonAdapter(this,persons);

//        Spinner spinner = findViewById(R.id.spinner);
//        spinner.setAdapter(personAdapter);
//
//        ListView listView = findViewById(R.id.listView);
//        listView.setAdapter(personAdapter);

        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(personAdapter);
    }
}
