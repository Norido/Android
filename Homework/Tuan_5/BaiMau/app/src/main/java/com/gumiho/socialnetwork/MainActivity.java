package com.gumiho.socialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv1;
    ArrayList <SocialNetwork> array = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1 = (ListView)findViewById(R.id.listView1);

        event();

    }
    public void event(){
        KhoiTao();
        SocialNetworkAdapter adapter = new SocialNetworkAdapter(this,R.layout.list_row,array);
        lv1.setAdapter(adapter);

    }
    public void KhoiTao(){
        array.add(new SocialNetwork(R.drawable.fb,R.string.fb));
        array.add(new SocialNetwork(R.drawable.yahoo,R.string.yh));
        array.add(new SocialNetwork(R.drawable.skype,R.string.sk));
        array.add(new SocialNetwork(R.drawable.msn,R.string.msn));
        array.add(new SocialNetwork(R.drawable.in,R.string.in));
    }
}
