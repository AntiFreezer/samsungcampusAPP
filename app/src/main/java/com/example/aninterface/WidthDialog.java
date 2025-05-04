package com.example.aninterface;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

public class WidthDialog extends Dialog {
    public WidthDialog(Context context) {
        super(context);

        setContentView(R.layout.width_dialog);
        Button buttonConfirm = findViewById(R.id.button_confirm_width_dialog);

        buttonConfirm.setOnClickListener(view -> {
            dismiss();
        });
    }
}
