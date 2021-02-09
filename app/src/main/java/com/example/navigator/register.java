package com.example.navigator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    EditText eMail, Pass;
    Button reg;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eMail = findViewById(R.id.editTextTextPersonName3);
        Pass = findViewById(R.id.editTextTextPersonName4);
        reg = findViewById(R.id.register);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        reg.setOnClickListener(new View.OnClickListener() {
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
                //рагистрация пользователя в firebase

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(register.this, "Error ! " +  task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });
    }
}