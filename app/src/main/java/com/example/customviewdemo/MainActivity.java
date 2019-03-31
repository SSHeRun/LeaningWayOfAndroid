package com.example.customviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class MainActivity extends AppCompatActivity {

    ViewGroup viewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewGroup = findViewById(R.id.constraintLayout);
    }

    public void add(View v){
        CircleView circleView = new CircleView(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100,100);
        circleView.setLayoutParams(params);

        viewGroup.addView(circleView);
        TranslateAnimation animation = new TranslateAnimation(0,viewGroup.getWidth(),0,0);
        animation.setDuration(2000);
        animation.setRepeatCount(Animation.INFINITE);

        circleView.setAnimation(animation);
    }

    public  void  clear(View v){
        viewGroup.removeAllViews();
        viewGroup.clearDisappearingChildren();
    }
}
