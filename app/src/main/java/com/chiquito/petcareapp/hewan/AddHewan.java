package com.chiquito.petcareapp.hewan;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chiquito.petcareapp.R;


import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AddHewan extends AppCompatActivity {

    List<String> spesies;
    AutoCompleteTextView inputSpesies;
    ArrayAdapter arrayAdapterSpesies;
    EditText inputTanggalLahir;
    TextView txtInputTannggalLahir;
    RadioGroup radioGroupJenisKelamin;
    RadioButton radioButtonJenisKelamin;
    ImageButton btnBack;
    Button btnAddHewan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hewan);

        btnAddHewan = findViewById(R.id.btn_tambah_hewan);
        btnAddHewan.setOnClickListener(v -> {
            Toast.makeText(this, "Hewan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnBack = findViewById(R.id.btn_back_add_hewan);
        btnBack.setOnClickListener(v -> finish());

        radioGroupJenisKelamin = findViewById(R.id.radio_group_jenis_kelamin);

        inputTanggalLahir = findViewById(R.id.input_tanggal_lahir);
        txtInputTannggalLahir = findViewById(R.id.txt_input_tanggal_lahir);
        inputTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateDialog();
            }
        });

        spesies = Stream.of(Hewan.Spesies.values()).map(Hewan.Spesies::name).collect(Collectors.toList());
        arrayAdapterSpesies = new ArrayAdapter(this, R.layout.dropdown_item, spesies);
        inputSpesies = findViewById(R.id.input_spesies_hewan);
        inputSpesies.setAdapter(arrayAdapterSpesies);

    }

    public void openDateDialog(){
        DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtInputTannggalLahir.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        dateDialog.show();
    }

    public void checkButton(View v){
        int radioId = radioGroupJenisKelamin.getCheckedRadioButtonId();
        radioButtonJenisKelamin = findViewById(radioId);
    }
}
