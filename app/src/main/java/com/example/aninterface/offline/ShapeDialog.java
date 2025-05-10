package com.example.aninterface.offline;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;

import com.example.aninterface.R;

public class ShapeDialog extends Dialog {
    public ShapeDialog(Context context, Drawing drawing){
        super(context);

        setContentView(R.layout.shape_dialog);

        Button buttonLine = findViewById(R.id.button_line_offline);
        Button buttonSquare = findViewById(R.id.button_square_offline);
        Button buttonCircle = findViewById(R.id.button_circle_offline);

        buttonLine.setOnClickListener(view -> {
            drawing.getDrawingThread().setCurrentShape("LINE");
            dismiss();
        });

        buttonSquare.setOnClickListener(view -> {
            drawing.getDrawingThread().setCurrentShape("SQUARE");
            dismiss();
        });

        buttonCircle.setOnClickListener(view -> {
            drawing.getDrawingThread().setCurrentShape("CIRCLE");
            dismiss();
        });
    }
}
