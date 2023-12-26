package com.chiquito.petcareapp.Controller.histori;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoriFragmentAdapter extends FragmentStateAdapter {
    //        public HistoriFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
//            super(fragmentManager, lifecycle);
//        }
//
//        @NonNull
//        @Override
//        public Fragment createFragment(int position) {
//            return new ListPesananFragment();
//        }
//
//        @Override
//        public int getItemCount() {
//            return 3;
//        }
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    public HistoriFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    public String getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

}
