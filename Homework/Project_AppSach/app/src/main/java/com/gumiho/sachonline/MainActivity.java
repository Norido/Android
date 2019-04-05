package com.gumiho.sachonline;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Random xuất hiện ảnh
    private LinearLayout layoutSlide;

    private TextView tvSachMoiNhat;
    private TextView tvSeeAll1;

    private TextView tvDocNhieuNhat;
    private TextView tvSeeAll2;

    private ImageButton imgbtnSachMoi1;
    private ImageButton imgbtnSachMoi2;
    private ImageButton imgbtnSachMoi3;
    private ImageButton imgbtnSachMoi4;
    private ImageButton imgbtnSachMoi5;
    private ImageButton imgbtnSachMoi6;
    private ImageButton imgbtnSachMoi7;
    private ImageButton imgbtnSachMoi8;
    private ImageButton imgbtnSachMoi9;

    private ImageButton imgbtnComeBack1;
    private ImageButton imgbtnComeBack2;

    private TextView tvSachMoi1;
    private TextView tvSachMoi2;
    private TextView tvSachMoi3;
    private TextView tvSachMoi4;
    private TextView tvSachMoi5;
    private TextView tvSachMoi6;
    private TextView tvSachMoi7;
    private TextView tvSachMoi8;
    private TextView tvSachMoi9;

    private GridView gridViewDocNhieuNhat;
    private GridView gridViewSeeAllSachMoi;
    private GridView gridViewSeeAllSachDocNhieu;


    ArrayList<GribView> item = new ArrayList<>();

    int img_slide[] = {R.drawable.img_slide1, R.drawable.img_slide1,
                       R.drawable.img_slide3, R.drawable.img_slide4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //Auto đổi ảnh
        Timer timer = new Timer();
        MyTimer mt = new MyTimer();
        timer.schedule(mt,2000,2000);

        //GribView Đọc Nhiều Nhất
        eventGridViewDocNhieuNhat();

        tvSeeAll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.layout_seeallsachmoi);
                gridViewSeeAllSachMoi = (GridView)findViewById(R.id.gridview_seeallsachmoi);
                imgbtnComeBack1 = (ImageButton)findViewById(R.id.imgbtn_comeback1);
                eventGridViewSeeAllSachMoi();
                imgbtnComeBack1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.activity_main);

                    }
                });
            }
        });

        tvSeeAll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.layout_seeallsachdocnhieu);
                gridViewSeeAllSachDocNhieu = (GridView)findViewById(R.id.gridview_seeallsachdocnhieu);
                imgbtnComeBack2 = (ImageButton)findViewById(R.id.imgbtn_comeback2);
                eventGridViewSeeAllSachDocNhieu();
                imgbtnComeBack2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.activity_main);
                    }
                });
            }
        });
    }

    public void init() {
        layoutSlide = (LinearLayout)findViewById(R.id.layout_slide);

        tvSachMoiNhat = (TextView)findViewById(R.id.tv_sachmoinhat);
        tvDocNhieuNhat = (TextView)findViewById(R.id.tv_docnhieunhat);
        tvSeeAll1 = (TextView)findViewById(R.id.tv_seeall1);
        tvSeeAll2 = (TextView)findViewById(R.id.tv_seeall2);

        imgbtnSachMoi1 = (ImageButton)findViewById(R.id.imgbtn_sachmoi1);
        imgbtnSachMoi2 = (ImageButton)findViewById(R.id.imgbtn_sachmoi2);
        imgbtnSachMoi3 = (ImageButton)findViewById(R.id.imgbtn_sachmoi3);
        imgbtnSachMoi4 = (ImageButton)findViewById(R.id.imgbtn_sachmoi4);
        imgbtnSachMoi5 = (ImageButton)findViewById(R.id.imgbtn_sachmoi5);
        imgbtnSachMoi6 = (ImageButton)findViewById(R.id.imgbtn_sachmoi6);
        imgbtnSachMoi7 = (ImageButton)findViewById(R.id.imgbtn_sachmoi7);
        imgbtnSachMoi8 = (ImageButton)findViewById(R.id.imgbtn_sachmoi8);
        imgbtnSachMoi9 = (ImageButton)findViewById(R.id.imgbtn_sachmoi9);

        tvSachMoi1 = (TextView)findViewById(R.id.tv_sachmoi1);
        tvSachMoi2 = (TextView)findViewById(R.id.tv_sachmoi2);
        tvSachMoi3 = (TextView)findViewById(R.id.tv_sachmoi3);
        tvSachMoi4 = (TextView)findViewById(R.id.tv_sachmoi4);
        tvSachMoi5 = (TextView)findViewById(R.id.tv_sachmoi5);
        tvSachMoi6 = (TextView)findViewById(R.id.tv_sachmoi6);
        tvSachMoi7 = (TextView)findViewById(R.id.tv_sachmoi7);
        tvSachMoi8 = (TextView)findViewById(R.id.tv_sachmoi8);
        tvSachMoi9 = (TextView)findViewById(R.id.tv_sachmoi9);

        gridViewDocNhieuNhat = (GridView)findViewById(R.id.gridview_docnhieunhat);
    }
    class MyTimer extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {

                Random rand = new Random();
                public void run() {
                    layoutSlide.setBackgroundResource(img_slide[rand.nextInt(4)]);

                }
            });
        }
    }

    public void eventGridViewDocNhieuNhat(){
        item.add(new GribView(R.drawable.sach18,"Cảm Xúc Cuộc Sống Từ Chiếc Xe Lăng","Daniel Gotrlieb"));
        item.add(new GribView(R.drawable.sach11,"Sống Bản Lĩnh Theo Cách Một quý Cô","Ellen Fein, Sherrie Schneider"));
        item.add(new GribView(R.drawable.sach12,"Thần Chết Làm Thêm 300 yên","Fujimaru"));
        item.add(new GribView(R.drawable.sach13,"Vượt Lên Nghịch Cảnh","Jack Canfield"));
        item.add(new GribView(R.drawable.sach14,"Wonder Woman","Leigh bardugo"));
        item.add(new GribView(R.drawable.sach15,"Between Shades Of Gray","Ruta Sepetys"));
        GribViewAdapter adapter = new GribViewAdapter(this,item);
        gridViewDocNhieuNhat.setAdapter(adapter);
    }

    public void eventGridViewSeeAllSachDocNhieu() {
        item.clear();
        item.add(new GribView(R.drawable.sach10,"Sapiends Lược Sử Loài Người","Yuval Noah Harari"));
        item.add(new GribView(R.drawable.sach11,"Sống Bản Lĩnh Theo Cách Một quý Cô","Ellen Fein, Sherrie Schneider"));
        item.add(new GribView(R.drawable.sach12,"Thần Chết Làm Thêm 300 yên","Fujimaru"));
        item.add(new GribView(R.drawable.sach13,"Vượt Lên Nghịch Cảnh","Jack Canfield"));
        item.add(new GribView(R.drawable.sach14,"Wonder Woman","Leigh bardugo"));
        item.add(new GribView(R.drawable.sach15,"Between Shades Of Gray","Ruta Sepetys"));
        item.add(new GribView(R.drawable.sach16,"Glass Sword","Victoria Aveyard"));
        item.add(new GribView(R.drawable.sach17,"Colorful","Mori Eto"));
        item.add(new GribView(R.drawable.sach18,"Cảm Xúc Cuộc Sống Từ Chiếc Xe Lăng","Daniel Gotrlieb"));
        GribViewAdapter adapter = new GribViewAdapter(this,item);
        gridViewSeeAllSachDocNhieu.setAdapter(adapter);
    }

    public void eventGridViewSeeAllSachMoi() {
        item.clear();
        item.add(new GribView(R.drawable.sach1,"Tình Yêu Vượt Thời Gian","Catherine Bybee"));
        item.add(new GribView(R.drawable.sach2,"Nghĩ đơn giản sống đơn thuần","Tolly-Burkan"));
        item.add(new GribView(R.drawable.sach3,"Đắc Nhân Tâm","Dale Carnegie"));
        item.add(new GribView(R.drawable.sach4,"Cô gái đến từ hôm qua","Nguyễn Nhật Ánh"));
        item.add(new GribView(R.drawable.sach5,"Người xưa đã quên ngày xưa","Anh Khang"));
        item.add(new GribView(R.drawable.sach6,"Your Name","ShinkaiMakoto"));
        item.add(new GribView(R.drawable.sach7,"5 Centimet Trên Giây","ShinkaiMakoto"));
        item.add(new GribView(R.drawable.sach8,"Bình Tĩnh Khi Ế Mạnh Mẽ Khi Yêu","Ellen Fein,Sherrie Scheneider"));
        item.add(new GribView(R.drawable.sach9,"Mình Về Nhà Thôi","Catherine Ryan Hyde"));
        GribViewAdapter adapter = new GribViewAdapter(this,item);
        gridViewSeeAllSachMoi.setAdapter(adapter);
    }

}

