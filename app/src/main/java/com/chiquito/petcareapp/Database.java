package com.chiquito.petcareapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Database {
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth fAuth;
    private String userID;

    public Database() {
        this.firebaseDatabase = FirebaseDatabase.getInstance(
                "https://petcareapp-85cfe-default-rtdb.asia-southeast1.firebasedatabase.app");
        this.fAuth = FirebaseAuth.getInstance();
        this.userID = fAuth.getCurrentUser().getUid();
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public FirebaseAuth getfAuth() {
        return fAuth;
    }

    public String getUserID() {
        return userID;
    }
}
