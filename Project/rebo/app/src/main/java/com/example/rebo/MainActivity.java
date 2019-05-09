package com.example.rebo;

import android.content.Intent;
import android.content.res.Configuration;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    // Drawable Navigation variable
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    // recycler view book horizontal - sach moi nhat
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;
    private ArrayList<Book> sach_moi_nhat = new ArrayList<>();
    // Grid View doc nhieu nhat
    private ArrayList<Book> doc_nhieu_nhat = new ArrayList<>();
    private Adapter_Book_doc_nhieu_nhat Adapter_doc_nhieu_nhat;
    private GridView Grid_doc_nhieu_nhat;
    // header
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<Book> header = new ArrayList<>();

    //Xem tat ca
    private TextView xem_tat_ca,xem_tat_ca_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Drawable Navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        // recycler view book horizontal
        sach_moi_nhat.add(new Book(R.drawable.comic, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        sach_moi_nhat.add(new Book(R.drawable.sach2, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        sach_moi_nhat.add(new Book(R.drawable.sach3, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        sach_moi_nhat.add(new Book(R.drawable.sach4, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        sach_moi_nhat.add(new Book(R.drawable.sach5, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        sach_moi_nhat.add(new Book(R.drawable.sach6, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        sach_moi_nhat.add(new Book(R.drawable.sach7, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        sach_moi_nhat.add(new Book(R.drawable.sach8, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        sach_moi_nhat.add(new Book(R.drawable.sach9, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_book);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new Adapter_Book(sach_moi_nhat);
        recyclerView.setAdapter(recyclerAdapter);

        // doc nhieu nhat
        doc_nhieu_nhat.add(new Book(R.drawable.sach10, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        doc_nhieu_nhat.add(new Book(R.drawable.sach11, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        doc_nhieu_nhat.add(new Book(R.drawable.sach12, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        doc_nhieu_nhat.add(new Book(R.drawable.sach13, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        doc_nhieu_nhat.add(new Book(R.drawable.sach14, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        doc_nhieu_nhat.add(new Book(R.drawable.sach15, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        doc_nhieu_nhat.add(new Book(R.drawable.sach16, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        doc_nhieu_nhat.add(new Book(R.drawable.sach17, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        doc_nhieu_nhat.add(new Book(R.drawable.sach18, R.string.naruto_shippuuden, R.string.kishimoto_masashi));
        Adapter_doc_nhieu_nhat = new Adapter_Book_doc_nhieu_nhat(this, doc_nhieu_nhat);
        Grid_doc_nhieu_nhat = (GridView) findViewById(R.id.gridview_docnhieunhat);
        Grid_doc_nhieu_nhat.setAdapter(Adapter_doc_nhieu_nhat);

        // Auto doi anh

        header.add(new Book(R.drawable.img_slide1));
        header.add(new Book(R.drawable.img_slide2));
        header.add(new Book(R.drawable.img_slide3));
        header.add(new Book(R.drawable.img_slide4));
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new Sliding_Image(MainActivity.this,header));
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        //lay kich thuoc
        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =header.size();
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
        //Xem tat ca sach moi nhatt
        xem_tat_ca = (TextView) findViewById(R.id.tv_seeall1);
        xem_tat_ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,xem_tat_ca.class);
                startActivity(intent);
            }
        });

        // Xem tat ca doc nhieu nhat
        xem_tat_ca_2 = (TextView) findViewById(R.id.tv_seeall2);
        xem_tat_ca_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,xem_tat_ca.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

