package com.chiquito.petcareapp.Controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chiquito.petcareapp.Controller.auth.Login;
import com.chiquito.petcareapp.Controller.grooming.GroomingFragmentAdapter;
import com.chiquito.petcareapp.Database;
import com.chiquito.petcareapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class AkunFragment extends Fragment {

    Button btnLogout, btnEdit;
    Database db;
    TextView tvNamaProfil, tvWAProfil, tvEmailProfil;
    CircleImageView fotoProfil;

    public AkunFragment() {
        super(R.layout.fragment_akun);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_akun, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvNamaProfil = view.findViewById(R.id.textView_nama_profil);
        tvWAProfil = view.findViewById(R.id.textView_wa_profile);
        tvEmailProfil = view.findViewById(R.id.textView_email_profile);
        btnLogout = view.findViewById(R.id.btn_logout_profile);
        btnEdit = view.findViewById(R.id.btn_edit_profile);
        fotoProfil = view.findViewById(R.id.profile_photo);

        db = new Database();
        db.setRef(db.getFirebaseDatabase().getReference());

        db.getRef().child("Admin").child(db.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    btnEdit.setVisibility(View.GONE);
                    db.setRef(db.getFirebaseDatabase().getReference().child("Admin").child(db.getUserID()));
                    getData();
                } else {
                    db.getRef().child("Customer").child(db.getUserID()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                db.setRef(db.getFirebaseDatabase().getReference().child("Customer").child(db.getUserID()));
                                getData();

                            } else {
                                Log.e("Error: ", "User not found");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                           Log.e("Error: ", error.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error: ", error.getMessage());
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.getfAuth().signOut();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditAkun.class);
                startActivity(intent);
            }
        });
    }

    public void getData(){
        db.getRef().get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                tvNamaProfil.setText(dataSnapshot.child("name").getValue().toString());
                tvWAProfil.setText(dataSnapshot.child("noWA").getValue().toString());
                tvEmailProfil.setText(dataSnapshot.child("email").getValue().toString());

                if(dataSnapshot.child("imageUrl").exists()){
                    Glide.with(getActivity()).load(dataSnapshot.child("imageUrl").getValue().
                            toString()).into(fotoProfil);
                }
            }
        });
    }
}