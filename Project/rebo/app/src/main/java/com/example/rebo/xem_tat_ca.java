package com.example.rebo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;

public class xem_tat_ca extends AppCompatActivity {
    private ArrayList<Book> book = new ArrayList<>();
    private GridView xem_tat_ca;
    private Adapter_Book_doc_nhieu_nhat adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_tat_ca);
        Slidr.attach(this);
        book.add(new Book(R.drawable.sach10, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach11, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach12, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach13, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach14, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach15, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach16, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach17, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach18, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        xem_tat_ca = (GridView) findViewById(R.id.xem_tat_ca);
        adapter = new Adapter_Book_doc_nhieu_nhat(this,book);
        xem_tat_ca.setAdapter(adapter);
    }
}
