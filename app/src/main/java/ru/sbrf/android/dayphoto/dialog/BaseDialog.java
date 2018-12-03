package ru.sbrf.android.dayphoto.dialog;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public abstract class BaseDialog {
    private AppCompatActivity context;
    private int layoutDialogId;

    private ViewGroup dialogViewGroup;

    public BaseDialog(AppCompatActivity context, int layoutDialogId) {
        this.context = context;
        this.layoutDialogId = layoutDialogId;
    }

    public AlertDialog.Builder createBuilder(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = context.getLayoutInflater();
        dialogViewGroup = (ViewGroup)inflater.inflate(layoutDialogId, null);

        builder.setView(dialogViewGroup);

        return builder;
    }

    public ViewGroup getDialogViewGroup() {
        return dialogViewGroup;
    }

    public AppCompatActivity getContext() {
        return context;
    }

    public LayoutInflater getLayoutInflater(){
        return context.getLayoutInflater();
    }

    public abstract AlertDialog createDialog();
}
