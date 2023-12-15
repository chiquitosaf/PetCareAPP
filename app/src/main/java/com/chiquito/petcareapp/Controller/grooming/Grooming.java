package com.chiquito.petcareapp.Controller.grooming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.chiquito.petcareapp.R;
import com.google.android.material.tabs.TabLayout;

public class Grooming extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    private GroomingFragmentAdapter groomingFragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grooming);

        GroomingFragmentMethod groomingFragmentMethod = new GroomingFragmentMethod();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_grooming, groomingFragmentMethod, "GroomingMethod");
        fragmentTransaction.addToBackStack("GroomingMethodBackStack");
        fragmentTransaction.commit();
    }
}