package com.example.aninterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OfflineActivity extends AppCompatActivity {
    Button backButton;
    Button colorButton;
    Button widthButton;
    Button fogButton;
    Button shapeButton;
    private SurfaceView surfaceView;



    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offline_activity);


        backButton = findViewById(R.id.getBackOfflineButton);
        colorButton = findViewById(R.id.button_color);
        widthButton = findViewById(R.id.button_width);
        fogButton = findViewById(R.id.button_fog);
        shapeButton = findViewById(R.id.button_shape);
        surfaceView = findViewById(R.id.drawing2);

        surfaceView.getHolder().addCallback((SurfaceHolder.Callback) this);

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