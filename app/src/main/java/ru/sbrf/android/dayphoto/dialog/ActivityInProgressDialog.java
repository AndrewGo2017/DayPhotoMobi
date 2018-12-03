package ru.sbrf.android.dayphoto.dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Calendar;

import ru.sbrf.android.dayphoto.R;
import ru.sbrf.android.dayphoto.common.CurrentUser;
import ru.sbrf.android.dayphoto.common.FinishedActivityHandler;
import ru.sbrf.android.dayphoto.common.LoadEffectHandler;
import ru.sbrf.android.dayphoto.common.TimerHandler;
import ru.sbrf.android.dayphoto.model.Activity;
import ru.sbrf.android.dayphoto.model.FinishedActivity;

public class ActivityInProgressDialog extends BaseDialog {
    private TimerHandler timerHandler;
    private TextView dialogTextView;
    private Activity activity;

    public ActivityInProgressDialog(AppCompatActivity context, int layoutDialogId, TimerHandler timerHandler, Activity activity) {
        super(context, layoutDialogId);
        this.timerHandler = timerHandler;
        this.activity = activity;
    }

    public AlertDialog createDialog() {
        AlertDialog.Builder builder = createBuilder();
        dialogTextView = (TextView) getDialogViewGroup().findViewById(R.id.layout_dialog_text);


        new LoadEffectHandler(getLayoutInflater(), getDialogViewGroup(), "").show();

        builder.setPositiveButton("Завершить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                if (timerHandler != null)
                    timerHandler.cancel();

                FinishedActivityHandler.save(new FinishedActivity(CurrentUser.getInstance().getUser(), activity, Calendar.getInstance(), timerHandler.getTotalTimeInSeconds()));
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Выполнение...\n" + activity.getName());
        alertDialog.setMessage("00:00:00");

        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        return alertDialog;
    }

    public void setText(String str) {
        dialogTextView.setText(str);
    }
}
