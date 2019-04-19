package com.gumiho.quanlimonhoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView1;
    ArrayList<MonHoc> data = new ArrayList<>();

    MonHocAdapter adapter = null;

    private Button btnInsert, btnDelete, btnExit, btnUpdate;
    private EditText mamh, tenmh, sotiet;

    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getMonHoc();

        adapter = new MonHocAdapter(MainActivity.this, R.layout.list_item_row, data);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonHoc mh = data.get(position);
                index = position;
                mamh.setText(mh.getMa());
                tenmh.setText(mh.getTen());
                sotiet.setText(mh.getSotiet());
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertMH();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMH();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteMH();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exit();
            }
        });
    }

    public void init() {
        mamh = (EditText) findViewById(R.id.edt_maMH);
        tenmh = (EditText) findViewById(R.id.edt_tenMH);
        sotiet = (EditText) findViewById(R.id.edt_soTiet);

        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnExit = (Button) findViewById(R.id.btn_exit);

        listView1 = (ListView) findViewById(R.id.listview_dsmh);
    }

    private MonHoc getMonHoc() {
        MonHoc monHoc = new MonHoc();
        monHoc.setMa(mamh.getText().toString());
        monHoc.setTen(tenmh.getText().toString());
        monHoc.setSotiet(sotiet.getText().toString());
        return monHoc;
    }

    public void InsertMH() {
        MyDatabase db = new MyDatabase(this);
        MonHoc monHoc = getMonHoc();
        data.add(monHoc);
        adapter.notifyDataSetChanged();
        db.insertMonHocs(monHoc);
        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
    }

    public void DeleteMH() {
        MonHoc monHoc = getMonHoc();
        MyDatabase db = new MyDatabase(this);
        if (index >= 0) {
            data.remove(index);
            db.deleteMonHocs(monHoc);
            adapter.notifyDataSetChanged();

            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
    }

    public void updateMH() {
        MyDatabase db = new MyDatabase(this);
//        MonHoc monHoc = getMonHoc();
//        String ma = mamh.getText().toString();
//        String ten = tenmh.getText().toString();
//        String st = sotiet.getText().toString();
//
//        data.get(index).setMa(ma);
//        data.get(index).setTen(ten);
//        data.get(index).setSotiet(st);
//
//        boolean result = db.updateMonHocs(monHoc);
//        if (result) {
//            Toast.makeText(this, "Update Thành Công!", Toast.LENGTH_SHORT).show();
//
//            adapter.notifyDataSetChanged();
//        } else Toast.makeText(this, "Update Không Thành Công!", Toast.LENGTH_SHORT).show();
        data.clear();
        db.getMonHocs(data);
        adapter.notifyDataSetChanged();
    }

    public void Exit() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startActivity(startMain);
        finish();
    }

}
