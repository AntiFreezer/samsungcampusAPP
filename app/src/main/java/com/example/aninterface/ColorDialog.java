package com.example.aninterface;

import android.app.Dialog;
import android.content.Context;

import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;

public class ColorDialog extends Dialog {
    Button buttonConfirm;
    Button buttonRed;
    Button buttonYellow;
    Button buttonGreen;
    Button buttonBlue;
    Button buttonBlack;
    EditText editTextColor;
    public ColorDialog(Context context, Drawing drawing) {
        super(context);

        setContentView(R.layout.color_dialog);

        editTextColor = findViewById(R.id.editTextColor);
        buttonConfirm = findViewById(R.id.button_confirm_own_color);
        buttonRed = findViewById(R.id.button_set_color_red);
        buttonYellow = findViewById(R.id.button_set_color_yellow);
        buttonGreen = findViewById(R.id.button_set_color_green);
        buttonBlue = findViewById(R.id.button_set_color_blue);
        buttonBlack = findViewById(R.id.button_set_color_black);

        buttonRed.setOnClickListener(view -> {
            drawing.getDrawingThread().setCurrentColor(Color.RED);
            dismiss();
        });

        buttonYellow.setOnClickListener(view -> {
            drawing.getDrawingThread().setCurrentColor(Color.YELLOW);
            dismiss();
        });

        buttonGreen.setOnClickListener(view -> {
            drawing.getDrawingThread().setCurrentColor(Color.GREEN);
            dismiss();
        });

        buttonBlue.setOnClickListener(view -> {
            drawing.getDrawingThread().setCurrentColor(Color.BLUE);
            dismiss();
        });

        buttonBlack.setOnClickListener(view -> {
            drawing.getDrawingThread().setCurrentColor(Color.BLACK);
            dismiss();
        });

        buttonConfirm.setOnClickListener(view -> {
            String myColor = editTextColor.getText().toString();
            if (!myColor.isEmpty()) {
                try {
                    int color = Color.parseColor(myColor);
                    drawing.getDrawingThread().setCurrentColor(color);
                } catch (IllegalArgumentException ignored) {}
            }
            dismiss();
        });

    }
}
