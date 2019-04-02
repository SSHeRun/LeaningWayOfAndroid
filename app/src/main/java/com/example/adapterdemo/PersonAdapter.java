package com.example.adapterdemo;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonAdapter extends BaseAdapter {

    private ArrayList<Person> persons;
    private Context context;

    private static final String TAG = "PersonAdapter";
    private int count = 0;

    public PersonAdapter(Context context,ArrayList<Person> persons) {
        this.persons = persons;
        this.context = context;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_person, null);
            viewHolder = new ViewHolder();
            viewHolder.textView_name = view.findViewById(R.id.textView_name);
            viewHolder.textView_age = view.findViewById(R.id.textView_other);
            viewHolder.imageView = view.findViewById(R.id.imageView);
            view.setTag(viewHolder);

            count++;
            Log.d(TAG, "getView: "+count);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        Person person = (Person) getItem(position);
        viewHolder.textView_name.setText(person.getName());

        viewHolder.textView_age.setText(""+person.getAge());

        if (person.getGender() == "man") {
            viewHolder.imageView.setImageResource(R.drawable.boy);
        } else {
            viewHolder.imageView.setImageResource(R.drawable.girl);
        }
        return view;
    }

    class ViewHolder {
        TextView textView_name;
        TextView textView_age;
        ImageView imageView;
    }
}
