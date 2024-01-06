package com.chiquito.petcareapp.Controller.hewan;

import android.net.Uri;

import com.chiquito.petcareapp.Controller.hewan.AddHewan;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class AddHewanTest {

    private AddHewan addHewan;

    @Mock
    private StorageReference mockStorageReference;

    @Mock
    private UploadTask mockUploadTask;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        addHewan = new AddHewan();
        addHewan.storageReference = mockStorageReference;
    }

    @Test
    public void testUploadImageToStorageWithNullUri() {
        // Simulate a null Uri passed to uploadImageToStorage
        addHewan.uploadImageToStorage(null, null, null);

        // Verify that Firebase StorageReference methods were not called with null parameter
        verify(mockStorageReference, never()).child(anyString());
        verify(mockStorageReference, never()).putFile(any(Uri.class));

        // Ensure other relevant verifications based on the expected behavior with null parameter
    }
}