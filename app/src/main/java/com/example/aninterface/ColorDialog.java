package com.example.aninterface;

import android.app.Dialog;
import android.content.Context;

import android.view.View;
import android.widget.Button;

public class ColorDialog extends Dialog {
    public ColorDialog(Context context) {
        super(context);

        setContentView(R.layout.color_dialog);

        Button buttonConfirm = findViewById(R.id.button_confirm_own_color);
        Button buttonRed = findViewById(R.id.button_set_color_red);
        Button buttonYellow = findViewById(R.id.button_set_color_yellow);
        Button buttonGreen = findViewById(R.id.button_set_color_green);
        Button buttonBlue = findViewById(R.id.button_set_color_blue);
        Button buttonBlack = findViewById(R.id.button_set_color_black);

        buttonConfirm.setOnClickListener(view -> {
            dismiss();
        });
        buttonRed.setOnClickListener(view -> {
            dismiss();
        });
        buttonYellow.setOnClickListener(view -> {
            dismiss();
        });
        buttonGreen.setOnClickListener(view -> {
            dismiss();
        });
        buttonBlue.setOnClickListener(view -> {
            dismiss();
        });
        buttonBlack.setOnClickListener(view -> {
            dismiss();
        });
    }
}
