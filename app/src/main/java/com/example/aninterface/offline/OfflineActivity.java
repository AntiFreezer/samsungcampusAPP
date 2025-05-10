package com.example.aninterface.offline;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aninterface.MainActivity;
import com.example.aninterface.R;

public class OfflineActivity extends AppCompatActivity {
    Button backButton;
    Button colorButton;
    Button widthButton;
    Button fogButton;
    Button shapeButton;
    Drawing drawingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offline_activity);

        drawingView = findViewById(R.id.drawingViewOffline);

        backButton = findViewById(R.id.getBackOfflineButton);
        colorButton = findViewById(R.id.button_color_offline);
        widthButton = findViewById(R.id.button_width_offline);
        fogButton = findViewById(R.id.button_fog_offline);
        shapeButton = findViewById(R.id.button_shape_offline);



        colorButton.setOnClickListener(view -> {
            ColorDialog colorDialog = new ColorDialog(this, drawingView);
            colorDialog.show();
        });

        widthButton.setOnClickListener(view -> {
            WidthDialog widthDialog = new WidthDialog(this, drawingView);
            widthDialog.show();
        });

        fogButton.setOnClickListener(view -> {
            FogDialog fogDialog = new FogDialog(this, drawingView);
            fogDialog.show();
        });

        shapeButton.setOnClickListener(view -> {
            ShapeDialog shapeDialog = new ShapeDialog(this, drawingView);
            shapeDialog.show();
        });

        backButton.setOnClickListener(view -> {
            Intent intention = new Intent(this, MainActivity.class);
            startActivity(intention);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
