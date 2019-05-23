package com.gumiho.giuaky;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

public class KhachHangActivity extends AppCompatActivity {
//    private ListView listviewDSKhachHang;
    private SwipeMenuListView listviewDSKhachHang;
    private List<KhachHang> data_KH = new ArrayList<>();
    private Database database;
    private KhachHangAdapter khachHangAdapter = null;
    private FloatingActionButton fab,btnInsertKH, btnDeleteKH, btnUpdateKH;
    private EditText  edtTenKH, edtDChi, edtDThoai;
    private TextView edtMaKH;
    private int index = -1;
    private boolean check = false, check_position = true;
    private int old_position = -1;
    private TextView title_chucnang;
    private Button ok,huy;
    private Dialog dialog;
    public DatabaseAccess databaseAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_khachhang);

        init();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    btnDeleteKH.hide();
                    btnInsertKH.hide();
                    btnUpdateKH.hide();
                    check = !check;
                }
                else{
                    btnDeleteKH.show();
                    btnInsertKH.show();
                    btnUpdateKH.show();
                    check = !check;
                }

            }
        });
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        khachHangAdapter = new KhachHangAdapter(KhachHangActivity.this, R.layout.item_khachhang, data_KH);
        listviewDSKhachHang.setAdapter(khachHangAdapter);
        database = new Database(this,"quotes.db",null,1);
        showData();
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0x44, 0x8A,
                        0xFF)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("Hóa đơn");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);
            }

        };

// set creator
        listviewDSKhachHang.setMenuCreator(creator);
        listviewDSKhachHang.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Intent intent = new Intent(KhachHangActivity.this,HoaDonActivity.class);
                        KhachHang khachHang = data_KH.get(position);
                        intent.putExtra("data",String.valueOf(khachHang.getMaKH()));
                        startActivity(intent);
                        // open
                        break;

                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        listviewDSKhachHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(old_position != position){
                    fab.show();
                    check_position = true;
                }else
                if(check_position){
                    fab.hide();
                    btnDeleteKH.hide();
                    btnInsertKH.hide();
                    btnUpdateKH.hide();
                    check_position = !check_position;
                }
                else {
                    fab.show();
                    check_position = !check_position;
                }

                old_position = position;
                index = position;

            }
        });



        btnInsertKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog("Thêm");

            }
        });
        btnDeleteKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder delete = new AlertDialog.Builder(KhachHangActivity.this);
                delete.setTitle("Cảnh báo!");
                delete.setMessage("Bạn chắc chắn muốn xóa?");
                delete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteKH();
                    }
                });
                delete.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                delete.show();
            }
        });
        btnUpdateKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog("Sửa");
                   // UpdateKH();

            }
        });
    }

    public void init() {
        fab = (FloatingActionButton) findViewById(R.id.fab_khachhang);
//        listviewDSKhachHang = (ListView) findViewById(R.id.listview_dskh);
        listviewDSKhachHang = (SwipeMenuListView) findViewById(R.id.listview_dskh);
        btnInsertKH = (FloatingActionButton) findViewById(R.id.btn_insert);
        btnUpdateKH = (FloatingActionButton) findViewById(R.id.btn_update);
        btnDeleteKH = (FloatingActionButton) findViewById(R.id.btn_delete);
    }
    private void Dialog(String s){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.chucnang_khachhang);
        title_chucnang = (TextView) dialog.findViewById(R.id.title_chucnang);
        edtMaKH = (TextView) dialog.findViewById(R.id.edt_maKH);
        edtTenKH = (EditText) dialog.findViewById(R.id.edt_tenKH);
        edtDChi = (EditText) dialog.findViewById(R.id.edt_dchi);
        edtDThoai = (EditText) dialog.findViewById(R.id.edt_dthoai);
        ok = (Button) dialog.findViewById(R.id.OK);
        huy = (Button) dialog.findViewById(R.id.huy);
        title_chucnang.setText(s);
        KhachHang kh = data_KH.get(index);
        edtMaKH.setText(String.valueOf(kh.getMaKH()));
        edtTenKH.setText(kh.getTenKH());
        edtDChi.setText(kh.getDiaChi());
        edtDThoai.setText(kh.getSDT());
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtTenKH.getText().equals("") || edtDChi.getText().equals("") || edtDThoai.getText().equals("")){
                    AlertDialog.Builder alert = new AlertDialog.Builder(KhachHangActivity.this);
                    alert.setTitle("Cảnh báo!");
                    alert.setMessage("Bạn chưa nhập đầy đủ thông tin");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert.show();
                }
                else if(title_chucnang.getText().equals("Thêm")){
                    InsertKH();
                }
                else if(title_chucnang.getText().equals("Sửa")){
                    UpdateKH();
                }
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private KhachHang getKhachHang() {
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKH(Integer.parseInt(edtMaKH.getText().toString()));
        khachHang.setTenKH(edtTenKH.getText().toString());
        khachHang.setDiaChi(edtDChi.getText().toString());
        khachHang.setSDT(edtDThoai.getText().toString());
        return khachHang;
    }
    public void showData(){
        Cursor data = database.Getdata("SELECT * FROM KHACHHANG");
        while (data.moveToNext()){
            int maKH = data.getInt(0);
            String tenKH = data.getString(1);
            String diaChi = data.getString(2);
            String soDienThoai = data.getString(3);
            data_KH.add(new KhachHang(maKH,tenKH,diaChi,soDienThoai));
        }
        khachHangAdapter.notifyDataSetChanged();
    }
    public void InsertKH() {
        KhachHang khachHang = getKhachHang();
        String query = "INSERT INTO KHACHHANG VALUES(null,'"+ khachHang.getTenKH() + "','" + khachHang.getDiaChi() + "','" + khachHang.getSDT() +"')";
        database.Querydata(query);
        data_KH.clear();
        showData();
        dialog.dismiss();
        fab_hide();
        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
    }

    public void UpdateKH() {
        KhachHang khachHang = getKhachHang();
        String query = "UPDATE KHACHHANG SET TenKH='"+ khachHang.getTenKH() + "',DiaChi='" + khachHang.getDiaChi() + "',DienThoai='" + khachHang.getSDT() +"' WHERE MaKH='"+ khachHang.getMaKH()+ "'";
        database.Querydata(query);
        data_KH.clear();
        showData();
        dialog.dismiss();
        fab_hide();
        Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
    }

    public void DeleteKH() {
        KhachHang khachHang = data_KH.get(index);
        if (index >= 0) {
            String query = "DELETE FROM KHACHHANG WHERE MaKH='"+ khachHang.getMaKH()+ "'";
            database.Querydata(query);
            data_KH.clear();
            showData();
            fab_hide();
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
    }
    public void fab_hide(){
        fab.hide();
        btnDeleteKH.hide();
        btnInsertKH.hide();
        btnUpdateKH.hide();
    }
}