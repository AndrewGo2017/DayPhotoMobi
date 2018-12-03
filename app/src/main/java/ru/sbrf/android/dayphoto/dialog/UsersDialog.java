package ru.sbrf.android.dayphoto.dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ru.sbrf.android.dayphoto.common.CurrentUser;
import ru.sbrf.android.dayphoto.common.SettingHandler;
import ru.sbrf.android.dayphoto.controller.UserGetController;
import ru.sbrf.android.dayphoto.model.User;

public class UsersDialog extends BaseDialog {
    private boolean hasCancelButton;

    public UsersDialog(AppCompatActivity context, int layoutDialogId, boolean hasCancelButton) {
        super(context, layoutDialogId);
        this.hasCancelButton = hasCancelButton;
    }

    @Override
    public AlertDialog createDialog() {
        AlertDialog.Builder builder = createBuilder();

        final List<User> users = UserGetController.getInstance().getAllFromCache();

        final List<String> nameList = new ArrayList<>();
        for (User user : users){
            nameList.add(user.getName());
        }

        String[] nameArray = new String[nameList.size()];
        nameList.toArray(nameArray);

        builder.setItems(nameArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CurrentUser.getInstance().setUser(users.get(which));
                getContext().getSupportActionBar().setTitle(CurrentUser.getInstance().getUser().getName());
                dialog.cancel();

                new SettingHandler<User>().saveSetting("currentUser",users.get(which) );
            }
        });

        if (hasCancelButton){
            builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        }

        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Пользователи");

        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        return alertDialog;
    }
}
