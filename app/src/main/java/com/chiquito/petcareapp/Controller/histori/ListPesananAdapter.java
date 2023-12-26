package com.chiquito.petcareapp.Controller.histori;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chiquito.petcareapp.Model.JenisPesanan;
import com.chiquito.petcareapp.Model.Pesanan;
import com.chiquito.petcareapp.R;
import com.chiquito.petcareapp.StatusPesanan;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListPesananAdapter extends RecyclerView.Adapter<ListPesananAdapter.PesananViewHolder>{

    private List<Pesanan> orders = new ArrayList<>();


    public static class PesananViewHolder extends RecyclerView.ViewHolder{
        TextView tvStatusPesanan, tvHewanPesanan, tvTanggalBookingPesanan, tvWaktuBookingPesanan,
            tvTanggalBuatPesanan, tvEstimasiPesanan, tvKeteranganPesanan, tvJenisPesanan,
            tvAlamatPesanan, tvNamaPaketPesanan, tvHargaPesanan, tvTest;

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
        }
    }

    public void setOrders(List<Pesanan> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    @Override
    public ListPesananAdapter.PesananViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan, parent, false);
        return new ListPesananAdapter.PesananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListPesananAdapter.PesananViewHolder holder, int position) {
        Pesanan pesanan = orders.get(position);
        holder.tvStatusPesanan.setText(pesanan.getStatus().toString());

        if(pesanan.getJenisPesanan() == JenisPesanan.DATANG){
            holder.alamatPesanan.setVisibility(View.GONE);
        }

        if(pesanan.getStatus() == StatusPesanan.DITERIMA) {
            holder.tvStatusPesanan.setBackgroundResource(R.drawable.rounded_bg_yellow);
        } else if (pesanan.getStatus() == StatusPesanan.DIPROSES ) {
            holder.tvStatusPesanan.setBackgroundResource(R.drawable.rounded_bg_blue);
        }

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
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
