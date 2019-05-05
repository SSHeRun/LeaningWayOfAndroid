package com.example.asnystask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        TextView textView;
        private final String TAG = "MainActivity:";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            textView = findViewById(R.id.textView);

        }

        public void download(View view){
            new MyAsyncTask().execute(0);

        }


        class MyAsyncTask extends AsyncTask<Integer,Integer,Integer> {

            //在子线程
            @Override
            protected Integer doInBackground(Integer... integers) {

                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(100); //1s
                        publishProgress(i);//可以传多个数据
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.d(TAG,"doInBackground:"+Thread.currentThread().getName());
                return 100; //执行完成后返回整数给onPostExecute
            }

            //后面三个在主线程
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                Log.d(TAG,"Thread will run!!!");
                Log.d(TAG,"onPreExecute:"+Thread.currentThread().getName());
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);

                //在主线程可以直接访问textView
                textView.setText("DownLoad finished!!!"+integer);
                Log.d(TAG,"onPostExecute:"+Thread.currentThread().getName());
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                textView.setText("Percent："+values[0]);

                Log.d(TAG,"onProgressUpdate:"+Thread.currentThread().getName());
            }
        }
    }

