package com.example.choibai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //click nút PLay trước khi click vào lá bài
    //Mỗi lượt chơi lá bài chỉ được lật 1 lần nên sau khi lick lá bài sẽ bị vô hiệu hóa muốn chơi lại phải lick nút play
    // Khi lick vào lá bài nút PLAY sẽ bị vô hiệu hóa và sau khi lick xong 3 lá nút PLAY sẽ được enable để chơi lại
    Button btnplay, nen;
    ImageButton btn1, btn2, btn3;
    TextView result;
    ArrayList<Integer> arrayImage = new ArrayList<Integer>();
    int diem1, diem2, diem3 = 0;
    int tong = 0;
    int check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        RandomHinh();
        btnDisable();

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Lick vào hinh để mở bai", Toast.LENGTH_LONG).show();
                reset();
                btnEnable();
                btnplay.setEnabled(false);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Hiện random hinh anh", Toast.LENGTH_LONG).show();
                        diem1 = RandHinh(btn1);
                        btn1.setEnabled(false);
                        if (check != 2)
                            check++;
                        else
                            setTong();
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Hiện random hinh anh", Toast.LENGTH_LONG).show();
                        diem2 = RandHinh(btn2);
                        btn2.setEnabled(false);
                        if (check != 2)
                            check++;
                        else
                            setTong();
                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Hiện random hinh anh", Toast.LENGTH_LONG).show();
                        diem3 = RandHinh(btn3);
                        btn3.setEnabled(false);
                        if (check != 2)
                            check++;
                        else
                            setTong();
                    }
                });

                btnplay.setEnabled(true);

            }
        });


    }

    private void reset(){
        check = 0;
        result.setText(String.valueOf(0));
        btn1.setBackgroundResource(R.drawable.labai);
        btn2.setBackgroundResource(R.drawable.labai);
        btn3.setBackgroundResource(R.drawable.labai);
    }
    private void setTong(){
        tong = (diem1 + diem2 + diem3)%10;
        result.setText(String.valueOf(tong));
    }
    private void AnhXa() {
        btn1 = (ImageButton) findViewById(R.id.la1);
        btn2 = (ImageButton) findViewById(R.id.la2);
        btn3 = (ImageButton) findViewById(R.id.la3);
        btnplay = (Button) findViewById(R.id.play);
        result = (TextView) findViewById(R.id.tongdiem);
    }

    private void RandomHinh() {
        arrayImage.add(0,R.drawable.jco0);
        arrayImage.add(1, R.drawable.b1);
        arrayImage.add(2, R.drawable.b2);
        arrayImage.add(3, R.drawable.b3);
        arrayImage.add(4, R.drawable.b4);
        arrayImage.add(5, R.drawable.b5);
        arrayImage.add(6, R.drawable.b6);
        arrayImage.add(7, R.drawable.b7);
        arrayImage.add(8, R.drawable.b8);
        arrayImage.add(9, R.drawable.b9);
        arrayImage.add(10, R.drawable.b10);
        arrayImage.add(11, R.drawable.c1);
        arrayImage.add(12, R.drawable.c2);
        arrayImage.add(13, R.drawable.c3);
        arrayImage.add(14, R.drawable.c4);
        arrayImage.add(15, R.drawable.c5);
        arrayImage.add(16, R.drawable.c6);
        arrayImage.add(17, R.drawable.c7);
        arrayImage.add(18, R.drawable.c8);
        arrayImage.add(19, R.drawable.c9);
        arrayImage.add(19, R.drawable.c9);
        arrayImage.add(20, R.drawable.c10);
        arrayImage.add(21, R.drawable.co1);
        arrayImage.add(22, R.drawable.co2);
        arrayImage.add(23, R.drawable.co3);
        arrayImage.add(24, R.drawable.co4);
        arrayImage.add(25, R.drawable.co5);
        arrayImage.add(26, R.drawable.co6);
        arrayImage.add(27, R.drawable.co7);
        arrayImage.add(28, R.drawable.co8);
        arrayImage.add(29, R.drawable.co9);
        arrayImage.add(30, R.drawable.co10);
        arrayImage.add(30, R.drawable.co10);
        arrayImage.add(31, R.drawable.r1);
        arrayImage.add(32, R.drawable.r2);
        arrayImage.add(33, R.drawable.r3);
        arrayImage.add(34, R.drawable.r4);
        arrayImage.add(35, R.drawable.r5);
        arrayImage.add(36, R.drawable.r6);
        arrayImage.add(37, R.drawable.r7);
        arrayImage.add(38, R.drawable.r8);
        arrayImage.add(39, R.drawable.r9);
        arrayImage.add(40, R.drawable.r10);
        arrayImage.add(41, R.drawable.jbich0);
        arrayImage.add(42, R.drawable.jchuon0);
        arrayImage.add(44, R.drawable.jro0);
        arrayImage.add(45, R.drawable.kbich0);
        arrayImage.add(46, R.drawable.kchuon0);
        arrayImage.add(47, R.drawable.kco0);
        arrayImage.add(48, R.drawable.kro0);
        arrayImage.add(49, R.drawable.qbich0);
        arrayImage.add(50, R.drawable.qchuon0);
        arrayImage.add(51, R.drawable.qco0);
        arrayImage.add(52, R.drawable.qro0);
    }

    private int RandHinh(ImageButton bt) {
        Random rd = new Random();
        int BG = rd.nextInt(arrayImage.size()+1);
        System.out.println(BG);
        System.out.println(arrayImage.lastIndexOf(R.drawable.b1));
        bt.setBackgroundResource(arrayImage.get(BG));
        if (BG > 40)
            return 0;
        else
            return (BG % 10);

    }

    private void btnEnable() {
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
    }

    private void btnDisable() {
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
    }

}
