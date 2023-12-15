package com.chiquito.petcareapp.Controller.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.chiquito.petcareapp.Controller.Home;
import com.chiquito.petcareapp.Controller.MainActivity;
import com.chiquito.petcareapp.Controller.auth.Login;
import com.chiquito.petcareapp.R;
import com.chiquito.petcareapp.Controller.auth.Register;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

//main onboarding activity that holds the default views
public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    SliderAdapter sliderAdapter;
    Button btnDaftar, btnNext, btnSkip;
    TextView textSudah, textLoginDisini, textHeadingLast;
    Animation animationAppear, animationDisappear;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();

//        if(firebaseAuth.getCurrentUser() != null){
//            startActivity(new Intent(OnBoarding.this, MainActivity.class));
//            finish();
//        }
        //hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_onboarding);

        //initiate first page position
        int position = 0;

        //check if there is any position passed from auth activity
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            position = extras.getInt("viewpager_position");
        }

        //assigning each view to their variable
        viewPager = findViewById(R.id.viewpager_onboarding);
        btnDaftar = findViewById(R.id.btn_daftar);
        btnNext = findViewById(R.id.btn_next);
        btnSkip = findViewById(R.id.btn_skip);
        textSudah = findViewById(R.id.txt_sudah_punya_akun);
        textLoginDisini = findViewById(R.id.txt_login_disini);

        viewPager.addOnPageChangeListener(changeListener);

        //Call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        //when the skip button clicked, display the last onboarding page
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3, false);
            }
        });

        //when the next button clicked, move to the next onboarding page
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoarding.this, Register.class));
            }
        });

        //when the "login disini" text clicked, open login activity
        textLoginDisini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoarding.this, Login.class));
                finish();
            }
        });

        //to set the position of onboarding page that will be displayed
        viewPager.setCurrentItem(position);
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //logic for the visibility of buttons and texts in onboarding page
        @Override
        public void onPageSelected(int position) {
            animationAppear = AnimationUtils.loadAnimation(OnBoarding.this,R.anim.fade_animation_appear);
            animationDisappear = AnimationUtils.loadAnimation(OnBoarding.this,R.anim.fade_animation_disappear);
            if(position == 3){
                btnDaftar.setAnimation(animationAppear);
                textSudah.setAnimation(animationAppear);
                textLoginDisini.setAnimation(animationAppear);
                btnNext.setAnimation(animationDisappear);
                btnSkip.setAnimation(animationDisappear);

                btnDaftar.setClickable(true);
                textLoginDisini.setClickable(true);
                btnDaftar.setVisibility(View.VISIBLE);
                textSudah.setVisibility(View.VISIBLE);
                textLoginDisini.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.INVISIBLE);
                btnSkip.setVisibility(View.INVISIBLE);

            }else{

                btnDaftar.setAnimation(animationDisappear);
                textSudah.setAnimation(animationDisappear);
                textLoginDisini.setAnimation(animationDisappear);
                btnNext.setAnimation(animationAppear);
                btnSkip.setAnimation(animationAppear);

                btnDaftar.setClickable(false);
                textLoginDisini.setClickable(false);
                btnDaftar.setVisibility(View.INVISIBLE);
                textSudah.setVisibility(View.INVISIBLE);
                textLoginDisini.setVisibility(View.INVISIBLE);
                btnNext.setVisibility(View.VISIBLE);
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
