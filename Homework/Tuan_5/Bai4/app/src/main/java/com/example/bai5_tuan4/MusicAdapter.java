package com.example.bai5_tuan4;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MusicAdapter extends ArrayAdapter<listArray> {
    public Context context;
    public int layoutResourceID;
    public ArrayList<listArray> data = null;

    public MusicAdapter(Context context, int layoutResourceID, ArrayList<listArray> data){
        super(context, layoutResourceID, data);
        this.layoutResourceID = layoutResourceID;
        this.context = context;
        this.data = data;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MucsicHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceID, parent, false);

            holder = new MucsicHolder();
            holder.imageIcon = row.findViewById(R.id.list_image);
            holder.txtTitle = row.findViewById(R.id.txttilte);
            holder.txtArtist = row.findViewById(R.id.artist);
            holder.txtduration = row.findViewById(R.id.duration);
            holder.arrow = row.findViewById(R.id.arrow);

            row.setTag(holder);
        }
        else {
            holder = (MucsicHolder)row.getTag();
        }
        listArray item = data.get(position);
        holder.imageIcon.setImageResource(item.icon);
        holder.txtTitle.setText(item.title);
        holder.txtduration.setText(item.duration);
        holder.txtArtist.setText(item.artist);

        return row;
    }
    static class MucsicHolder
    {
        ImageView imageIcon;
        TextView txtTitle;
        TextView txtArtist;
        TextView txtduration;
        ImageView arrow;
    }
}
