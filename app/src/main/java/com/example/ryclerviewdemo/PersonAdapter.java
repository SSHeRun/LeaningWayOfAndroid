package com.example.ryclerviewdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private ArrayList<Person> personArrayList;

    public PersonAdapter(ArrayList<Person> personArrayList) {
        this.personArrayList = personArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView_name;
        TextView textView_age;
        TextView textView_gender;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView_name = itemView.findViewById(R.id.textView1);
            textView_age = itemView.findViewById(R.id.textView2);
            textView_gender = itemView.findViewById(R.id.textView3);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_person,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Person person = personArrayList.get(i);
        viewHolder.textView_name.setText(person.getName());
        viewHolder.textView_age.setText(""+person.getAge());
        viewHolder.textView_gender.setText(person.isGender()?"男":"女");
        viewHolder.imageView.setImageResource(person.isGender()?R.drawable.boy:R.drawable.girl);
    }

    @Override
    public int getItemCount() {
        return personArrayList.size();
    }


}
