package com.chiquito.petcareapp.Controller.grooming;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.chiquito.petcareapp.ErrorFragment;

public class GroomingFragmentAdapter extends FragmentStateAdapter {
    private final Fragment[] fragments;
    public GroomingFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        fragments = new Fragment[2];
        fragments[0] = new AntarJemputFragment();
        fragments[1] = new DatangFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position < 0 || position >= fragments.length)
            return new ErrorFragment();
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return 2;
    }




}
