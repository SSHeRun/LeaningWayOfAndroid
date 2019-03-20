package com.example.menudemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.hello_textview);//找到textview
        registerForContextMenu(textView);//注册上下文菜单
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        menu.add("other");
        return super.onCreateOptionsMenu(menu);
    }//菜单响应

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,"you choose "+item.getTitle(),Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }//可选按钮响应

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }//重载上下文菜单

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(this,"you choose "+item.getTitle(),Toast.LENGTH_LONG).show();
        return super.onContextItemSelected(item);
    }//上下文菜单选项按钮响应

    public void showpop(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.inflate(R.menu.main);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {//按键响应
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(MainActivity.this,"you choose "+menuItem.getTitle(),Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }//按键弹出对话框

    public void showdialog1(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录");
        builder.setMessage("请确认是否登录");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"you choose 确定",Toast.LENGTH_LONG).show();
            }
        });//确定按钮响应
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"you choose 取消",Toast.LENGTH_LONG).show();
            }
        });//取消按钮响应
        builder.show();
    }//单选框

    public void showdialog2(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录");
        final String[] strings = {"1","2","3","4"};
        final boolean[] choosen = { false,false,false,false};
        builder.setMultiChoiceItems(strings, choosen, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String context = "";
                for(int i1= 0;i1<choosen.length;i1++){
                    if(choosen[i1]){
                        context += strings[i1];
                    }
                }
                Toast.makeText(MainActivity.this,"you choose "+context,Toast.LENGTH_LONG).show();
            }
        });//确定按钮响应
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"you choose 取消",Toast.LENGTH_LONG).show();
            }
        });//取消按钮响应
        builder.show();
    }//多选框
}
