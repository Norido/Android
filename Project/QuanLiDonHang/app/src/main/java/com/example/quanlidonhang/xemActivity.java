package com.example.quanlidonhang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class xemActivity  extends AppCompatActivity {
  /*  Spinner spinner;
    Button btnmua, btnxem,btnsua,btnthoat;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem);
     /*   spinner = (Spinner)findViewById(R.id.spinner_mkh);
        btnmua = (Button)findViewById(R.id.btn_muahang);
        btnxem = (Button)findViewById(R.id.btn_xemlai);
        btnsua = (Button)findViewById(R.id.btn_chinhsua);
        btnthoat =(Button)findViewById(R.id.btn_thoatktra);
*/
       /* btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thoat1 = new Intent(xemActivity.this,MainActivity.class);
                startActivity(thoat1);
            }
        });
*/
    }
}
