package cn.edu.sicnu.musicservicedemo;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }

    }

    public void startService(View view){
        Intent intent = new Intent(this,MusicService.class);
        startService(intent);
    }
    public void stopService(View view){
        Intent intent = new Intent(this,MusicService.class);
        stopService(intent);
    }

    public void playMusic(View view){
        Intent intent = new Intent(this,MusicService.class);
        intent.putExtra("operate",1);
        startService(intent);

    }
    public void nextMusic(View view){
        Intent intent = new Intent(this,MusicService.class);
        intent.putExtra("operate",2);
        startService(intent);
    }
    public void prevMusic(View view){
        Intent intent = new Intent(this,MusicService.class);
        intent.putExtra("operate",3);
        startService(intent);
    }


}
