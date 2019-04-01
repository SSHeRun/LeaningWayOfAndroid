package com.example.listandrrecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> namelist = new ArrayList<>();

        namelist.add("aaa");
        namelist.add("bbb");
        namelist.add("ccc");
        namelist.add("ddd");

        namelist.add("aaa");
        namelist.add("bbb");
        namelist.add("ccc");
        namelist.add("ddd");

        namelist.add("aaa");
        namelist.add("bbb");
        namelist.add("ccc");
        namelist.add("ddd");

        namelist.add("aaa");
        namelist.add("bbb");
        namelist.add("ccc");
        namelist.add("ddd");

        ListView listView = findViewById(R.id.listview);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,namelist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"you choose "+namelist.get(i),Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                namelist.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
