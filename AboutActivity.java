package com.example.zakattotalizer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button in the toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("About Us"); // Optional, if you want to set the title programmatically
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Handle the back button behavior
        finish(); // Closes the current activity and goes back to the previous one
        return true;
    }

    // Method to open GitHub link
    public void openGitHub(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/shahrul030119/ZakatTotalizer.git"));
        startActivity(browserIntent);
    }
}