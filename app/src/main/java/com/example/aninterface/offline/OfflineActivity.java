package com.example.aninterface.offline;

import android.annotation.SuppressLint;
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
    DrawingOffline drawingOfflineView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offline_activity);

        drawingOfflineView = findViewById(R.id.drawingViewOffline);

        backButton = findViewById(R.id.getBackOfflineButton);
        colorButton = findViewById(R.id.button_color_offline);
        widthButton = findViewById(R.id.button_width_offline);
        fogButton = findViewById(R.id.button_fog_offline);
        shapeButton = findViewById(R.id.button_shape_offline);





        colorButton.setOnClickListener(view -> {
            ColorDialogOffline colorDialog = new ColorDialogOffline(this, drawingOfflineView);
            colorDialog.show();
        });

        widthButton.setOnClickListener(view -> {
            WidthDialogOffline widthDialogOffline = new WidthDialogOffline(this, drawingOfflineView);
            widthDialogOffline.show();
        });

        fogButton.setOnClickListener(view -> {
            FogDialogOffline fogDialogOffline = new FogDialogOffline(this, drawingOfflineView);
            fogDialogOffline.show();
        });

        shapeButton.setOnClickListener(view -> {
            ShapeDialogOffline shapeDialogOffline = new ShapeDialogOffline(this, drawingOfflineView);
            shapeDialogOffline.show();
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
