package com.example.aninterface;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.Switch;

public class FogDialog extends Dialog {
    public FogDialog(Context context, Drawing drawing) {
        super(context);

        setContentView(R.layout.fog_dialog);

        Switch switcher = findViewById(R.id.switch_fog_dialog);
        Button buttonConfirmed = findViewById(R.id.button_confirm_fog_dialog);

        boolean switcherChecked = switcher.isChecked();

        buttonConfirmed.setOnClickListener(view -> {
            drawing.getDrawingThread().setCurrentFog(switcherChecked);
            dismiss();
        });
    }
}
