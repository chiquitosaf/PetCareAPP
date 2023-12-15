package com.chiquito.petcareapp.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chiquito.petcareapp.Controller.grooming.GroomingFragmentAdapter;
import com.chiquito.petcareapp.R;
import com.google.android.material.tabs.TabLayout;

public class AkunFragment extends Fragment {

    public AkunFragment() {
        super(R.layout.fragment_akun);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_akun, container, false);
    }


}