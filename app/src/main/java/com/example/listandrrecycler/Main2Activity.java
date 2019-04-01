package com.example.listandrrecycler;

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

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ArrayList<String> numlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        numlist.add("111");
        numlist.add("222");
        numlist.add("333");
        numlist.add("444");
        numlist.add("555");
        numlist.add("666");
        numlist.add("777");
        numlist.add("888");
        numlist.add("111");
        numlist.add("222");
        numlist.add("333");
        numlist.add("444");
        numlist.add("555");
        numlist.add("666");
        numlist.add("777");
        numlist.add("888");
        numlist.add("111");
        numlist.add("222");
        numlist.add("333");
        numlist.add("444");
        numlist.add("555");
        numlist.add("666");
        numlist.add("777");
        numlist.add("888");
        numlist.add("111");
        numlist.add("222");
        numlist.add("333");
        numlist.add("444");
        numlist.add("555");
        numlist.add("666");
        numlist.add("777");
        numlist.add("888");

        MyAdater adater = new MyAdater(numlist);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);

        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adater);
    }
    class MyAdater extends RecyclerView.Adapter<MyAdater.ViewHolder>{

        private ArrayList<String> list;
        public MyAdater(ArrayList<String> list) {
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
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
            viewHolder.textView.setText(list.get(i));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
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
