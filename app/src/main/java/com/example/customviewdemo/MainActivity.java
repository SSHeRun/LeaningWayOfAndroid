package com.example.customviewdemo;

import android.os.PersistableBundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");

        textView  = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("I am clicked!");
            }
        });


        if(savedInstanceState!=null){
            textView.setText(savedInstanceState.getString("name"));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");
        outState.putString("name",textView.getText().toString());
        super.onSaveInstanceState(outState);

    }




    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }


    public void add(View v){
        CircleView circleView = new CircleView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(100,100);
        circleView.setLayoutParams(layoutParams);

        ViewGroup viewGroup = findViewById(R.id.constraintLayout);
        viewGroup.addView(circleView);


        Animation animation = new TranslateAnimation(0,viewGroup.getWidth(),0,0);
        animation.setDuration(2000);
        animation.setRepeatCount(Animation.INFINITE);
        circleView.startAnimation(animation);

    }

    public void clear(View v){
        ViewGroup viewGroup = findViewById(R.id.constraintLayout);
        viewGroup.removeAllViews();
        viewGroup.clearDisappearingChildren();
    }
}
