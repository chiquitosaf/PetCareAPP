package com.chiquito.petcareapp.Model;

import java.util.Date;

public class Hewan {
    public enum JenisKelamin{
        Jantan, Betina
    }
    public enum Spesies{
        Kucing, Anjing;
    }

    String namaHewan, warnaHewan, keterangan, ras;
    JenisKelamin jenisKelamin;
    Spesies spesies;
    Date tanggalLahir;

    public String getRas() {
        return ras;
    }

    public void setRas(String ras) {
        this.ras = ras;
    }

    public Hewan(String namaHewan, String warnaHewan, String keterangan, String ras, JenisKelamin jenisKelamin, Spesies spesies, Date tanggalLahir) {
        this.namaHewan = namaHewan;
        this.warnaHewan = warnaHewan;
        this.keterangan = keterangan;
        this.ras = ras;
        this.jenisKelamin = jenisKelamin;
        this.spesies = spesies;
        this.tanggalLahir = tanggalLahir;
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

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
}
