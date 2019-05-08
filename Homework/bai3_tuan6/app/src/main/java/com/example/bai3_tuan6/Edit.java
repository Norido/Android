package com.example.bai3_tuan6;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class Edit extends AppCompatActivity {
    ImageView edit_img;
    EditText edit_des, edit_name;
    Button btn_ok, btn_cancle;
    RatingBar ratingbar;
    String des, name;
    Float rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        // Init
        edit_img = (ImageView) findViewById(R.id.edit_img);
        edit_des = (EditText) findViewById(R.id.edit_des);
        ratingbar = (RatingBar) findViewById(R.id.edit_rating);
        edit_name = (EditText) findViewById(R.id.edit_name);
        // do du lieu ra
        Intent intent = this.getIntent();

        edit_img.setImageResource(intent.getIntExtra("icon", 0));
        name = intent.getStringExtra("name");
        edit_name.setText(name);
        des = intent.getStringExtra("des");
        edit_des.setText(des.substring(1,des.length()-1));
        rating = intent.getFloatExtra("rating", (float) 3.0);
        ratingbar.setRating(rating);
        // tra du lieu ve
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_cancle = (Button) findViewById(R.id.btn_cancle);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent respond = new Intent();
                des = "(" + edit_des.getText().toString() + ")";
                respond.putExtra("des",des);
                respond.putExtra("rating",ratingbar.getRating());
                respond.putExtra("name",edit_name.getText().toString());
                setResult(RESULT_OK,respond);
                finish();
            }
        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent respond = new Intent();
                respond.putExtra("des",des);
                respond.putExtra("name",name);
                respond.putExtra("rating",rating);
                setResult(RESULT_OK,respond);
                finish();
            }
        });
    }
}
