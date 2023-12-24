package com.chiquito.petcareapp.Model;

import com.chiquito.petcareapp.StatusPesanan;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class Pesanan {
    String tanggalBooking, waktuBooking, kondisiHewan, tanggalBuat;
    float harga;
    Alamat alamat;
    Hewan hewan;
    StatusPesanan status;

    @ParcelConstructor
    public Pesanan() {
    }

    public String getTanggalBooking() {
        return tanggalBooking;
    }

    public void setTanggalBooking(String tanggalBooking) {
        this.tanggalBooking = tanggalBooking;
    }

    public String getWaktuBooking() {
        return waktuBooking;
    }

    public void setWaktuBooking(String waktuBooking) {
        this.waktuBooking = waktuBooking;
    }

    public String getKondisiHewan() {
        return kondisiHewan;
    }

    public void setKondisiHewan(String kondisiHewan) {
        this.kondisiHewan = kondisiHewan;
    }

    public String getTanggalBuat() {
        return tanggalBuat;
    }

    public void setTanggalBuat(String tanggalBuat) {
        this.tanggalBuat = tanggalBuat;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public Hewan getHewan() {
        return hewan;
    }

    public void setHewan(Hewan hewan) {
        this.hewan = hewan;
    }

    public StatusPesanan getStatus() {
        return status;
    }

    public void setStatus(StatusPesanan status) {
        this.status = status;
    }
}
