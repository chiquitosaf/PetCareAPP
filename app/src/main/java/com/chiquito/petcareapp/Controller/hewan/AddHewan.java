package com.chiquito.petcareapp.Controller.hewan;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.MimeTypeMap;
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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.chiquito.petcareapp.Database;
import com.chiquito.petcareapp.Model.Hewan;
import com.chiquito.petcareapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.hdodenhof.circleimageview.CircleImageView;


public class AddHewan extends AppCompatActivity {

    List<String> spesies;
    AutoCompleteTextView inputSpesies;
    ArrayAdapter arrayAdapterSpesies;
    EditText inputTanggalLahir;
    TextView txtInputTannggalLahir;
    RadioGroup radioGroupJenisKelamin;
    RadioButton radioButtonJenisKelamin;
    ImageButton btnBack;
    Button btnAddHewan, buttonUploadImage;
    CircleImageView hewanImage;
    Uri imageUri;
    Uri imageUrl;
    TextInputEditText inputNamaHewan, inputWarnaHewan, inputKeteranganHewan, inputRasHewan;
    String tanggalLahirString;
    Database db;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String namaAwal;
//    ActivityResultLauncher<PickVisualMediaRequest> pickImage;
    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

            @Override
            public void onActivityResult(ActivityResult o) {
                if(o.getResultCode() == Activity.RESULT_OK){
                    if (o.getData() != null) imageUri = o.getData().getData();
                    Glide.with(getApplicationContext()).load(imageUri).into(hewanImage);
                }else{
                    Toast.makeText(AddHewan.this, "Image not found", Toast.LENGTH_SHORT).show();
                }
            }
        }
);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hewan);

        FirebaseApp.initializeApp(this);
        Bundle bundle = getIntent().getExtras();


        db = new Database();
        databaseReference = db.getFirebaseDatabase().getReference("Hewan").child(db.getUserID());
        storageReference = FirebaseStorage.getInstance().getReference("Hewan").child(db.getUserID());

        radioGroupJenisKelamin = findViewById(R.id.radio_group_jenis_kelamin);
        radioGroupJenisKelamin.clearCheck();

        inputNamaHewan = findViewById(R.id.input_nama_hewan);
        inputWarnaHewan = findViewById(R.id.input_warna_hewan);
        inputKeteranganHewan = findViewById(R.id.input_keterangan_hewan);
        inputRasHewan = findViewById(R.id.input_ras_hewan);

        hewanImage = findViewById(R.id.profile_photo_hewan);

        buttonUploadImage = findViewById(R.id.btn_tambah_profile_hewan);
        buttonUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                pickImage.launch(intent);
            }
        });

        //button add hewan
        btnAddHewan = findViewById(R.id.btn_tambah_hewan);
        btnAddHewan.setOnClickListener(v -> {
            String namaHewan = Objects.requireNonNull(inputNamaHewan.getText()).toString();
            String warnaHewan = Objects.requireNonNull(inputWarnaHewan.getText()).toString();
            String keteranganHewan = Objects.requireNonNull(inputKeteranganHewan.getText()).toString();
            String rasHewan = Objects.requireNonNull(inputRasHewan.getText()).toString();
            String spesiesHewan = inputSpesies.getText().toString();
            String jenisKelamin = radioButtonJenisKelamin.getText().toString();

            namaAwal = namaHewan;
            Hewan hewanInput = new Hewan(namaHewan, warnaHewan, keteranganHewan, rasHewan,
                    Hewan.JenisKelamin.valueOf(jenisKelamin), Hewan.Spesies.valueOf(spesiesHewan),
                    tanggalLahirString, null);

            if(imageUri != null){
                uploadImageToStorage(imageUri, hewanInput, bundle);
            }else{
                Toast.makeText(this, "Image not found", Toast.LENGTH_SHORT).show();
            }

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

        spesies = Stream.of(Hewan.Spesies.values()).map(Hewan.Spesies::name).collect(Collectors
                .toList());
        arrayAdapterSpesies = new ArrayAdapter(this, R.layout.dropdown_item, spesies);
        inputSpesies = findViewById(R.id.input_spesies_hewan);
        inputSpesies.setAdapter(arrayAdapterSpesies);

        Hewan hewan = Parcels.unwrap(getIntent().getParcelableExtra("hewan"));
        if (bundle != null){
            inputNamaHewan.setText(hewan.getNamaHewan());
            inputWarnaHewan.setText(hewan.getWarnaHewan());
            inputKeteranganHewan.setText(hewan.getKeterangan());
            inputRasHewan.setText(hewan.getRas());
            inputSpesies.setText(hewan.getSpesies().toString());
            txtInputTannggalLahir.setText(hewan.getTanggalLahir());
            if(Objects.equals(hewan.getJenisKelamin().toString(), "Jantan")){
                radioGroupJenisKelamin.check(R.id.radio_jantan);
            }else{
                radioGroupJenisKelamin.check(R.id.radio_betina);
            }
            Glide.with(this).load(hewan.getUrlImage()).into(hewanImage);
        }

    }

    public void uploadImageToStorage(Uri uri, Hewan hewan, Bundle bundle){
        final StorageReference imageRef = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(uri));
        imageRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        System.out.println(uri.toString());
                        hewan.setUrlImage(uri.toString());
                        if(bundle != null){
                            databaseReference.child(Objects.requireNonNull(namaAwal)).removeValue();
                        }
                        databaseReference.child(hewan.getNamaHewan()).setValue(hewan);
                        Toast.makeText(getApplicationContext(), "Hewan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
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

    public void openDateDialog(){
//        final Calendar currentData = Calendar.getInstance();
//        Calendar date = Calendar.getInstance();
        DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                date.set(year, month, dayOfMonth);
                txtInputTannggalLahir.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
                Calendar tanggalLahir = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault());
                tanggalLahirString = simpleDateFormat.format(tanggalLahir.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.YEAR, -20);
//        dateDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        dateDialog.show();
    }

    public void checkButton(View v){
        int radioId = radioGroupJenisKelamin.getCheckedRadioButtonId();
        radioButtonJenisKelamin = findViewById(radioId);
    }
}
