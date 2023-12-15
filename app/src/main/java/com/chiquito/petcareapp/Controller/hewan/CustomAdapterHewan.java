package com.chiquito.petcareapp.Controller.hewan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chiquito.petcareapp.Model.Hewan;
import com.chiquito.petcareapp.R;

import java.util.List;

public class CustomAdapterHewan extends RecyclerView.Adapter<CustomViewHolderHewan> {
    private Context context;
    private List<Hewan> list;
    private SelectListenerHewan listenerHewan;

    public CustomAdapterHewan(Context context, List<Hewan> list, SelectListenerHewan listenerHewan) {
        this.context = context;
        this.list = list;
        this.listenerHewan = listenerHewan;
    }

    @NonNull
    @Override
    public CustomViewHolderHewan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolderHewan(LayoutInflater.from(context).inflate(R.layout.item_hewan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolderHewan holder, int position) {
        holder.nama.setText(list.get(position).getNamaHewan());
        holder.spesies.setText(list.get(position).getSpesies().toString());
        holder.ras.setText(list.get(position).getRas());
        holder.cardView.setOnClickListener(v -> listenerHewan.onItemClicked(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
