package com.example.aninterface.online;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aninterface.R;

public class ColorDialogOnline extends Dialog {
    Button buttonConfirm;
    Button buttonRed;
    Button buttonYellow;
    Button buttonGreen;
    Button buttonBlue;
    Button buttonBlack;
    EditText editTextColor;

    public ColorDialogOnline(Context context, DrawingOnline drawingOnline) {
        super(context, R.style.RoundedDialog);

        setContentView(R.layout.color_dialog_online);

        editTextColor = findViewById(R.id.editTextColorOnline);
        buttonConfirm = findViewById(R.id.button_confirm_own_color_online);
        buttonRed = findViewById(R.id.button_set_color_red_online);
        buttonYellow = findViewById(R.id.button_set_color_yellow_online);
        buttonGreen = findViewById(R.id.button_set_color_green_online);
        buttonBlue = findViewById(R.id.button_set_color_blue_online);
        buttonBlack = findViewById(R.id.button_set_color_black_online);

        buttonRed.setOnClickListener(view -> {
            drawingOnline.getDrawingThread().setCurrentColor(Color.RED);
            dismiss();
        });

        buttonYellow.setOnClickListener(view -> {
            drawingOnline.getDrawingThread().setCurrentColor(Color.YELLOW);
            dismiss();
        });

        buttonGreen.setOnClickListener(view -> {
            drawingOnline.getDrawingThread().setCurrentColor(Color.GREEN);
            dismiss();
        });

        buttonBlue.setOnClickListener(view -> {
            drawingOnline.getDrawingThread().setCurrentColor(Color.BLUE);
            dismiss();
        });

        buttonBlack.setOnClickListener(view -> {
            drawingOnline.getDrawingThread().setCurrentColor(Color.BLACK);
            dismiss();
        });

        buttonConfirm.setOnClickListener(view -> {
            String myColor = editTextColor.getText().toString();
            if (!myColor.isEmpty()) {
                try {
                    if (!myColor.startsWith("#")) {
                        myColor = "#" + myColor;
                    }
                    int color = Color.parseColor(myColor);
                    drawingOnline.getDrawingThread().setCurrentColor(color);
                    dismiss();
                } catch (IllegalArgumentException e) {
                    Toast.makeText(context, "Неверный формат цвета. Используйте формат #RRGGBB", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Введите цвет", Toast.LENGTH_SHORT).show();
            }
        });
    }
}