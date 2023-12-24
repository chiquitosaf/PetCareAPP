package com.chiquito.petcareapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chiquito.petcareapp.Model.Alamat;
import com.chiquito.petcareapp.Model.Pesanan;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Pesanan> userInputData = new MutableLiveData<>();

    public SharedViewModel() {
        userInputData.setValue(new Pesanan());
    }

    public void setUserTanggal(String input) {
        Pesanan data = userInputData.getValue();
        if (data != null) {
            data.setTanggalBooking(input);
            userInputData.setValue(data);
        }
    }

    public void setUserWaktu(String input) {
        Pesanan data = userInputData.getValue();
        if (data != null) {
            data.setWaktuBooking(input);
            userInputData.setValue(data);
        }
    }

    public void setUserAlamat(Alamat alamat) {
        Pesanan data = userInputData.getValue();
        if (data != null) {
            data.setAlamat(alamat);
            userInputData.setValue(data);
        }
    }

    public LiveData<Pesanan> getUserInputData() {
        return userInputData;
    }
}

