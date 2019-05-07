package com.example.herun;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static java.security.spec.PSSParameterSpec.DEFAULT;

public class MainActivity extends AppCompatActivity {

    EditText number1 ;
    EditText number2 ;
    TextView result ;
    ArrayList<String> jisuan  = new ArrayList<>();;
    RemoteViews remoteViews;
    NotificationManager manager;
    final static String myChannel = "计算";
    NotificationCompat.Builder builder;
    FileOutputStream fileOutputStream;
    File file;
    String filename;
    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }


        number1 = findViewById(R.id.editText1);
        number2 = findViewById(R.id.editText2);
        result = findViewById(R.id.textView_result);

        //准备好外部存储文件
        file= getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        filename = file.getAbsolutePath()+"/1.txt";


        int len = -1;
        byte[] bytes = new byte[100];  //字节数组
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filename);
            while ((len=inputStream.read(bytes))!=-1){   //read按字节读取

                try {
                    String srt2=new String(bytes,"UTF-8");
                    jisuan.add(srt2);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public void add(View view) throws IOException {

        int num1 = Integer.parseInt(number1.getText().toString());
        int num2 = Integer.parseInt(number2.getText().toString());
        int all = num1+num2;
        result.setText("Result is:"+all);
        res="Result is:"+ all;

        jisuan.add(res);

        byte[] midbytes=res.getBytes("UTF8");
        try {
            fileOutputStream = new FileOutputStream(filename,true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fileOutputStream.write(midbytes,0,res.length());
        fileOutputStream.close();

        sendNotification();
    }

    public void history(View view) throws IOException {

        Intent intent = new Intent(MainActivity.this,history.class);
        intent.putStringArrayListExtra("history",jisuan);
        startActivity(intent);

    }

    public void sendNotification() {

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(myChannel, "计算结果", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(this, myChannel);
            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("计算结果"+res);
            manager.notify(1, builder.build());
        }
    }
}
