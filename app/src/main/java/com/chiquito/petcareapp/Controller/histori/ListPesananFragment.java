package com.chiquito.petcareapp.Controller.histori;

import android.content.Context;
import android.net.http.UrlRequest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chiquito.petcareapp.Controller.MainActivity;
import com.chiquito.petcareapp.Database;
import com.chiquito.petcareapp.Model.Alamat;
import com.chiquito.petcareapp.Model.Customer;
import com.chiquito.petcareapp.Model.Hewan;
import com.chiquito.petcareapp.Model.JenisPesanan;
import com.chiquito.petcareapp.Model.Paket;
import com.chiquito.petcareapp.Model.Pesanan;
import com.chiquito.petcareapp.R;
import com.chiquito.petcareapp.StatusPesanan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListPesananFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListPesananFragment extends Fragment {

    private HashMap<String, Pesanan> dataMap;
    private Database db;
    private RecyclerView recyclerView;
    private ListPesananAdapter listPesananAdapter;

    ValueEventListener valueEventListener;

    public static final int DITERIMA = 0;
    public static final int DIPROSES = 1;
    public static final int SELESAI = 2;

    public ListPesananFragment() {
        // Required empty public constructor
    }

    public static ListPesananFragment newInstance(int status) {
        ListPesananFragment fragment = new ListPesananFragment();
        Bundle args = new Bundle();
        args.putInt("status", status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_pesanan, container, false);

        db = new Database();

        recyclerView = view.findViewById(R.id.recycle_view_histori);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listPesananAdapter = new ListPesananAdapter();
        recyclerView.setAdapter(listPesananAdapter);

        int status = getArguments().getInt("status");
        loadOrders(status);

        return view;
    }


        private void loadOrders(int status) {
        List<Pesanan> orders = getOrdersBasdOnStatus(status);
        listPesananAdapter.setOrders(orders);
    }

//    private void loadOrdersHash(int status) {
//        HashMap<String, Pesanan> orders = getOrdersBasedOnStatusHash(status);
//
//        listPesananAdapter.setOrdersHash(orders);
//    }

//    private HashMap<String, Pesanan> getOrdersBasedOnStatusHash(int status) {
//        HashMap<String, Pesanan> orders = new HashMap<>();
//
//        db.setRef(db.getFirebaseDatabase().getReference());
//
//        if(db.getUserID() != null) {
//            // Check if the user is in the 'admin' node
//            db.getRef().child("Admin").child(db.getUserID()).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists()) {
//                        // User is an admin
////                        callback.onUserTypeCallback(false);
//                        db.setRef(db.getFirebaseDatabase().getReference().child("Pesanan"));
//
//                        valueEventListener = db.getRef().addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                orders.clear();
//                                for (DataSnapshot userSnapshot : snapshot.getChildren()){
//                                    String userID = userSnapshot.getKey();
//                                    DatabaseReference diterimaRef = db.getRef().child(userID).child("Diterima");
//                                    diterimaRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                                            for (DataSnapshot item : snapshot.getChildren()) {
//                                                Pesanan pesanan = getItem(item);
//                                                orders.put(String.valueOf(item.getKey()), pesanan);
//                                                System.out.println("this is the order " + orders.get(item.getKey()).getCustomer().getName());
//                                            }
//                                        }
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError error) {
//                                            System.out.println(error.getMessage());
//                                        }
//                                    });
//
////                                    loadOrdersHash(orders);
////                                    listPesananAdapter.notifyDataSetChanged();
//                                } System.out.println(orders.size());
//                            }
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                                System.out.println(error.getMessage());
//                            }
//                        });
//
//
//                    } else {
//                        // Check if the user is in the 'customer' node
//                        db.getRef().child("Customer").child(db.getUserID()).addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                if (dataSnapshot.exists()) {
//                                    // User is a customer
////                                    callback.onUserTypeCallback(true);
//                                    db.setRef(db.getFirebaseDatabase().getReference("Pesanan").child(db.getUserID()));
//                                    if (status == 0){
//                                        db.setRef(db.getRef().child("Diterima"));
//                                    } else if(status == 1){
//                                        db.setRef(db.getRef().child("Proses"));
//                                    } else if(status == 2){
//                                        db.setRef(db.getRef().child("Selesai"));
//                                    }
//
//                                    valueEventListener = db.getRef().addValueEventListener(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                            orders.clear();
//                                            System.out.println(snapshot.getRef());
//                                            for (DataSnapshot item : snapshot.getChildren()) {
//
//                                                Pesanan pesanan = getItem(item);
//
//                                                orders.put(String.valueOf(item.getKey()), pesanan);
//                                            }
//                                            listPesananAdapter.notifyDataSetChanged();
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError error) {
//                                            System.out.println(error.getMessage());
//                                        }
//                                    });
//                                }
//                            }
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//                                // Handle errors
//                                Log.d("TAG", databaseError.getMessage());
//                            }
//                        });
//                    }
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    // Handle errors
//                    Log.d("TAG", databaseError.getMessage());
//                }
//            });
//        }
//
//        return orders;
//    }


    private List<Pesanan> getOrdersBasdOnStatus(int status) {
        List<Pesanan> orders = new ArrayList<>();

        db.setRef(db.getFirebaseDatabase().getReference());

        if(db.getUserID() != null) {
            // Check if the user is in the 'admin' node
            db.getRef().child("Admin").child(db.getUserID()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // User is an admin
//                        callback.onUserTypeCallback(false);
                        db.setRef(db.getFirebaseDatabase().getReference().child("Pesanan"));


                        valueEventListener = db.getRef().addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                orders.clear();
                                for (DataSnapshot userSnapshot : snapshot.getChildren()){
                                    String userID = userSnapshot.getKey();
                                    System.out.println("this is the userID " + userID);

                                    db.setRef(db.getRef().child(userID));

                                    if (status == 0){
                                        db.setRef(db.getRef().child("Diterima"));
                                    } else if(status == 1){
                                        db.setRef(db.getRef().child("Proses"));
                                    } else if(status == 2){
                                        db.setRef(db.getRef().child("Selesai"));
                                    }

                                    db.getRef().addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                            for (DataSnapshot item : snapshot.getChildren()) {
                                                System.out.println("this is the ref " + item.getRef());
                                                Pesanan pesanan = getItem(item);

                                                orders.add(pesanan);
                                            }
                                            listPesananAdapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            System.out.println(error.getMessage());
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                System.out.println(error.getMessage());
                            }
                        });

                    } else {
                        // Check if the user is in the 'customer' node
                        db.getRef().child("Customer").child(db.getUserID()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    // User is a customer
//                                    callback.onUserTypeCallback(true);
                                    db.setRef(db.getFirebaseDatabase().getReference("Pesanan").child(db.getUserID()));
                                    if (status == 0){
                                        db.setRef(db.getRef().child("Diterima"));
                                    } else if(status == 1){
                                        db.setRef(db.getRef().child("Proses"));
                                    } else if(status == 2){
                                        db.setRef(db.getRef().child("Selesai"));
                                    }

                                    valueEventListener = db.getRef().addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            orders.clear();
                                            System.out.println(snapshot.getRef());
                                            for (DataSnapshot item : snapshot.getChildren()) {

                                                Pesanan pesanan = getItem(item);

                                                orders.add(pesanan);
                                            }
                                            listPesananAdapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            System.out.println(error.getMessage());
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
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle errors
                    Log.d("TAG", databaseError.getMessage());
                }
            });
        }

        return orders;
    }

    public Pesanan getItem(DataSnapshot item){
        String namaPemesan = item.child("customer").child("name").getValue(String.class);
        String emailPemesan = item.child("customer").child("email").getValue(String.class);
        String passwordPemesan = item.child("customer").child("password").getValue(String.class);
        String noWaPemesan = item.child("customer").child("noWA").getValue(String.class);
        String uidPemesan = item.child("customer").child("uid").getValue(String.class);
        Customer pemesan = new Customer(namaPemesan, emailPemesan, passwordPemesan, noWaPemesan, uidPemesan);

        String tanggalBooking = item.child("tanggalBooking").getValue(String.class);
        String waktuBooking = item.child("waktuBooking").getValue(String.class);
        String kondisiHewan = item.child("kondisiHewan").getValue(String.class);
        String tanggalBuat = item.child("tanggalBuat").getValue(String.class);
        int harga = item.child("harga").getValue(int.class);
        String estimasi = item.child("estimasi").getValue(String.class);

        String tagAlamat = item.child("alamat").child("tag").getValue(String.class);
        String alamatLengkap = item.child("alamat").child("alamatLengkap").getValue(String.class);
        Alamat alamat = new Alamat(tagAlamat, alamatLengkap);

        String namaHewan = item.child("hewan").child("namaHewan").getValue(String.class);
        String warnaHewan = item.child("hewan").child("warnaHewan").getValue(String.class);
        String keterangan = item.child("hewan").child("keterangan").getValue(String.class);
        String ras = item.child("hewan").child("ras").getValue(String.class);
        String urlImage = item.child("hewan").child("urlImage").getValue(String.class);
        String tanggalLahir = item.child("hewan").child("tanggalLahir").getValue(String.class);
        Hewan.Spesies spesies = Hewan.Spesies.valueOf(item.child("hewan").
                child("spesies").getValue(String.class));
        Hewan.JenisKelamin jenisKelamin = Hewan.JenisKelamin.valueOf(item.child("hewan").
                child("jenisKelamin").getValue(String.class));
        Hewan hewan = new Hewan(namaHewan, warnaHewan, keterangan, ras, jenisKelamin,
                spesies, tanggalLahir, urlImage);

        StatusPesanan statusPesanan = StatusPesanan.valueOf(item.child("status").getValue(String.class));

        String namaPaket = item.child("paket").child("namaPaket").getValue(String.class);
        int hargaPaket = item.child("paket").child("hargaPaket").getValue(int.class);
        Paket paket = new Paket(namaPaket, hargaPaket);

        JenisPesanan jenisPesanan = JenisPesanan.valueOf(item.child("jenisPesanan").getValue(String.class));

        Pesanan pesanan = new Pesanan(tanggalBooking, waktuBooking, kondisiHewan, tanggalBuat,
                estimasi, harga, alamat, hewan, statusPesanan, paket, jenisPesanan, pemesan);

        return pesanan;
    }
}