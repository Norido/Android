package com.example.rebo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.os.SystemClock.sleep;

public class Online extends AppCompatActivity {
    // Drawable Navigation variable
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    // recycler view book horizontal - sach moi nhat
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    private RecyclerView.Adapter recyclerAdapter;
    private ArrayList<Book> sach_moi_nhat = new ArrayList<>();
    // Grid View doc nhieu nhat
    private ArrayList<Book> doc_nhieu_nhat = new ArrayList<>();
    public HashMap<String,String> mylovebook = new HashMap<>();
    // doc nhieu nhat
    private Adapter_Book_doc_nhieu_nhat Adapter_doc_nhieu_nhat = new Adapter_Book_doc_nhieu_nhat(this, doc_nhieu_nhat);
    private GridView Grid_doc_nhieu_nhat;
    // header
    private static ViewPager mPager;
    private static int currentPage = 0,currentWord = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<Book> header = new ArrayList<>();
    private Sliding_Image sliding_image;
    private CirclePageIndicator indicator;
    private TextView noiDung,tacGia;
    private Animation animFadeIn,animFadeOut;
    //Xem tat ca
    private TextView xem_tat_ca, xem_tat_ca_2;
    //sach cua tui
    private LinearLayout layout_sach_cua_tui;
    //sach tui thich
    private LinearLayout layout_sach_tui_thich,layout_logout,layout_user_info;
    //firebase database
    private DatabaseReference databaseReference,book,adv;
    private FirebaseDatabase firebaseDatabase;
    //user infor
    public SharedPreferences sharedPrefManager;
    public SharedPreferences.Editor editor;
    public String uid, email, displayname, avatar, phone;
    public TextView onl_displayname;
    public de.hdodenhof.circleimageview.CircleImageView onl_img_avatar;
    MenuItem item1,item2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online);
        // database
        setControl();
        setEvent();
    }
    public void setControl(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        onl_displayname = findViewById(R.id.activity_main_tv_user_name);
        onl_img_avatar = findViewById(R.id.activity_main_imv_avatar);
        // Auto doi anh
        mPager = (ViewPager) findViewById(R.id.pager);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        // tap hop nhung cau noi hay trong ngay
        noiDung = (TextView) findViewById(R.id.header_online_noidung);
        tacGia = (TextView) findViewById(R.id.header_online_tacgia);
        // recycler view book horizontal sach moi nhat
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_book);
        Grid_doc_nhieu_nhat = (GridView) findViewById(R.id.gridview_docnhieunhat);
        //Xem tat ca sach moi nhatt
        xem_tat_ca = (TextView) findViewById(R.id.tv_seeall1);
        // Xem tat ca doc nhieu nhat
        xem_tat_ca_2 = (TextView) findViewById(R.id.tv_seeall2);
        // Sach cua tui
        layout_sach_cua_tui = (LinearLayout) findViewById(R.id.layout_sach_cua_tui);
        // Sach cua tui
        layout_sach_tui_thich = (LinearLayout) findViewById(R.id.layout_sach_tui_thich);
        //user info
        layout_user_info = findViewById(R.id.user_information);
        //logout
        layout_logout = findViewById(R.id.logout);
        item1 = (MenuItem) findViewById(R.id.dang_nhap);
        item2 = (MenuItem) findViewById(R.id.dang_ki);
        sharedPrefManager = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        editor = sharedPrefManager.edit();
    }
    public void setEvent(){

        // Drawable Navigation
        getSupportActionBar().setTitle("Online");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        //user information

        uid = sharedPrefManager.getString("uid","");
        email = sharedPrefManager.getString("email","Rebo@gmail.com");
        displayname = sharedPrefManager.getString("username","Anonymous");
        avatar = sharedPrefManager.getString("photo","");
        phone = sharedPrefManager.getString("phone","0123456789");

        onl_displayname.setText(displayname);
        if (!avatar.equals("")){
            Picasso.get().load(avatar).into(onl_img_avatar);
        }
        Grid_doc_nhieu_nhat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (FirebaseAuth.getInstance().getCurrentUser()==null)
                {
                    Intent intent = new Intent(Online.this,Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Online.this, ActivityDetail.class);
                    Book book = doc_nhieu_nhat.get(position);
                    intent.putExtra("tenSach", book.getTenSach());
                    startActivity(intent);
                }

            }
        });
        xem_tat_ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Online.this, xem_tat_ca.class);
                intent.putExtra("xem_tat_ca","sach_moi_nhat");
                startActivity(intent);
            }
        });


        xem_tat_ca_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Online.this, xem_tat_ca.class);
                intent.putExtra("xem_tat_ca","doc_nhieu_nhat");
                startActivity(intent);
            }
        });
        layout_sach_cua_tui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser()==null)
                {
                    Intent intent = new Intent(Online.this,Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(Online.this, Sach_cua_tui.class);
                    startActivity(intent);
                }

            }
        });

        layout_sach_tui_thich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser()==null)
                {
                    Intent intent = new Intent(Online.this,Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(Online.this, Sach_tui_thich.class);
                    startActivity(intent);
                }

            }
        });

        layout_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser()==null)
                {
                    Intent intent = new Intent(Online.this,Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(Online.this, User_info.class);
                    startActivity(intent);
                }

            }
        });
        layout_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser()!=null)
                {
                    FirebaseAuth.getInstance().signOut();
                    editor.clear();
                    editor.apply();
                    Intent i = getBaseContext().getPackageManager().
                            getLaunchIntentForPackage(getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }
            }
        });

        adv = databaseReference.child("Adv");
        sliding_image = new Sliding_Image(Online.this, header);
        mPager.setAdapter(sliding_image);
        adv.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final ArrayList<Book> words = new ArrayList<>();
                NUM_PAGES = (int) dataSnapshot.getChildrenCount();
                for(DataSnapshot data: dataSnapshot.getChildren()) {
                    header.add(data.getValue(Book.class));
                    sliding_image.notifyDataSetChanged();
                }
                //lay kich thuoc
                final float density = getResources().getDisplayMetrics().density;
                //Set circle indicator radius
                indicator.setRadius(5 * density);
                // Auto start of viewpager
                final Handler handler = new Handler();
                final Runnable Update = new Runnable() {
                    public void run() {
                        if (currentPage == NUM_PAGES) {
                            currentPage = 0;
                        }
                        mPager.setCurrentItem(currentPage, true);
                        currentPage++;
                    }
                };
                Timer swipeTimer = new Timer();
                swipeTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(Update);
                    }
                }, 3000, 3000);
//                animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
//                animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
//                animFadeIn.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//                        if (currentWord == NUM_PAGES) {
//                            currentWord = 0;
//                        }
//                        noiDung.setText(header.get(currentWord).getNoiDung());
//                        tacGia.setText(header.get(currentWord).getTacGia());
//                        currentWord++;
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        sleep(5000);
//                        noiDung.startAnimation(animFadeOut);
//                        tacGia.startAnimation(animFadeOut);
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//                    }
//                });
//                animFadeOut.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        noiDung.startAnimation(animFadeIn);
//                        tacGia.startAnimation(animFadeIn);
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });
//                noiDung.startAnimation(animFadeIn);
//                tacGia.startAnimation(animFadeIn);
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
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        book = databaseReference.child("Books");
        recyclerView.setHasFixedSize(true);
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new Adapter_Book_moi_nhat(sach_moi_nhat);
        recyclerView.setAdapter(recyclerAdapter);

        book.limitToLast(5).addChildEventListener(new ChildEventListener() {
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
        Grid_doc_nhieu_nhat.setAdapter(Adapter_doc_nhieu_nhat);
        book.orderByChild("soLanDoc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                doc_nhieu_nhat.add(dataSnapshot.getValue(Book.class));
                Collections.reverse(doc_nhieu_nhat); // reverse
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
        switch (item.getItemId()){
            case R.id.dang_nhap:{
                Intent intent = new Intent(Online.this,Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.dang_ki:{
                Intent intent = new Intent(Online.this,SignUp.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);

    }

}

