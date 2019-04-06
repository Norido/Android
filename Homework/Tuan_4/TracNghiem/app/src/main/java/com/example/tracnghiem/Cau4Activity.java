package com.example.tracnghiem;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Cau4Activity extends AppCompatActivity {
    Button btright, btnleft, btnKQ;
    TextView txtdapan;
    ToggleButton checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau4);
        setControl();
        setEvent();
    }
    public void setControl(){
        btright = findViewById(R.id.right);
        btnleft = findViewById(R.id.left);
        btnKQ = findViewById(R.id.KQ);
        checked = findViewById(R.id.tog3);
        txtdapan = findViewById(R.id.dapan);
    }
    public void setEvent(){
        KetQua.lsKQ.add(3,"Câu 4: Đáp án sai");
        btright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cau4Activity.this, activity_cau5.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                txtdapan.setTextColor(Color.parseColor("#ff0006"));
                if (checked.isChecked()){
                    KetQua.lsKQ.set(3,"Câu 4: Đáp án đúng");

                } else
                {
                    KetQua.lsKQ.set(3,"Câu 4: Đáp án sai");
                }
            }
        });
        btnleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cau4Activity.this, Cau3Activity.class);
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
