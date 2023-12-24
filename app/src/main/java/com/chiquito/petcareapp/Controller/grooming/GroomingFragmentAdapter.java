package com.chiquito.petcareapp.Controller.grooming;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.chiquito.petcareapp.ErrorFragment;

public class GroomingFragmentAdapter extends FragmentStateAdapter {

    public GroomingFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position==1){
            return new DatangFragment();
        }else if(position == 0){
            return new AntarJemputFragment();
        }else{
            return new ErrorFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }




}
