package com.example.aninterface;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

public class OfflineActivity extends AppCompatActivity {
    private final Button settingsButton = findViewById(R.id.settings);
    private final Button backButton = findViewById(R.id.getBackOfflineButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_offline);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        backButton.setOnClickListener(view -> {
            Intent intention = new Intent(OfflineActivity.this, MainActivity.class);
            startActivity(intention);
        });

        settingsButton.setOnClickListener(view -> {
            PaletteFragment pf = new PaletteFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, pf);
            ft.commit();
        });
    }
}