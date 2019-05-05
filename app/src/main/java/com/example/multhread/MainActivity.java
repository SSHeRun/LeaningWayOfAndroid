package com.example.multhread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        handler = new MyHandler(this);


    }
    //创建线程的视图不能访问其他视图，通过hander弱引用
    public void download(View view){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(100); //1s
//                      访问其他视图
                        handler.sendEmptyMessage(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    //用弱引用没有警告
    static class MyHandler extends Handler{
        private WeakReference<MainActivity> activityWeakReference;

        MyHandler(MainActivity activity){
            this.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = "Percent:"+msg.what;
            activityWeakReference.get().textView.setText(str);
        }
    }

}
