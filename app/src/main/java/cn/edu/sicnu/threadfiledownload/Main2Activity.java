package cn.edu.sicnu.threadfiledownload;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

//    private static final String TAG = "Main2Activity";
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        layout = findViewById(R.id.layout);

        Intent intent = getIntent();
        ArrayList<String> images = (ArrayList<String>) intent.getSerializableExtra("images");
        for(int i=0;i<images.size();i++){
            String imagePath = images.get(i);
//            Log.d(TAG, "onCreate: "+imagePath + "第" + i);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(bitmap);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setAdjustViewBounds(true);

            layout.addView(imageView);
        }
    }
}
