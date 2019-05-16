package com.example.rebo;

import android.content.Intent;
import android.content.res.Configuration;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    private Sliding_Image sliding_image;
    private CirclePageIndicator indicator;

    //Xem tat ca
    private TextView xem_tat_ca, xem_tat_ca_2;
    //sach cua tui
    private LinearLayout layout_sach_cua_tui;
    //sach tui thich
    private LinearLayout layout_sach_tui_thich;
    //firebase database
    private DatabaseReference databaseReference,book,adv;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // database
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        book = databaseReference.child("Books");
        adv = databaseReference.child("Adv");
        // Drawable Navigation
        getSupportActionBar().setTitle("Online");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        // Auto doi anh
        mPager = (ViewPager) findViewById(R.id.pager);
        sliding_image = new Sliding_Image(MainActivity.this, header);
        mPager.setAdapter(sliding_image);
        adv.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                header.add(dataSnapshot.getValue(Book.class));
                sliding_image.notifyDataSetChanged();
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
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        //lay kich thuoc
        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = header.size();
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

        // recycler view book horizontal sach moi nhat
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_book);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new Adapter_Book_moi_nhat(sach_moi_nhat);
        recyclerView.setAdapter(recyclerAdapter);

        book.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                sach_moi_nhat.add(dataSnapshot.getValue(Book.class));
                recyclerAdapter.notifyDataSetChanged();

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


        // doc nhieu nhat
        Adapter_doc_nhieu_nhat = new Adapter_Book_doc_nhieu_nhat(this, doc_nhieu_nhat);
        Grid_doc_nhieu_nhat = (GridView) findViewById(R.id.gridview_docnhieunhat);
        Grid_doc_nhieu_nhat.setAdapter(Adapter_doc_nhieu_nhat);
        book.orderByChild("soLanDoc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                doc_nhieu_nhat.add(dataSnapshot.getValue(Book.class));
                Adapter_doc_nhieu_nhat.notifyDataSetChanged();
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
        Grid_doc_nhieu_nhat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ActivityDetail.class);
                Book book = doc_nhieu_nhat.get(position);
                intent.putExtra("img", book.getBiaSach());
                intent.putExtra("title", book.getTenSach());
                intent.putExtra("author", book.getTacGia());
                startActivity(intent);
            }
        });

        //Xem tat ca sach moi nhatt
        xem_tat_ca = (TextView) findViewById(R.id.tv_seeall1);
        xem_tat_ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, xem_tat_ca.class);
                intent.putExtra("xem_tat_ca","sach_moi_nhat");
                startActivity(intent);
            }
        });

        // Xem tat ca doc nhieu nhat
        xem_tat_ca_2 = (TextView) findViewById(R.id.tv_seeall2);
        xem_tat_ca_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, xem_tat_ca.class);
                intent.putExtra("xem_tat_ca","doc_nhieu_nhat");
                startActivity(intent);
            }
        });


        // Sach cua tui
        layout_sach_cua_tui = (LinearLayout) findViewById(R.id.layout_sach_cua_tui);
        layout_sach_cua_tui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Sach_cua_tui.class);
                startActivity(intent);
            }
        });
        // Sach cua tui
        layout_sach_tui_thich = (LinearLayout) findViewById(R.id.layout_sach_tui_thich);
        layout_sach_tui_thich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Sach_tui_thich.class);
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

