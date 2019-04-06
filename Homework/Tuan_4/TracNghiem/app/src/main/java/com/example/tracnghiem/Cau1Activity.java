package com.example.tracnghiem;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Cau1Activity extends AppCompatActivity {
    public Button btright, btnleft, btnKQ;
    public TextView txtdapan;
    public RadioButton checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau1);
        setControl();
        setEvent();
    }
    public void setControl(){
        btright = findViewById(R.id.right);
        btnleft = findViewById(R.id.left);
        btnKQ = findViewById(R.id.KQ);
        checked = findViewById(R.id.check3);
        txtdapan = findViewById(R.id.check3);
    }
    public void setEvent(){
        KetQua.lsKQ.add(0,"Câu 1: Đáp án sai");
        btright.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Cau1Activity.this, Cau2Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                txtdapan.setTextColor(Color.parseColor("#ff0006"));
                if (checked.isChecked()){
                    KetQua.lsKQ.set(0,"Câu 1: Đáp án đúng");
                }
                else
                {
                    KetQua.lsKQ.set(0,"Câu 1: Đáp án sai");
                }
            }
        });
        btnleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cau1Activity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        btnKQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               txtdapan.setTextColor(Color.parseColor("#ff0006"));
            }
        });

    }
}
