package com.chiquito.petcareapp.Controller;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chiquito.petcareapp.Controller.hewan.AddHewan;
import com.chiquito.petcareapp.Database;
import com.chiquito.petcareapp.Model.Customer;
import com.chiquito.petcareapp.Model.Hewan;
import com.chiquito.petcareapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditAkun extends AppCompatActivity {

    CircleImageView fotoProfil;
    Button btnPilihFoto, btnSimpan ;
    ImageButton btnBack;
    TextInputEditText etNama, etNoWa;
    Database db;
    Uri imageUri;
    StorageReference storageReference;

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == Activity.RESULT_OK){
                        if (o.getData() != null) imageUri = o.getData().getData();
                        Glide.with(getApplicationContext()).load(imageUri).into(fotoProfil);
                    }else{
                        Toast.makeText(EditAkun.this, "Foto tidak terpilih", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);

        fotoProfil = findViewById(R.id.profile_photo_akun);
        btnPilihFoto = findViewById(R.id.btn_tambah_profile_akun);
        etNama = findViewById(R.id.nama_edit);
        etNoWa = findViewById(R.id.no_wa_edit);
        btnSimpan = findViewById(R.id.btn_simpan);
        btnBack = findViewById(R.id.btn_back);

        db = new Database();
        db.setRef(db.getFirebaseDatabase().getReference().child("Customer").child(db.getUserID()));
        storageReference = FirebaseStorage.getInstance().getReference("Customer").child(db.getUserID());
        db.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                etNama.setText(snapshot.child("name").getValue().toString());

                etNoWa.setText(snapshot.child("noWA").getValue().toString());

                if(snapshot.child("imageUrl").exists()){
                    Glide.with(getApplicationContext()).load(snapshot.child("imageUrl").getValue().toString()).into(fotoProfil);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.println(Log.ERROR, "Error", error.getMessage());
            }
        });

        btnPilihFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(("image/*"));
                pickImage.launch(intent);
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString();

                String noWa = etNoWa.getText().toString();

                db.getRef().child("name").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        if(!nama.equals(dataSnapshot.getValue().toString())){
                            db.getRef().child("name").setValue(nama);
                        }
                    }
                });
                db.getRef().child("noWA").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        if(!noWa.equals(dataSnapshot.getValue().toString())){
                            db.getRef().child("noWA").setValue(noWa);
                        }
                    }
                });

                if(imageUri != null){
                    uploadImageToStorage(imageUri);
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            //open onboarding page 4
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void uploadImageToStorage(Uri uri){
        final StorageReference imageRef = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(uri));
        imageRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        db.getRef().child("imageUrl").setValue(uri.toString());
                    }
                });
            }
        });

    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap =  MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}