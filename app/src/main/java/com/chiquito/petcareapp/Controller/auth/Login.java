package com.chiquito.petcareapp.Controller.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.chiquito.petcareapp.Controller.Home;
import com.chiquito.petcareapp.Controller.MainActivity;
import com.chiquito.petcareapp.R;
import com.chiquito.petcareapp.Controller.onboarding.OnBoarding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextInputEditText email, password;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);

        Button btnLogin =  findViewById(R.id.btn_login);
        ImageButton btnBack = findViewById(R.id.btn_back);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, OnBoarding.class);
                intent.putExtra("viewpager_position", 3);

                startActivity(intent);
            }
        });
    }

    private void loginUser() {
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();

        if (!emailText.isEmpty() && !passwordText.isEmpty()) {
            auth.signInWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                            finish();
                        }else{
                            Toast.makeText(Login.this, "Email atau Password yang dimasukkan salah!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{
            email.setError("Email tidak boleh kosong");
            password.setError("Password tidak boleh kosong");
        }
    }
}