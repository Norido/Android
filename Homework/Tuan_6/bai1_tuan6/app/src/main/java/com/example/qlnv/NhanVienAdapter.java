package com.example.qlnv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {

    Context context = null;
    ArrayList<NhanVien> myArray = null;
    int layoutId;

    public NhanVienAdapter(Context context,
                          int layoutId,
                          ArrayList<NhanVien> arr) {
        super(context, layoutId, arr);
        this.context = context;
        this.layoutId = layoutId;
        this.myArray = arr;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
        convertView = inflater.inflate(layoutId, null);

        if (myArray.size() > 0 && position >= 0) {

            final TextView txtdisplay = (TextView)
                    convertView.findViewById(R.id.txtitem);

            final NhanVien emp = myArray.get(position);
            //đưa thông tin lên TextView
            txtdisplay.setText(emp.toString());

            final ImageView imgitem = (ImageView)
                    convertView.findViewById(R.id.imgitem);

            if (emp.isGender())
                imgitem.setImageResource(R.drawable.girlicon);
            else
                imgitem.setImageResource(R.drawable.boyicon);
        }

        return convertView;
    }
}
