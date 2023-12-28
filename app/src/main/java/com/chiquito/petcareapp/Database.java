package com.chiquito.petcareapp;

import android.util.Log;
import android.widget.Toast;

import com.chiquito.petcareapp.Controller.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.annotation.Nullable;

public class Database {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;
    private FirebaseAuth fAuth;
    @Nullable
    private String userID;

    public Database() {
        this.firebaseDatabase = FirebaseDatabase.getInstance(
                "https://petcareapp-85cfe-default-rtdb.asia-southeast1.firebasedatabase.app");
        this.fAuth = FirebaseAuth.getInstance();
        this.userID = fAuth.getCurrentUser().getUid();
    }

    public Database(String noUserID){
        this.firebaseDatabase = FirebaseDatabase.getInstance(
                "https://petcareapp-85cfe-default-rtdb.asia-southeast1.firebasedatabase.app");
        this.fAuth = FirebaseAuth.getInstance();
        this.userID = noUserID;
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public FirebaseAuth getfAuth() {
        return fAuth;
    }

    public void setfAuth(FirebaseAuth fAuth) {
        this.fAuth = fAuth;
    }

    public String getUserID() {
        return userID;
    }

    public DatabaseReference getRef() {
        return ref;
    }

    public void setRef(DatabaseReference ref) {
        this.ref = ref;
    }

}
