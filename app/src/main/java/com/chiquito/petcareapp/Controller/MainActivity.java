package com.chiquito.petcareapp.Controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chiquito.petcareapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private FirebaseUser currentUser;
    public int cek;

    public void setCek(int cek) {
        this.cek = cek;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance("https://petcareapp-85cfe-default-rtdb.asia-southeast1.firebasedatabase.app");
        userRef = database.getReference();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userID = currentUser.getUid();

        System.out.println("userID: " + userID);

        if(currentUser != null) {
            // Check if the user is in the 'admin' node
            userRef.child("Admin").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // User is an admin
//                        callback.onUserTypeCallback(false);
                        getHomeFragment(2);
                    } else {
                        // Check if the user is in the 'customer' node
                        userRef.child("Customer").child(userID).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    // User is a customer
//                                    callback.onUserTypeCallback(true);
                                    getHomeFragment(1);

                                } else {
                                    // User is neither admin nor customer
                                    Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // Handle errors
                                Log.d("TAG", databaseError.getMessage());
                            }
                        });
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle errors
                    Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            });
        }

//        System.out.println("cek: " + cek);
//        getHomeFragment(cek);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navbar_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.akun){
                AkunFragment akunFragment = new AkunFragment();
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer_home, akunFragment, null)
                        .commit();
            }
            else if(item.getItemId() == R.id.histori){
                HistoriFragment historiFragment = new HistoriFragment();
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer_home, historiFragment, null)
                        .commit();
            }
            else if(item.getItemId() == R.id.home){
                userRef.child("Admin").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // User is an admin
//                        callback.onUserTypeCallback(false);
                            getHomeFragment(2);
                        } else {
                            // Check if the user is in the 'customer' node
                            userRef.child("Customer").child(userID).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        // User is a customer
//                                    callback.onUserTypeCallback(true);
                                        getHomeFragment(1);

                                    } else {
                                        // User is neither admin nor customer
                                        Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // Handle errors
                                    Log.d("TAG", databaseError.getMessage());
                                }
                            });
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle errors
                        Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return true;
        });

    }

    public void getHomeFragment(int cek){
        if(cek == 1){
            HomeCustomerFragment homeCustomerFragment = new HomeCustomerFragment();
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer_home, homeCustomerFragment, null)
                    .commit();
        } else if (cek == 2){
            HomeAdminFragment homeAdminFragment = new HomeAdminFragment();
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer_home, homeAdminFragment, null)
                    .commit();
        }
    }

//    interface UserTypeCallback {
//        void onUserTypeCallback(boolean isCustomer);
//    }

//        void checkUserType(String userID) {
//            DatabaseReference usersRef = FirebaseDatabase.getInstance("https://petcareapp-85cfe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
//
//
//            // Check if the user is in the 'admin' node
//            usersRef.child("Admin").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//
//                    if (dataSnapshot.exists()) {
//                        // User is an admin
////                        callback.onUserTypeCallback(false);
//                        cek = 1;
//                    } else {
//                        // Check if the user is in the 'customer' node
//                        usersRef.child("Customer").child(userID).addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                if (dataSnapshot.exists()) {
//                                    // User is a customer
//
////                                    callback.onUserTypeCallback(true);
//                                    cek = 1;
//
//                                } else {
//                                    // User is neither admin nor customer
//                                    Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//                                // Handle errors
//                                Log.d("TAG", databaseError.getMessage());
//                            }
//                        });
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    // Handle errors
//                    Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
////        public void getUserType(String userID){
////            checkUserType(userID, new UserTypeCallback() {
////                @Override
////                public void onUserTypeCallback(boolean isCustomer) {
////                    if(isCustomer){
////                        cek = 1;
////                    } else{
////                        cek = 2;
////                    }
////                }
////            });
////        }
//

}