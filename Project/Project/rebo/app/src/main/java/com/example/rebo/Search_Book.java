package com.example.rebo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;

public class Search_Book extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private SearchViewAdapter BookAdapter;
    private ListView ViewBook;
    public SearchView searchView;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private boolean sync;
    public static ArrayList<BookName> bookNamesArrayList = new ArrayList<BookName>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__book);
        init();

    }
    public void init(){
        ViewBook = (ListView) findViewById(R.id.lv_book);
        searchView = findViewById(R.id.searchview);
        searchView.setQueryHint("Search...");
        Slidr.attach(this);

        getSupportActionBar().setTitle("Tìm kiếm sách");
        bookNamesArrayList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        sync = true;
        //adding few data to arraylist
        databaseReference.child("Books").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                bookNamesArrayList.add(new BookName(dataSnapshot.getValue(Book.class).getBiaSach(),dataSnapshot.getValue(Book.class).getTenSach()));
                event();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void event(){
        BookAdapter = new SearchViewAdapter(this);
        ViewBook.setAdapter(BookAdapter);
        searchView.setOnQueryTextListener(this);
        ViewBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Search_Book.this, ActivityDetail.class);
                intent.putExtra("tenSach", bookNamesArrayList.get(position).getBookName());
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        BookAdapter.filter(text);
        return false;
    }
    public void onBackPressed() {
        Intent intent = new Intent(Search_Book.this, Online.class);
        startActivity(intent);
    }
}
