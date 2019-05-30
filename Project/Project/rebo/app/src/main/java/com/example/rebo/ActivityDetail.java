package com.example.rebo;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.r0adkll.slidr.Slidr;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;


public class ActivityDetail extends AppCompatActivity {
    private ExpandableTextView expandableTextView;
    private TextView name, author,theloai,nhaxuatban,ngayxuanban;
    private Button read_book;
    //private ImageView img;
    private ImageView img;
    private LottieAnimationView lottieAnimationView;
    //firebase
    private DatabaseReference databaseReference,book,check_book;
    private FirebaseDatabase firebaseDatabase;
    private Book b;
    public SharedPreferences sharedPrefManager;
    public String uid;
    private String tenSach;

    // chapter
    private ListView lv_chapter;
    private int countChapter;;
    private ArrayList<String> chapter;
    private ArrayAdapter<String> adapter;
    private ImageView img_chapter;
    //font
    private Calligrapher calligrapher;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darkTheme);
        }
        else setTheme(R.style.AppTheme);
        calligrapher = new Calligrapher(ActivityDetail.this);
        calligrapher.setFont(ActivityDetail.this,Online.stringfont, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_book);
        // slide activity
        Slidr.attach(this);

        getSupportActionBar().setTitle("Chi tiết sách");


        // Nhan du lieu
        //xem them
        expandableTextView = (ExpandableTextView) findViewById(R.id.expandable_text_view);
        img = (ImageView) findViewById(R.id.img_detail);
        name = (TextView) findViewById(R.id.name_detail);
        author = (TextView) findViewById(R.id.author_detail);
        theloai = (TextView) findViewById(R.id.the_loai);
        nhaxuatban = (TextView) findViewById(R.id.nha_xuat_ban);
        ngayxuanban = (TextView) findViewById(R.id.ngay_xuat_ban);
        sharedPrefManager = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        Intent recv_from_online = getIntent();
        tenSach = recv_from_online.getStringExtra("tenSach");
        uid = sharedPrefManager.getString("uid",null);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        book = databaseReference.child("Books");
        check_book = (DatabaseReference) databaseReference.child("users").child(uid);
        check_book.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("mylovebook")){
                    check_book.child("mylovebook").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(tenSach)){
                                lottieAnimationView.setFrame((int) lottieAnimationView.getMaxFrame());
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
       book.orderByChild("tenSach").equalTo(tenSach).addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               b = dataSnapshot.getValue(Book.class);
               name.setText(b.getTenSach());
               author.setText(b.getTacGia());
               Picasso.get().load(b.getBiaSach()).into(img);
               expandableTextView.setText(b.getNoiDung());
               theloai.setText(b.getTheLoai());
               nhaxuatban.setText(b.getNhaXuatBan());
               ngayxuanban.setText(b.getNgayXuatBan());
               calligrapher.setFont(ActivityDetail.this,Online.stringfont, true);
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

        // LIKE
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.animation_view);
        lottieAnimationView.setOnClickListener(new View.OnClickListener(){
                @Override
            public void onClick(View v) {
                    if(lottieAnimationView.getFrame() == 0) {
                        lottieAnimationView.playAnimation();
                        lottieAnimationView.setRepeatCount(0);

                        check_book.child("mylovebook").child(tenSach).setValue("true");

                    }
                    else{
                        //104 frame
                        lottieAnimationView.pauseAnimation();
                        lottieAnimationView.setFrame(0);
                        check_book.child("mylovebook").child(tenSach).removeValue();


                    }
            }
        });


        // chuyen sang doc sach
        read_book = (Button) findViewById(R.id.doc_sach);
        read_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog();

            }
        });

    }
    public void Dialog(){
        calligrapher.setFont(ActivityDetail.this,Online.stringfont,true);
        Dialog dialog = new Dialog(ActivityDetail.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.chapter);
        img_chapter = (ImageView) dialog.findViewById(R.id.img_chapter);
        lv_chapter = (ListView) dialog.findViewById(R.id.listview_chapter);
        chapter = new ArrayList<>();
        book.orderByChild("tenSach").equalTo(tenSach).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Book book = dataSnapshot.getValue(Book.class);
                Picasso.get().load(book.getBiaSach()).into(img_chapter);
                countChapter = (int) dataSnapshot.child("link").getChildrenCount();
                for(int i = 1; i <= countChapter;i++){
                    chapter.add("chapter "+i);

                }
                adapter = new ArrayAdapter(ActivityDetail.this,R.layout.item_spinner,chapter);
                lv_chapter.setAdapter(adapter);
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

        lv_chapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                book.orderByChild("tenSach").equalTo(tenSach).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Book b = dataSnapshot.getValue(Book.class);
                        int solandoc = Integer.parseInt(b.getSoLanDoc());
                        DatabaseReference ref = dataSnapshot.getRef();
                        ref.child("soLanDoc").setValue(String.valueOf(solandoc + 1));
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
                System.out.println("position" + position);
                Intent intent = new Intent(ActivityDetail.this, ReadBook.class);
                intent.putExtra("tenSach",name.getText());
                intent.putExtra("chapter",position);
                startActivity(intent);
            }
        });

        dialog.show();

    }

}