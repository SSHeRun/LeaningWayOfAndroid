package com.example.elegant_kp.calculator;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BigdataCal extends AppCompatActivity {

    private EditText fisrt,second;
    private RadioGroup radioGroup=null;
    private RadioButton jia,jian,chen,chu,mod;
    private Button bigdatacal;
    private TextView result;
    String history;  //记录单个历史记录
    ArrayList<String> stringList = new ArrayList<>();//历史记录
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigdata_cal);
        fisrt=findViewById(R.id.firstdata);
        second=findViewById(R.id.seconddata);
        radioGroup=findViewById(R.id.radioGroup);
        jia=findViewById(R.id.jia);
        jian=findViewById(R.id.jian);
        chen=findViewById(R.id.chen);
        chu=findViewById(R.id.chu);
        mod=findViewById(R.id.mod);
        bigdatacal=findViewById(R.id.cal_btn);
        result=findViewById(R.id.result);

        bigdatacal.setOnClickListener(onClick);
    }

    private View.OnClickListener onClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            bigdatacal = (Button) v;
            String fisrt_str = fisrt.getText().toString();
            String second_str = second.getText().toString();
            String op="";

            try {
                BigInteger a = new BigInteger(fisrt_str);
                BigInteger b = new BigInteger(second_str);
                BigInteger c = null;
                //  result.setText("test"+radioGroup.getCheckedRadioButtonId());
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.jia:
                        c = a.add(b);
                        op="+";
                        break;
                    case R.id.jian:
                        c = a.subtract(b);
                        op="-";
                        break;
                    case R.id.chen:
                        c = a.multiply(b);
                        op="x";
                        break;
                    case R.id.chu:
                        c = a.divide(b);
                        op="/";
                        break;
                    case R.id.mod:
                        c = a.mod(b);
                        op="%";
                        break;
                    default:
                        break;
                }
                result.setText("result:" + c.toString());
                history=a.toString()+op+b.toString()+"="+c.toString();
                stringList.add(history);
                //保存到数据库
                saveToDb();

        }catch (Exception e){
            System.out.println("输入字段为空！");
        }
   };};
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bigdata_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_return2:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_db2:
                Intent intent1 = new Intent(this, DBActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
