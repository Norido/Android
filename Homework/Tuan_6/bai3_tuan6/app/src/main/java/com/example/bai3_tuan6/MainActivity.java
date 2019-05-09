package com.example.bai3_tuan6;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView1;
    ArrayList<film> data = new ArrayList<>();
    Adapter adapter = null;
    Button btnEdit, btnRemove;
    TextView txt_name, txt_des;
    ImageView img_film;
    RatingBar ratingbar;
    private static int save = -1;
    private static boolean check = true;
    film fl = new film();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Adapter listview
        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_des = (TextView) findViewById(R.id.txt_des);
        ratingbar = (RatingBar) findViewById(R.id.rating);
        img_film = (ImageView) findViewById(R.id.img_show);
        btnEdit = (Button) findViewById(R.id.btn_edit);
        btnRemove = (Button) findViewById(R.id.btn_remove);
        listView1 = (ListView) findViewById(R.id.lv_main);
        init();
        adapter = new Adapter(this, R.layout.list_row, data);
        listView1.setAdapter(adapter);

        // Event listview
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if(check && save == position){
                    parent.getChildAt(position).setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    check = !check;
                }
                else {
                    parent.getChildAt(position).setBackgroundColor(Color.parseColor("#EBA51B"));
                    check = !check;
                }

                if (save != -1 && save != position){
                    parent.getChildAt(save).setBackgroundColor(Color.parseColor("#00FFFFFF"));
                }

                save = position;
                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(MainActivity.this,Edit.class);
                        intent.putExtra("icon", data.get(position).getIcon());
                        intent.putExtra("name", data.get(position).getName());
                        intent.putExtra("des", data.get(position).getDes());
                        intent.putExtra("rating", data.get(position).getRatingbar());
                        fl.setIcon(data.get(position).getIcon());
                        startActivityForResult(intent,200);

                    }
                });
                btnRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        data.remove(position);
                        adapter.notifyDataSetChanged();
                        save = -1;

                    }
                });
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        super.onActivityResult(requestCode,resultCode,result);
        if (resultCode == Activity.RESULT_OK) {
            fl.setDes(result.getStringExtra("des"));
            fl.setRatingbar(result.getFloatExtra("rating", (float) 3.0));
            fl.setName(result.getStringExtra("name"));
            data.set(save,fl);
            adapter.notifyDataSetChanged();
        }
    }
    private void init() {
        data.add(new film("Supergirl", "(Nữ siêu nhân)", 3, R.drawable.supergirl));
        data.add(new film("Supergirl", "(Nữ siêu nhân)", 5, R.drawable.avengers));
        data.add(new film("Supergirl", "(Nữ siêu nhân)", 1, R.drawable.infinity));
    }

}
