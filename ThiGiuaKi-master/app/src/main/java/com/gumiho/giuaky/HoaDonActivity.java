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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class HoaDonActivity extends AppCompatActivity {
    public SwipeMenuListView listviewDSHoaDon;
    ArrayList<HoaDon> data_HD = new ArrayList<>();

    HoaDonAdapter hoaDonAdapter = null;
    Database database;
    private EditText edtNgaylap, edtNgaygiao;
    private Spinner edtMaKH;
    private TextView edtSoHD;
    private String ma_khach_hang;
    int index = -1;
    private FloatingActionButton fab, btnInsertHD, btnDeleteHD, btnUpdateHD;
    private boolean check = false, check_position = true;
    private int old_position = -1;
    private Dialog dialog;
    private TextView title_chucnang;
    private Button ok,huy;
    private int position_MaKH;
    private ArrayList<String> hoadon;
    public DatabaseAccess databaseAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hoadon);
        Intent intent = getIntent();
        ma_khach_hang = intent.getStringExtra("data");
        init();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    btnDeleteHD.hide();
                    btnInsertHD.hide();
                    btnUpdateHD.hide();
                    check = !check;
                }
                else{
                    btnDeleteHD.show();
                    btnInsertHD.show();
                    btnUpdateHD.show();
                    check = !check;
                }

            }
        });
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        hoaDonAdapter = new HoaDonAdapter(HoaDonActivity.this, R.layout.item_hoadon, data_HD);
        listviewDSHoaDon.setAdapter(hoaDonAdapter);
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
                openItem.setTitle("Chi Tiết Hóa Đơn");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);
            }

        };
        listviewDSHoaDon.setMenuCreator(creator);
        listviewDSHoaDon.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Intent intent = new Intent(HoaDonActivity.this,ChiTietHoaDonActivity.class);
                        HoaDon hoaDon = data_HD.get(position);
                        intent.putExtra("data",String.valueOf(hoaDon.getSoHD()));
                        startActivity(intent);
                        // open
                        break;

                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        listviewDSHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(old_position != position){
                    fab.show();
                    check_position = true;
                }else
                if(check_position){
                    fab_hide();
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


        btnInsertHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog("Thêm");
            }
        });

        btnUpdateHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog("Sửa");
            }
        });
        btnDeleteHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder delete = new AlertDialog.Builder(HoaDonActivity.this);
                delete.setTitle("Cảnh báo!");
                delete.setMessage("Bạn chắc chắn muốn xóa?");
                delete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteHD();
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

    }
    public void init() {
        listviewDSHoaDon = (SwipeMenuListView) findViewById(R.id.listview_hd);
        fab = (FloatingActionButton) findViewById(R.id.fab_hoadon);
        btnInsertHD = (FloatingActionButton) findViewById(R.id.btn_insert);
        btnUpdateHD = (FloatingActionButton) findViewById(R.id.btn_update);
        btnDeleteHD = (FloatingActionButton) findViewById(R.id.btn_delete);


    }
    private void Dialog(String s){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.chucnang_hoadon);
        title_chucnang = (TextView) dialog.findViewById(R.id.title_chucnang);
        edtSoHD = (TextView) dialog.findViewById(R.id.edt_sohd);
        edtNgaylap = (EditText) dialog.findViewById(R.id.edt_ngaylap);
        edtNgaygiao = (EditText) dialog.findViewById(R.id.edt_ngaygiao);
        edtMaKH = (Spinner) dialog.findViewById(R.id.edt_makh_hoadon);
        edtMaKH.setGravity(View.TEXT_ALIGNMENT_CENTER);
        ok = (Button) dialog.findViewById(R.id.OK);
        huy = (Button) dialog.findViewById(R.id.huy);

        hoadon = new ArrayList<>();
        Cursor data = database.Getdata("SELECT MaKH FROM KHACHHANG");
        while (data.moveToNext()){
            int MaKH = data.getInt(0);
            hoadon.add(String.valueOf(MaKH));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,hoadon);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        edtMaKH.setAdapter(adapter);
        title_chucnang.setText(s);
        HoaDon hd = data_HD.get(index);
        edtSoHD.setText(String.valueOf(hd.getSoHD()));
        edtNgaylap.setText(hd.getNgayLap());
        edtNgaygiao.setText(hd.getNgayGiao());
        edtMaKH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position_MaKH = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtNgaylap.getText().equals("") || edtNgaygiao.getText().equals("") || hoadon.get(position_MaKH).equals("")){
                    AlertDialog.Builder alert = new AlertDialog.Builder(HoaDonActivity.this);
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
                    InsertHD();
                }
                else if(title_chucnang.getText().equals("Sửa")){
                    UpdateHD();
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

    private HoaDon getHoaDon() {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setSoHD(Integer.parseInt(edtSoHD.getText().toString()));
        hoaDon.setNgayLap(edtNgaylap.getText().toString());
        hoaDon.setNgayGiao(edtNgaygiao.getText().toString());
        hoaDon.setMaKH(Integer.parseInt(hoadon.get(position_MaKH)));
        return hoaDon;
    }

    public void showData(){
        String query;
        if(ma_khach_hang.equals("all")){
            query = "SELECT * FROM HOADON";
        }
        else{
            query = "SELECT * FROM HOADON WHERE MaKH='"+ ma_khach_hang +"'";
        }
        Cursor data = database.Getdata(query);
        while (data.moveToNext()){
            int soHD = data.getInt(0);
            String ngayLap = data.getString(1);
            String ngayGiao = data.getString(2);
            int maKH = data.getInt(3);
            data_HD.add(new HoaDon(soHD,ngayLap,ngayGiao,maKH));
        }
        hoaDonAdapter.notifyDataSetChanged();
    }
    public void InsertHD() {
        HoaDon hoaDon = getHoaDon();
        String query = "INSERT INTO HOADON VALUES(null,'"+ hoaDon.getNgayLap() + "','" + hoaDon.getNgayGiao() + "','" + hoaDon.getMaKH() +"')";
        database.Querydata(query);
        data_HD.clear();
        showData();
        dialog.dismiss();
        fab_hide();
        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
    }

    public void UpdateHD() {
        HoaDon hoaDon = getHoaDon();
        String query = "UPDATE HOADON SET NgayLap='"+ hoaDon.getNgayLap() + "',NgayGiao='" + hoaDon.getNgayGiao() + "',MaKH='" + hoaDon.getMaKH() +"' WHERE SoHD='"+ hoaDon.getSoHD()+ "'";
        database.Querydata(query);
        data_HD.clear();
        showData();
        dialog.dismiss();
        fab_hide();
        Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
    }
    //
    public void DeleteHD() {
        HoaDon hoaDon = data_HD.get(index);
        if (index >= 0) {
            String query = "DELETE FROM HOADON WHERE SoHD='"+ hoaDon.getSoHD() + "'";
            database.Querydata(query);
            data_HD.clear();
            showData();
            dialog.dismiss();
            fab_hide();
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
    }
    public void fab_hide(){
        fab.hide();
        btnDeleteHD.hide();
        btnInsertHD.hide();
        btnUpdateHD.hide();
    }
}
