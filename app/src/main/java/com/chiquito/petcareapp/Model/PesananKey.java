package com.chiquito.petcareapp.Model;

import java.util.HashMap;

public class PesananKey {

    private static HashMap<Pesanan, String> pesananKey = new HashMap<>();

    public static void addPesananKey(Pesanan pesanan, String key) {
        pesananKey.put(pesanan, key);
    }

    public static String getPesananKey(Pesanan pesanan) {
        return pesananKey.get(pesanan);
    }
}
