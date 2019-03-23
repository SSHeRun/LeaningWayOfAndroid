package com.example.adapterdemo2;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonAdpter extends BaseAdapter {


    private ArrayList<Person> pensons;
    private Context context;
    private int count = 0;
    //调试调用次数
    private static final String TAG = "PersonAdpter";


    public PersonAdpter( Context context,ArrayList<Person> pensons) {
        this.pensons = pensons;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pensons.size();
    }

    @Override
    public Object getItem(int i) {
        return pensons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;
        ViewHolder viewHolder;
        if(view==null) {
            count++;
            Log.d(TAG,"getview "+count);//调试信息
            view1 = LayoutInflater.from(context).inflate(R.layout.layout_person,null);
            viewHolder = new ViewHolder();
            viewHolder.textView_name = view1.findViewById(R.id.text1);
            viewHolder.textView_age = view1.findViewById(R.id.text2);
            viewHolder.textView_gender = view1.findViewById(R.id.text3);
            viewHolder.imageView = view1.findViewById(R.id.imageview);
            view1.setTag(viewHolder);
        }else {
            view1 = view;
            viewHolder = (ViewHolder)view1.getTag();
        }//简化重复调用


//        TextView textView_name = view1.findViewById(R.id.text1);
//        TextView textView_age = view1.findViewById(R.id.text2);
//        TextView textView_gender = view1.findViewById(R.id.text3);
//        ImageView imageView = view1.findViewById(R.id.imageview);

        Person person = (Person)getItem(i);
        viewHolder.textView_name.setText(person.getName());
        viewHolder.textView_age.setText(" "+person.getAge());
        viewHolder.textView_gender.setText(person.isGender()?"man":"woman");
        if (person.isGender()){
            viewHolder.imageView.setImageResource(R.drawable.man);
        }else {
            viewHolder.imageView.setImageResource(R.drawable.woman);
        }
        return view1;
    }

    class ViewHolder{
        TextView textView_name ;
        TextView textView_age ;
        TextView textView_gender;
        ImageView imageView ;
    }//代码重用
}
