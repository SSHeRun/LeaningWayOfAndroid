package com.example.sqlitedemo2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    MySQLHelper mySQLHelper;
    SQLiteDatabase db;
    EditText editText_name,editText_age;
    ListView listView;

    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_name = findViewById(R.id.editText_name);
        editText_age = findViewById(R.id.editText_age);
        listView = findViewById(R.id.listView);

        mySQLHelper = new MySQLHelper(this,"db",null,1);
        db = mySQLHelper.getWritableDatabase();
        Cursor cursor = db.query(MySQLHelper.PersonTable,null,null,null,null,null,null,null);

        adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,new String[]{"name","age"},new int[]{android.R.id.text1,android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
    }

    public void add(View view){
        String name = editText_name.getText().toString();
        String age = editText_age.getText().toString();
//        db.execSQL("insert into person(name,age) values('"+name+"','"+age+"')");
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("age",age);
        db.insert(MySQLHelper.PersonTable,null,contentValues);

        reload();

    }

    public void delete(View view){
        String name = editText_name.getText().toString();
        db.delete(MySQLHelper.PersonTable,"name=?",new String[]{name});
        reload();
    }


    public void update(View view){
        String name = editText_name.getText().toString();
        String age = editText_age.getText().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("age",age);
        db.update(MySQLHelper.PersonTable,contentValues,"name=?",new String[]{name});
        reload();
    }

    public void retrieve(View view){
        String name = editText_name.getText().toString();
        Cursor cursor=db.query(MySQLHelper.PersonTable,null,"name like *",new String[]{name},null,null,null,null);
        adapter.swapCursor(cursor);
    }


    private void reload() {
        Cursor cursor = db.query(MySQLHelper.PersonTable,null,null,null,null,null,null,null);
        adapter.swapCursor(cursor);
    }
}
