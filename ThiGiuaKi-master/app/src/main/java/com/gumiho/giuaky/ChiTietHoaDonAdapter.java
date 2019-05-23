package com.gumiho.giuaky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChiTietHoaDonAdapter extends ArrayAdapter<ChiTietHoaDon> {
    Context context;
    int layoutResourceId;
    ArrayList<ChiTietHoaDon> data = null;

    public ChiTietHoaDonAdapter(Context context, int layoutResourceId, ArrayList<ChiTietHoaDon> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convert, ViewGroup parent) {
        ChiTietHoaDonAdapter.ChiTietDonHangHolder holder = new ChiTietHoaDonAdapter.ChiTietDonHangHolder();

        if (convert != null) {
            holder = (ChiTietHoaDonAdapter.ChiTietDonHangHolder) convert.getTag();
        } else {
            convert = LayoutInflater.from(context).inflate(R.layout.item_chitiethoadon, parent, false);
            holder.txt1 = (TextView) convert.findViewById(R.id.hd_mahd);
            holder.txt2 = (TextView) convert.findViewById(R.id.mh_mamh);
            holder.txt3 = (TextView) convert.findViewById(R.id.soluong);

            convert.setTag(holder);
        }
        ChiTietHoaDon cthd = data.get(position);

        holder.txt1.setText("Số HD: " + cthd.getSoHD());
        holder.txt2.setText("Mã hàng: " + cthd.getMaHang());
        holder.txt3.setText("Số lượng: " + cthd.getSoLuong());


        return convert;
    }
    public class ChiTietDonHangHolder {
        TextView txt1, txt2, txt3;
    }
}
