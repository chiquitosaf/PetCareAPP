package com.chiquito.petcareapp.Controller.grooming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.chiquito.petcareapp.Model.Pesanan;
import com.chiquito.petcareapp.R;
import com.google.android.material.tabs.TabLayout;

import org.parceler.Parcels;

public class Grooming2 extends AppCompatActivity {



    Button btnLanjut;
    ImageButton btn_back_grooming2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grooming2);

        Pesanan pesanan = Parcels.unwrap(getIntent().getParcelableExtra("pesanan"));
        System.out.println("this is from grooming activity"+pesanan.getTanggalBooking());
        System.out.println("this is from grooming activity"+pesanan.getWaktuBooking());
        System.out.println("this is from grooming activity"+pesanan.getAlamat());

        /**
         * Inisialisasi View
         */
        btnLanjut = findViewById(R.id.btn_lanjut);
        btn_back_grooming2 = findViewById(R.id.btn_back_grooming2);

        /**
         * Penerapan button lanjut
         */
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Grooming3.class);
                startActivity(intent);
            }
        });

        /**
         * Penerapan button back
         */
        btn_back_grooming2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}