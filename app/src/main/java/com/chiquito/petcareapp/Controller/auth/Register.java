package com.chiquito.petcareapp.Controller.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chiquito.petcareapp.Database;
import com.chiquito.petcareapp.R;
import com.chiquito.petcareapp.Model.Customer;
import com.chiquito.petcareapp.Controller.onboarding.OnBoarding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private Button btnDaftar;
    private ImageButton btnBack;
    private TextInputEditText nama, email, password, noWA;
    private Database db;
    private ProgressBar progressBarRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // initialize database
        db = new Database(null);
        db.setfAuth(FirebaseAuth.getInstance());

        btnDaftar = findViewById(R.id.btn_daftar);
        btnBack = findViewById(R.id.btn_back);
        nama = findViewById(R.id.nama_register);
        email = findViewById(R.id.email_register);
        password = findViewById(R.id.password_register);
        progressBarRegister = findViewById(R.id.progress_bar_register);
        noWA = findViewById(R.id.no_wa_register);

        // register button action when clicked
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            //open login activity
            @Override
            public void onClick(View v) {
                btnDaftar.setText("");
                progressBarRegister.setVisibility(View.VISIBLE);
                createUser();

            }
        });

        //back button action when clicked
        btnBack.setOnClickListener(new View.OnClickListener() {
            //open onboarding page 4
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, OnBoarding.class);
                intent.putExtra("viewpager_position", 3);
                startActivity(intent);
            }
        });
    }

    private void createUser() {
        String userNama = nama.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String userWA = noWA.getText().toString();

        if (TextUtils.isEmpty(userNama)) {
            nama.requestFocus();
            nama.setError("Nama tidak boleh kosong");
            return;
        }

        if (TextUtils.isEmpty(userEmail)) {
            email.requestFocus();
            email.setError("Email tidak boleh kosong");
            return;

        }

        if (TextUtils.isEmpty(userPassword)){
            password.requestFocus();
            password.setError("Password tidak boleh kosong");
            return;
        }

        if (userPassword.length() < 6){
            password.requestFocus();
            password.setError("Password harus lebih dari 6 karakter");
            return;
        }

        if (TextUtils.isEmpty(userWA)){
            password.requestFocus();
            password.setError("Nomor WhatsApp tidak boleh kosong");
            return;
        }

        //Create User
        db.getfAuth().createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Check if success
                        if (task.isSuccessful()) {
                            //Save user to database
                            String id = task.getResult().getUser().getUid();
                            Customer customer = new Customer(userNama, userEmail, userPassword, userWA, id);
                            db.getFirebaseDatabase().getReference().child("Customer").child(id).setValue(customer);
                            Toast.makeText(Register.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, Login.class));
                            finish();
                        } else {
                            Toast.makeText(Register.this, "Error: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        progressBarRegister.setVisibility(View.GONE);
                        btnDaftar.setText("Daftar");
                    }
                }).addOnFailureListener(e -> {
                    if (e instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(Register.this, "Email sudah terdaftar", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}