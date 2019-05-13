package com.example.elegant_kp.calculator;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView textView_input, textView_output;
    Button btn_clear, btn_left, btn_right, btn_del, btn_div, btn_mul, btn_sub, btn_add, btn_point, btn_equal;
    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;

    private String str = ""; //保存数字
    private String strOld = ""; //原数字
    private double result = 0; //计算输出结果
    private boolean isHistory = false;//判断有无历史记录
    private boolean flagBoolean = false;//如果为true，可以响应运算消息， 只有前面是数字才可以响应运算消息

    String history;  //记录单个历史记录

    ArrayList<String> stringList = new ArrayList<>();//历史记录


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_input = findViewById(R.id.textView_input);
        textView_output = findViewById(R.id.textView_output);

        btn_0 = findViewById(R.id.zero);
        btn_1 = findViewById(R.id.one);
        btn_2 = findViewById(R.id.two);
        btn_3 = findViewById(R.id.three);
        btn_4 = findViewById(R.id.four);
        btn_5 = findViewById(R.id.five);
        btn_6 = findViewById(R.id.six);
        btn_7 = findViewById(R.id.seven);
        btn_8 = findViewById(R.id.eight);
        btn_9 = findViewById(R.id.nine);
        btn_clear = findViewById(R.id.clear);
        btn_left = findViewById(R.id.left);
        btn_right = findViewById(R.id.right);
        btn_del = findViewById(R.id.del);
        btn_div = findViewById(R.id.div);
        btn_mul = findViewById(R.id.mul);
        btn_add = findViewById(R.id.add);
        btn_sub = findViewById(R.id.sub);
        btn_point = findViewById(R.id.point);
        btn_equal = findViewById(R.id.equal);


        //设置按钮侦听事件
        btn_0.setOnClickListener(listener);
        btn_1.setOnClickListener(listener);
        btn_2.setOnClickListener(listener);
        btn_3.setOnClickListener(listener);
        btn_4.setOnClickListener(listener);
        btn_5.setOnClickListener(listener);
        btn_6.setOnClickListener(listener);
        btn_7.setOnClickListener(listener);
        btn_8.setOnClickListener(listener);
        btn_9.setOnClickListener(listener);


        btn_clear.setOnClickListener(listener);
        btn_left.setOnClickListener(listener);
        btn_right.setOnClickListener(listener);
        btn_del.setOnClickListener(listener);
        btn_div.setOnClickListener(listener);
        btn_mul.setOnClickListener(listener);
        btn_add.setOnClickListener(listener);
        btn_sub.setOnClickListener(listener);
        btn_point.setOnClickListener(listener);
        btn_equal.setOnClickListener(listener);
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.zero:
                    num(0);
                    break;
                case R.id.one:
                    num(1);
                    break;
                case R.id.two:
                    num(2);
                    break;
                case R.id.three:
                    num(3);
                    break;
                case R.id.four:
                    num(4);
                    break;
                case R.id.five:
                    num(5);
                    break;
                case R.id.six:
                    num(6);
                    break;
                case R.id.seven:
                    num(7);
                    break;
                case R.id.eight:
                    num(8);
                    break;
                case R.id.nine:
                    num(9);
                    break;
                case R.id.clear:
                    my_clear();
                    break;
                case R.id.left:
                    putChar('(');
                    break;
                case R.id.right:
                    putChar(')');
                    break;
                case R.id.del:
                    del();
                    break;
                case R.id.div:
                    putChar('/');
                    break;
                case R.id.mul:
                    putChar('*');
                    break;
                case R.id.add:
                    putChar('+');
                    break;
                case R.id.sub:
                    putChar('-');
                    break;
                case R.id.point:
                    putChar('.');
                    break;
                case R.id.equal:
//                    try{
//                        getResult();
//                    }catch (Exception e)
//                    {
//                        System.out.println("计算错误！");
//                    }
                    getResult();
                    break;
                default:
                        break;

            }
        }
    };


    private void num(int i) {
        str = String.valueOf(i);
        strOld = strOld + str;
        textView_input.setText(strOld);
//        flagBoolean = true;//可以响应运算
    }

    private void putChar(char c) {
        str = String.valueOf(c);
        strOld = strOld + str;
        textView_input.setText(strOld);

    }
  //清除界面c
    private void my_clear() {
        str = strOld = "";
        result = 0;
        flagBoolean = false;
        textView_input.setText("");
        textView_output.setText("0");

    }
    //回退
    private void del() {
        try{
            str = strOld.substring(0, strOld.length() - 1);
            strOld = str;
            textView_input.setText(strOld);
        }
        catch (Exception e){
            System.out.println("输入字段为空！");
        }
    }

    private void getResult() {
        String exp = textView_input.getText().toString();
        Calculate cal = new Calculate();
        double result = cal.getResult(exp);
        textView_output.setText(String.valueOf(result));
        //历史记录
        history = textView_input.getText().toString() + " = " + textView_output.getText().toString();
        if(!isHistory) {
            stringList.clear();
            stringList.add(history);
            isHistory = true;
        }
        else {
            stringList.add(history);
        }
        //保存到数据库
        saveToDb();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.history:
                Intent intent = new Intent(this, HistoryActivity.class);
                if(stringList.isEmpty() && !isHistory) {
                    stringList.add("暂无历史记录！");
                }
                intent.putExtra("historys", stringList);
                startActivity(intent);
                break;
            case R.id.science_model:  //科学模式
                Intent intent1 = new Intent(this, BigdataCal.class);
                startActivity(intent1);
                break;
            case R.id.setting:  //设置
                Intent intent2 = new Intent(this, Setting.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void saveToDb() {
        SQLiteDatabase db;
        MySQLHelper dbhelper;
        dbhelper = new MySQLHelper(this,"thrinity.db",null,1);
        db = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();   //存储键值对

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String temp = df.format(new Date());// new Date()为获取当前系统时间

        contentValues.put("result",history);
        contentValues.put("date",temp);

        db.insert("historys_db", null, contentValues);

    }

}