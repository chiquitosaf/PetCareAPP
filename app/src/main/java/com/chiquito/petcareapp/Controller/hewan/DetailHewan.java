package com.chiquito.petcareapp.Controller.hewan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chiquito.petcareapp.Database;
import com.chiquito.petcareapp.Model.Hewan;
import com.chiquito.petcareapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;

import org.parceler.Parcels;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author      Chiquito 1301210284 <chiquitosaf@gmail.com>
 */
public class DetailHewan extends AppCompatActivity {
    /**
     * Deklarasi variabel
     */
    TextInputEditText nama, spesies, ras, warna, keterangan;
    TextView tanggalLahir;
    RadioButton jantan, betina;
    CircleImageView foto_detail_hewan;
    ImageButton btnBack;
    Button btnEdit, btnDelete;
    Database db;
    DatabaseReference databaseReference;

    /**
     * Method membuat activity detail hewan
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hewan);

        /**
         * Inisialisasi view
         */
        nama = findViewById(R.id.input_nama_detail_hewan);
        spesies = findViewById(R.id.input_spesies_detail_hewan);
        ras = findViewById(R.id.input_ras_detail_hewan);
        warna = findViewById(R.id.input_warna_detail_hewan);
        tanggalLahir = findViewById(R.id.txt_input_tanggal_lahir);
        keterangan = findViewById(R.id.input_keterangan_detail_hewan);
        jantan = findViewById(R.id.radio_jantan_detail_hewan);
        betina = findViewById(R.id.radio_betina_detail_hewan);
        foto_detail_hewan = findViewById(R.id.foto_detail_hewan);
        btnEdit = findViewById(R.id.btn_edit_detail_hewan);
        btnDelete = findViewById(R.id.btn_delete_detail_hewan);

        /**
         * Mengambil data dari intent
         */
        Hewan hewan = Parcels.unwrap(getIntent().getParcelableExtra("hewan"));
        assert hewan != null;
        nama.setText(hewan.getNamaHewan());
        spesies.setText(hewan.getSpesies().toString());
        ras.setText(hewan.getRas());
        warna.setText(hewan.getWarnaHewan());
        tanggalLahir.setText(hewan.getTanggalLahir());
        keterangan.setText(hewan.getKeterangan());
        if (hewan.getJenisKelamin().equals("Jantan")) {
            jantan.setChecked(true);
        } else {
            betina.setChecked(true);
        }
        Glide.with(this).load(hewan.getUrlImage()).into(foto_detail_hewan);
        btnBack = findViewById(R.id.btn_back_detail_hewan);

        /**
         * Logic tombol kembali saat ditekan
         * Menutup Activity DetailHewan
         */
        btnBack.setOnClickListener(v -> finish());

        /**
         * Logic tombol edit saat ditekan
         * Pindah ke activity AddHewan untuk merubah data
         */
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddHewan.class);
                Bundle bundle = new Bundle();

                bundle.putParcelable("hewan", Parcels.wrap(hewan));
                intent.putExtras(bundle);
                intent.putExtra("nama", hewan.getNamaHewan());
                startActivity(intent);
                finish();
            }
        });

        /**
         * Logic tombol delete saat ditekan
         * Menghapus data hewan dari database
         */
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new Database();
                databaseReference = db.getFirebaseDatabase().getReference("Hewan").child(db.getUserID());
                databaseReference.child(hewan.getNamaHewan()).removeValue();
                Toast.makeText(getApplicationContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}