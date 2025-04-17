package com.example.aninterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private final Button buttonOffline = findViewById(R.id.offlineButton);
    private final Button buttonOnline = findViewById(R.id.onlineButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonOffline.setOnClickListener(view -> {
            Intent intention = new Intent(MainActivity.this, OfflineActivity.class);
            startActivity(intention);
        });
        buttonOnline.setOnClickListener(view -> {
            Intent intention = new Intent(MainActivity.this, OnlineActivity.class);
            startActivity(intention);
        });
    }

}