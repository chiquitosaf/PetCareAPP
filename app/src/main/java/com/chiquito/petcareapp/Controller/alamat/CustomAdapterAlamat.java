package com.chiquito.petcareapp.Controller.alamat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chiquito.petcareapp.Model.Alamat;
import com.chiquito.petcareapp.R;

import java.util.List;

public class CustomAdapterAlamat extends RecyclerView.Adapter<CustomViewHolderAlamat> {
    private Context context;

    private List<Alamat> list;
    private SelectListenerAlamat listenerAlamat;
    public CustomAdapterAlamat(Context context, List<Alamat> list, SelectListenerAlamat listenerAlamat) {
        this.context = context;
        this.list = list;
        this.listenerAlamat = listenerAlamat;
    }

    @NonNull
    @Override
    public CustomViewHolderAlamat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolderAlamat(LayoutInflater.from(context).inflate(R.layout.item_alamat,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolderAlamat holder, @SuppressLint("RecyclerView") int position) {
        holder.tag.setText(list.get(position).getTag());
//        holder.alamat.setText(list.get(position).getAlamatLengkap());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerAlamat.onItemClicked(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
