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
import java.util.concurrent.TimeUnit;


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
        Calendar tanggalLahir = Calendar.getInstance();
        tanggalLahir.add(Calendar.DAY_OF_MONTH, 6);

        CustomDatePickerDialog datePickerDialog = new CustomDatePickerDialog(requireContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Handle the selected date within the valid range
                    // Your logic here for handling the valid date selection
                    selectedMonth += 1;
                    txtInputTanggalGroomingDatang.setText(selectedDay+"/"+ selectedMonth+"/"+selectedYear);

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy",
                            java.util.Locale.getDefault());
                    tanggalBookingString = simpleDateFormat.format(tanggalLahir.getTime());
                });

        datePickerDialog.show();
    }

    private void showTimePickerDialog(){
        TimePickerDialog timeDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                if (hour < 8 || (hour == 18 && minute > 0) || hour > 18) {
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