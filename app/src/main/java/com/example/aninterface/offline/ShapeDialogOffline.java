package com.example.aninterface.offline;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;

import com.example.aninterface.R;

public class ShapeDialogOffline extends Dialog {
    public ShapeDialogOffline(Context context, DrawingOffline drawingOffline){
        super(context, R.style.RoundedDialog);

        setContentView(R.layout.shape_dialog_offline);

        Button buttonLine = findViewById(R.id.button_line_online);
        Button buttonSquare = findViewById(R.id.button_square_online);
        Button buttonCircle = findViewById(R.id.button_circle_online);
        Button buttonEraser = findViewById(R.id.button_eraser_online);

        buttonLine.setOnClickListener(view -> {
            drawingOffline.getDrawingThread().setCurrentShape("LINE");
            dismiss();
        });

        buttonSquare.setOnClickListener(view -> {
            drawingOffline.getDrawingThread().setCurrentShape("SQUARE");
            dismiss();
        });

        buttonCircle.setOnClickListener(view -> {
            drawingOffline.getDrawingThread().setCurrentShape("CIRCLE");
            dismiss();
        });

        buttonEraser.setOnClickListener(view -> {
            drawingOffline.getDrawingThread().setCurrentShape("ERASER");
            dismiss();
        });
    }
}
