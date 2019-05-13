package com.example.elegant_kp.calculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ListView listView_result;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    SQLiteDatabase db;
    MySQLHelper dbhelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);

        listView_result = findViewById(R.id.listview_result);

        Intent intent = getIntent();
        arrayList = intent.getStringArrayListExtra("historys");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView_result.setAdapter(arrayAdapter);

        listView_result.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                builder.setTitle("提醒");
                builder.setMessage("是否删除？");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        arrayList.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        Toast.makeText(HistoryActivity.this, "已清空该条记录！", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(HistoryActivity.this, "已取消清空！", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
                return true;
            }
        });

    }

    public void myReturn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_return:
                myReturn();
                break;
            case R.id.menu_clear:
                clear();
                break;
            case R.id.menu_db:
                Intent intent1 = new Intent(this, DBActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
        builder.setTitle("提醒");
        builder.setMessage("是否全部删除？");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                arrayList.clear();
                arrayList.add("列表暂无历史记录！如有需要，可从数据库恢复！");
                arrayAdapter.notifyDataSetChanged();
//                //数据库删除
//                dbhelper = new MySQLHelper(HistoryActivity.this,"thrinity.db",null,1);
//                db = dbhelper.getWritableDatabase();//打开数据库
//                db.delete("historys_db",null, null);
//                Toast.makeText(HistoryActivity.this, "已清空全部历史记录！", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(HistoryActivity.this, "取消清空历史记录！", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }



}
