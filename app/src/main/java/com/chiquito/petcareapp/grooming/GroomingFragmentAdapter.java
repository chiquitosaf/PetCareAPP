package com.chiquito.petcareapp.grooming;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class GroomingFragmentAdapter extends FragmentStateAdapter {

    public GroomingFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position==1){
            return new DatangFragment();
        }
        return new AntarJemputFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
