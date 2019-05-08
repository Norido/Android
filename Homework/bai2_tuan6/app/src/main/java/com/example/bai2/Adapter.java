package com.example.bai2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Player> {
    Context context;
    int layoutResourceId;
    ArrayList<Player> data = null;

    public Adapter(Context context, int layoutResourceId, ArrayList<Player> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    static class Holder {
        TextView medal, home,name;
        ImageView icon;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView != null) {
            holder = (Holder) convertView.getTag();
        } else {
            holder = new Holder();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_row, parent, false);
            holder.medal = (TextView) convertView.findViewById(R.id.txt_medal);
            holder.home = (TextView) convertView.findViewById(R.id.txt_home);
            holder.name = (TextView) convertView.findViewById(R.id.txt_name);
            holder.icon = (ImageView) convertView.findViewById(R.id.img_show);
            convertView.setTag(holder);
        }
        Player pl = data.get(position);
        holder.medal.setText(pl.getMedal()) ;
        holder.home.setText(pl.getHome());
        holder.name.setText(pl.getName());
        holder.icon.setImageBitmap(pl.getBitmap());
        return convertView;
    }
}
