package com.example.navigator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    Button log, reg;
    EditText eMail, Pass;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eMail = findViewById(R.id.editTextTextPersonName);
        Pass = findViewById(R.id.editTextTextPersonName2);
        log = findViewById(R.id.login);
        reg = findViewById(R.id.register);


        fAuth = FirebaseAuth.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), register.class));
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = eMail.getText().toString().trim();
                String password = Pass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    eMail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Pass.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    Pass.setError("Password must be >= 6");
                    return;
                }

                //вход пользователя

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login.this, "Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        }else{
                            Toast.makeText(login.this, "Error ! " +  task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}