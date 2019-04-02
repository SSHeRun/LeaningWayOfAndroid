package com.example.listdemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        arrayList.add("1234564");
        arrayList.add("1111111");
        arrayList.add("2222222");
        arrayList.add("333333");
        arrayList.add("12312134654");
        arrayList.add("44444");
        arrayList.add("555555");
        arrayList.add("66666");
        arrayList.add("1234564");
        arrayList.add("1111111");
        arrayList.add("2222222");
        arrayList.add("333333");
        arrayList.add("12312134654");
        arrayList.add("44444");
        arrayList.add("555555");
        arrayList.add("66666");
        arrayList.add("1234564");
        arrayList.add("1111111");
        arrayList.add("2222222");
        arrayList.add("333333");
        arrayList.add("12312134654");
        arrayList.add("44444");
        arrayList.add("555555");
        arrayList.add("66666");
        arrayList.add("1234564");
        arrayList.add("1111111");
        arrayList.add("2222222");
        arrayList.add("333333");
        arrayList.add("12312134654");
        arrayList.add("44444");
        arrayList.add("555555");
        arrayList.add("66666");


        MyAdapter adapter = new MyAdapter(arrayList);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
//        GridLayoutManager manager = new GridLayoutManager(this,3);
//        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);


    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        ArrayList<String> list;

        public MyAdapter(ArrayList<String> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout,viewGroup,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
            viewHolder.textView.setText(list.get(i));

            viewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Main2Activity.this,"you choose "+list.get(i),Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    list.remove(i);
                    MyAdapter.this.notifyDataSetChanged();
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.imageView);
                textView = itemView.findViewById(R.id.textView);
            }
        }
    }
}
