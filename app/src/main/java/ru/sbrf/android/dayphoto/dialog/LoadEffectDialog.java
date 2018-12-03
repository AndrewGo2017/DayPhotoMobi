package ru.sbrf.android.dayphoto.dialog;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import ru.sbrf.android.dayphoto.common.LoadEffectHandler;

public class LoadEffectDialog extends BaseDialog {
    private String title;

    public LoadEffectDialog(AppCompatActivity context, int layoutDialogId, String title) {
        super(context, layoutDialogId);
        this.title = title;
    }

    @Override
    public AlertDialog createDialog() {
        AlertDialog.Builder builder = createBuilder();

        new LoadEffectHandler(getLayoutInflater(), getDialogViewGroup(), "" ).show();

        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle(title);

        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        return alertDialog;
    }
}
