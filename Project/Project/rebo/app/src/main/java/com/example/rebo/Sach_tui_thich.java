package com.example.rebo;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Sach_tui_thich extends AppCompatActivity {
    private ArrayList<Book> book = new ArrayList<>();
    private GridView sach_tui_thich;
    private Adapter_Book_doc_nhieu_nhat adapter;
    private DatabaseReference databaseReference,lovebook;
    private FirebaseDatabase firebaseDatabase;
    public String uid;
    public SharedPreferences sharedPrefManager;
//    public List<MyLoveBook> LoveB = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sach_tui_thich);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        sharedPrefManager = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        uid = sharedPrefManager.getString("uid",null);
        sach_tui_thich = (GridView) findViewById(R.id.sach_tui_thich);
        adapter = new Adapter_Book_doc_nhieu_nhat(this,book);
        sach_tui_thich.setAdapter(adapter);
        lovebook = databaseReference.child("users").child(uid).child("mylovebook");
        lovebook.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    if (dataSnapshot.getValue().equals("1")){
                        databaseReference.child("Books").equalTo(String.valueOf(dataSnapshot.getKey())).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                book.add(dataSnapshot.getValue(Book.class));
                                adapter.notifyDataSetChanged();
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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
