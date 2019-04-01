package com.gumiho.socialnetwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class SocialNetworkAdapter extends ArrayAdapter<SocialNetwork> {
    private Context context;
    private int layoutResourced;
    private ArrayList<SocialNetwork> data = null;

    public SocialNetworkAdapter (Context context, int layoutResourced, ArrayList<SocialNetwork> data){
        super(context,layoutResourced, data );
        this.context = context;
        this.layoutResourced = layoutResourced;
        this.data = data;

    }

    @Override
    public View getView(int pos, View convert, ViewGroup parent){
        MyViewHolder viewHolder = new MyViewHolder();

        if (convert==null) {
            convert = LayoutInflater.from(context).inflate(R.layout.list_row,parent,false);
            viewHolder.imgIcon = (ImageView) convert.findViewById(R.id.imgIcon);
            viewHolder.txtTittle = (TextView) convert.findViewById(R.id.txtTittle);
            convert.setTag(viewHolder);
        }
        else {
            viewHolder = (MyViewHolder)convert.getTag();

        }
        SocialNetwork item = data.get(pos);
        viewHolder.txtTittle.setText(item.gettitle());
        viewHolder.imgIcon.setImageResource(item.getIcon());
        return convert;

    }
    public class MyViewHolder{
        ImageView imgIcon;
        TextView txtTittle;
    }

}
