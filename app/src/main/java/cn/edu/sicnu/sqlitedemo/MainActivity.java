package cn.edu.sicnu.sqlitedemo;

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

    EditText editText_name,editText_age;
    ListView listView;

    //操作数据库游标适配器
    SimpleCursorAdapter cursorAdapter;
    SQLiteDatabase db;
    MySQLHelper dbhelper;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_name = findViewById(R.id.editText_name);
        editText_age = findViewById(R.id.editText_age);
        listView = findViewById(R.id.listView);


        //初始化MySQLHelper对象（建立一个数据库链接/如果没有该数据库，则创建一个数据库）
        dbhelper = new MySQLHelper(this,"lgy.db",null,1);
        //它会调用并返回一个可以读写数据库的对象
        //在第一次调用时会调用onCreate的方法
        //当数据库存在时会调用onOpen方法
       // 结束时调用onClose方法
        db = dbhelper.getWritableDatabase();
//        cursor = db.rawQuery("select * from person",null);
        cursor = db.query("person",null,null,null,null,null,null);


        cursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,new String[]{"name","age"},new int[]{android.R.id.text1,android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(cursorAdapter);
    }

    public void add(View view){
//        db.execSQL("insert into person(name,age) values(?,?)",new Object[]{editText_name.getText().toString(),editText_age.getText().toString()});
        ContentValues contentValues = new ContentValues();   //存储键值对
        contentValues.put("name",editText_name.getText().toString());
        contentValues.put("age",editText_age.getText().toString());

        db.insert("person",null,contentValues);


        cursor = db.query("person",null,null,null,null,null,null);
        cursorAdapter.swapCursor(cursor);//如果游标被放置在一个CursorAdapter中，你应使用swapCursor()方法，以使旧的游标不被关闭

        clearEdit();
    }

    public void delete(View view){
//        db.execSQL("delete from person where name=?",new Object[]{editText_name.getText().toString()});

        db.delete("person","name=?",new String[]{editText_name.getText().toString()});

        cursor = db.query("person",null,null,null,null,null,null);
        cursorAdapter.swapCursor(cursor);
        clearEdit();

    }

    public void update(View view){
//        db.execSQL("update person set age=? where name=?",new Object[]{editText_age.getText().toString(),editText_name.getText().toString()});

        ContentValues contentValues = new ContentValues();
        contentValues.put("age",editText_age.getText().toString());

        db.update("person",contentValues,"name=?",new String[]{editText_name.getText().toString()});

        cursor = db.query("person",null,null,null,null,null,null);
        cursorAdapter.swapCursor(cursor);
        clearEdit();

    }

    public void query(View view){
        cursor = db.query("person",null,null,null,null,null,null);
        cursorAdapter.swapCursor(cursor);
    }

    public void clearEdit(){
        editText_name.setText("");
        editText_age.setText("");
        editText_name.requestFocus();
    }

}
