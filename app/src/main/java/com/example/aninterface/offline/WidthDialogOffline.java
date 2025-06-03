package com.example.aninterface.offline;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.aninterface.R;

public class WidthDialogOffline extends Dialog {
    public WidthDialogOffline(Context context, DrawingOffline drawingOffline) {
        super(context, R.style.RoundedDialog);

        setContentView(R.layout.width_dialog_offline);

        Button buttonConfirm = findViewById(R.id.button_confirm_width_dialog_offline);
        SeekBar seekBar = findViewById(R.id.seekBar_width_dialog_offline);

        buttonConfirm.setOnClickListener(view -> {
            drawingOffline.getDrawingThread().setCurrentWidth(seekBar.getProgress());
            dismiss();
        });
    }
}