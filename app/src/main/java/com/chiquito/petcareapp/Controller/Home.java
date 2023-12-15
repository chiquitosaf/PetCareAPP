package com.chiquito.petcareapp.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chiquito.petcareapp.Controller.alamat.RecycleViewAlamat;
import com.chiquito.petcareapp.Controller.grooming.Grooming;
import com.chiquito.petcareapp.Controller.hewan.RecycleViewHewan;
import com.chiquito.petcareapp.R;

public class Home extends AppCompatActivity {

    CardView alamat,hewan,grooming,penitipan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        alamat = findViewById(R.id.alamat);
        alamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, RecycleViewAlamat.class);
                startActivity(intent);
            }
        });

        hewan = findViewById(R.id.hewan_kamu);
        hewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, RecycleViewHewan.class);
                startActivity(intent);
            }
        });

        grooming = findViewById(R.id.grooming);
        grooming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Grooming.class);
                startActivity(intent);
            }
        });
    }
}