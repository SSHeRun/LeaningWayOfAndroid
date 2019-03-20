package com.example.scrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    //图片数组
    private  static  final int[] images = {R.drawable.bg_black_small,R.drawable.bg_blue_small,R.drawable.bg_dark_small,R.drawable.bg_green_small,R.drawable.bg_orange_small,R.drawable.bg_pink_small,R.drawable.bg_red_small,R.drawable.bg_turquois_small,R.drawable.bg_violet_small};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageViewmain = findViewById(R.id.imageView);
        LinearLayout linearLayout = findViewById(R.id.imagelayout);
        for(int i = 0;i<images.length;i++){
            final ImageView imageView=new ImageView(this);
            imageView.setImageResource(images[i]);
            imageView.setAdjustViewBounds(true);
            imageView.setPadding(20,20,20,20);
            linearLayout.addView(imageView);
            imageView.setTag(i);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = (int) view.getTag();
                    imageViewmain.setImageResource(images[index]);
                }
            });
        }
    }
}
