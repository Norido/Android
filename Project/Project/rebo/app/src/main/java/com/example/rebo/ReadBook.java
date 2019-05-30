package com.example.rebo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

public class ReadBook extends AppCompatActivity implements OnPageChangeListener, OnTapListener {
    private PDFView pdfView;
    private Fab fab;
    private ArrayList<String> chapter = new ArrayList<>();
    private Spinner spiner;
    private MaterialSheetFab materialSheetFab;
    private TextView readbook_name;
    private boolean check = false;

    //firebase
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference pages;
    public SharedPreferences sharedPrefManager;
    public String uid;
    private Book b = new Book();
    private int countChapter;

    //Intent
    private String tenSach;

    private Calligrapher calligrapher;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darkTheme);
        }
        else setTheme(R.style.AppTheme);
        calligrapher = new Calligrapher(ReadBook.this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_book);
        getSupportActionBar().hide();
        pdfView = (PDFView) findViewById(R.id.pdf_book);
        fab = (Fab) findViewById(R.id.btn_chapter);
        readbook_name = (TextView) findViewById(R.id.readbook_name);

        //Nhan du lieu
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Books");
        pages = firebaseDatabase.getReference("users");
        sharedPrefManager = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        uid = sharedPrefManager.getString("uid",null);
        Intent intent = getIntent();
        tenSach = intent.getStringExtra("tenSach");
        databaseReference.orderByChild("tenSach").equalTo(tenSach).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Book book = dataSnapshot.getValue(Book.class);
                countChapter = (int) dataSnapshot.child("link").getChildrenCount();
                readbook_name.setText(book.getTenSach());
                readbook_name.setSelected(true);
                for(int i = 1; i <= countChapter;i++){
                    chapter.add("chap"+i);
                }
                chapter();
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


//        pdfView.fromAsset("dac_nhan_tam.pdf")
//                .enableSwipe(true) // allows to block changing pages using swipe
//                .swipeHorizontal(true)
//                .defaultPage(0)
//                .onPageChange(this)
//                .onTap(this)
//                .scrollHandle(null)
//                .pageSnap(true) // snap pages to screen boundaries
//                .pageFling(false) // make a fling change only a single page like ViewPager
//                .nightMode(false) // toggle night mode
//                .load();

        //sheet FAB
        setupFab();

    }

    public void chapter(){
        spiner = (Spinner) findViewById(R.id.chapter_spinner);
        spiner.setGravity(View.TEXT_ALIGNMENT_CENTER);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.item_spinner,chapter);
        adapter.setDropDownViewResource(R.layout.item_spinner);
        spiner.setAdapter(adapter);
        calligrapher.setFont(ReadBook.this,Online.stringfont, true);
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                    databaseReference.orderByChild("tenSach").equalTo(tenSach).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            new readBookFromFireBase().execute((String) dataSnapshot.child("link").child(chapter.get(position)).getValue());
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
                    Toast.makeText(ReadBook.this,"Chúc bạn đọc sách vui vẻ", Toast.LENGTH_LONG).show();
                }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    class readBookFromFireBase extends AsyncTask<String,Void, InputStream>{

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if(urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch (IOException e){
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream){
            pdfView.fromStream(inputStream)
                    .enableSwipe(true) // allows to block changing pages using swipe
                    .swipeHorizontal(true)
                    .defaultPage(0)
                    .onPageChange(ReadBook.this)
                    .scrollHandle(null)
                    .pageSnap(true) // snap pages to screen boundaries
                    .pageFling(false) // make a fling change only a single page like ViewPager
                    .nightMode(false)
                    .onTap(ReadBook.this)
                    // toggle night mode
                    .load();
        }
    }

    // get so trang hien tai cua
    @Override
    public void onPageChanged(int page, int pageCount) {
//        Toast.makeText(this,"trang" + page,Toast.LENGTH_LONG).show();
//        System.out.println("trang:" + page);
        pages.child(uid).child("page").child(tenSach).setValue(page);

    }

    // Tap listener PDF
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onTap(MotionEvent e){
        check = !check;
        if(e.getActionMasked() == MotionEvent.ACTION_DOWN){
            fab.show();
            fab.setVisibility(View.VISIBLE);
        }
        else if(e.getActionMasked() == MotionEvent.ACTION_UP){
            fab.hide();
            fab.setVisibility(View.INVISIBLE);
        }

        return check;
    }

    // sheet FAB onBackPressed
    @Override
    public void onBackPressed() {
        if (materialSheetFab.isSheetVisible()) {
            materialSheetFab.hideSheet();
        } else {
            super.onBackPressed();
        }
    }
    private void setupFab(){

        View sheetView = findViewById(R.id.fab_sheet);
        View overlay = findViewById(R.id.overlay);
        int sheetColor;
        int fabColor;
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            sheetColor = getResources().getColor(R.color.sheet_fab_dark);
            fabColor = getResources().getColor(R.color.sheet_fab_dark);
        }
        else{
            sheetColor = getResources().getColor(R.color.sheet_fab);
            fabColor = getResources().getColor(R.color.sheet_fab);
        }

        // Create material sheet FAB
        materialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay, sheetColor, fabColor);

        // Set material sheet event listener
        materialSheetFab.setEventListener(new MaterialSheetFabEventListener() {
            @Override
            public void onShowSheet() {
            }
            @Override
            public void onHideSheet() {

            }
        });
    }

}

