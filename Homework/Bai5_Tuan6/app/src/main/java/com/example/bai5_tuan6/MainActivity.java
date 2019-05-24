package com.example.bai5_tuan6;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ten, phone;
    Button btnSave;
    ListView contactlistV;
    ArrayList<MyContact>arrContact = new ArrayList<>();
    ArrayAdapter<MyContact>adapterContact = null;
    MyContact selectedContact = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrContact.add(new MyContact("KHOA","0937437982"));
        arrContact.add(new MyContact("GIANG","0123456789"));
        arrContact.add(new MyContact("PHÚ","0123456789"));
        arrContact.add(new MyContact("NHỚ","0123456789"));
        setControl();
        setEvent();
    }
    public void setControl(){
        btnSave = findViewById(R.id.btnsave);
        ten = findViewById(R.id.ten);
        phone = findViewById(R.id.phone);
        contactlistV = findViewById(R.id.list);
        adapterContact = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1
        ,arrContact);
        contactlistV.setAdapter(adapterContact);
        registerForContextMenu(contactlistV);
    }
    public void setEvent(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyContact ct = new MyContact();
                ct.setName(ten.getText().toString());
                ct.setPhone(phone.getText().toString());
                arrContact.add(ct);
                adapterContact.notifyDataSetChanged();
            }
        });
        contactlistV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedContact = arrContact.get(position);
                return false;
            }
        });
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.phonecontextmenu, menu);
        menu.setHeaderTitle("Call-SMS");
        menu.getItem(0).setTitle("Call to "+ selectedContact.getPhone());
        menu.getItem(1).setTitle("Send SMS to "+selectedContact.getPhone());
    }
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId())
        {
            case R.id.mnuCall:
                doMakeCall();
                break;
            case R.id.mnuSms:
                doMakeSms();
                break;
            case R.id.mnuRemove:
                arrContact.remove(selectedContact);
                adapterContact.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
    public void doMakeCall()
    {
        Uri uri=Uri.parse("tel:"+selectedContact.getPhone());
        Intent i=new Intent(Intent.ACTION_CALL, uri);
        startActivity(i);
    }
    public void doMakeSms()
    {
        Intent i=new Intent(this, sms_contact.class);
        Bundle b=new Bundle();
        b.putSerializable("CONTACT", selectedContact);
        i.putExtra("DATA", b);
        startActivity(i);
    }
}
