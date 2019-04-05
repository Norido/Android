package com.example.appdocsach;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class XemThemActivity extends AppCompatActivity{
    Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them);
        btn= (Button) findViewById(R.id.rutgon);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Toast.makeText(XemThemActivity.this,"Hiện thi rut gon",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(XemThemActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
