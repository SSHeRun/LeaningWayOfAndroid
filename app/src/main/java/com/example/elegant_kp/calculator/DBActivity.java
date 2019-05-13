package com.example.elegant_kp.calculator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DBActivity extends AppCompatActivity {

    ListView listView_db;
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

        dbhelper = new MySQLHelper(this,"thrinity.db",null,1);
        db = dbhelper.getWritableDatabase();

        cursor = db.query("historys_db",null,null,null,null,null,null);
        cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor,
                new String[] {"result", "date"}, new int[]{android.R.id.text1, android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView_db.setAdapter(cursorAdapter);
        query();
    }

    public void query(){
        cursor = db.query("historys_db",null,null,null,null,null,null);
        cursorAdapter.swapCursor(cursor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.db_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_return:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_clear:
                db_clear();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void db_clear() {
         dbhelper = new MySQLHelper(DBActivity.this,"thrinity.db",null,1);
         db = dbhelper.getWritableDatabase();//打开数据库
         db.delete("historys_db",null, null);
         cursor = db.query("historys_db",null,null,null,null,null,null);
         cursorAdapter.swapCursor(cursor);
         Toast.makeText(DBActivity.this, "已清空数据库！", Toast.LENGTH_SHORT).show();
    }

}
