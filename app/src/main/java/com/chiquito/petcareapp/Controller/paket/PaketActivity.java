package com.chiquito.petcareapp.Controller.paket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chiquito.petcareapp.Controller.alamat.CustomViewHolderAlamat;
import com.chiquito.petcareapp.Controller.alamat.SelectListenerAlamat;
import com.chiquito.petcareapp.Database;
import com.chiquito.petcareapp.Model.Alamat;
import com.chiquito.petcareapp.Model.Paket;
import com.chiquito.petcareapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class PaketActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Paket> paketList;
    Database db;
    ValueEventListener eventListener;
    CustomAdapterPaket adapterPaket;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket);

        /**
         * Inisialisasi View
         */
        recyclerView = findViewById(R.id.recycle_view_paket);
        btnBack = findViewById(R.id.btn_back_paket);

        /** Inisialisasi List */
        paketList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,
                false));
        adapterPaket = new CustomAdapterPaket(this, paketList, new SelectListenerPaket() {
            @Override
            public void onItemClicked(Paket paket) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable("paket", Parcels.wrap(paket));
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        recyclerView.setAdapter(adapterPaket);

        /** Inisialisasi database */
        db = new Database();
        db.setRef(db.getFirebaseDatabase().getReference("Paket"));

        /** Mengambil data dari database */
        eventListener = db.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                paketList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String namaPaket = dataSnapshot.child("nama").getValue(String.class);
                    int hargaPaket = dataSnapshot.child("harga").getValue(Integer.class);
                    Paket paket = new Paket(namaPaket, hargaPaket);
                    paketList.add(paket);
                }
                adapterPaket.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PaketActivity.this, "Opsss.... Something is wrong",
                        Toast.LENGTH_SHORT).show();
            }
        });

        /** Penerapan button back */
        btnBack.setOnClickListener(v -> finish());

    }

    public class CustomAdapterPaket extends RecyclerView.Adapter<CustomViewHolderPaket> {
        private Context context;

        private List<Paket> list;
        private SelectListenerPaket listenerPaket;

        public CustomAdapterPaket(Context context, List<Paket> list, SelectListenerPaket listenerPaket) {
            this.context = context;
            this.list = list;
            this.listenerPaket = listenerPaket;
        }

        @NonNull
        @Override
        public CustomViewHolderPaket onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CustomViewHolderPaket(LayoutInflater.from(context).inflate(R.layout.item_paket,parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolderPaket holder, @SuppressLint("RecyclerView") int position) {
            holder.namaPaket.setText(list.get(position).getNamaPaket());
            holder.hargaPaket.setText(String.valueOf(list.get(position).getHargaPaket()));
            holder.paketLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerPaket.onItemClicked(list.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class  CustomViewHolderPaket extends RecyclerView.ViewHolder {

        public TextView namaPaket,hargaPaket;
        public ConstraintLayout paketLayout;
        public CustomViewHolderPaket(@NonNull View itemView) {
            super(itemView);
            namaPaket = itemView.findViewById(R.id.nama_paket);
            hargaPaket = itemView.findViewById(R.id.harga_paket);
            paketLayout = itemView.findViewById(R.id.paket_container);
        }
    }

    public interface SelectListenerPaket {
        public void onItemClicked(Paket paket);
    }
}