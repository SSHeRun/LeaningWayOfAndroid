package cn.edu.sicnu.networkjsondemo2;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    TextView textView_name,textView_stuno,textView_gender,textView_class;
    ImageView imageView;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView_name = findViewById(R.id.textView_name);
        textView_stuno = findViewById(R.id.textView_stuno);
        textView_gender = findViewById(R.id.textView_gender);
        textView_class = findViewById(R.id.textView_class);
        imageView = findViewById(R.id.imageView);

        Student student = (Student) getIntent().getSerializableExtra("student");

        textView_name.setText(student.stuName);
        textView_stuno.setText(student.stuNo);
        textView_gender.setText(student.stuGender);
        textView_class.setText(student.stuClass);

        queue = Volley.newRequestQueue(this);

        ImageRequest request = new ImageRequest("http://10.0.1.2/" + student.imagePath, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);


    }
}
