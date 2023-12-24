package com.chiquito.petcareapp.Controller.grooming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.chiquito.petcareapp.R;

public class Grooming3 extends AppCompatActivity {

    ImageButton btn_back_grooming3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grooming3);

        /**
         * Inisialisasi View
         */
        btn_back_grooming3 = findViewById(R.id.btn_back_grooming3);

        /**
         * Penerapan button back
         */
        btn_back_grooming3.setOnClickListener(v -> {
            finish();
        });
    }
}