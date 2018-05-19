package com.example.elegant_kp.calculator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DBActivity extends AppCompatActivity {

    ListView listView_db = findViewById(R.id.listview_db);
    //操作数据库游标适配器
    SimpleCursorAdapter cursorAdapter;
    SQLiteDatabase db;
    MySQLHelper dbhelper;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        listView_db = findViewById(R.id.listview_db);

        dbhelper = new MySQLHelper(this,"kangping.db",null,1);
        db = dbhelper.getWritableDatabase();

        cursor = db.query("historys",null,null,null,null,null,null);
        cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor,
                new String[] {"result", "date"}, new int[]{android.R.id.text1, android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView_db.setAdapter(cursorAdapter);
        query();
    }

    public void query(){
        cursor = db.query("historys",null,null,null,null,null,null);
        cursorAdapter.swapCursor(cursor);
    }
}
