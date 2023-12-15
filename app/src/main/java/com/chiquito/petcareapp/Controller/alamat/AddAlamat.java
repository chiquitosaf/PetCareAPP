package com.chiquito.petcareapp.Controller.alamat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chiquito.petcareapp.Database;
import com.chiquito.petcareapp.Model.Alamat;
import com.chiquito.petcareapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddAlamat extends AppCompatActivity {
    ImageButton btnBack;
    Button btnAddAlamat;
    Database db;
    DatabaseReference databaseReference;
    TextInputEditText inputTag, inputAlamat;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alamat);

        Bundle bundle = getIntent().getExtras();

        inputTag = findViewById(R.id.input_tag_alamat);
        inputAlamat = findViewById(R.id.input_alamat);
        btnAddAlamat = findViewById(R.id.btn_tambah_alamat);

        title = findViewById(R.id.title_tambahAlamat);


        db = new Database();
        databaseReference = db.getFirebaseDatabase().getReference("Alamat");

        if (bundle != null){
            title.setText("Edit Alamat");
            btnAddAlamat.setText("Simpan Perubahan");
            inputTag.setText(bundle.getString("tag"));
            inputAlamat.setText(bundle.getString("alamatLengkap"));
        }

        btnAddAlamat.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setView(R.layout.progress_layout);
            AlertDialog dialog = builder.create();
            dialog.show();

            String tag = inputTag.getText().toString();
            String alamat = inputAlamat.getText().toString();
            Alamat alamatClass = new Alamat(tag, alamat);
            if (bundle != null){
                databaseReference.child(db.getUserID()).child(bundle.getString("tag")).removeValue();
            }

            databaseReference.child(db.getUserID()).child(tag).setValue(alamatClass);

            dialog.dismiss();
            finish();
        });

        btnBack = findViewById(R.id.btn_back_add_alamat);
        btnBack.setOnClickListener(v -> finish());
    }
}