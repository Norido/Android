package com.example.bai1_listviewcountry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CountryAdapter extends BaseAdapter {

    Context mycontext;
    int myresource;
    List<ClassCountry> arrCountry;
    public CountryAdapter(Context context, int resource, List objects) {
        mycontext = context;
        myresource = resource;
        arrCountry = objects;
    }
    @Override
    public int getCount() {
        return arrCountry.size();
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
        LayoutInflater layoutInflater = (LayoutInflater)mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(myresource,null);

        ImageView icon = (ImageView) view.findViewById(R.id.img);
        TextView txtName = (TextView) view.findViewById(R.id.name);
        TextView txtDes = (TextView) view.findViewById(R.id.desr);

        icon.setImageResource(arrCountry.get(i).icon);
        txtName.setText(arrCountry.get(i).ten);
        txtDes.setText(arrCountry.get(i).detal);
        return view;
    }
}
