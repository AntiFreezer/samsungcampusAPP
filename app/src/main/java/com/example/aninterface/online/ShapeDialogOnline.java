package com.example.aninterface.online;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;

import com.example.aninterface.R;

public class ShapeDialogOnline extends Dialog {
    public ShapeDialogOnline(Context context, DrawingOnline drawingOnline){
        super(context, R.style.RoundedDialog);

        setContentView(R.layout.shape_dialog_online);

        Button buttonLine = findViewById(R.id.button_line_online);
        Button buttonSquare = findViewById(R.id.button_square_online);
        Button buttonCircle = findViewById(R.id.button_circle_online);
        Button buttonEraser = findViewById(R.id.button_eraser_online);

        buttonLine.setOnClickListener(view -> {
            drawingOnline.getDrawingThread().setCurrentShape("LINE");
            dismiss();
        });

        buttonSquare.setOnClickListener(view -> {
            drawingOnline.getDrawingThread().setCurrentShape("SQUARE");
            dismiss();
        });

        buttonCircle.setOnClickListener(view -> {
            drawingOnline.getDrawingThread().setCurrentShape("CIRCLE");
            dismiss();
        });

        buttonEraser.setOnClickListener(view -> {
            drawingOnline.getDrawingThread().setCurrentShape("ERASER");
            dismiss();
        });
    }
}