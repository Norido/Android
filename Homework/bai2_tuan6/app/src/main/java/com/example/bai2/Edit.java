package com.example.bai2;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Edit extends AppCompatActivity {
    ImageView edit_img;
    TextView edit_name;
    EditText edit_home, edit_medal;
    Button btn_ok, btn_cancle;
    Bitmap bmp;
    String home, medal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        // Init
        edit_img = (ImageView) findViewById(R.id.edit_img);
        edit_home = (EditText) findViewById(R.id.edit_home);
        edit_medal = (EditText) findViewById(R.id.edit_medal);
        edit_name = (TextView) findViewById(R.id.edit_name);
        // do du lieu ra
        Intent intent = this.getIntent();
        bmp = (Bitmap) intent.getParcelableExtra("icon");
        edit_img.setImageBitmap(bmp);
        edit_name.setText(intent.getStringExtra("name"));
        home = intent.getStringExtra("home");
        edit_home.setText(home);
        medal = intent.getStringExtra("medal");
        edit_medal.setText(medal);
        // tra du lieu ve
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_cancle = (Button) findViewById(R.id.btn_cancle);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent respond = new Intent();
                respond.putExtra("medal",edit_medal.getText().toString());
                respond.putExtra("home",edit_home.getText().toString());
                respond.putExtra("icon", bmp);
                respond.putExtra("name",edit_name.getText().toString());
                setResult(RESULT_OK,respond);
                finish();
            }
        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent respond = new Intent();
                respond.putExtra("medal",medal);
                respond.putExtra("home",home);
                respond.putExtra("icon", bmp);
                respond.putExtra("name",edit_name.getText().toString());
                setResult(RESULT_OK,respond);
                finish();
            }
        });
    }
}
