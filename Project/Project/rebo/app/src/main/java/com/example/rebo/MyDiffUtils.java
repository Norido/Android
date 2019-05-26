package com.example.rebo;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class MyDiffUtils extends DiffUtil.Callback{

    List<Book> oldPersons;
    List<Book> newPersons;

    public MyDiffUtils(List<Book> newPersons, List<Book> oldPersons) {
        this.newPersons = newPersons;
        this.oldPersons = oldPersons;
    }

    @Override
    public int getOldListSize() {
        return oldPersons.size();
    }

    @Override
    public int getNewListSize() {
        return newPersons.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPersons.get(oldItemPosition).getTenSach() == newPersons.get(newItemPosition).getTenSach();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPersons.get(oldItemPosition).equals(newPersons.get(newItemPosition));
    }
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
