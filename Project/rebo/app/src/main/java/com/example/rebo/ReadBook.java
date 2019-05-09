package com.example.rebo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

public class ReadBook extends AppCompatActivity implements OnPageChangeListener{
    private PDFView pdfView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_book);
        pdfView = (PDFView) findViewById(R.id.pdf_book);
        pdfView.fromAsset("dac_nhan_tam.pdf")
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .defaultPage(0)
                .onPageChange(this)
                .scrollHandle(null)
                .pageSnap(true) // snap pages to screen boundaries
                .pageFling(false) // make a fling change only a single page like ViewPager
                .nightMode(false) // toggle night mode
                .load();

    }
    @Override
    public void onPageChanged(int page, int pageCount) {
        Toast.makeText(this,"trang" + page,Toast.LENGTH_LONG).show();
    }

}
