package com.example.rebo;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Sliding_Image extends PagerAdapter {

    // Sliding Image dung de slide header quang cao tren activity online
    private ArrayList<Book> data;
    private LayoutInflater inflater;
    private Context context;

    public Sliding_Image(Context context,ArrayList<Book> data) {
        this.context = context;
        this.data=data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.item_pager, view, false);

        ImageView imageView = (ImageView) imageLayout.findViewById(R.id.img_pager);
        Book book = data.get(position);
        Picasso.get().load(book.getBiaSach()).into(imageView);
        view.addView(imageLayout);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}

