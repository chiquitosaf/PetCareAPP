package com.chiquito.petcareapp.Controller.grooming;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chiquito.petcareapp.R;
import com.google.android.material.tabs.TabLayout;

public class GroomingFragmentMethod extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private GroomingFragmentAdapter groomingFragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grooming_method, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.tabLayout_grooming);
        viewPager = view.findViewById(R.id.viewPager_grooming);

        tabLayout.addTab(tabLayout.newTab().setText("Antar Jemput"));
        tabLayout.addTab(tabLayout.newTab().setText("Datang ke Tempat"));

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        groomingFragmentAdapter = new GroomingFragmentAdapter(fragmentManager, getLifecycle());
        viewPager.setAdapter(groomingFragmentAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab){
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab){
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position){
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}