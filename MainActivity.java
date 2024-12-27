package com.example.zakattotalizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private EditText etGoldWeight, etGoldValue;
    private RadioGroup rgGoldType;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.blue));  // Set blue background color


        // Initialize UI components
        etGoldWeight = findViewById(R.id.etGoldWeight);
        etGoldValue = findViewById(R.id.etGoldValue);
        rgGoldType = findViewById(R.id.rgGoldType);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        // Set OnClickListener for Calculate Button
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateZakat();
            }
        });
    }

    private void calculateZakat() {
        try {
            // Get input values
            double goldWeight = Double.parseDouble(etGoldWeight.getText().toString());
            double goldValuePerGram = Double.parseDouble(etGoldValue.getText().toString());

            // Determine type of gold (keep = 85g, wear = 200g)
            int selectedType = rgGoldType.getCheckedRadioButtonId();
            double threshold = selectedType == R.id.rbKeep ? 85.0 : 200.0;

            // Calculate total value of gold
            double totalGoldValue = goldWeight * goldValuePerGram;

            // Calculate gold value that is zakat payable
            double zakatPayableGoldValue = (goldWeight > threshold) ? (goldWeight - threshold) * goldValuePerGram : 0.0;

            // Calculate total zakat (2.5%)
            double totalZakat = zakatPayableGoldValue * 0.025;

            // Display results
            String result = "Total Gold Value: RM " + String.format("%.2f", totalGoldValue) + "\n" +
                    "Zakat Payable Gold Value: RM " + String.format("%.2f", zakatPayableGoldValue) + "\n" +
                    "Total Zakat: RM " + String.format("%.2f", totalZakat);

            tvResult.setText(result);
            tvResult.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid inputs.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            // Open About Us activity
            Intent aboutIntent = new Intent(this, AboutActivity.class);  // Ensure AboutUsActivity is correctly defined
            startActivity(aboutIntent);
            return true;
        } else if (id == R.id.action_share) {
            // Share app link
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareMessage = "Check out this Zakat Totalizer app!\n\nGitHub Repository: https://github.com/shahrul030119/ZakatTotalizer.git";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}