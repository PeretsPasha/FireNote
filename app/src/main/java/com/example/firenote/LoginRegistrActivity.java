package com.example.firenote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginRegistrActivity extends AppCompatActivity {

    int AUTH_REQUEST_CODE = 10001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registr);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }
    }

    public void handleLoginRegister(View view) {
        List<AuthUI.IdpConfig> provider = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build()
        );

        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(provider)
                .setTosAndPrivacyPolicyUrls("https://example.com", "https://example.com")
                .setLogo(R.drawable.note)
                .setAlwaysShowSignInMethodScreen(true)
                .build();

        startActivityForResult(intent, AUTH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTH_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();
                if (user.getMetadata().getCreationTimestamp() == user.getMetadata().getLastSignInTimestamp()) {
                    Toast.makeText(this, "Новий користувач", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "З поверненням)", Toast.LENGTH_SHORT).show();
                }

                startActivity(new Intent(this, MainActivity.class));
                this.finish();

            } else {
                IdpResponse response = IdpResponse.fromResultIntent(data);
                if (response == null) {
                    Toast.makeText(this, "Користувач відмінив вхід", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Сталась помилка", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}