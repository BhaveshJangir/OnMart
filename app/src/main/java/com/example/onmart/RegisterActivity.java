package com.example.onmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onmart.Database.OrderDatabase;
import com.example.onmart.Database.UserInfo;
import com.example.onmart.ui.home.HomeFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String TAG = "googa";
    EditText mUsername, mEmail, mPassword, mConfirmPassword;
    Button mRegister;
    TextView mHaveAcTv;
    OrderDatabase orderDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        Intent i = new Intent(getApplicationContext(),designActivity.class);
//        startActivity(i);

        mUsername = (EditText) findViewById(R.id.username_et);
        mEmail = (EditText) findViewById(R.id.email_et);
        mPassword = (EditText) findViewById(R.id.password_et);
        mConfirmPassword = (EditText) findViewById(R.id.confim_password_et);
        mRegister = (Button) findViewById(R.id.register_bt);
        mHaveAcTv = (TextView) findViewById(R.id.have_ac_tv);

       // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        orderDatabase = OrderDatabase.getDb(this);
        // database;

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mUsername.getText().toString();
                String email = mEmail.getText().toString();
                String passwrod = mPassword.getText().toString();
                String confirmPassword = mConfirmPassword.getText().toString();

                if (name.equals("") || email.equals("") || passwrod.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Please Enter Details", Toast.LENGTH_SHORT).show();
                } else if (!passwrod.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Password is not same", Toast.LENGTH_SHORT).show();
                    mConfirmPassword.setText("");
                } else {
                    orderDatabase.userDao().addUserData(new UserInfo(name,email,null));
                    RegisterUser(email, passwrod);
                }

            }
        });

        mHaveAcTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }

    private void RegisterUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //  updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //  reload();
        }
    }

}