package com.example.aninterface.online;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aninterface.MainActivity;
import com.example.aninterface.R;
import com.example.aninterface.offline.ColorDialogOffline;
import com.example.aninterface.offline.DrawingOffline;
import com.example.aninterface.offline.FogDialogOffline;
import com.example.aninterface.offline.ShapeDialogOffline;
import com.example.aninterface.offline.WidthDialogOffline;

public class OnlineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_activity);

        DrawingOnline drawingOnlineView = findViewById(R.id.drawingViewOnline);

        Button backButton = findViewById(R.id.getBackOnlineButton);
        Button colorButton = findViewById(R.id.button_color_online);
        Button widthButton = findViewById(R.id.button_width_online);
        Button fogButton = findViewById(R.id.button_fog_online);
        Button shapeButton = findViewById(R.id.button_shape_online);



        colorButton.setOnClickListener(view -> {
            ColorDialogOnline colorDialog = new ColorDialogOnline(this, drawingOnlineView);
            colorDialog.show();
        });

        widthButton.setOnClickListener(view -> {
            WidthDialogOnline widthDialogOnline = new WidthDialogOnline(this, drawingOnlineView);
            widthDialogOnline.show();
        });

        fogButton.setOnClickListener(view -> {
            FogDialogOnline fogDialogOnline = new FogDialogOnline(this, drawingOnlineView);
            fogDialogOnline.show();
        });

        shapeButton.setOnClickListener(view -> {
            ShapeDialogOnline shapeDialogOnline = new ShapeDialogOnline(this, drawingOnlineView);
            shapeDialogOnline.show();
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
