package com.chiquito.petcareapp.Controller.auth;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
public class LoginUnitTest {

    private Login loginActivity;

    @Mock
    private FirebaseAuth mockAuth;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginActivity = new Login();
        loginActivity.auth = mockAuth;
    }

    @Test
    public void testLoginUserSuccess() {
        when(mockAuth.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(mock(Task.class));
        when(mockAuth.signInWithEmailAndPassword(anyString(), anyString()).addOnCompleteListener(any())).thenAnswer(invocation -> {
            ((OnCompleteListener<AuthResult>) invocation.getArgument(0)).onComplete(mock(Task.class));
            return null;
        });

        loginActivity.email.setText("test@example.com");
        loginActivity.password.setText("password");

        loginActivity.loginUser();

        verify(mockAuth).signInWithEmailAndPassword("test@example.com", "password");
        // Add more verifications based on expected behavior
    }

    @Test
    public void testLoginUserEmptyFields() {
        loginActivity.email.setText("");
        loginActivity.password.setText("");

        loginActivity.loginUser();

        // Verify that Firebase method was not called due to empty fields
        verify(mockAuth, never()).signInWithEmailAndPassword(anyString(), anyString());
        // Add more verifications based on expected behavior for empty fields
    }
}
