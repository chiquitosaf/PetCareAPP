package com.chiquito.petcareapp.grooming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TextView;

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

        tabLayout = findViewById(R.id.tabLayout_grooming);
        viewPager = findViewById(R.id.viewPager_grooming);

        tabLayout.addTab(tabLayout.newTab().setText("Antar Jemput"));
        tabLayout.addTab(tabLayout.newTab().setText("Datang ke Tempat"));

        FragmentManager fragmentManager = getSupportFragmentManager();
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