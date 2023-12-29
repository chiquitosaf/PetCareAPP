package com.chiquito.petcareapp.Controller.histori;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.chiquito.petcareapp.Controller.MainActivity;
import com.chiquito.petcareapp.Database;
import com.chiquito.petcareapp.Model.JenisPesanan;
import com.chiquito.petcareapp.Model.Pesanan;
import com.chiquito.petcareapp.R;
import com.chiquito.petcareapp.StatusPesanan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ListPesananAdapter extends RecyclerView.Adapter<ListPesananAdapter.PesananViewHolder>{

    private List<Pesanan> orders = new ArrayList<>();
//    private ArrayList<String> keys; // ArrayList to hold keys from HashMap
//    private HashMap<String, Pesanan> dataMap;
    private Context context;

    public ListPesananAdapter() {

    }


    public static class PesananViewHolder extends RecyclerView.ViewHolder{
        TextView tvStatusPesanan, tvHewanPesanan, tvTanggalBookingPesanan, tvWaktuBookingPesanan,
            tvTanggalBuatPesanan, tvEstimasiPesanan, tvKeteranganPesanan, tvJenisPesanan,
            tvAlamatPesanan, tvNamaPaketPesanan, tvHargaPesanan, tvPemesanPesanan, tvWAPesanan;
        Button btnUbahStatus;
        LinearLayout linearDataPemesan;
        RelativeLayout alamatPesanan;

        public PesananViewHolder(View itemView) {
            super(itemView);
            tvNamaPaketPesanan = itemView.findViewById(R.id.tv_nama_paket_pesanan);
            tvHargaPesanan = itemView.findViewById(R.id.tv_harga_pesanan);
            tvStatusPesanan = itemView.findViewById(R.id.tv_status_pesanan);
            tvHewanPesanan = itemView.findViewById(R.id.tv_hewan_pesanan);
            tvTanggalBookingPesanan = itemView.findViewById(R.id.tv_tanggal_booking_pesanan);
            tvWaktuBookingPesanan = itemView.findViewById(R.id.tv_waktu_booking_pesanan);
            tvTanggalBuatPesanan = itemView.findViewById(R.id.tv_tanggal_buat_pesanan);
            tvEstimasiPesanan = itemView.findViewById(R.id.tv_estimasi_pesanan);
            tvKeteranganPesanan = itemView.findViewById(R.id.tv_keterangan_pesanan);
            tvJenisPesanan = itemView.findViewById(R.id.tv_jenis_pesanan);
            tvAlamatPesanan = itemView.findViewById(R.id.tv_alamat_pesanan);
            alamatPesanan = itemView.findViewById(R.id.alamat_pesanan);
            tvPemesanPesanan = itemView.findViewById(R.id.tv_pemesan_pesanan);
            tvWAPesanan = itemView.findViewById(R.id.tv_wa_pesanan);
            btnUbahStatus = itemView.findViewById(R.id.btn_ubah_status);
            linearDataPemesan = itemView.findViewById(R.id.data_pemesan_pesanan);
        }
    }

    public void setOrders(List<Pesanan> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

//    public void setOrdersHash(HashMap<String, Pesanan> orders) {
//        this.dataMap = orders;
//        this.keys = new ArrayList<>(dataMap.keySet()); // Store keys from HashMap in ArrayList
//        notifyDataSetChanged();
//    }

    @Override
    public ListPesananAdapter.PesananViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan, parent, false);
        return new ListPesananAdapter.PesananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListPesananAdapter.PesananViewHolder holder, int position) {
        Pesanan pesanan = orders.get(position);
//        String key = keys.get(position);
//        System.out.println("Key: " + key);
//        Pesanan pesanan = dataMap.get(key);

        holder.tvStatusPesanan.setText(pesanan.getStatus().toString());

        if(pesanan.getJenisPesanan() == JenisPesanan.DATANG){
            holder.alamatPesanan.setVisibility(View.GONE);
        }

        if(pesanan.getStatus() == StatusPesanan.DITERIMA) {
            holder.tvStatusPesanan.setBackgroundResource(R.drawable.rounded_bg_yellow);
        } else if (pesanan.getStatus() == StatusPesanan.DIPROSES ) {
            holder.tvStatusPesanan.setBackgroundResource(R.drawable.rounded_bg_blue);
            holder.btnUbahStatus.setText("Selesai");
        } else if (pesanan.getStatus() == StatusPesanan.SELESAI){
            holder.btnUbahStatus.setVisibility(View.GONE);
        }

        Database db = new Database();
        db.setRef(db.getFirebaseDatabase().getReference());

        db.getRef().child("Admin").child(db.getUserID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User is an admin
//                        callback.onUserTypeCallback(false);
                    holder.tvPemesanPesanan.setText(pesanan.getCustomer().getName());
                    holder.tvWAPesanan.setText(pesanan.getCustomer().getNoWA());
                } else {
                    // Check if the user is in the 'customer' node
                    db.getRef().child("Customer").child(db.getUserID()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // User is a customer
//                                    callback.onUserTypeCallback(true);
                                holder.linearDataPemesan.setVisibility(View.GONE);
                                holder.btnUbahStatus.setVisibility(View.GONE);

                            } else {
                                // User is neither admin nor customer
                                System.out.println("User not found ListPesananAdapter");
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Handle errors
                            Log.d("TAG", databaseError.getMessage());
                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Log.d("TAG", databaseError.getMessage());
            }
        });

        holder.tvHewanPesanan.setText(pesanan.getHewan().getNamaHewan());
        holder.tvTanggalBookingPesanan.setText(pesanan.getTanggalBooking());
        holder.tvWaktuBookingPesanan.setText(pesanan.getWaktuBooking());
        holder.tvTanggalBuatPesanan.setText(pesanan.getTanggalBuat());
        holder.tvEstimasiPesanan.setText(pesanan.getWaktuBooking());
        holder.tvKeteranganPesanan.setText(pesanan.getKondisiHewan());
        holder.tvJenisPesanan.setText(pesanan.getPaket().getNamaPaket());
        if(pesanan.getJenisPesanan() == JenisPesanan.ANTAR_JEMPUT){
            holder.tvAlamatPesanan.setText(pesanan.getAlamat().getAlamatLengkap());
        }
        holder.tvNamaPaketPesanan.setText(pesanan.getPaket().getNamaPaket());
        String tampilanTotalHarga = "Rp " + String.format("%,d", pesanan.getHarga());;
        holder.tvHargaPesanan.setText(tampilanTotalHarga);

        holder.btnUbahStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pesanan.getStatus() == StatusPesanan.DITERIMA){
                    pesanan.setStatus(StatusPesanan.DIPROSES);
                    db.getRef().child("Pesanan").child("Diterima")
                            .child(pesanan.getCustomer().getName()+pesanan.getTanggalBuat())
                            .child("status").setValue(StatusPesanan.DIPROSES);

                    db.getRef().child("Pesanan").child("Proses")
                            .child(pesanan.getCustomer().getName()+pesanan.getTanggalBuat())
                            .setValue(pesanan);

                    db.getRef().child("Pesanan").child("Diterima")
                            .child(pesanan.getCustomer().getName()+pesanan.getTanggalBuat())
                            .removeValue();

                    Toast.makeText(v.getContext(), "Pesanan telah diterima", Toast.LENGTH_SHORT).show();
                } else if(pesanan.getStatus() == StatusPesanan.DIPROSES){
                    pesanan.setStatus(StatusPesanan.SELESAI);
                    db.getRef().child("Pesanan").child("Proses")
                            .child(pesanan.getCustomer().getName()+pesanan.getTanggalBuat())
                            .child("status").setValue(StatusPesanan.SELESAI);

                    db.getRef().child("Pesanan").child("Selesai")
                            .child(pesanan.getCustomer().getName()+pesanan.getTanggalBuat()).setValue(pesanan);

                    db.getRef().child("Pesanan").child("Proses")
                            .child(pesanan.getCustomer().getName()+pesanan.getTanggalBuat())
                            .removeValue();

                    Toast.makeText(v.getContext(), "Pesanan telah selesai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
