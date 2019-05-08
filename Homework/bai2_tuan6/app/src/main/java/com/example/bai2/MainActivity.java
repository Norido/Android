package com.example.bai2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView1;
    ArrayList<Player> data = new ArrayList<>();
    Adapter adapter = null;
    Button btnEdit, btnRemove;
    TextView txt_medal, txt_home;
    ImageView img_player;
    private static int save = -1;
    private static boolean check = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Adapter listview
        txt_home = (TextView) findViewById(R.id.txt_home);
        txt_medal = (TextView) findViewById(R.id.txt_medal);
        btnEdit = (Button) findViewById(R.id.btn_edit);
        btnRemove = (Button) findViewById(R.id.btn_remove);
        listView1 = (ListView) findViewById(R.id.lv_main);
        init("13","Manchester United","Wayne Rooney", R.drawable.rooney);
        init("13", "Barcelona", "Lionel Messi", R.drawable.messi);
        init("13", "Real Marid", "Gareth Bale", R.drawable.bale);
        init("13", "Bayern Munich", "Thomas Muller", R.drawable.muller);
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
                        parent.getChildAt(position).setBackgroundResource(R.color.text);
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
                        intent.putExtra("icon", data.get(position).getBitmap());
                        intent.putExtra("name", data.get(position).getName());
                        intent.putExtra("medal", data.get(position).getMedal());
                        intent.putExtra("home", data.get(position).getHome());
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
            Bitmap bitmap = (Bitmap) result.getParcelableExtra("icon");
            Player pl = new Player();
            pl.setMedal(result.getStringExtra("medal"));
            pl.setHome(result.getStringExtra("home"));
            pl.setName(result.getStringExtra("name"));
            pl.setIconBitmap(bitmap);
            data.set(save,pl);
            adapter.notifyDataSetChanged();
        }
    }
    private void init(String medal, String home, String name, int image) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),image);
        data.add(new Player(medal, home, name, bmp));
    }



}
