package com.example.tracnghiem;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class activity_cau5 extends AppCompatActivity {
    Button btright, btnleft, btnKQ;
    TextView txtdapan;
    Switch checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau5);
        setControl();
        setEvent();
    }
    public void setControl(){
        btright = findViewById(R.id.right);
        btnleft = findViewById(R.id.left);
        btnKQ = findViewById(R.id.KQ);
        txtdapan = findViewById(R.id.dapan);
        checked = findViewById(R.id.sw3);
    }
    public void setEvent(){
        KetQua.lsKQ.add(4,"Câu 5: Đáp án sai");
        btright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checked.isChecked()){
                    KetQua.lsKQ.set(4,"Câu 5: Đáp án đúng");

                } else
                {
                    KetQua.lsKQ.set(4,"Câu 5: Đáp án sai");
                }
                Intent intent = new Intent(activity_cau5.this, ListView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("Cau1",KetQua.lsKQ.get(0));
                intent.putExtra("Cau2",KetQua.lsKQ.get(1));
                intent.putExtra("Cau3",KetQua.lsKQ.get(2));
                intent.putExtra("Cau4",KetQua.lsKQ.get(3));
                intent.putExtra("Cau5",KetQua.lsKQ.get(4));
                startActivity(intent);
                txtdapan.setTextColor(Color.parseColor("#ff0006"));
            }
        });
        btnleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_cau5.this, Cau4Activity.class);
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
