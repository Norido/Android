package com.gumiho.giuaky;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MatHangAdapter extends ArrayAdapter<MatHang> {
    Context context;
    int layoutResourceId;
    ArrayList<MatHang> data = null;

    public MatHangAdapter(Context context, int layoutResourceId, ArrayList<MatHang> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View convert, ViewGroup parent) {
        MatHangAdapter.MatHangHolder holder = new MatHangAdapter.MatHangHolder();

        if (convert != null) {
            holder = (MatHangAdapter.MatHangHolder) convert.getTag();
        } else {
            convert = LayoutInflater.from(context).inflate(R.layout.item_mathang, parent, false);
            holder.txt1 = (TextView) convert.findViewById(R.id.mamh);
            holder.txt2 = (TextView) convert.findViewById(R.id.tenmh);
            holder.txt3 = (TextView) convert.findViewById(R.id.dvt);
            holder.txt4 = (TextView) convert.findViewById(R.id.dongia);

            convert.setTag(holder);
        }

        MatHang mh = data.get(position);

//        holder.img.setImageResource(R.drawable.logo);
        holder.txt1.setText("Mã Hàng: " + mh.getMaHang());
        holder.txt2.setText("Tên Hàng: " + mh.getTenHang());
        holder.txt3.setText("Đơn Vị Tính: " + mh.getDVT());
        holder.txt4.setText("Đơn Giá: " + mh.getDonGia());

        return convert;
    }

    public class MatHangHolder {
        TextView txt1, txt2, txt3, txt4;
    }
}
