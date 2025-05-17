package com.example.aninterface.online;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.Switch;

import com.example.aninterface.R;

public class FogDialogOnline extends Dialog {
    public FogDialogOnline(Context context, DrawingOnline drawingOnline) {
        super(context);

        setContentView(R.layout.fog_dialog_online);

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switcher = findViewById(R.id.switch_fog_dialog_online);
        Button buttonConfirmed = findViewById(R.id.button_confirm_fog_dialog_online);

        buttonConfirmed.setOnClickListener(view -> {
            drawingOnline.getDrawingThread().setCurrentFog(switcher.isChecked());
            dismiss();
        });
    }
}
