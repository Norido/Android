package com.example.bai1_listviewcountry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    ArrayList<ClassCountry> mangCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.lv);
        mangCountry = new ArrayList<>();

        mangCountry.add(new ClassCountry (R.drawable.vn,"Viet Nam","Population:98000000"));
        mangCountry.add(new ClassCountry (R.drawable.us,"United States","Population:320000000"));
        mangCountry.add(new ClassCountry (R.drawable.jp,"Russia","Population:142000000"));

        CountryAdapter adapter = new CountryAdapter(MainActivity.this,R.layout.list_country,mangCountry);
        listview.setAdapter(adapter);
    }
}
