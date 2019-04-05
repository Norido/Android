package com.example.bai51_tuan4;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button doan,thucuong, thoat;
    TextView resutl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }
    public void setControl(){
        doan = findViewById(R.id.doan);
        thucuong = findViewById(R.id.thucuong);
        thoat = findViewById(R.id.thoat);
        resutl = findViewById(R.id.result);
    }
    public void setEvent(){

        doan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DoAn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        thucuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThucUong.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exit();
            }
        });
        Intent i = getIntent();
        if (i.getStringExtra("doan")!=null){
            Result.res1 = i.getStringExtra("doan");
        }
        if (i.getStringExtra("thucuong")!=null){
            Result.res2 = i.getStringExtra("thucuong");
        }

        resutl.setText(Result.res1 +" + "+ Result.res2);
    }
    public void Exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
