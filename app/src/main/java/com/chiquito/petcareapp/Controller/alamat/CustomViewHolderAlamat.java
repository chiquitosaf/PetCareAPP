package com.chiquito.petcareapp.Controller.alamat;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.chiquito.petcareapp.R;

public class  CustomViewHolderAlamat extends RecyclerView.ViewHolder {

    public TextView tag,alamat;
    public CardView cardView;
    public CustomViewHolderAlamat(@NonNull View itemView) {
        super(itemView);
        tag = itemView.findViewById(R.id.tag_alamat);
//        alamat = itemView.findViewById(R.id.alamat_lengkap);
        cardView = itemView.findViewById(R.id.main_container);
    }
}
