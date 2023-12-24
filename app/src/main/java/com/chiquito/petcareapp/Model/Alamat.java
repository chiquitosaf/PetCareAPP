package com.chiquito.petcareapp.Model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class Alamat {
    private String tag, alamatLengkap;

    @ParcelConstructor
    public Alamat(String tag, String alamatLengkap) {
        this.tag = tag;
        this.alamatLengkap = alamatLengkap;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkap = alamatLengkap;
    }
}
