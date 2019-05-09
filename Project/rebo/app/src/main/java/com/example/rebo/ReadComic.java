package com.example.rebo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;


public class ReadComic extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Book> doc_sach = new ArrayList<>();
    private TextView textView;
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_comic);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_doc_sach);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        doc_sach.add(new Book(R.drawable.n3));
        doc_sach.add(new Book(R.drawable.n4));
        doc_sach.add(new Book(R.drawable.n5));
        doc_sach.add(new Book(R.drawable.n6));
        mAdapter = new Adapter_doc_sach(doc_sach);
        recyclerView.setAdapter(mAdapter);


    }
}