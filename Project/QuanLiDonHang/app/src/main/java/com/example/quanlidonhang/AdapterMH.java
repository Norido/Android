package com.example.quanlidonhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterMH extends BaseAdapter {
    Context context;
    ArrayList<MatHang> list;

    public AdapterMH(Context context, ArrayList<MatHang> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.mathang_layout,null);
        TextView textMaMH = (TextView)view.findViewById(R.id.txt_mahang);
        TextView textTenMH = (TextView)view.findViewById(R.id.txt_tenhang);
        TextView textDVT = (TextView)view.findViewById(R.id.txt_dvt);
        TextView textST = (TextView)view.findViewById(R.id.txt_gia);

        MatHang matHang= list.get(position);
        textMaMH.setText(matHang.maMatHang);
        textTenMH.setText(matHang.tenMatHang);
        textDVT.setText(matHang.donViTinh);
        textST.setText(matHang.soTien);
        return view;
    }
}
