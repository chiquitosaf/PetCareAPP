package com.chiquito.petcareapp.hewan;

import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.chiquito.petcareapp.R;

public class CustomViewHolderHewan extends RecyclerView.ViewHolder {
    public TextView nama, spesies, ras;
    public CardView cardView;

    public CustomViewHolderHewan(@NonNull View itemView) {
        super(itemView);
        nama = itemView.findViewById(R.id.nama_hewan);
        spesies = itemView.findViewById(R.id.spesies_hewan);
        ras = itemView.findViewById(R.id.ras_hewan);
        cardView = itemView.findViewById(R.id.main_container_hewan);
    }
}
