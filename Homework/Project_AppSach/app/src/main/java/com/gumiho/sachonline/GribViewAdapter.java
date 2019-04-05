package com.gumiho.sachonline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class GribViewAdapter extends BaseAdapter {
    private Context content;
    private ArrayList<GribView> listSach;
    private LayoutInflater layoutInflater;

    public GribViewAdapter (Context content, ArrayList<GribView> listSach){
        this.content = content;
        this.listSach = listSach;
        layoutInflater = LayoutInflater.from(content);
    }

    @Override
    public int getCount() {
        return listSach.size();
    }

    @Override
    public Object getItem(int position) {
        return listSach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null) {
            convertView = layoutInflater.from(content).inflate(R.layout.layout_gridview,null);
            holder = new ViewHolder();
            holder.biaSach = (ImageButton)convertView.findViewById(R.id.gridview_biasach);
            holder.tenSach = (TextView)convertView.findViewById(R.id.gridview_tensach);
            holder.tacGia = (TextView)convertView.findViewById(R.id.gridview_tacgia);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        GribView item = this.listSach.get(position);
        holder.biaSach.setImageResource(item.getBiaSach());
        holder.tenSach.setText(item.getTenSach());
        holder.tacGia.setText(item.getTacGia());
        return convertView;
    }
    static class ViewHolder {
        ImageButton biaSach;
        TextView tenSach;
        TextView tacGia;
    }
}
