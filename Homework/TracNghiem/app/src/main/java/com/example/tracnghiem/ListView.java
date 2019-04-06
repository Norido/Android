package com.example.tracnghiem;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListView extends AppCompatActivity {
    Button btright, btnleft, btnKQ;
    TextView txtdapan;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        android.widget.ListView list = findViewById(R.id.listView);
        Intent intent = getIntent();
        Result Cau1 = new Result(intent.getStringExtra("Cau1"));
        Result Cau2 = new Result(intent.getStringExtra("Cau2"));
        Result Cau3 = new Result(intent.getStringExtra("Cau3"));
        Result Cau4 = new Result(intent.getStringExtra("Cau4"));
        Result Cau5 = new Result(intent.getStringExtra("Cau5"));

        Result[] results = new Result[]{Cau1,Cau2,Cau3,Cau4,Cau5};

        ArrayAdapter<Result> arrayAdapter = new ArrayAdapter<Result>(this,android.R.layout.simple_list_item_1, results);

        list.setAdapter(arrayAdapter);
    }

}
