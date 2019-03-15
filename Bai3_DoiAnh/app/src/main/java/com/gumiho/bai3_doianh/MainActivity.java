package com.gumiho.bai3_doianh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private RadioButton rbtnBird;
    private RadioButton rbtnCat;
    private RadioButton rbtnDog;
    private RadioButton rbtnRabbit;
    private RadioButton rbtnPig;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();

        final RadioGroup rGroup = (RadioGroup) findViewById(R.id.rbtn_group);
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                imageView.setImageBitmap(null);
                if (rbtnBird.isChecked()) {
                    imageView.setBackgroundResource(R.drawable.bird);
                }
                if (rbtnCat.isChecked()) {
                    imageView.setBackgroundResource(R.drawable.cat);
                }
                if (rbtnDog.isChecked()) {
                    imageView.setBackgroundResource(R.drawable.dog);
                }
                if (rbtnRabbit.isChecked()) {
                    imageView.setBackgroundResource(R.drawable.rabbit);
                }
                if (rbtnPig.isChecked()) {
                    imageView.setBackgroundResource(R.drawable.pig);
                }
            }
        });
    }


    public void initWidget() {
        imageView = (ImageView) findViewById(R.id.img_View);
        rbtnBird = (RadioButton) findViewById(R.id.rbtn_bird);
        rbtnCat = (RadioButton) findViewById(R.id.rbtn_cat);
        rbtnDog = (RadioButton) findViewById(R.id.rbtn_dog);
        rbtnRabbit = (RadioButton) findViewById(R.id.rbtn_rabbit);
        rbtnPig = (RadioButton) findViewById(R.id.rbtn_pig);
        imageView = (ImageView) findViewById(R.id.img_View);
    }
}
