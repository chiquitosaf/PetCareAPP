package com.chiquito.petcareapp.Controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.chiquito.petcareapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdminFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    FirebaseAuth fAuth;
    TextView textViewBanner;
    CircleImageView profilePhoto;

    public HomeAdminFragment() {
        super(R.layout.fragment_home_admin);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewBanner = view.findViewById(R.id.label_banner_admin);
        profilePhoto = view.findViewById(R.id.profile_photo);

        fAuth = FirebaseAuth.getInstance();
        String uID = fAuth.getUid();
        database = FirebaseDatabase.getInstance("https://petcareapp-85cfe-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = database.getReference("Admin").child(uID);
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String namaUser = snapshot.child("name").getValue(String.class);
                String labelBanner = "Hi "+ namaUser +"\nApa kabar hari ini?";
                textViewBanner.setText(labelBanner);

                if(snapshot.child("imageUrl").exists()){
                    Glide.with(getActivity()).load(snapshot.child("imageUrl").getValue().
                            toString()).into(profilePhoto);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error: ", error.getMessage());
            }
        });
    }
}