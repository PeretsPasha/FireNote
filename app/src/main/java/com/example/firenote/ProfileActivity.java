package com.example.firenote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    CircleImageView circleImageView;
    TextInputEditText textInputEditText;
    Button updateProfileButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        circleImageView = findViewById(R.id.profileImageView);
        textInputEditText = findViewById(R.id.displayNameEditText);
        updateProfileButton = findViewById(R.id.updateProfileButton);
        progressBar = findViewById(R.id.progressBar);
    }

    public void updateProfile(View view) {
    }

    public void handleImageClick(View view) {
    }
}