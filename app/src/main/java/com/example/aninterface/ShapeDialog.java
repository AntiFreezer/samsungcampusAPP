package com.example.aninterface;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;

public class ShapeDialog extends Dialog {
    public ShapeDialog(Context context){
        super(context);

        setContentView(R.layout.shape_dialog);

        Button buttonLine = findViewById(R.id.button_line);
        Button buttonSquare = findViewById(R.id.button_square);
        Button buttonCircle = findViewById(R.id.button_circle);

        buttonLine.setOnClickListener(view -> {
            dismiss();
        });

        buttonSquare.setOnClickListener(view -> {
            dismiss();
        });

        buttonCircle.setOnClickListener(view -> {
            dismiss();
        });
    }
}
