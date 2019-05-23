package com.gumiho.giuaky;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HoaDonAdapter extends ArrayAdapter<HoaDon> {
    Context context;
    int layoutResourceId;
    ArrayList<HoaDon> data = null;

    public HoaDonAdapter(Context context, int layoutResourceId, ArrayList<HoaDon> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convert, ViewGroup parent) {
        HoaDonAdapter.HoaDonHolder holder = new HoaDonAdapter.HoaDonHolder();

        if (convert != null) {
            holder = (HoaDonAdapter.HoaDonHolder ) convert.getTag();
        } else {
            convert = LayoutInflater.from(context).inflate(R.layout.item_hoadon, parent, false);
            holder.txt1 = (TextView) convert.findViewById(R.id.sohd);
            holder.txt2 = (TextView) convert.findViewById(R.id.ngaylap);
            holder.txt3 = (TextView) convert.findViewById(R.id.ngaygiao);
            holder.txt4 = (TextView) convert.findViewById(R.id.makh_hoadon);

            convert.setTag(holder);
        }

        HoaDon hd = data.get(position);

//        holder.img.setImageResource(R.drawable.logo);
        holder.txt1.setText("Số HD: " + hd.getSoHD());
        holder.txt2.setText("Ngày lập: " + hd.getNgayLap());
        holder.txt3.setText("Ngày giao: " + hd.getNgayGiao());
        holder.txt4.setText("Mã KH: " + hd.getMaKH());

        return convert;
    }

    public class HoaDonHolder {
        TextView txt1, txt2, txt3, txt4;
    }
}
