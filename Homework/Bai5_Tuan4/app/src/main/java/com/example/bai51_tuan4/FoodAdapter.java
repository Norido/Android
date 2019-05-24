package com.example.bai51_tuan4;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<list> {
    public Context context;
    public int layoutResourceID;
    public ArrayList<list> data = null;

    public FoodAdapter(Context context, int layoutResourceID, ArrayList<list> data){
        super(context, layoutResourceID, data);
        this.layoutResourceID = layoutResourceID;
        this.context = context;
        this.data = data;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FoodHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceID, parent, false);

            holder = new FoodHolder();
            holder.imageIcon = row.findViewById(R.id.list_image);
            holder.txtTitle = row.findViewById(R.id.txttilte);

            row.setTag(holder);
        }
        else {
            holder = (FoodHolder)row.getTag();
        }
        list item = data.get(position);
        holder.imageIcon.setImageResource(item.icon);
        holder.txtTitle.setText(item.title);

        return row;
    }
    static class FoodHolder
    {
        ImageView imageIcon;
        TextView txtTitle;
    }
}
