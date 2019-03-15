package com.example.choibai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
  //click nút PLay trước khi click vào lá bài
    //Mỗi lượt chơi lá bài chỉ được lật 1 lần nên sau khi lick lá bài sẽ bị vô hiệu hóa muốn chơi lại phải lick nút play
    // Khi lick vào lá bài nút PLAY sẽ bị vô hiệu hóa và sau khi lick xong 3 lá nút PLAY sẽ được enable để chơi lại
    Button btnplay, nen;
    ImageButton btn1,btn2,btn3;
    TextView scource;
    ArrayList<card> arrayImage= new ArrayList<>();
    int diem =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        btnDisable();
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Lick vào hinh để mở bai",Toast.LENGTH_LONG).show();

               btnEnable();
               btnplay.setEnabled(false);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"Hiện random hinh anh",Toast.LENGTH_LONG).show();
                        RandomHinh(btn1);
                        btn1.setEnabled(false);
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"Hiện random hinh anh",Toast.LENGTH_LONG).show();
                        RandomHinh(btn2);
                        btn2.setEnabled(false);
                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"Hiện random hinh anh",Toast.LENGTH_LONG).show();
                        RandomHinh(btn3);
                        btn3.setEnabled(false);
                    }
                });

                btnplay.setEnabled(true);
            }
        });

    }

    private void AnhXa ()
    {
        btn1 = (ImageButton)findViewById(R.id.la1);
        btn2 = (ImageButton)findViewById(R.id.la2);
        btn3 = (ImageButton) findViewById(R.id.la3);
        btnplay = (Button)findViewById(R.id.play);
        scource = (TextView)findViewById(R.id.diem);
    }
    private void RandomHinh(ImageButton bt)
    {
        int btn = bt.getId();
        nen =(Button) findViewById(btn); // hiện thị ngẫu nhiên hinh nền

        arrayImage.add(new card(0,R.drawable.ba0));
        arrayImage.add(new card(10,R.drawable.b10));
        arrayImage.add(new card(2,R.drawable.b2));
        arrayImage.add(new card(3,R.drawable.b3));
        arrayImage.add(new card(4,R.drawable.b4));
        arrayImage.add(new card(5,R.drawable.b5));
        arrayImage.add(new card(6,R.drawable.b6));
        arrayImage.add(new card(7,R.drawable.b7));
        arrayImage.add(new card(8,R.drawable.b8));
        arrayImage.add(new card(9,R.drawable.b9));
        arrayImage.add(new card(0,R.drawable.ca0));
        arrayImage.add(new card(10,R.drawable.c10));
        arrayImage.add(new card(2,R.drawable.c2));
        arrayImage.add(new card(3,R.drawable.c3));
        arrayImage.add(new card(4,R.drawable.c4));
        arrayImage.add(new card(5,R.drawable.chuon5));
        arrayImage.add(new card(6,R.drawable.c6));
        arrayImage.add(new card(7,R.drawable.c7));
        arrayImage.add(new card(8,R.drawable.c8));
        arrayImage.add(new card(9,R.drawable.c9));
        arrayImage.add(new card(0,R.drawable.jbich0));
        arrayImage.add(new card(0,R.drawable.jchuon0));
        arrayImage.add(new card(0,R.drawable.jco0));
        arrayImage.add(new card(0,R.drawable.jro0));
        arrayImage.add(new card(0,R.drawable.kbich0));
        arrayImage.add(new card(0,R.drawable.kchuon0));
        arrayImage.add(new card(0,R.drawable.kco0));
        arrayImage.add(new card(0,R.drawable.kro0));
        arrayImage.add(new card(0,R.drawable.qbich0));
        arrayImage.add(new card(0,R.drawable.qchuon0));
        arrayImage.add(new card(0,R.drawable.qco0));
        arrayImage.add(new card(0,R.drawable.qro0));
        arrayImage.add(new card(1,R.drawable.ra0));
        arrayImage.add(new card(10,R.drawable.r10));
        arrayImage.add(new card(2,R.drawable.r2));
        arrayImage.add(new card(3,R.drawable.ro3));
        arrayImage.add(new card(4,R.drawable.ro4));
        arrayImage.add(new card(5,R.drawable.r5));
        arrayImage.add(new card(6,R.drawable.r6));
        arrayImage.add(new card(7,R.drawable.r7));
        arrayImage.add(new card(8,R.drawable.r8));
        arrayImage.add(new card(9,R.drawable.r9));

        Random rd = new Random();
        int BG = rd.nextInt(arrayImage.size());
        nen.setBackgroundResource(arrayImage.get(BG).getTen());

    }
    public int tongDiem(int a1, int b1, int c1){
        diem = a1 + b1 + c1;
        return diem;
    }

    private void btnEnable()
    {
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
    }
    private void btnDisable()
    {
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
    }

}
