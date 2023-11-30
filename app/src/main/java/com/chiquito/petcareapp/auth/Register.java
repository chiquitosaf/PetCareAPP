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

public class Register extends AppCompatActivity {

    Button btnDaftar;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnDaftar = findViewById(R.id.btn_daftar);
        btnBack = findViewById(R.id.btn_back);

        // register button action when clicked
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            //open login activity
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        //back button action when clicked
        btnBack.setOnClickListener(new View.OnClickListener() {
            //open onboarding page 4
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, OnBoarding.class);
                intent.putExtra("viewpager_position", 3);
                startActivity(intent);
            }
        });
    }
}