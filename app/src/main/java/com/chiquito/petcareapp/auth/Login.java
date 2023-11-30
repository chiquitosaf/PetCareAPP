package com.chiquito.petcareapp.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.chiquito.petcareapp.Home;
import com.chiquito.petcareapp.R;
import com.chiquito.petcareapp.onboarding.OnBoarding;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin =  findViewById(R.id.btn_login);
        ImageButton btnBack = findViewById(R.id.btn_back);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(Login.this, Home.class);
                startActivity(go);
                finish();
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
}