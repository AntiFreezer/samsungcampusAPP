package com.example.aninterface;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class WidthDialog extends Dialog {
    public WidthDialog(Context context, Drawing drawing) {
        super(context);

        setContentView(R.layout.width_dialog);

        Button buttonConfirm = findViewById(R.id.button_confirm_width_dialog);
        SeekBar seekBar = findViewById(R.id.seekBar_width_dialog);

        buttonConfirm.setOnClickListener(view -> {
            drawing.getDrawingThread().setCurrentWidth(seekBar.getProgress());
            dismiss();
        });
    }
}
