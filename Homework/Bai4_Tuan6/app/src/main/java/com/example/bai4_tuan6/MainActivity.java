package com.example.bai4_tuan6;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spin;
    EditText edtma,editten;
    Button btnNhap;
    ListView listView;
    ArrayList<Catalog_spin> arraySpin = new ArrayList<>();
    ArrayAdapter<Catalog_spin> adapterSpin = null;
    ArrayList<Product_listView>  arrlistV = new ArrayList<>();
    ArrayAdapter<Product_listView> adapterlistV = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }
    public void setControl(){
        spin = findViewById(R.id.spinner);
        edtma = findViewById(R.id.ma);
        editten = findViewById(R.id.ten);
        btnNhap = findViewById(R.id.btn);
        listView = findViewById(R.id.list);
        adapterSpin = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, arraySpin);
        adapterSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapterSpin);
        adapterlistV = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrlistV);
        listView.setAdapter(adapterlistV);
    }
    public void setEvent(){
        arraySpin.add(new Catalog_spin("1","SamSum"));
        arraySpin.add(new Catalog_spin("2","Iphone"));
        arraySpin.add(new Catalog_spin("3","Ipad"));
        adapterSpin.notifyDataSetChanged();
        arraySpin.get(0).addProduct(new Product_listView("S1","SAMSUM1"));
        arraySpin.get(0).addProduct(new Product_listView("S2","SAMSUM2"));
        arraySpin.get(0).addProduct(new Product_listView("S3","SAMSUM3"));
        arraySpin.get(1).addProduct(new Product_listView("A1","IPHONE1"));
        arraySpin.get(1).addProduct(new Product_listView("A2","IPHONE2"));
        arraySpin.get(1).addProduct(new Product_listView("A3","IPHONE3"));
        arraySpin.get(2).addProduct(new Product_listView("I1","IPAD1"));
        arraySpin.get(2).addProduct(new Product_listView("I2","IPAD2"));
        arraySpin.get(2).addProduct(new Product_listView("I3","IPAD3"));
        loadListSP(arraySpin.get(0));
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadListSP(arraySpin.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Product_listView p = new Product_listView();
                p.setId(edtma.getText().toString());
                p.setName(editten.getText().toString());
                Catalog_spin c = (Catalog_spin) spin.getSelectedItem();
                if (!c.isDuplicate(p)){
                    c.addProduct(p);
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(R.string.app_name);
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setMessage("SẢN PHẨM ĐÃ CÓ. MỜI BẠN NHẬP LẠI!!!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
                loadListSP(c);

            }
        });
    }
    private void loadListSP(Catalog_spin c){
        arrlistV.clear();
        arrlistV.addAll(c.getListSP());
        adapterlistV.notifyDataSetChanged();
    }
}
