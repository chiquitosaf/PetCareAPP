package com.chiquito.petcareapp.Controller.hewan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.chiquito.petcareapp.Database;
import com.chiquito.petcareapp.Model.Alamat;
import com.chiquito.petcareapp.Model.Hewan;
import com.chiquito.petcareapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;


public class RecycleViewHewan extends AppCompatActivity implements SelectListenerHewan {

    RecyclerView recyclerView;
    List<Hewan> hewanList;
    CustomAdapterHewan adapterHewan;
    ImageButton btnBack, btnAdd;
    ValueEventListener eventListener;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hewan);

        this.recyclerView = findViewById(R.id.recycle_view_hewan);
        this.recyclerView.setHasFixedSize(true);
        this.btnBack = findViewById(R.id.btn_back_hewan);
        this.btnAdd = findViewById(R.id.btn_add_hewan);

        hewanList = new ArrayList<>();
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView
                .VERTICAL, false));
        this.adapterHewan = new CustomAdapterHewan(this, hewanList, this);
        this.recyclerView.setAdapter(adapterHewan);

        this.btnBack.setOnClickListener(v -> finish());

        this.btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddHewan.class);
            startActivity(intent);
        });

        db = new Database();
        db.setRef(db.getFirebaseDatabase().getReference("Hewan").child(db.getUserID()));


        db.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hewanList.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
//                    Hewan hewan = item.getValue(Hewan.class);
                    String namaHewan = item.child("namaHewan").getValue(String.class);
                    String jenisKelamin = item.child("jenisKelamin").getValue(String.class);
                    String rasHewan = item.child("ras").getValue(String.class);
                    String warnaHewan = item.child("warnaHewan").getValue(String.class);
                    String keteranganHewan = item.child("keterangan").getValue(String.class);
                    String spesies = item.child("spesies").getValue(String.class);
                    String tanggalLahir = item.child("tanggalLahir").getValue(String.class);
                    String urlImage = item.child("urlImage").getValue(String.class);
                    Hewan hewan = new Hewan(namaHewan, warnaHewan, keteranganHewan, rasHewan,
                            Hewan.JenisKelamin.valueOf(jenisKelamin), Hewan.Spesies.valueOf(spesies)
                            , tanggalLahir, urlImage);
                    hewanList.add(hewan);
                }
                adapterHewan.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RecycleViewHewan.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClicked(Hewan hewan) {
        Intent intent = new Intent(this, DetailHewan.class);
        Bundle bundle = new Bundle();

        bundle.putParcelable("hewan", Parcels.wrap(hewan));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}