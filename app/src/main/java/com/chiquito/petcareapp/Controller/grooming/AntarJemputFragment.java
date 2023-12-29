package com.chiquito.petcareapp.Controller.grooming;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.chiquito.petcareapp.Controller.alamat.AddAlamat;
import com.chiquito.petcareapp.Controller.alamat.RecycleViewAlamat;
import com.chiquito.petcareapp.Model.Alamat;
import com.chiquito.petcareapp.R;
import com.chiquito.petcareapp.SharedViewModel;
import com.google.android.material.textfield.TextInputEditText;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AntarJemputFragment extends Fragment {

    TextInputEditText inputTanggalGroomingAntarjemput, inputWaktuGroomingAntarjemput,
            inputAlamatGroomingAntarJemput;
    TextView txtInputTanggalGroomingAntarJemput, txtInputWaktuGroomingAntarJemput;
    String tanggalBookingString, waktuBookingString;
    ActivityResultLauncher<Intent> alamatLauncher;
    Alamat alamat;
    SharedViewModel sharedViewModel;

    public interface OnDataPassListenerAntarJemput {
        void onDataPassAntarJemput(String tanggal, String waktu, Alamat alamat);
    }
    AntarJemputFragment.OnDataPassListenerAntarJemput dataPassListener;

    private void sendDataToActivity(String tanggal, String waktu, Alamat alamat) {
        if (dataPassListener != null) {
            dataPassListener.onDataPassAntarJemput(tanggal, waktu, alamat);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        alamatLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            alamat = new Alamat(data.getStringExtra("tag"),
                                    data.getStringExtra("alamatLengkap"));
                            inputAlamatGroomingAntarJemput.setText(alamat.getAlamatLengkap());
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_antar_jemput, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        inputTanggalGroomingAntarjemput = view.findViewById(R.id.input_tanggal_grooming_antarjemput);
        inputWaktuGroomingAntarjemput = view.findViewById(R.id.input_waktu_grooming_antarjemput);
        txtInputTanggalGroomingAntarJemput = view.findViewById(R.id.txt_input_tanggal_grooming_antarjemput);
        txtInputWaktuGroomingAntarJemput = view.findViewById(R.id.txt_input_waktu_grooming_antarjemput);
        inputAlamatGroomingAntarJemput = view.findViewById(R.id.input_alamat_grooming_antarjemput);

        inputWaktuListener();
        inputTanggalListener();
        inputAlamatListener();

        inputTanggalGroomingAntarjemput.setOnClickListener(v -> {
            showDatePickerDialog();
        });

        inputWaktuGroomingAntarjemput.setOnClickListener(v -> {
            showTimePickerDialog();
        });

        inputAlamatGroomingAntarJemput.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RecycleViewAlamat.class);
            intent.putExtra("isFromGrooming", true);
            alamatLauncher.launch(intent);
        });
    }

    public void clearEditTextInputs() {
        txtInputTanggalGroomingAntarJemput.setText("--Pilih--");
        txtInputWaktuGroomingAntarJemput.setText("--Pilih--");
        inputAlamatGroomingAntarJemput.setText("--Pilih--");
    }
    private void showDatePickerDialog() {
        Calendar tanggalLahir = Calendar.getInstance();
        tanggalLahir.add(Calendar.DAY_OF_MONTH, 6);

        CustomDatePickerDialog datePickerDialog = new CustomDatePickerDialog(requireContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Handle the selected date within the valid range
                    // Your logic here for handling the valid date selection
                    selectedMonth += 1;
                    txtInputTanggalGroomingAntarJemput.setText(selectedDay+"/"+ selectedMonth+"/"+selectedYear);

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy",
                            java.util.Locale.getDefault());
                    tanggalBookingString = simpleDateFormat.format(tanggalLahir.getTime());
                });

        datePickerDialog.show();


//        DatePickerDialog dateDialog = new DatePickerDialog(requireContext(),
//                (view, selectedYear, selectedMonth, selectedDay) -> {
//                        Calendar selectedDate = Calendar.getInstance();
//                        selectedDate.set(selectedYear, selectedMonth, selectedDay);
//
//                        long diffInMillis = selectedDate.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
//                        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis);
//
//                        if (diffInDays > 6 || diffInDays < 0) {
//                            Toast.makeText(requireContext(), "Please select a date within 6 days from today.", Toast.LENGTH_SHORT).show();
//                            dateDia; // Close the DatePickerDialog
//                        } else {
//                            // Handle the selected date within the valid range
//                            // Your logic here for handling the valid date selection
//                            txtInputTanggalGroomingAntarJemput.setText(selectedDay+"/"+ selectedMonth+"/"+selectedYear);
//
//                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy",
//                                    java.util.Locale.getDefault());
//                            tanggalBookingString = simpleDateFormat.format(tanggalLahir.getTime());
//                        }
//                }, maxYear, maxMonth, maxDay);
//        dateDialog.show();
    }

    private void showTimePickerDialog(){
        TimePickerDialog timeDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        if (hour < 8 || (hour == 15 && minute > 0) || hour > 15) {
                            // Display an error message or take appropriate action
                            Toast.makeText(getContext(), "Pilih waktu antara jam 8 pagi - 3 sore"
                                    , Toast.LENGTH_SHORT).show();
                            // You might also reset the time picker to a default value here
                            return;
                        }
                        waktuBookingString = String.format(Locale.getDefault(), "%02d:%02d", hour,
                                minute);
                        sharedViewModel.setEstimasi(String.format(Locale.getDefault(),
                                "%02d:%02d", hour+3, minute));
                        txtInputWaktuGroomingAntarJemput.setText(waktuBookingString);
                    }
                }, 12, 0, true);
        timeDialog.show();
    }

    public void inputWaktuListener() {
        txtInputWaktuGroomingAntarJemput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sharedViewModel.setUserWaktu(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void inputTanggalListener() {
        txtInputTanggalGroomingAntarJemput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sharedViewModel.setUserTanggal(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void inputAlamatListener() {
        inputAlamatGroomingAntarJemput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sharedViewModel.setUserAlamat(alamat);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public class CustomDatePickerDialog extends DatePickerDialog {
        private long maxDateMillis;

        public CustomDatePickerDialog(Context context, OnDateSetListener listener) {
            super(context, listener, Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 6);
            maxDateMillis = calendar.getTimeInMillis();

            getDatePicker().setMaxDate(maxDateMillis);
        }

        @Override
        public void onDateChanged(@NonNull DatePicker view, int year, int month, int dayOfMonth) {
            super.onDateChanged(view, year, month, dayOfMonth);

            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(year, month, dayOfMonth);

            long diffInMillis = selectedDate.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
            long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis);

            if (diffInDays > 6 || diffInDays < 0) {
                Toast.makeText(getContext(), "Pilih waktu dalam rentang waktu 6 hari.", Toast.LENGTH_SHORT).show();
                dismiss(); // Close the dialog if the selected date is not within the allowed range
            }
        }
    }

}