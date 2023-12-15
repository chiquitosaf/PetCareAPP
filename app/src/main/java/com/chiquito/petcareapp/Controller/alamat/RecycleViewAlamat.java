package com.chiquito.petcareapp.Controller.alamat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chiquito.petcareapp.Model.Alamat;
import com.chiquito.petcareapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAlamat extends AppCompatActivity implements SelectListenerAlamat{

    RecyclerView recyclerView;
    List<Alamat> alamatList;
    CustomAdapterAlamat customAdapterAlamat;
    ImageButton btnBack, btnAdd;
    Dialog detailAlamat;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat);

        this.detailAlamat = new Dialog(this);
        this.recyclerView = findViewById(R.id.recycle_view_alamat);
        this.btnAdd = findViewById(R.id.btn_add_alamat);
        this.btnBack = findViewById(R.id.btn_back_alamat);

        alamatList = new ArrayList<>();
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        alamatList = new ArrayList<>();
        this.customAdapterAlamat = new CustomAdapterAlamat(this, alamatList, this);
        this.recyclerView.setAdapter(customAdapterAlamat);

        fAuth = FirebaseAuth.getInstance();
        String uID = fAuth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance(
                "https://petcareapp-85cfe-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Alamat").child(uID);
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                alamatList.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
//                    Alamat alamat = item.getValue(Alamat.class);
                    String tag = item.child("tag").getValue(String.class);
                    String alamatLengkap = item.child("alamatLengkap").getValue(String.class);
                    Alamat alamat = new Alamat(tag, alamatLengkap);
                    alamatList.add(alamat);
                }
                customAdapterAlamat.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

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

        ImageButton buttonDelete = detailAlamat.findViewById(R.id.button_delete_alamat);
        buttonDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setView(R.layout.progress_layout);
            AlertDialog dialog = builder.create();
            dialog.show();

            databaseReference.child(alamat.getTag()).removeValue();
            dialog.dismiss();
            detailAlamat.dismiss();
        });

        ImageButton buttonEdit = detailAlamat.findViewById(R.id.button_edit_alamat);
        buttonEdit.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddAlamat.class);
            intent.putExtra("tag", alamat.getTag());
            intent.putExtra("alamatLengkap", alamat.getAlamatLengkap());
            startActivity(intent);
            detailAlamat.dismiss();
        });

        detailAlamat.show();
    }
}