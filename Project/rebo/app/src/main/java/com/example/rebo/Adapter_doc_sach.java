package com.example.rebo;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class Adapter_doc_sach extends RecyclerView.Adapter<Adapter_doc_sach.MyViewHolder> {

    private ArrayList<Book> data;

    // Provide a suitable constructor (depends on the kind of dataset)
    public Adapter_doc_sach(ArrayList<Book> data) {
        this.data = data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Adapter_doc_sach.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doc_sach, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Book book = data.get(position);
        holder.img.setImageResource(book.getBiaSach());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView img;
        public MyViewHolder(View v) {
            super(v);
            img = v.findViewById((R.id.img_doc_sach));
        }
    }
}