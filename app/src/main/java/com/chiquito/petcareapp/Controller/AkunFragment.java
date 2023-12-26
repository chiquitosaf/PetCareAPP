package com.chiquito.petcareapp.Controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chiquito.petcareapp.Controller.auth.Login;
import com.chiquito.petcareapp.Controller.grooming.GroomingFragmentAdapter;
import com.chiquito.petcareapp.Database;
import com.chiquito.petcareapp.R;
import com.google.android.material.tabs.TabLayout;

public class AkunFragment extends Fragment {

    Button btnLogout;
    Database db;

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

        btnLogout = view.findViewById(R.id.btn_logout_profile);

        db = new Database();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.getfAuth().signOut();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}