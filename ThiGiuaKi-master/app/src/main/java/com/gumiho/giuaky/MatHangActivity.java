package com.gumiho.giuaky;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
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

import java.util.ArrayList;

public class MatHangActivity extends AppCompatActivity {
    private ListView listviewDSMatHang;
    private ArrayList<MatHang> data_MH = new ArrayList<>();

    private MatHangAdapter matHangAdapter = null;
    private Database database;
    private EditText  edtTenMH, edtDVT, edtDonGia;
    private TextView edtMaMH;

    int index = -1;
    private FloatingActionButton btnInsertMH, btnDeleteMH, btnUpdateMH;
    private boolean check = false, check_position = true;
    private int old_position = -1;
    private Dialog dialog;
    private TextView title_chucnang;
    private Button ok,huy;
    public DatabaseAccess databaseAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mathang);

        init();
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        matHangAdapter = new MatHangAdapter(MatHangActivity.this, R.layout.item_mathang, data_MH);
        listviewDSMatHang.setAdapter(matHangAdapter);
        database = new Database(this,"quotes.db",null,1);
        showData();
        listviewDSMatHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(old_position != position){
                    fab_show();
                    check_position = true;
                }else
                if(check_position){
                    fab_hide();
                    check_position = !check_position;
                }
                else {
                    fab_show();
                    check_position = !check_position;
                }

                old_position = position;
                index = position;
            }

        });


        btnInsertMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog("Thêm");

            }
        });

        btnUpdateMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog("Sửa");
            }
        });
        btnDeleteMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder delete = new AlertDialog.Builder(MatHangActivity.this);
                delete.setTitle("Cảnh báo!");
                delete.setMessage("Bạn chắc chắn muốn xóa?");
                delete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteMH();
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
        listviewDSMatHang = (ListView) findViewById(R.id.listview_dsmh);
        btnInsertMH = (FloatingActionButton) findViewById(R.id.btn_insert);
        btnUpdateMH = (FloatingActionButton) findViewById(R.id.btn_update);
        btnDeleteMH = (FloatingActionButton) findViewById(R.id.btn_delete);


    }
    private void Dialog(String s){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.chucnang_mathang);
        title_chucnang = (TextView) dialog.findViewById(R.id.title_chucnang);
        edtMaMH = (TextView) dialog.findViewById(R.id.edt_maMH);
        edtTenMH = (EditText) dialog.findViewById(R.id.edt_tenMH);
        edtDVT = (EditText) dialog.findViewById(R.id.edt_dvt);
        edtDonGia = (EditText) dialog.findViewById(R.id.edt_dongia);
        ok = (Button) dialog.findViewById(R.id.OK);
        huy = (Button) dialog.findViewById(R.id.huy);
        title_chucnang.setText(s);
        if(title_chucnang.getText().equals("Sửa")) {
            MatHang mh = data_MH.get(index);
            edtMaMH.setText(String.valueOf(mh.getMaHang()));
            edtTenMH.setText(mh.getTenHang());
            edtDVT.setText(mh.getDVT());
            edtDonGia.setText(mh.getDonGia());
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtTenMH.getText().length() == 0 || edtDVT.getText().length() == 0 || edtDonGia.getText().length() == 0){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MatHangActivity.this);
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
                    InsertMH();
                }
                else if(title_chucnang.getText().equals("Sửa")){
                    UpdateMH();
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

    private MatHang getMatHang() {
        MatHang matHang = new MatHang();
        matHang.setMaHang(Integer.parseInt(edtMaMH.getText().toString()));
        matHang.setTenHang(edtTenMH.getText().toString());
        matHang.setDVT(edtDVT.getText().toString());
        matHang.setDonGia(edtDonGia.getText().toString());
        return matHang;
    }

    public void showData(){
        Cursor data = database.Getdata("SELECT * FROM MATHANG");
        while (data.moveToNext()){
            int maHang = data.getInt(0);
            String tenHang = data.getString(1);
            String dvt = data.getString(2);
            String donGia = data.getString(3);
            data_MH.add(new MatHang(maHang,tenHang,dvt,donGia));
        }
        matHangAdapter.notifyDataSetChanged();
    }
    public void InsertMH() {
        String query = "INSERT INTO MATHANG VALUES(null,'"+ edtTenMH.getText().toString() + "','" + edtDVT.getText().toString() + "','" + edtDonGia.getText().toString() +"')";
        database.Querydata(query);
        data_MH.clear();
        showData();
        dialog.dismiss();
        fab_hide();
        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
    }

    public void UpdateMH() {
        MatHang matHang = getMatHang();

        String query = "UPDATE MATHANG SET TenHang='"+ matHang.getTenHang() + "',DVT='" + matHang.getDVT() + "',DonGia='" + matHang.getDonGia() +"' WHERE MaHang='"+ matHang.getMaHang()+ "'";
        database.Querydata(query);
        data_MH.clear();
        showData();
        dialog.dismiss();
        fab_hide();
        Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
    }
//
    public void DeleteMH() {
        MatHang matHang = data_MH.get(index);
        if (index >= 0) {
            String query = "DELETE FROM MATHANG WHERE MaHang='"+ matHang.getMaHang()+ "'";
            database.Querydata(query);
            data_MH.clear();
            showData();
            dialog.dismiss();
            fab_hide();
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
    }
    public void fab_hide(){
        btnDeleteMH.hide();
        btnUpdateMH.hide();
    }
    public void fab_show(){
        btnDeleteMH.show();
        btnUpdateMH.show();
    }
}
