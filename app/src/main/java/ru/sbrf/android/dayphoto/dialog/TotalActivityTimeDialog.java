package ru.sbrf.android.dayphoto.dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ru.sbrf.android.dayphoto.R;
import ru.sbrf.android.dayphoto.common.FinishedActivityHandler;
import ru.sbrf.android.dayphoto.common.LoadEffectHandler;

public class TotalActivityTimeDialog extends BaseDialog {
    public TotalActivityTimeDialog(AppCompatActivity context, int layoutDialogId) {
        super(context, layoutDialogId);
    }

    @Override
    public AlertDialog createDialog() {
        AlertDialog.Builder builder = createBuilder();

        builder.setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Общее время");
        alertDialog.setMessage(FinishedActivityHandler.retrieve());

        return alertDialog;
    }
}
