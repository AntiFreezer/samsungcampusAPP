package com.example.aninterface.online;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.aninterface.R;

public class WidthDialogOnline extends Dialog {
    public WidthDialogOnline(Context context, DrawingOnline drawingOnline) {
        super(context, R.style.RoundedDialog);

        setContentView(R.layout.width_dialog_online);

        Button buttonConfirm = findViewById(R.id.button_confirm_width_dialog_online);
        SeekBar seekBar = findViewById(R.id.seekBar_width_dialog_online);

        buttonConfirm.setOnClickListener(view -> {
            drawingOnline.getDrawingThread().setCurrentWidth(seekBar.getProgress());
            dismiss();
        });
    }
}
