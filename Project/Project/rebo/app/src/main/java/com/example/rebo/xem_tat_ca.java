package com.example.rebo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

import me.anwarshahriar.calligrapher.Calligrapher;


public class xem_tat_ca extends AppCompatActivity {
    private ArrayList<Book> book = new ArrayList<>();
    private GridView xem_tat_ca;
    private Adapter_Book_doc_nhieu_nhat adapter;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private Calligrapher calligrapher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darkTheme);
        }
        else setTheme(R.style.AppTheme);
        calligrapher = new Calligrapher(xem_tat_ca.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_tat_ca);

        //database
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        //grid view
        xem_tat_ca = (GridView) findViewById(R.id.xem_tat_ca);
        adapter = new Adapter_Book_doc_nhieu_nhat(this,book);
        xem_tat_ca.setAdapter(adapter);

        // database load
        Intent intent = getIntent();
        if(intent.getStringExtra("xem_tat_ca").equals("sach_moi_nhat")){
            sach_moi_nhat();
            getSupportActionBar().setTitle("Sách mới nhất");

        }
        else {
            doc_nhieu_nhat();
            getSupportActionBar().setTitle("Đọc nhiều nhất");

        }
        //Event
        xem_tat_ca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(xem_tat_ca.this, ActivityDetail.class);
                Book data = book.get(position);
                intent.putExtra("tenSach", data.getTenSach());
                startActivity(intent);
            }
        });

    }
    public void sach_moi_nhat(){
        databaseReference.child("Books").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                book.add(dataSnapshot.getValue(Book.class));
                Collections.reverse(book); // reverse
                adapter.notifyDataSetChanged();
                calligrapher.setFont(xem_tat_ca.this,Online.stringfont, true);
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
    public void doc_nhieu_nhat(){
        databaseReference.child("Books").orderByChild("soLanDoc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                book.add(dataSnapshot.getValue(Book.class));
                Collections.reverse(book); // reverse
                adapter.notifyDataSetChanged();
                calligrapher.setFont(xem_tat_ca.this,Online.stringfont, true);
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
}
