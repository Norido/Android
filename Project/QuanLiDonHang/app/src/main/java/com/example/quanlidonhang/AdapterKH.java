package com.example.quanlidonhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;

public class AdapterKH extends BaseAdapter {
    Context context;
    ArrayList<KhachHang> list;
    public AdapterKH(Context context, ArrayList<KhachHang> list) {
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
        View view = layoutInflater.inflate(R.layout.activity_them,null);
        EditText textMaKH = (EditText)view.findViewById(R.id.edt_makh);
        EditText textTenKH = (EditText)view.findViewById(R.id.edt_tenkh);
        EditText textDC = (EditText)view.findViewById(R.id.edt_diachi);
        EditText textSDT = (EditText)view.findViewById(R.id.edt_sdt);

        KhachHang khachHang = list.get(position);
        textMaKH.setText(khachHang.maKH);
        textTenKH.setText(khachHang.tenKH);
        textDC.setText(khachHang.diaChi);
        textSDT.setText(khachHang.sdt);
        return view;
    }
}
