package com.example.rebo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SignUp extends AppCompatActivity {
    Button btnSignUp;
    private EditText name, email_id, passwordcheck;
    private FirebaseAuth mAuth;
    private static final String TAG = "Error";
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    public String displayname, email, password;
    public SharedPreferences sharedPrefManager;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setControl();
        setEvent();
    }
    public void setControl() {
        mAuth = FirebaseAuth.getInstance();
        btnSignUp = findViewById(R.id.signup);
        email_id = findViewById(R.id.email);
        passwordcheck = findViewById(R.id.password);
        name = findViewById(R.id.displayname);
        sharedPrefManager = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        editor  = sharedPrefManager.edit();
    }

    public void setEvent() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayname = name.getText().toString();
                email = email_id.getText().toString();
                password = passwordcheck.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Eamil Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(displayname)) {
                    Toast.makeText(getApplicationContext(), "Enter Username", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                createUserInFirebase(user.getUid(), displayname, email, "   ", "   ");
                            }
                            editor.putString("uid",user.getUid());
                            editor.putString("username",user.getDisplayName());
                            editor.putString("email",user.getEmail());
                            editor.putString("phone",user.getPhoneNumber());
                            editor.apply();
                            Intent intent = new Intent(SignUp.this,Online.class);
                            startActivity(intent);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void createUserInFirebase(final String uid, final String username, final String email, final String avatar, final String SDT) {
        Log.d(TAG, "createUserWithEmail:success2");
        User user = new User(username, email, avatar, SDT);
        myRef.child("users").child(uid).setValue(user);
    }
}