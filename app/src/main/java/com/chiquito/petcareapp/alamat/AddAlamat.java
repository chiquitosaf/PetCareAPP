package com.chiquito.petcareapp.alamat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.chiquito.petcareapp.R;

public class AddAlamat extends AppCompatActivity {
    ImageButton btnBack;
    Button btnAddAlamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alamat);

        btnAddAlamat = findViewById(R.id.btn_tambah_alamat);
        btnAddAlamat.setOnClickListener(v -> {
            Toast.makeText(this, "Alamat berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnBack = findViewById(R.id.btn_back_add_alamat);
        btnBack.setOnClickListener(v -> finish());
    }
}