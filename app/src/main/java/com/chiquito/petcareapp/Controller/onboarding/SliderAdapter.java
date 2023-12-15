package com.chiquito.petcareapp.Controller.onboarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.chiquito.petcareapp.R;

//adapter used to control the view showed in onboarding pages
public class SliderAdapter extends PagerAdapter {

    Context context;

    LayoutInflater layoutInflater;
    public SliderAdapter(Context context) {
        this.context = context;
    }

    //images for each page stored in an array
    int imagesArray[] = {
            R.drawable.image_onboarding_first,
            R.drawable.image_onboarding_second,
            R.drawable.image_onboarding_third,
            R.drawable.image_onboarding_fourth
    };

    //heading (string) for each page stored in an array
    int headingsArray[] = {
        R.string.heading_onboarding_first,
        R.string.heading_onboarding_second,
        R.string.heading_onboarding_third,
        R.string.heading_onboarding_fourth
    };

    //description (string) for each page stored in an array
    int descriptionArray[] = {
        R.string.description_onboarding_first,
        R.string.description_onboarding_second,
        R.string.description_onboarding_third,
        R.string.description_onboarding_fourth
    };

    @Override
    public int getCount() {
        return headingsArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    //to instantiate all the item (view) necessary for each page
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sliding_layout_onboarding,container, false);

        ImageView imageView= view.findViewById(R.id.image_onboarding);
        TextView heading = view.findViewById(R.id.heading_onboarding);
        TextView description = view.findViewById(R.id.description_onboarding);

        imageView.setImageResource(imagesArray[position]);
        heading.setText(headingsArray[position]);
        description.setText(descriptionArray[position]);

        container.addView(view);

        return view;
    }

    //to destroy unused page
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
