package com.example.rebo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import java.util.ArrayList;
public class Sach_tui_thich extends AppCompatActivity {
    private ArrayList<Book> book = new ArrayList<>();
    private GridView sach_tui_thich;
    private Adapter_Book_doc_nhieu_nhat adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sach_tui_thich);
        book.add(new Book(R.drawable.sach10, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach11, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach12, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach13, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach14, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach15, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach16, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach17, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        book.add(new Book(R.drawable.sach18, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        sach_tui_thich = (GridView) findViewById(R.id.sach_tui_thich);
        adapter = new Adapter_Book_doc_nhieu_nhat(this,book);
        sach_tui_thich.setAdapter(adapter);
    }
}
