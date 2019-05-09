package com.example.qlnv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<NhanVien> arrEmployee = new ArrayList<NhanVien>();

    NhanVienAdapter adapter = null;
    ListView lvNhanvien = null;

    Button btnNhap;
    ImageButton btnRemoveAll;
    EditText editMa, editTen;
    RadioGroup genderGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNhap = (Button) findViewById(R.id.btnNhap);
        btnRemoveAll = (ImageButton) findViewById(R.id.btndelete);
        editMa = (EditText) findViewById(R.id.editMa);
        editTen = (EditText) findViewById(R.id.editTen);
        genderGroup = (RadioGroup) findViewById(R.id.radioGroup1);

        lvNhanvien = (ListView) findViewById(R.id.lvnhanvien);
        arrEmployee = new ArrayList<NhanVien>();

        adapter = new NhanVienAdapter(this,R.layout.list_item,arrEmployee);
        lvNhanvien.setAdapter(adapter);
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma = editMa.getText() + "";
                String ten = editTen.getText() + "";
                boolean gioitinh = false;//Nam =false
                if (genderGroup.getCheckedRadioButtonId() == R.id.radNu)
                    gioitinh = true;
                //Tạo một employee
                NhanVien emp = new NhanVien();
                emp.setId(ma);
                emp.setName(ten);
                emp.setGender(gioitinh);

                arrEmployee.add(emp);

                adapter.notifyDataSetChanged();

                editMa.setText("");
                editTen.setText("");
                editMa.requestFocus();
            }
        });
        btnRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = lvNhanvien.getChildCount() - 1; i >= 0; i--) {

                    View v = lvNhanvien.getChildAt(i);

                    CheckBox chk = (CheckBox) v.findViewById(R.id.chkitem);

                    if (chk.isChecked()) {

                        arrEmployee.remove(i);
                    }
                }

                adapter.notifyDataSetChanged();
            }
        });
    }
}
