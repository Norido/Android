package com.example.appdocsach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= (Button) findViewById(R.id.xemthem);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Toast.makeText(MainActivity.this,"Hiện thi thêm",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,XemThemActivity.class);
                startActivity(intent);
            }
        });

    }

    }