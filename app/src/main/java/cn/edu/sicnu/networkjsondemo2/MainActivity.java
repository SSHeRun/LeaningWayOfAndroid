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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ListView listView;
    MyAdapter adapter;
    RequestQueue queue;
    ArrayList<Student> student;

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
                try {
                    Student
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    intent.putExtra("student",jsonObject.toString());
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        
    }

    public void downloadJsonData(){
        JsonArrayRequest request = new JsonArrayRequest("http://10.0.1.2/piclist.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                jsonArray = response;
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
            return jsonArray.length();
        }

        @Override
        public Object getItem(int position) {
            try {
                return jsonArray.getJSONObject(position).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
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

            try {
                JSONObject jsonObject = jsonArray.getJSONObject(position);

                viewHolder.textView_name.setText(jsonObject.getString("stuName"));
                viewHolder.textView_stuno.setText(jsonObject.getString("stuNo"));
                viewHolder.textView_gender.setText(jsonObject.getString("stuGender"));


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return convertView;
        }

        class ViewHolder {
            TextView textView_name,textView_stuno,textView_gender;
        }
    }
}
