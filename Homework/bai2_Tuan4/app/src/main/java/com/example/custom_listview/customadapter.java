package com.example.custom_listview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class customadapter extends ArrayAdapter<fruit> {
    private Context context;
    private int layoutid;
    private ArrayList<fruit> arr = null;

    public customadapter(Context context, int id, ArrayList<fruit> arr){
        super(context,id,arr);
        this.context = context;
        this.layoutid = id;
        this.arr = arr;
    }
    @Override
    public View getView(int pos, View convert, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convert == null) {
            convert = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false);
            viewHolder.img = (ImageView) convert.findViewById(R.id.img1);
            viewHolder.tv1 = (TextView) convert.findViewById(R.id.tv1);
            viewHolder.tv2 = (TextView) convert.findViewById(R.id.tv2);

            convert.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convert.getTag();


        fruit fr = arr.get(pos);
        viewHolder.img.setImageResource(fr.getimg());
        viewHolder.tv1.setText(fr.getName_fruit());
        viewHolder.tv2.setText(fr.getDescription());
        return convert;
    }
    public class ViewHolder {
        ImageView img;
        TextView tv1;
        TextView tv2;
    }
}
