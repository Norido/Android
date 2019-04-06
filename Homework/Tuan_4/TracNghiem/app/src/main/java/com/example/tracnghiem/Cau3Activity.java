package com.example.tracnghiem;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Cau3Activity extends AppCompatActivity {
    Button btright, btnleft, btnKQ;
    TextView txtdapan;
    Spinner spin, spin2, spin3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau3);
        setControl();
        setEvent();
    }
    public void setControl(){
        btright = findViewById(R.id.right);
        btnleft = findViewById(R.id.left);
        btnKQ = findViewById(R.id.KQ);
        txtdapan = findViewById(R.id.dapan);
        spin = findViewById(R.id.spinner1);
        spin2 = findViewById(R.id.spinner2);
        spin3 = findViewById(R.id.spinner3);

    }
    public void setEvent(){
        KetQua.lsKQ.add(2,"Câu 3: Đáp án sai");
        btright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cau3Activity.this, Cau4Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                txtdapan.setTextColor(Color.parseColor("#ff0006"));
                String selected = String.valueOf(spin.getSelectedItem());
                String selected2 = String.valueOf(spin2.getSelectedItem());
                String selected3 = String.valueOf(spin3.getSelectedItem());

                if (selected.equals("ĐÚNG")&selected2.equals("SAI")&selected3.equals("SAI")){
                    KetQua.lsKQ.set(2,"Câu 3: Đáp án đúng");
                }
                else {
                    KetQua.lsKQ.set(2,"Câu 3: Đáp án sai");
                }
            }
        });
        btnleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cau3Activity.this, Cau2Activity.class);
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
