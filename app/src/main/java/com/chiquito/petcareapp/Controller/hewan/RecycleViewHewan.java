package com.chiquito.petcareapp.Controller.hewan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.chiquito.petcareapp.Model.Hewan;
import com.chiquito.petcareapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewHewan extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Hewan> hewanList;
    CustomAdapterHewan adapterHewan;
    ImageButton btnBack, btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hewan);

        this.recyclerView = findViewById(R.id.recycle_view_hewan);
        this.recyclerView.setHasFixedSize(true);
        this.btnBack = findViewById(R.id.btn_back_hewan);
        this.btnAdd = findViewById(R.id.btn_add_hewan);
        hewanList = new ArrayList<>();
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
        this.hewanList.add(new Hewan("Moi", "Hitam", "Kucing ini sangat lucu", "Anggora", Hewan.JenisKelamin.Betina, Hewan.Spesies.Kucing, null));
        this.hewanList.add(new Hewan("Bolt", "Putih", "Anjing ini sangat lucu", "Pug", Hewan.JenisKelamin.Jantan, Hewan.Spesies.Anjing, null));
        this.adapterHewan = new CustomAdapterHewan(this, hewanList, null);
        this.recyclerView.setAdapter(adapterHewan);
        this.btnBack.setOnClickListener(v -> finish());
        this.btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddHewan.class);
            startActivity(intent);
        });
    }
}