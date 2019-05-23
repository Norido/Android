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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class ChiTietHoaDonActivity extends AppCompatActivity{
    public ListView listviewChiTietHoaDon;
    ArrayList<ChiTietHoaDon> data_CTHD = new ArrayList<>();

    ChiTietHoaDonAdapter chitiethoaDonAdapter = null;
    Database database;
    private EditText soLuong;
    private Spinner soHD,maHang;
    private String so_hoa_don;
    int index = -1;
    private FloatingActionButton fab, btnInsert, btnDelete, btnUpdate;
    private boolean check = false, check_position = true;
    private int old_position = -1;
    private Dialog dialog;
    private TextView title_chucnang;
    private Button ok,huy;
    private int position_MaHang,position_soHD;
    private ArrayList<String> sohoadon,mahang;
    public DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitiethoadon);
        Intent intent = getIntent();
        so_hoa_don = intent.getStringExtra("data");
        init();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    btnDelete.hide();
                    btnInsert.hide();
                    btnUpdate.hide();
                    check = !check;
                }
                else{
                    btnDelete.show();
                    btnInsert.show();
                    btnUpdate.show();
                    check = !check;
                }

            }
        });
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        chitiethoaDonAdapter = new ChiTietHoaDonAdapter(ChiTietHoaDonActivity.this, R.layout.item_chitiethoadon, data_CTHD);
        listviewChiTietHoaDon.setAdapter(chitiethoaDonAdapter);
        database = new Database(this,"quotes.db",null,1);
        showData();
        listviewChiTietHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog("Thêm");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog("Sửa");
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder delete = new AlertDialog.Builder(ChiTietHoaDonActivity.this);
                delete.setTitle("Cảnh báo!");
                delete.setMessage("Bạn chắc chắn muốn xóa?");
                delete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Delete();
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
        listviewChiTietHoaDon =  findViewById(R.id.listview_dschitiethoadon);
        fab = (FloatingActionButton) findViewById(R.id.fab_chitiethoadon);
        btnInsert = (FloatingActionButton) findViewById(R.id.btn_insert);
        btnUpdate = (FloatingActionButton) findViewById(R.id.btn_update);
        btnDelete = (FloatingActionButton) findViewById(R.id.btn_delete);


    }
    private void Dialog(String s){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.chucnang_hoadon);
        title_chucnang = (TextView) dialog.findViewById(R.id.title_chucnang);
        soHD = (Spinner) dialog.findViewById(R.id.edt_cthd_sohd);
        maHang = (Spinner) dialog.findViewById(R.id.edt_cthd_mahang);
        soLuong = (EditText) dialog.findViewById(R.id.edt_cthd_soluong);
        soHD.setGravity(View.TEXT_ALIGNMENT_CENTER);
        maHang.setGravity(View.TEXT_ALIGNMENT_CENTER);
        ok = (Button) dialog.findViewById(R.id.OK);
        huy = (Button) dialog.findViewById(R.id.huy);

        sohoadon = new ArrayList<>();
        mahang = new ArrayList<>();
        Cursor data = database.Getdata("SELECT SoHD FROM HOADON");
        while (data.moveToNext()){
            int soHD = data.getInt(0);
            sohoadon.add(String.valueOf(soHD));
        }
        Cursor data_1 = database.Getdata("SELECT MaHang FROM MATHANG");
        while (data_1.moveToNext()){
            int maHang = data.getInt(0);
            sohoadon.add(String.valueOf(maHang));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sohoadon);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        soHD.setAdapter(adapter);
        ArrayAdapter<String> adapter_1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mahang);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        maHang.setAdapter(adapter_1);

        title_chucnang.setText(s);
        ChiTietHoaDon cthd = data_CTHD.get(index);
        soLuong.setText(cthd.getSoLuong());
        soHD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position_soHD = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        maHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position_MaHang = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(soLuong.getText().length() == 0){
                    AlertDialog.Builder alert = new AlertDialog.Builder(ChiTietHoaDonActivity.this);
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
                    Insert();
                }
                else if(title_chucnang.getText().equals("Sửa")){
                    Update();
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

    private ChiTietHoaDon getChiTietHoaDon() {
        ChiTietHoaDon chitiethoaDon = new ChiTietHoaDon();
        chitiethoaDon.setSoLuong(Integer.parseInt(soLuong.getText().toString()));
        chitiethoaDon.setSoHD(Integer.parseInt(sohoadon.get(position_soHD)));
        chitiethoaDon.setSoHD(Integer.parseInt(mahang.get(position_soHD)));
        return chitiethoaDon;
    }

    public void showData(){
        Cursor data = database.Getdata("SELECT * FROM CHITIETDONHANG WHERE soHD='"+ so_hoa_don +"'");
        while (data.moveToNext()){
            int soHD = data.getInt(0);
            int mahang = data.getInt(1);
            int soluong = data.getInt(2);
            data_CTHD.add(new ChiTietHoaDon(soHD,mahang,soluong));
        }
        chitiethoaDonAdapter.notifyDataSetChanged();
    }
    public void Insert() {
        ChiTietHoaDon chiTietHoaDon = getChiTietHoaDon();
        String query = "INSERT INTO HOADON VALUES(null,'"+ chiTietHoaDon.getSoHD() + "','" + chiTietHoaDon.getMaHang() + "','" + chiTietHoaDon.getSoLuong() +"')";
        database.Querydata(query);
        data_CTHD.clear();
        showData();
        dialog.dismiss();
        fab_hide();
        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
    }

    public void Update() {
        ChiTietHoaDon chiTietHoaDon = getChiTietHoaDon();
        String query = "UPDATE HOADON SET MaHang='"+ chiTietHoaDon.getMaHang() + "',SoLuong='" + chiTietHoaDon.getSoLuong() + "' WHERE SoHD='"+ chiTietHoaDon.getSoHD()+ "'";
        database.Querydata(query);
        data_CTHD.clear();
        showData();
        dialog.dismiss();
        fab_hide();
        Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
    }
    //
    public void Delete() {
        ChiTietHoaDon chitiethoaDon = data_CTHD.get(index);
        if (index >= 0) {
            String query = "DELETE FROM HOADON WHERE SoHD='"+ chitiethoaDon.getSoHD() + "'";
            database.Querydata(query);
            data_CTHD.clear();
            showData();
            dialog.dismiss();
            fab_hide();
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
    }
    public void fab_hide(){
        fab.hide();
        btnDelete.hide();
        btnInsert.hide();
        btnUpdate.hide();
    }
}
