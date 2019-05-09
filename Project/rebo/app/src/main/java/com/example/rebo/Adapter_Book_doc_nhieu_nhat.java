package com.example.rebo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class Adapter_Book_doc_nhieu_nhat extends BaseAdapter {
    private Context context;
    private ArrayList<Book> listSach;

    public Adapter_Book_doc_nhieu_nhat(Context context, ArrayList<Book> listSach) {
        this.context = context;
        this.listSach = listSach;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sach,parent,false);
            holder = new ViewHolder();
            holder.biaSach = (ImageView)convertView.findViewById(R.id.item_book);
            holder.tenSach = (TextView)convertView.findViewById(R.id.item_title);
            holder.tacGia = (TextView)convertView.findViewById(R.id.item_author);
            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        Book item = this.listSach.get(position);
        holder.biaSach.setImageResource(item.getBiaSach());
        holder.tenSach.setText(item.getTenSach());
        holder.tacGia.setText(item.getTacGia());
        return convertView;
    }
    static class ViewHolder {
        ImageView biaSach;
        TextView tenSach;
        TextView tacGia;
    }
}