package com.example.custom_listview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class customadapter extends ArrayAdapter<football> {
    private Context context;
    private int layoutid;
    private ArrayList<football> arr = null;

    public customadapter(Context context, int id, ArrayList<football> arr){
        super(context,id,arr);
        this.context = context;
        this.layoutid = id;
        this.arr = arr;
    }
    @Override
    public View getView(int pos, View convert, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convert == null) {
            convert = LayoutInflater.from(context).inflate(R.layout.list2_row, parent, false);
            viewHolder.img = (ImageView) convert.findViewById(R.id.img2);
            viewHolder.tv1 = (TextView) convert.findViewById(R.id.tv1);
            viewHolder.tv2 = (TextView) convert.findViewById(R.id.tv2);
            viewHolder.img2 = (ImageView) convert.findViewById(R.id.img3);

            convert.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convert.getTag();


        football fr = arr.get(pos);
        viewHolder.img.setImageResource(fr.getImg());
        viewHolder.img2.setImageResource(fr.getImg2());
        viewHolder.tv1.setText(fr.getName_football());
        viewHolder.tv2.setText(fr.getDescription());
        return convert;
    }
    public class ViewHolder {
        ImageView img;
        TextView tv1;
        TextView tv2;
        ImageView img2;
    }
}
