package com.chiquito.petcareapp.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chiquito.petcareapp.Controller.grooming.AntarJemputFragment;
import com.chiquito.petcareapp.Controller.grooming.DatangFragment;
import com.chiquito.petcareapp.Controller.grooming.GroomingFragmentAdapter;
import com.chiquito.petcareapp.R;
import com.google.android.material.tabs.TabLayout;

public class HistoriFragment extends Fragment {

    public HistoriFragment() {
        super(R.layout.fragment_histori);
    }
//    private TabLayout tabLayout;
//    private ViewPager2 viewPager;
//    private GroomingFragmentAdapter groomingFragmentAdapter;
//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_histori, container, false);
    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        tabLayout = view.findViewById(R.id.tabLayout_histori);
//        viewPager = view.findViewById(R.id.viewPager_histori);
//
//        tabLayout.addTab(tabLayout.newTab().setText("Konfirmasi"));
//        tabLayout.addTab(tabLayout.newTab().setText("Proses"));
//        tabLayout.addTab(tabLayout.newTab().setText("Selesai"));
//
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        HistoriFragmentAdapter historiFragmentAdapter = new HistoriFragmentAdapter(fragmentManager, getLifecycle());
//        viewPager.setAdapter(historiFragmentAdapter);
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab){
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab){
//            }
//            @Override
//            public void onTabReselected(TabLayout.Tab tab){
//            }
//        });
//
//        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position){
//                tabLayout.selectTab(tabLayout.getTabAt(position));
//            }
//        });
//    }
//
//    public class HistoriFragmentAdapter extends FragmentStateAdapter {
//
//        public HistoriFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
//            super(fragmentManager, lifecycle);
//        }
//
//        @NonNull
//        @Override
//        public Fragment createFragment(int position) {
//            if (position==1){
//                return new DatangFragment();
//            } else if (position == 2){
//                return new DatangFragment();
//            }
//            return new AntarJemputFragment();
//        }
//
//        @Override
//        public int getItemCount() {
//            return 3;
//        }
//    }
}