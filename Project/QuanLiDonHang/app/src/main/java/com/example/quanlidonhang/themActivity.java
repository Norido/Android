package com.example.quanlidonhang;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class themActivity extends AppCompatActivity {

    final String database_name="DonHang.sqlite";
    SQLiteDatabase database;
    private EditText maKH;
    private EditText tenKH;
    private EditText diaChi;
    private EditText sodienthoai;
    private Button themKH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        initDatabase();
        anhXa();
        xuLi();
    }

    private void anhXa(){
        maKH = (EditText) findViewById(R.id.edt_makh);
        tenKH = (EditText)findViewById(R.id.edt_tenkh);
        diaChi = (EditText)findViewById(R.id.edt_diachi);
        sodienthoai = (EditText)findViewById(R.id.edt_sdt);
        themKH = (Button)findViewById(R.id.btn_themkh);
    }

    private int check(String s){
        if(s.matches("")){
            Toast.makeText(themActivity.this," Bạn chưa đầy đủ hết thông tin",Toast.LENGTH_LONG).show();
            return 0;
        }else
            return 1;
    }
    private void xuLi(){
        themKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ma = maKH.getText().toString();
                if (check(ma)==0){return;}

                String ten  = maKH.getText().toString();
                if (check(ten)==0){return;}

                String dc = maKH.getText().toString();
                if (check(dc)==0){return;}

                String dt = maKH.getText().toString();
                if (check(dt)==0){return;}
                String insert_kh = "INSERT INTO KhachHang(MAKH,TENKH,DIACHI,SDT) VALUES ("+"\"\"+ma+\"\\\"\"+\",\"+\"\"\"+ten+\"\\\"\"+\",\"+\"\"\"+dc+\"\\\"\"+\",\"+\"\"\"+dt+\"\\\"\"+ \")";

                try {
                    database.execSQL(insert_kh);

                }catch (SQLException e){
                    Toast.makeText(themActivity.this, "Khách hàng đã tồn tại", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(themActivity.this," Thêm thành công",Toast.LENGTH_LONG).show();
                finish();
            }

        });
    }
    private void initDatabase() {
        database = Database.initDatabase(themActivity.this, database_name);
        String query = "SELECT * FROM KhachHang";
        Cursor cursor = database.rawQuery(query,null);
        if ( !(cursor.moveToFirst())) {
            Toast.makeText(themActivity.this, "Fail to connect to database", Toast.LENGTH_SHORT).show();
        }
    }

}
