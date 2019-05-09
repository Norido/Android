package com.example.rebo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;


import java.util.ArrayList;
public class Sach_cua_tui extends AppCompatActivity {
    private ArrayList<Book> book = new ArrayList<>();
    private GridView sach_cua_tui;
    private Adapter_Book_doc_nhieu_nhat adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sach_cua_tui);
        book.add(new Book(R.drawable.sach10, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach11, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach12, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach13, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach14, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach15, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach16, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach17, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach18, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        sach_cua_tui = (GridView) findViewById(R.id.sach_cua_tui);
        adapter = new Adapter_Book_doc_nhieu_nhat(this,book);
        sach_cua_tui.setAdapter(adapter);
    }
}
