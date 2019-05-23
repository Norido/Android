package com.example.rebo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_info extends AppCompatActivity {
    private ImageView user_img;
    private EditText email, phone, displayname2;
    private TextView displayname1;
    private Button btnUpdate, btnSave;
    public FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference databaseReference = firebaseDatabase.getReference();
    private String TAG = "Error:", updatePhone, updateUsername;
    private String keyuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        getSupportActionBar().hide();
        setControl();
        setEvent();
    }
    public void setControl(){
        user_img = findViewById(R.id.image_user);
        displayname1 = findViewById(R.id.displayname1);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone_number);
        displayname2 = findViewById(R.id.displayname2);
        btnUpdate = findViewById(R.id.update);
        btnSave = findViewById(R.id.save);

    }
    public void setEvent(){
        Intent intent = getIntent();
        keyuser = intent.getStringExtra("key");
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone.setEnabled(true);
                displayname2.setEnabled(true);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePhone = String.valueOf(phone.getText());
                updateUsername = displayname2.getText().toString();
                User userUpdate = new User(updateUsername,email.getText().toString(),updatePhone);
                databaseReference.child("users").child(keyuser).setValue(userUpdate);
                phone.setEnabled(false);
                displayname2.setEnabled(false);
            }
        });
        Log.d(TAG, "Key user: "+ keyuser);
        databaseReference.child("users").child(keyuser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue()!=null)
                {
                    User user = dataSnapshot.getValue(User.class);
                    String username = user.getUsername();
                    displayname1.setText(username);
                    email.setText(user.getEmail());
                    phone.setText(user.getSDT());
                    displayname2.setText(username);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
