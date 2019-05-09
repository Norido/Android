package com.example.bai5_tuan6;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sms_contact extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_contact);
        setControl();
        setEvent();
    }
    public void setControl(){
        editText = findViewById(R.id.editcontent);
        textView = findViewById(R.id.contact);
        btnSend = findViewById(R.id.btnSend);
    }
    public void setEvent(){
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("DATA");
        final MyContact c = (MyContact) b.getSerializable("CONTACT");
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSms(c);
            }
        });
        textView.setText(c.getPhone());

    }
    public void sendSms(MyContact c)
    {
        //lấy mặc định SmsManager
        final SmsManager sms = SmsManager.getDefault();
        Intent msgSent = new Intent("ACTION_MSG_SENT");
        //Khai báo pendingintent để kiểm tra kết quả
        final PendingIntent pendingMsgSent =
                PendingIntent.getBroadcast(this, 0, msgSent, 0);
        registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                String msg="Send OK";
                if (result != Activity.RESULT_OK) {
                    msg="Send failed";
                }
                Toast.makeText(sms_contact.this, msg,
                        Toast.LENGTH_LONG).show();
            }
        }, new IntentFilter("ACTION_MSG_SENT"));
        //Gọi hàm gửi tin nhắn đi
        sms.sendTextMessage(c.getPhone(), null, editText.getText()+"",
                pendingMsgSent, null);
        finish();
    }
}
