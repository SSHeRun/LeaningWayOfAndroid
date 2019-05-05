package cn.edu.sicnu.musicservicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service {

    private static final String TAG = "MusicService";

    ArrayList<String> musicList = new ArrayList<>();
    ArrayList<String> musicNameList = new ArrayList<>();
    Notification.Builder builder;
    RemoteViews remoteViews;
    MediaPlayer mediaPlayer;
    int current = 0;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getMusicList();
        initNotification();

        mediaPlayer = new MediaPlayer();
        playNew();


    }

    public void initNotification(){
        builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);

        remoteViews = new RemoteViews(getPackageName(),R.layout.musiclayout);
        builder.setContent(remoteViews);

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        Intent intentPlay = new Intent(this,MusicService.class);
        intentPlay.putExtra("operate",1);
        PendingIntent pendingIntentPlay = PendingIntent.getService(this,1,intentPlay,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.button_play,pendingIntentPlay);

        Intent intentNext = new Intent(this,MusicService.class);
        intentNext.putExtra("operate",2);
        PendingIntent pendingIntentNext = PendingIntent.getService(this,2,intentNext,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.button_next,pendingIntentNext);

        Intent intentPrev = new Intent(this,MusicService.class);
        intentPrev.putExtra("operate",3);
        PendingIntent pendingIntentPrev = PendingIntent.getService(this,3,intentPrev,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.button_prev,pendingIntentPrev);

        Intent intentExit = new Intent(this,MusicService.class);
        intentExit.putExtra("operate",4);
        PendingIntent pendingIntentExit = PendingIntent.getService(this,4,intentExit,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.button_exit,pendingIntentExit);

        startForeground(1,builder.build());

    }

    public void getMusicList(){
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext()){
            String musicPath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            musicList.add(musicPath);
            String musicName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            musicNameList.add(musicName);
        }
        cursor.close();

        Log.d(TAG, "getMusicList: "+musicList);
        Log.d(TAG, "getMusicNameList: "+musicNameList);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int operate = intent.getIntExtra("operate",0);
        Log.d(TAG, "onStartCommand: "+operate);
        switch (operate){
            case 1:
                playMusic();
                break;
            case 2:
                nextMusic();
                break;
            case 3:
                prevMusic();
                break;
            case 4:
                stopSelf();
                break;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
    public void playMusic(){
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            remoteViews.setTextViewText(R.id.button_play,"play");
            startForeground(1, builder.build());
        }else{
            mediaPlayer.start();
            remoteViews.setTextViewText(R.id.button_play,"pause");
            startForeground(1, builder.build());   }
    }
    public void nextMusic(){
        current++;
        if(current>=musicList.size()) current = 0;
        playNew();
    }
    public void prevMusic(){
        current--;
        if(current<0) current = musicList.size() - 1;
        playNew();
    }

    public void playNew(){
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(musicList.get(current));
            mediaPlayer.prepare();
            mediaPlayer.start();
            remoteViews.setTextViewText(R.id.button_play,"pause");
            startForeground(1, builder.build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
