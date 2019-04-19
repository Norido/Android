package com.gumiho.quanlimonhoc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MonHocAdapter extends ArrayAdapter<MonHoc> {
    Context context;
    int layoutResourceId;
    ArrayList<MonHoc> data = null;

    public MonHocAdapter(Context context, int layoutResourceId, ArrayList<MonHoc> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }



    @SuppressLint("SetTextI18n")
    public View getView(int position, View convert, ViewGroup parent) {
        MonHocHolder holder = new MonHocHolder();

        if (convert != null) {
            holder = (MonHocHolder) convert.getTag();
        } else {
            convert = LayoutInflater.from(context).inflate(R.layout.list_item_row,parent,false);

            holder.img = (ImageView) convert.findViewById(R.id.tdc);
            holder.txt1 = (TextView) convert.findViewById(R.id.mamh);
            holder.txt2 = (TextView) convert.findViewById(R.id.tenmh);
            holder.txt3 = (TextView) convert.findViewById(R.id.sotiet);

            convert.setTag(holder);
        }

        MonHoc mh = data.get(position);

        holder.img.setImageResource(R.drawable.logo);
        holder.txt1.setText("Mã MH: " + mh.getMa());
        holder.txt2.setText("Tên MH: " + mh.getTen());
        holder.txt3.setText("Số Tiết: " + mh.getSotiet());

        return convert;
    }
    public class MonHocHolder {
        ImageView img;
        TextView txt1, txt2, txt3;
    }
}
