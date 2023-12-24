package com.chiquito.petcareapp.Controller.grooming;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

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

import com.chiquito.petcareapp.R;
import com.chiquito.petcareapp.SharedViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DatangFragment extends Fragment {
    TextView txtInputTanggalGroomingDatang, txtInputWaktuGroomingDatang;
    TextInputEditText inputTanggalGroomingDatang, inputWaktuGroomingDatang;
    String tanggalBookingString, waktuBookingString;
    SharedViewModel sharedViewModel;
//    public interface OnDataPassListenerDatang {
//        void onDataPassDatang(String tanggal, String waktu);
//    }
//    OnDataPassListenerDatang dataPassListener;
//
//    private void sendDataToActivity(String tanggal, String waktu) {
//        if (dataPassListener != null) {
//            dataPassListener.onDataPassDatang(tanggal, waktu);
//        }
//    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        try {
//            dataPassListener = (OnDataPassListenerDatang) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString() + " must implement OnDataPassListener");
//        }
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_datang, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inputTanggalGroomingDatang = view.findViewById(R.id.input_tanggal_grooming_datang);
        inputWaktuGroomingDatang = view.findViewById(R.id.input_waktu_grooming_datang);
        txtInputWaktuGroomingDatang = view.findViewById(R.id.txt_input_waktu_grooming);
        txtInputTanggalGroomingDatang = view.findViewById(R.id.txt_input_tanggal_groomin_datang);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        
        inputTanggalGroomingDatang.setOnClickListener(v -> {
            showDatePickerDialog();
        });

        inputWaktuGroomingDatang.setOnClickListener(v -> {
            showTimePickerDialog();
        });
//
        inputTanggalListener();
        inputWaktuListener();

    }

    private void showDatePickerDialog() {
        DatePickerDialog dateDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtInputTanggalGroomingDatang.setText(dayOfMonth+"/"+ month+"/"+year);
                Calendar tanggalLahir = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy",
                        java.util.Locale.getDefault());
                tanggalBookingString = simpleDateFormat.format(tanggalLahir.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dateDialog.show();
    }

    private void showTimePickerDialog(){
        TimePickerDialog timeDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                if (hour < 8 || (hour == 18 && minute > 0) || hour > 18) {
                    // Display an error message or take appropriate action
                    Toast.makeText(getContext(), "Mohon pilih waktu antara jam 8 pagi - 6 malam"
                            , Toast.LENGTH_SHORT).show();
                    // You might also reset the time picker to a default value here
                    return;
                }
                waktuBookingString = String.format(Locale.getDefault(), "%02d:%02d", hour,
                        minute);
                txtInputWaktuGroomingDatang.setText(waktuBookingString);
            }
        }, 12, 0, true);
        timeDialog.show();
    }

   public void clearEditTextInputs() {
        txtInputTanggalGroomingDatang.setText("--Pilih--");
        txtInputWaktuGroomingDatang.setText("--Pilih--");
   }

    public void inputTanggalListener(){
        txtInputTanggalGroomingDatang.addTextChangedListener(new TextWatcher() {
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
//        txtInputTanggalGroomingDatang.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                txtInputWaktuGroomingDatang.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//                        sendDataToActivity(txtInputTanggalGroomingDatang.getText().toString(),
//                                txtInputWaktuGroomingDatang.getText().toString());
//                    }
//                });
//            }
//        });
    }
//
    public void inputWaktuListener(){
        txtInputWaktuGroomingDatang.addTextChangedListener(new TextWatcher() {
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
//        txtInputWaktuGroomingDatang.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                txtInputTanggalGroomingDatang.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//                        sendDataToActivity(txtInputTanggalGroomingDatang.getText().toString(),
//                                txtInputWaktuGroomingDatang.getText().toString());
//                    }
//                });
//            }
//        });
    }
}