package com.example.rebo;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ReadBook extends AppCompatActivity implements OnPageChangeListener, OnTapListener {
    private PDFView pdfView;
    TranslateAnimation animation,animationD;
    private Fab fab;
    private ArrayList<String> chapter = new ArrayList<>();
    private Spinner spiner;
    private MaterialSheetFab materialSheetFab;
    private TextView readbook_name;
    private int statusBarColor;
    private boolean check = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_book);
        getSupportActionBar().hide();
        pdfView = (PDFView) findViewById(R.id.pdf_book);
        fab = (Fab) findViewById(R.id.btn_chapter);
//        animation = new TranslateAnimation(0.0f, 0.0f, 600.0f, 0.0f);
//        animationD = new TranslateAnimation(0.0f, 0.0f, 0.0f, 600.0f);
//        animation.setDuration(500);
//        animationD.setDuration(500);

        //read book name
        readbook_name = (TextView) findViewById(R.id.readbook_name);
        readbook_name.setSelected(true);

        // chapter
        chapter.add("chap1");
        chapter.add("chap2");
        chapter.add("chap3");
        spiner = (Spinner) findViewById(R.id.chapter_spinner);
        spiner.setGravity(View.TEXT_ALIGNMENT_CENTER);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,chapter);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spiner.setAdapter(adapter);
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(chapter.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //sheet FAB
        setupFab();

        pdfView.fromAsset("dac_nhan_tam.pdf")
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .defaultPage(0)
                .onPageChange(this)
                  .onTap(this)
                .scrollHandle(null)
                .pageSnap(true) // snap pages to screen boundaries
                .pageFling(false) // make a fling change only a single page like ViewPager
                .nightMode(false) // toggle night mode
                .load();

       // new readBookFromFireBase().execute("https://firebasestorage.googleapis.com/v0/b/rebo-thebookapp.appspot.com/o/Book%2FLink%2Floi_song_toi_gian_cua_nguoi_nhat%2FSachvui.Com-loi-song-toi-gian-cua-nguoi-nhat-sasaki-fumio.pdf?alt=media&token=7f2e1be3-cc90-4c85-9d96-0243aaecb896");
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
        Toast.makeText(this,"trang" + page,Toast.LENGTH_LONG).show();
        System.out.println("trang:" + page);

    }

    // Tap listener PDF
    @Override
    public boolean onTap(MotionEvent e){
        check = !check;
        if(e.getActionMasked() == MotionEvent.ACTION_DOWN && check){
//            fab.startAnimation(animation);
            fab.show();

        }
        else if(e.getActionMasked() == MotionEvent.ACTION_DOWN && !check){
//            fab.startAnimation(animationD);
            fab.hide();

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
        int sheetColor = getResources().getColor(R.color.white);
        int fabColor = getResources().getColor(R.color.colorPrimary);

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

