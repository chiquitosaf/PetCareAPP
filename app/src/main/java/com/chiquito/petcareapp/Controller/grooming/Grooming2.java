package com.chiquito.petcareapp.Controller.grooming;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import com.chiquito.petcareapp.Controller.hewan.RecycleViewHewan;
import com.chiquito.petcareapp.Controller.paket.PaketActivity;
import com.chiquito.petcareapp.Model.Alamat;
import com.chiquito.petcareapp.Model.Hewan;
import com.chiquito.petcareapp.Model.Paket;
import com.chiquito.petcareapp.Model.Pesanan;
import com.chiquito.petcareapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import org.parceler.Parcels;

public class Grooming2 extends AppCompatActivity {



    Button btnLanjut;
    ImageButton btn_back_grooming2;
    ActivityResultLauncher<Intent> hewanLauncher, paketLauncher;
    Hewan hewan;
    Paket paket;
    AutoCompleteTextView autoCompleteHewanGrooming, autoCompletePaketGrooming;
    TextInputEditText txtKondisiGrooming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grooming2);

        Pesanan pesanan = Parcels.unwrap(getIntent().getParcelableExtra("pesanan"));


        /**
         * Inisialisasi View
         */
        btnLanjut = findViewById(R.id.btn_lanjut);
        btn_back_grooming2 = findViewById(R.id.btn_back_grooming2);
        autoCompleteHewanGrooming = findViewById(R.id.autoComplete_hewan_grooming);
        autoCompletePaketGrooming = findViewById(R.id.autoComplete_paket_grooming);
        txtKondisiGrooming = findViewById(R.id.editText_keluhan_grooming);

        /**
         * Penerapan button lanjut
         */
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Grooming3.class);
                Bundle bundle = new Bundle();
                pesanan.setKondisiHewan(txtKondisiGrooming.getText().toString());

                bundle.putParcelable("pesanan", Parcels.wrap(pesanan));
                intent.putExtras(bundle);
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

        /**
         * Penerapan input hewan
         */
        hewanLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            hewan = Parcels.unwrap(data.getParcelableExtra("hewan"));
                            pesanan.setHewan(hewan);
                            autoCompleteHewanGrooming.setText(hewan.getNamaHewan());
                        }
                    }
                });
        autoCompleteHewanGrooming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecycleViewHewan.class);
                intent.putExtra("isFromGrooming", true);
                hewanLauncher.launch(intent);
            }
        });

        /**
         * Penerapan input paket
         */
        paketLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result){
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            paket = Parcels.unwrap(data.getParcelableExtra("paket"));
                            pesanan.setPaket(paket);
                            autoCompletePaketGrooming.setText(paket.getNamaPaket());
                        }
                    }
                });
        autoCompletePaketGrooming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PaketActivity.class);
                intent.putExtra("isFromGrooming", true);
                paketLauncher.launch(intent);
            }
        });

    }
}