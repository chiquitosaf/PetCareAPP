package com.chiquito.petcareapp.Model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class Paket {
    private String namaPaket;
    private int hargaPaket;

    @ParcelConstructor
    public Paket(String namaPaket, int hargaPaket) {
        this.namaPaket = namaPaket;
        this.hargaPaket = hargaPaket;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public int getHargaPaket() {
        return hargaPaket;
    }

    public void setHargaPaket(int hargaPaket) {
        this.hargaPaket = hargaPaket;
    }
}
