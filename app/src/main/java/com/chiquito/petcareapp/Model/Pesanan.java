package com.chiquito.petcareapp.Model;

import com.chiquito.petcareapp.StatusPesanan;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class Pesanan {

    String tanggalBooking, waktuBooking, kondisiHewan, tanggalBuat, estimasi;
    Customer customer;
    int harga;
    Alamat alamat;
    Hewan hewan;
    StatusPesanan status;
    Paket paket;
    JenisPesanan jenisPesanan;



    @ParcelConstructor
    public Pesanan() {
    }

    public Pesanan(String tanggalBooking, String waktuBooking, String kondisiHewan, String tanggalBuat,
                   String estimasi, int harga, Alamat alamat, Hewan hewan, StatusPesanan status,
                   Paket paket, JenisPesanan jenisPesanan, Customer customer) {
        this.tanggalBooking = tanggalBooking;
        this.waktuBooking = waktuBooking;
        this.kondisiHewan = kondisiHewan;
        this.tanggalBuat = tanggalBuat;
        this.estimasi = estimasi;
        this.harga = harga;
        this.alamat = alamat;
        this.hewan = hewan;
        this.status = status;
        this.paket = paket;
        this.jenisPesanan = jenisPesanan;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getEstimasi() {
        return estimasi;
    }

    public void setEstimasi(String estimasi) {
        this.estimasi = estimasi;
    }

    public JenisPesanan getJenisPesanan() {
        return jenisPesanan;
    }

    public void setJenisPesanan(JenisPesanan jenisPesanan) {
        this.jenisPesanan = jenisPesanan;
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

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
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

    public void setPaket(Paket paket) {
        this.paket = paket;
    }

    public Paket getPaket() {
        return paket;
    }
}
