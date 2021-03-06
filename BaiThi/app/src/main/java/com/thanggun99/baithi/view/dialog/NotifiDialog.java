package com.thanggun99.baithi.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.TextView;

import com.thanggun99.baithi.R;


public class NotifiDialog extends BaseDialog {
    private TextView tvNotifi;

    public NotifiDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_notifi);
        setCancelable(true);

        tvNotifi = (TextView) findViewById(R.id.tv_notifi);
        btnCancle = (Button) findViewById(R.id.btn_cancel);
        btnCancle.setOnClickListener(this);
    }

    public void notifi(String message) {
        tvNotifi.setText(message);
        show();
    }
}
