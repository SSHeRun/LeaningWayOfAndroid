package cn.edu.sicnu.networkjsondemo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ListView listView;
    MyAdapter adapter;
    RequestQueue queue;
    ArrayList<Student> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        queue = Volley.newRequestQueue(this);

        downloadJsonData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Student student = students.get(position);
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("student", student);
                startActivity(intent);

            }
        });

    }

    public void downloadJsonData(){
        JsonArrayRequest request = new JsonArrayRequest("http://10.0.1.2/piclist.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type type = new TypeToken<ArrayList<Student>>(){}.getType();
                Gson gson = new Gson();
                students = gson.fromJson(response.toString(),type);

                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }


    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return students.size();
        }

        @Override
        public Object getItem(int position) {
            return students.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView==null){
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.stulayout,null);
                viewHolder = new ViewHolder();
                viewHolder.textView_name = convertView.findViewById(R.id.textView_name);
                viewHolder.textView_stuno = convertView.findViewById(R.id.textView_stuno);
                viewHolder.textView_gender = convertView.findViewById(R.id.textView_gender);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Student student = students.get(position);

            viewHolder.textView_name.setText(student.stuName);
            viewHolder.textView_stuno.setText(student.stuNo);
            viewHolder.textView_gender.setText(student.stuGender);


            return convertView;
        }

        class ViewHolder {
            TextView textView_name,textView_stuno,textView_gender;
        }
    }
}
