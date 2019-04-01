package com.example.custom_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    private ListView lv1;
    ArrayList<fruit> fr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1 = (ListView) findViewById(R.id.lv1);
        setevent();
    }
    public void setevent(){
        init();
        customadapter adapter = new customadapter(this,R.layout.list_row,fr);
        lv1.setAdapter(adapter);
    }
    public void init(){
        fr.add(new fruit(R.string.straw,R.string.des_straw,R.drawable.straw));
        fr.add(new fruit(R.string.banana,R.string.des_banana,R.drawable.banana));

    }

}
