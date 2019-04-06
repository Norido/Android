package com.example.custom_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    private ListView lv1;
    ArrayList<football> fr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1 = (ListView) findViewById(R.id.lv1);
        setevent();
    }
    public void setevent(){
        init();
        customadapter adapter = new customadapter(this,R.layout.list2_row,fr);
        lv1.setAdapter(adapter);
    }
    public void init(){
        fr.add(new football(R.string.pele,R.string.des_pele,R.drawable.pele,R.drawable.brazil));
        fr.add(new football(R.string.diego,R.string.des_diego,R.drawable.diego,R.drawable.argentina));

    }

}
