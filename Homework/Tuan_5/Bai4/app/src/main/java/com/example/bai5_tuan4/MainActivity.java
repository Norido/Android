package com.example.bai5_tuan4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    ArrayList<listArray> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }
    private void setControl(){
        listView = findViewById(R.id.list);
    }
    private void setEvent(){
        KhoiTao();
        MusicAdapter adapter = new MusicAdapter(this, R.layout.activity_list_row, data);
        listView.setAdapter(adapter);
    }
    void KhoiTao(){
        data.add(new listArray(R.drawable.rihanna, "Love The Way You Lie","Rihanna","4:23"));
        data.add(new listArray(R.drawable.adele, "Someone Like You","Adele","4:47"));
        data.add(new listArray(R.drawable.spacebound, "Space Bound","Emlnem","4:38"));
        data.add(new listArray(R.drawable.lifeforrent, "Life For Rent","Dido","3:41"));
    }

}
