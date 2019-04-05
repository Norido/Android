package com.example.mybook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class myAdapter extends BaseAdapter {

    Context mycontext;
    int myresource;
    List<ClassBook> arrBook;

    public myAdapter(Context mycontext, int myresource, List<ClassBook> arrBook) {
        this.mycontext = mycontext;
        this.myresource = myresource;
        this.arrBook = arrBook;
    }

    @Override
    public int getCount() {
        return arrBook.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(myresource,null);
        ImageView icon = (ImageView) view.findViewById(R.id.img);
        TextView txtName = (TextView) view.findViewById(R.id.nameBook);
        TextView txtDes = (TextView) view.findViewById(R.id.tacGia);
        ImageView icon2 = (ImageView) view.findViewById(R.id.img2);
        TextView txtName2 = (TextView) view.findViewById(R.id.nameBook2);
        TextView txtDes2 = (TextView) view.findViewById(R.id.tacGia2);
        ImageView icon3 = (ImageView) view.findViewById(R.id.img3);
        TextView txtName3 = (TextView) view.findViewById(R.id.nameBook3);
        TextView txtDes3 = (TextView) view.findViewById(R.id.tacGia3);

        icon.setImageResource(arrBook.get(i).anhBia);
        txtName.setText(arrBook.get(i).tenSach);
        txtDes.setText(arrBook.get(i).tacGia);

        icon2.setImageResource(arrBook.get(i).anhBia2);
        txtName2.setText(arrBook.get(i).tenSach2);
        txtDes2.setText(arrBook.get(i).tacGia2);
        icon3.setImageResource(arrBook.get(i).anhBia3);
        txtName3.setText(arrBook.get(i).tenSach3);
        txtDes3.setText(arrBook.get(i).tacGia3);
        return view;
    }
}
