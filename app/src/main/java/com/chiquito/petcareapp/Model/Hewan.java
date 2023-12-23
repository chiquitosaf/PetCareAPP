package com.chiquito.petcareapp.Model;

import android.os.Parcelable;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.Calendar;
import java.util.Date;
@Parcel
public class Hewan {
    public enum JenisKelamin{
        Jantan, Betina
    }
    public enum Spesies{
        Kucing, Anjing;
    }

    String namaHewan, warnaHewan, keterangan, ras, urlImage;
    JenisKelamin jenisKelamin;
    Spesies spesies;
    String tanggalLahir;


    public String getRas() {
        return ras;
    }

    public void setRas(String ras) {
        this.ras = ras;
    }
    @ParcelConstructor
    public Hewan(String namaHewan, String warnaHewan, String keterangan, String ras,
                 JenisKelamin jenisKelamin, Spesies spesies, String tanggalLahir, String urlImage) {
        this.namaHewan = namaHewan;
        this.warnaHewan = warnaHewan;
        this.keterangan = keterangan;
        this.ras = ras;
        this.jenisKelamin = jenisKelamin;
        this.spesies = spesies;
        this.tanggalLahir = tanggalLahir;
        this.urlImage = urlImage;
    }

    public String getNamaHewan() {
        return namaHewan;
    }

    public void setNamaHewan(String namaHewan) {
        this.namaHewan = namaHewan;
    }

    public String getWarnaHewan() {
        return warnaHewan;
    }

    public void setWarnaHewan(String warnaHewan) {
        this.warnaHewan = warnaHewan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public JenisKelamin getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(JenisKelamin jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Spesies getSpesies() {
        return spesies;
    }

    public void setSpesies(Spesies spesies) {
        this.spesies = spesies;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
