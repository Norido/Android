package com.example.rebo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private User user;
    private String TAG = "Error:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
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
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    public void setEvent(){
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        Log.d(TAG, key);
        databaseReference.child("users").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                displayname1.setText(user.getUsername());
                email.setText(user.getEmail());
                phone.setText(user.getSDT());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
