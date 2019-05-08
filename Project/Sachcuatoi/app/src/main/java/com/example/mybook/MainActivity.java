package com.example.mybook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView li;
    ArrayList<ClassBook> dsBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        li = (ListView) findViewById(R.id.myList);
        dsBook = new ArrayList<>();
        dsBook.add(new ClassBook (R.drawable.bookgif,"Món qùa","Tac Gia",R.drawable.tuoitre,"Tuổi Trẻ","Tác Giả",R.drawable.me_you,"Tôi thành","Tác Giả"));
        dsBook.add(new ClassBook (R.drawable.dacnhantam,"Đắc Nhân tâm","Tác Giả",R.drawable.heal,"Sức Khỏe","Tác Giả",R.drawable.tu_dien,"Từ Điển","Tác Giả"));
        dsBook.add(new ClassBook (R.drawable.bookgif,"Món qùa","Tác Giả",R.drawable.walk,"walk","Tác Giả",R.drawable.plain,"Kế hoạch","Tác Giả"));
        dsBook.add(new ClassBook (R.drawable.me_you,"Success","Tác Giả",R.drawable.heal,"Sưc khỏe","Tác Giả",R.drawable.bookjourneys,"Trip","Tác Giả"));
        dsBook.add(new ClassBook (R.drawable.bookgif,"Món qùa","Tác Giả",R.drawable.bookgif,"Món qùa","Tác Giả",R.drawable.tu_dien,"Từ Điển","Tác Giả"));
        dsBook.add(new ClassBook (R.drawable.walk,"My","Tác Giả",R.drawable.heal,"Sức Khỏe","Tác Giả",R.drawable.bookgif,"Món qùa","Tác Giả"));


        myAdapter adapter = new myAdapter(MainActivity.this,R.layout.sach_cua_tui,dsBook);
        li.setAdapter(adapter);
    }
}
