package com.example.aninterface.offline;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.aninterface.R;

public class WidthDialog extends Dialog {
    public WidthDialog(Context context, Drawing drawing) {
        super(context);

        setContentView(R.layout.width_dialog);

        Button buttonConfirm = findViewById(R.id.button_confirm_width_dialog_offline);
        SeekBar seekBar = findViewById(R.id.seekBar_width_dialog_offline);

        buttonConfirm.setOnClickListener(view -> {
            drawing.getDrawingThread().setCurrentWidth(seekBar.getProgress());
            dismiss();
        });
    }
}
