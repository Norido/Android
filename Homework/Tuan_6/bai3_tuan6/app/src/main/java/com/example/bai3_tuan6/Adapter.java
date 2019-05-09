package com.example.bai3_tuan6;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<film> {
    Context context;
    int layoutResourceId;
    ArrayList<film> data = null;

    public Adapter(Context context, int layoutResourceId, ArrayList<film> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    static class Holder {
        TextView des,name;
        ImageView icon;
        RatingBar ratingbar;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView != null) {
            holder = (Holder) convertView.getTag();
        } else {
            holder = new Holder();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_row, parent, false);
            holder.des = (TextView) convertView.findViewById(R.id.txt_des);
            holder.ratingbar = (RatingBar) convertView.findViewById(R.id.rating);
            holder.name = (TextView) convertView.findViewById(R.id.txt_name);
            holder.icon = (ImageView) convertView.findViewById(R.id.img_show);
            convertView.setTag(holder);
        }
        film fl = data.get(position);
        holder.des.setText(fl.getDes()) ;
        holder.ratingbar.setRating((float)fl.getRatingbar());
        holder.name.setText(fl.getName());
        holder.icon.setImageResource(fl.getIcon());
        return convertView;
    }
}
