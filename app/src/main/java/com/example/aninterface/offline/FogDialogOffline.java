package com.example.aninterface.offline;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.Switch;

import com.example.aninterface.R;

public class FogDialogOffline extends Dialog {
    public FogDialogOffline(Context context, DrawingOffline drawingOffline) {
        super(context, R.style.RoundedDialog);

        setContentView(R.layout.fog_dialog_offline);

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switcher = findViewById(R.id.switch_fog_dialog_offline);
        Button buttonConfirmed = findViewById(R.id.button_confirm_fog_dialog_offline);

        buttonConfirmed.setOnClickListener(view -> {
            drawingOffline.getDrawingThread().setCurrentFog(switcher.isChecked());
            dismiss();
        });
    }
}