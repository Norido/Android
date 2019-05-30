package com.example.rebo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class SearchViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<BookName> arraylist;

    public SearchViewAdapter(Context context ) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(Search_Book.bookNamesArrayList);
    }

    public class ViewHolder {
        ImageView img;
        TextView name;
    }

    @Override
    public int getCount() {
        return Search_Book.bookNamesArrayList.size();
    }

    @Override
    public BookName getItem(int position) {
        return Search_Book.bookNamesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.search_book_item, null);
            // Locate the TextViews in listview_item.xml
            holder.img = view.findViewById(R.id.item_book_search);
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        Picasso.get().load(Search_Book.bookNamesArrayList.get(position).getBiaSach()).into(holder.img);
        holder.name.setText(Search_Book.bookNamesArrayList.get(position).getBookName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        Search_Book.bookNamesArrayList.clear();
        if (charText.length() == 0) {
           Search_Book.bookNamesArrayList.addAll(arraylist);
        } else {
            for (BookName wp : arraylist) {
                if (wp.getBookName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    Search_Book.bookNamesArrayList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
