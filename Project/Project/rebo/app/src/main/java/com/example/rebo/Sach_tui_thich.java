package com.example.rebo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Sach_tui_thich extends AppCompatActivity {
    private ArrayList<Book> book = new ArrayList<>();
    private GridView sach_tui_thich;
    private Adapter_Book_doc_nhieu_nhat adapter;
    private DatabaseReference databaseReference,lovebook;
    private FirebaseDatabase firebaseDatabase;
    public String uid;
    public SharedPreferences sharedPrefManager;
    private Calligrapher calligrapher;
//    public List<MyLoveBook> LoveB = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darkTheme);
        }
        else setTheme(R.style.AppTheme);
        calligrapher = new Calligrapher(Sach_tui_thich.this);
        calligrapher.setFont(Sach_tui_thich.this,Online.stringfont, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sach_tui_thich);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        sharedPrefManager = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        uid = sharedPrefManager.getString("uid",null);
        sach_tui_thich = (GridView) findViewById(R.id.sach_tui_thich);
        adapter = new Adapter_Book_doc_nhieu_nhat(this,book);
        sach_tui_thich.setAdapter(adapter);
        sach_tui_thich.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Sach_tui_thich.this, ActivityDetail.class);
                Book data = book.get(position);
                intent.putExtra("tenSach", data.getTenSach());
                startActivity(intent);
            }
        });
        lovebook = (DatabaseReference) databaseReference.child("users").child(uid);
        lovebook.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("mylovebook")){
                    lovebook.child("mylovebook").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot data: dataSnapshot.getChildren()){
                                databaseReference.child("Books").orderByChild("tenSach").equalTo(data.getKey()).addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                        book.add(dataSnapshot.getValue(Book.class));
                                        adapter.notifyDataSetChanged();
                                        calligrapher.setFont(Sach_tui_thich.this,Online.stringfont, true);
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

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
