package ru.sbrf.android.dayphoto.dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Objects;

import ru.sbrf.android.dayphoto.R;
import ru.sbrf.android.dayphoto.common.LoadEffectHandler;
import ru.sbrf.android.dayphoto.common.TimerHandler;

public class ActivityInProgressDialog extends BaseDialog {
    private TimerHandler timerHandler;
    private TextView dialogTextView;
    private boolean hasCloseBtn;

    public ActivityInProgressDialog(AppCompatActivity context, int layoutDialogId, TimerHandler timerHandler, boolean hasCloseBtn) {
        super(context, layoutDialogId);
        this.timerHandler = timerHandler;
        this.hasCloseBtn = hasCloseBtn;
    }

    public AlertDialog createDialog() {
        AlertDialog.Builder builder = createBuilder();
        dialogTextView = (TextView) getDialogViewGroup().findViewById(R.id.layout_dialog_text);

        if (hasCloseBtn) {
            builder.setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        }

        AlertDialog alertDialog = builder.create();
        if (timerHandler == null){
            alertDialog.setTitle("Не начата ни одна активность.");
        } else{
            alertDialog.setTitle(timerHandler.getActivity().getActivityGroup().getName().substring(0, 22) + "\n" + timerHandler.getActivity().getName());
            alertDialog.setMessage("");
            timerHandler.setAlertDialog(alertDialog);
            new LoadEffectHandler(getLayoutInflater(), getDialogViewGroup(), "").show();
        }

        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        Objects.requireNonNull(alertDialog.getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;

        return alertDialog;
    }

    public void setText(String str) {
        dialogTextView.setText(str);
    }
}
