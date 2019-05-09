package com.example.rebo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

public class Login extends AppCompatActivity {

    Button btnSignUp;
    LoginButton btnfb;
    ImageView btnImgfb,btnImgGg;
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    SignInButton googleSignInButton;
    GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setControl();
        setEvent();
        loginFaceBook();

    }
    /*public void onStart() {
        super.onStart();
        //This starts the access token tracking
        accessTokenTracker.startTracking();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        useLoginInformation(accessToken);

    }
    public void onDestroy() {
        super.onDestroy();
        // We stop the tracking before destroying the activity
        accessTokenTracker.stopTracking();
    }*/
    public void setControl(){
        btnSignUp = findViewById(R.id.signup);
        btnfb = findViewById(R.id.btnfb);
        btnImgfb = findViewById(R.id.btnImgFb);
        googleSignInButton = findViewById(R.id.btngg);
        btnImgGg = findViewById(R.id.btnImgGg);
       /* btnfb.setReadPermissions(Arrays.asList("email","public_profile"));
        accessTokenTracker = new AccessTokenTracker() {
            // This method is invoked everytime access token changes
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                // currentAccessToken is null if the user is logged out
                if (currentAccessToken != null) {
                    // AccessToken is not null implies user is logged in and hence we sen the GraphRequest
                    useLoginInformation(currentAccessToken);
                }else{

                }

            }
        };*/
    }
    public void setEvent(){
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });
        btnImgfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnfb.performClick();
            }
        });
        btnImgGg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginGoogle();
            }
        });
    }

    public void loginGoogle(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this,gso);
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 101);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            switch (requestCode) {
                case 101:
                    Intent intent = new Intent(Login.this,SignUp.class);
                    startActivity(intent);
                    break;
            }
        }

    }

    public void loginFaceBook(){
        callbackManager = CallbackManager.Factory.create();
        btnfb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Retrieving access token using the LoginResult
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });
    }
    /*private void useLoginInformation(AccessToken accessToken) {
        /*
         Creating the GraphRequest to fetch user details
         1st Param - AccessToken
         2nd Param - Callback (which will be invoked once the request is successful)

        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            //OnCompleted is invoked once the GraphRequest is successful
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String name = object.getString("name");
                    String email = object.getString("email");
                    String image = object.getJSONObject("picture").getJSONObject("data").getString("url");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        // We set parameters to the GraphRequest using a Bundle.
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture.width(200)");
        request.setParameters(parameters);
        // Initiate the GraphRequest
        request.executeAsync();
    }*/

}
