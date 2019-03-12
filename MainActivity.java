package com.example.cardgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //主活动
    int flipCount = 0;
    //定义构造方法
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定Button资源
        btn = (Button) findViewById(R.id.button);
        //设置Button监听
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Button card = (Button) v;
        if (card.getText() == "") {
            card.setText("A🖤");
            card.setBackgroundResource(R.drawable.blankcard);
        } else {
            card.setText("");
            card.setBackgroundResource(R.drawable.stanfordtree);
        }
        flipCount++;
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("已经翻牌了" + flipCount + "次");
    }
}