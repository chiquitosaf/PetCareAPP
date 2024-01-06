package com.chiquito.petcareapp.Controller.histori;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import android.os.Bundle;
import android.util.Log;

import com.chiquito.petcareapp.Controller.histori.ListPesananFragment;
import com.chiquito.petcareapp.Database;
import com.chiquito.petcareapp.Model.Pesanan;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

@RunWith(BlockJUnit4ClassRunner.class)
@PrepareForTest(FirebaseDatabase.class)
public class ListPesananFragmentTest extends ListPesananFragment{

    @Mock
    Database mockedDatabase;
    @Mock
    FirebaseDatabase mockedFirebaseDatabase;
    @Mock
    DatabaseReference mockedDatabaseReference;


    ListPesananFragment listPesananFragment;

    @Before
    public void setUp() {
        listPesananFragment = new ListPesananFragment();
        mockedDatabase = Mockito.mock(Database.class);
    }

    @Test
    public void testSetRefWithoutNullPointerException() {
        // buat lispesananfragment
        ListPesananFragment listPesananFragment = new ListPesananFragment();
        mockedDatabase = Mockito.mock(Database.class);
        mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
        mockedDatabaseReference = Mockito.mock(DatabaseReference.class);

        when(mockedDatabase.getFirebaseDatabase()).thenReturn(mockedFirebaseDatabase);
        when(mockedFirebaseDatabase.getReference()).thenReturn(mockedDatabaseReference);
        when(mockedDatabase.getUserID()).thenReturn(null);


        listPesananFragment.db = mockedDatabase;


        List<Pesanan> result = listPesananFragment.getOrdersBasedOnStatus(0);
        assertEquals(0, result.size());
    }
}
