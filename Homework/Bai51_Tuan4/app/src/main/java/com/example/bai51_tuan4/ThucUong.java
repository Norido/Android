package com.example.bai51_tuan4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ThucUong extends AppCompatActivity {
    ListView listView;
    ArrayList<list> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_uong);
        setControl();
        setEvent();
    }
    private void setControl(){
        listView = findViewById(R.id.list);
    }
    private void setEvent(){
        KhoiTao();
        FoodAdapter adapter = new FoodAdapter(this, R.layout.list_row, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                list m = data.get(position);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("thucuong",m.title);
                startActivity(intent);
            }
        });
    }
    void KhoiTao(){
        data.add(new list(R.drawable.coca, "CoCaCola"));
        data.add(new list(R.drawable.aqua, "Aquafina"));
        data.add(new list(R.drawable.heineken, "Heineken"));
        data.add(new list(R.drawable.pepsi, "PepSi"));
        data.add(new list(0," "));
    }
}
