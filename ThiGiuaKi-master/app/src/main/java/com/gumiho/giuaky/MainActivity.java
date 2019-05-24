package com.gumiho.giuaky;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  ImageButton imgbtnKhachHang;
    private  ImageButton imgbtnMatHang;
    private  ImageButton imgbtnHoaDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        eventKhachHang();
        eventMatHang();
        eventHoaDon();

    }
    public void init(){
        imgbtnKhachHang = (ImageButton) findViewById(R.id.imgbtn_khachhang);
        imgbtnMatHang = (ImageButton) findViewById(R.id.imgbtn_mathang);
        imgbtnHoaDon = (ImageButton) findViewById(R.id.imgbtn_hoadon);
    }
    public void eventKhachHang() {
        imgbtnKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,KhachHangActivity.class);
                startActivity(intent);
            }
        });
    }
    public void eventMatHang() {
        imgbtnMatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MatHangActivity.class);
                startActivity(intent);
            }
        });
    }
    public void eventHoaDon(){
        imgbtnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HoaDonActivity.class);
                intent.putExtra("data", "all");
                startActivity(intent);
            }
        });
    }
}
