package com.example.rebo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import me.anwarshahriar.calligrapher.Calligrapher;

public class User_info extends AppCompatActivity {
    private de.hdodenhof.circleimageview.CircleImageView user_img;
    private EditText email, phone, displayname2;
    private TextView displayname1;
    private Button btnUpdate, btnSave;
    public FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference databaseReference = firebaseDatabase.getReference();
    private String TAG = "Error:", updatePhone, updateUsername;
    public SharedPreferences sharedPrefManager;
    public String uid, emailSave, displayname, avatar, phoneSave;
    public SharedPreferences.Editor editor;
    private Calligrapher calligrapher;
    private int count;
    private TextView love_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darkTheme);
        }
        else setTheme(R.style.AppTheme);
        calligrapher = new Calligrapher(User_info.this);
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
        sharedPrefManager = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        editor  = sharedPrefManager.edit();
        love_book = findViewById(R.id.number_love_book);

    }
    public void setEvent(){
        uid = sharedPrefManager.getString("uid","");
        emailSave = sharedPrefManager.getString("email","Rebo@gmail.com");
        displayname = sharedPrefManager.getString("username","Anonymous");
        avatar = sharedPrefManager.getString("photo","");
        phoneSave = sharedPrefManager.getString("phone","0123456789");
        displayname1.setText(displayname);
        displayname2.setText(displayname);
        email.setText(emailSave);
        phone.setText(phoneSave);
        calligrapher.setFont(User_info.this,Online.stringfont, true);
        if (!avatar.equals("")){
            Picasso.get().load(avatar).into(user_img);
        }
        love_book.setText("0");
        databaseReference.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("mylovebook")){
                    databaseReference.child("users").child(uid).child("mylovebook").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists())
                            {
                                count = (int) dataSnapshot.getChildrenCount();
                                love_book.setText(String.valueOf(count));
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
                editor.putString("phone",updatePhone);
                editor.putString("username",updateUsername);
                editor.apply();
                User userUpdate = new User(updateUsername,email.getText().toString(),updatePhone);
                databaseReference.child("users").child(uid).setValue(userUpdate);
                phone.setEnabled(false);
                displayname2.setEnabled(false);
                Intent intent = new Intent(User_info.this,Online.class);
                startActivity(intent);
            }
        });


    }
}
