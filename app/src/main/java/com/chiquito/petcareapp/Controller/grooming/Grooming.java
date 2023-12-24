package com.chiquito.petcareapp.Controller.grooming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.chiquito.petcareapp.Model.Alamat;
import com.chiquito.petcareapp.Model.Pesanan;
import com.chiquito.petcareapp.R;
import com.chiquito.petcareapp.SharedViewModel;
import com.google.android.material.tabs.TabLayout;

import org.parceler.Parcels;

/**
 * This class is the main activity of the grooming section
 * It contains the tab layout and the view pager
 * The view pager contains the fragments of the grooming section
 * The tab layout is used to navigate between the fragments
 */
public class Grooming extends AppCompatActivity{ //implements DatangFragment.OnDataPassListenerDatang,
    //AntarJemputFragment.OnDataPassListenerAntarJemput{

    /**
     * Deklarasi Variable
     */
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private GroomingFragmentAdapter groomingFragmentAdapter;
    private ImageButton btnBackGrooming;
    private Button btnLanjut;
    SharedViewModel sharedViewModel;
    Pesanan inputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grooming);

//        if(getIntent().getExtras() != null){
////            Alamat alamat = Parcels.unwrap(getIntent().getParcelableExtra("alamat"));
//            System.out.println("this is from grooming activity"+getIntent().getStringExtra("alamat"));
//        }

        /**
         * Inisialisasi View
         */
        btnBackGrooming = findViewById(R.id.btn_back_grooming);
        btnLanjut = findViewById(R.id.btn_lanjut);
        tabLayout = findViewById(R.id.tabLayout_grooming);
        viewPager = findViewById(R.id.viewPager_grooming);
        tabLayout.addTab(tabLayout.newTab().setText("Antar Jemput"));
        tabLayout.addTab(tabLayout.newTab().setText("Datang ke Tempat"));

        /**
         * Penerapan transaksi fragment
         */
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        groomingFragmentAdapter = new GroomingFragmentAdapter(fragmentManager, getLifecycle());
        viewPager.setAdapter(groomingFragmentAdapter);

        /**
         * Penerapan button back
         */
        btnBackGrooming.setOnClickListener(v -> {
            finish();
        });

        // Initialize ViewModel using ViewModelProvider
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        sharedViewModel = new ViewModelProvider(this, factory).get(SharedViewModel.class);

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
            int previousPosition = viewPager.getCurrentItem();
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));

                FragmentStateAdapter adapter = (FragmentStateAdapter) viewPager.getAdapter();
                if (adapter != null) {
                    Fragment previousFragment = getSupportFragmentManager().findFragmentByTag("f" + previousPosition); // Finding the previous fragment by tag

                    if (previousFragment instanceof DatangFragment) {
                        DatangFragment yourFragment = (DatangFragment) previousFragment;
                        yourFragment.clearEditTextInputs();
                    }else if (previousFragment instanceof AntarJemputFragment){
                        AntarJemputFragment yourFragment = (AntarJemputFragment) previousFragment;
                        yourFragment.clearEditTextInputs();
                    }
                }
                previousPosition = position;
            }
        });



        /**
         * Penerapan button lanjut
         */
        btnLanjut.setOnClickListener(v -> {
            Intent intent = new Intent(Grooming.this, Grooming2.class);
            Bundle bundle = new Bundle();
            inputData = sharedViewModel.getUserInputData().getValue();
            if(tabLayout.getSelectedTabPosition() == 1){
                inputData.setAlamat(null);
            }
            System.out.println("this is from grooming activity"+inputData.getTanggalBooking());
            bundle.putParcelable("pesanan", Parcels.wrap(inputData));
            intent.putExtras(bundle);
            startActivity(intent);
        });

    }

    /**
     * This method is used to send data from the fragment to the activity
     * @param tanggal, waktu
     */
//    @Override
//    public void onDataPassDatang(String tanggal, String waktu) {
//        Intent intent = new Intent(Grooming.this, Grooming2.class);
//        intent.putExtra("tanggal", tanggal);
//        intent.putExtra("waktu", waktu);
//        btnLanjut.setOnClickListener(v -> {
//            startActivity(intent);
//        });
//    }

    /**
     * This method is used to send data from the fragment to the activity
     * @param tanggal, waktu, alamat
     */
//    @Override
//    public void onDataPassAntarJemput(String tanggal, String waktu, Alamat alamat) {
//        Intent intent = new Intent(Grooming.this, Grooming2.class);
//        intent.putExtra("tanggal", tanggal);
//        intent.putExtra("waktu", waktu);
//        intent.putExtra("alamat", Parcels.wrap(alamat));
//        btnLanjut.setOnClickListener(v -> {
//            startActivity(intent);
//        });
//    }
}