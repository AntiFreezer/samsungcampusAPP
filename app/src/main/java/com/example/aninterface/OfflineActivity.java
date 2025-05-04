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

public class OfflineActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.offline_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button backButton = findViewById(R.id.getBackOfflineButton);
        Button colorButton = findViewById(R.id.button_color);
        Button widthButton = findViewById(R.id.button_width);
        Button fogButton = findViewById(R.id.button_fog);
        Button shapeButton = findViewById(R.id.button_shape);


        colorButton.setOnClickListener(view -> {
            ColorDialog colorDialog = new ColorDialog(OfflineActivity.this);
            colorDialog.show();
        });

        widthButton.setOnClickListener(view -> {
            WidthDialog widthDialog = new WidthDialog(OfflineActivity.this);
            widthDialog.show();
        });

        fogButton.setOnClickListener(view -> {
            FogDialog fogDialog = new FogDialog(OfflineActivity.this);
            fogDialog.show();
        });

        shapeButton.setOnClickListener(view -> {
            ShapeDialog shapeDialog = new ShapeDialog(OfflineActivity.this);
            shapeDialog.show();
        });

        backButton.setOnClickListener(view -> {
            Intent intention = new Intent(OfflineActivity.this, MainActivity.class);
            startActivity(intention);
        });
    }
}