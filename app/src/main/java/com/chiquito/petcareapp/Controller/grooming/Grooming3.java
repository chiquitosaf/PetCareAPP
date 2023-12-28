package com.chiquito.petcareapp.Controller.grooming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chiquito.petcareapp.Controller.MainActivity;
import com.chiquito.petcareapp.Database;
import com.chiquito.petcareapp.Model.Customer;
import com.chiquito.petcareapp.Model.Pesanan;
import com.chiquito.petcareapp.R;
import com.chiquito.petcareapp.StatusPesanan;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Grooming3 extends AppCompatActivity {

    ImageButton btn_back_grooming3;
    TextView textViewTanggalGrooming, textViewDetailHewan, textViewNamaPaketGrooming,
            textViewHargaPaketGrooming, textViewHargaAntarJemputGrooming, textViewTotalHargaGrooming;
    EditText editTextAlamatGrooming;
    Button btnPesan;
    Database db;
    int hargaAntarJemput = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grooming3);

        Pesanan pesanan = Parcels.unwrap(getIntent().getParcelableExtra("pesanan"));

        /**
         * Inisialisasi View
         */
        btn_back_grooming3 = findViewById(R.id.btn_back_grooming3);
        textViewTanggalGrooming = findViewById(R.id.textView_tanggal_grooming);
        textViewDetailHewan = findViewById(R.id.textView_detail_hewan);
        textViewNamaPaketGrooming = findViewById(R.id.textView_namaPaket_grooming);
        textViewHargaPaketGrooming = findViewById(R.id.textView_hargaPaket_grooming);
        editTextAlamatGrooming = findViewById(R.id.editText_alamat_grooming);
        textViewHargaAntarJemputGrooming = findViewById(R.id.textView_hargaAntarJemput_grooming);
        textViewTotalHargaGrooming = findViewById(R.id.textView_totalHarga_grooming);
        btnPesan = findViewById(R.id.button_pesan_grooming);

        /**
         * Set Text View
         */
        String textTanggalBooking = pesanan.getTanggalBooking() + " " + pesanan.getWaktuBooking();
        textViewTanggalGrooming.setText(textTanggalBooking);
        String textDetailHewan = pesanan.getHewan().getNamaHewan() + " | " +
                pesanan.getHewan().getSpesies() + "\n" + pesanan.getHewan().getNamaHewan();
        textViewDetailHewan.setText(textDetailHewan);
        textViewNamaPaketGrooming.setText(pesanan.getPaket().getNamaPaket());
        String textHargaPaket = "Rp " + pesanan.getPaket().getHargaPaket();
        textViewHargaPaketGrooming.setText(textHargaPaket);
        if(pesanan.getAlamat() != null){
            editTextAlamatGrooming.setText(pesanan.getAlamat().getAlamatLengkap());
            hargaAntarJemput = 15000;
            String textHargaAntarJemput = "Rp " + hargaAntarJemput;
            textViewHargaAntarJemputGrooming.setText(textHargaAntarJemput);
        }
        int totalHarga = pesanan.getPaket().getHargaPaket() + hargaAntarJemput;
        pesanan.setHarga(totalHarga);
        String textTotalHarga = "Rp " + totalHarga;
        textViewTotalHargaGrooming.setText(textTotalHarga);
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a formatter to convert LocalDateTime to string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Convert LocalDateTime to string
        String formattedDateTime = currentDateTime.format(formatter);
        pesanan.setTanggalBuat(formattedDateTime);

        pesanan.setStatus(StatusPesanan.DITERIMA);


        /**
         * Penerapan button back
         */
        btn_back_grooming3.setOnClickListener(v -> {
            finish();
        });

        /**
         * Penerapan button pesan
         */
        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new Database();
                String userID = db.getUserID();
                DatabaseReference userRef = db.getFirebaseDatabase().getReference("Customer").child(userID);
                System.out.println("userref:" + userRef);
                System.out.println("userid: "+userID);
                userRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        System.out.println("nama pemesan "+dataSnapshot.child("name").getValue(String.class));
                        String nama = dataSnapshot.child("name").getValue(String.class);
                        String noWa = dataSnapshot.child("noWa").getValue(String.class);
                        String email = dataSnapshot.child("email").getValue(String.class);
                        String password = dataSnapshot.child("password").getValue(String.class);

                        Customer pemesan = new Customer(nama, email, password, noWa, userID);
                        pesanan.setCustomer(pemesan);

                        db.setRef(db.getFirebaseDatabase().getReference("Pesanan").child(db.getUserID())
                                .child("Diterima"));
                        db.getRef().push().setValue(pesanan);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });


            }
        });
    }
}