package com.example.formatchange;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBG, checktxtColor, checkCenter;
    RadioButton txtLe, txtChan, txtBoth;
    Button btnresult;
    TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }
    private void setControl(){
        checkBG = findViewById(R.id.background);
        checktxtColor = findViewById(R.id.textcolor);
        checkCenter = findViewById(R.id.center);

        txtChan = findViewById(R.id.chan);
        txtLe = findViewById(R.id.le);
        txtBoth = findViewById(R.id.both);

        txtView= findViewById(R.id.textshow);
        btnresult = findViewById(R.id.show);
    }
    public void setEvent(){
        btnresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBG.isChecked()){
                    txtView.setBackgroundColor(Color.parseColor("#ff00ff"));
                } else {
                    txtView.setBackgroundColor(Color.parseColor("#f498ad"));
                }
                if (checktxtColor.isChecked()){
                    txtView.setTextColor(Color.parseColor("#ff0006"));
                }
                else txtView.setTextColor(Color.parseColor("#010203"));
                if (checkCenter.isChecked()){
                    txtView.setGravity(Gravity.CENTER);
                }
                else txtView.setGravity(Gravity.NO_GRAVITY);
                if (txtChan.isChecked()){
                    txtView.setText("48");
                }
                if (txtLe.isChecked()){
                    txtView.setText("49");

                }
                if (txtBoth.isChecked()){
                    txtView.setText("48, 49");
                }
            }
        });
    }
}
