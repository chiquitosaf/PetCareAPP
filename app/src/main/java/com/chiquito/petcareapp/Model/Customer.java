package com.chiquito.petcareapp.Model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class Customer {
    String name, email, password, noWA, imageUrl, uid;

    @ParcelConstructor
    public Customer(String name, String email, String password, String noWA, String uid) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.noWA = noWA;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoWA() {
        return noWA;
    }

    public void setNoWA(String noWA) {
        this.noWA = noWA;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
