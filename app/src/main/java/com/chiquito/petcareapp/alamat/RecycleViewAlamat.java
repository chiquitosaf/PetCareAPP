package com.chiquito.petcareapp.alamat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chiquito.petcareapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAlamat extends AppCompatActivity implements SelectListenerAlamat{

    RecyclerView recyclerView;
    List<Alamat> alamatList;
    CustomAdapterAlamat customAdapterAlamat;
    ImageButton btnBack, btnAdd;
    Dialog detailAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat);

        this.detailAlamat = new Dialog(this);
        this.recyclerView = findViewById(R.id.recycle_view_alamat);
        this.btnAdd = findViewById(R.id.btn_add_alamat);
        this.btnBack = findViewById(R.id.btn_back_alamat);
        this.recyclerView.setHasFixedSize(true);
        alamatList = new ArrayList<>();
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
        this.alamatList.add(new Alamat("Rumah","Jl. H. Umayah II No.33, Citeureup, Kec. Dayeuhkolot, Kabupaten Bandung, Jawa Barat 40257"));
        this.alamatList.add(new Alamat("Kos","contoh"));
        this.customAdapterAlamat = new CustomAdapterAlamat(this, alamatList, this);
        this.recyclerView.setAdapter(customAdapterAlamat);

        this.btnBack.setOnClickListener(v -> finish());
        this.btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddAlamat.class);
            startActivity(intent);
        });

    }

    //open pop up of detail alamat for each item
    @Override
    public void onItemClicked(Alamat alamat) {
        CreatePopUpWindow(alamat);
    }

    private void CreatePopUpWindow(Alamat alamat) {
        TextView tagAlamat, descriptionAlamat;
        ImageButton closeButton;

        detailAlamat.setContentView(R.layout.popup_alamat);
        tagAlamat = detailAlamat.findViewById(R.id.tag_alamat_popup);
        descriptionAlamat = detailAlamat.findViewById(R.id.description_alamat_popup);
        closeButton = detailAlamat.findViewById(R.id.btn_close_alamat_popup);
        tagAlamat.setText(alamat.getTag());
        descriptionAlamat.setText(alamat.getAlamatLengkap());
        closeButton.setOnClickListener(v -> detailAlamat.dismiss());
        detailAlamat.show();
    }
}