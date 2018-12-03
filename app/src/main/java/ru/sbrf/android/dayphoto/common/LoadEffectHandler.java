package ru.sbrf.android.dayphoto.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import ru.sbrf.android.dayphoto.R;

public class LoadEffectHandler {
    private Window windowToLock;
    private ViewGroup viewGroup;

    private View progressView;

    public LoadEffectHandler(LayoutInflater inflater, ViewGroup viewGroup) {
        this.viewGroup = viewGroup;

        progressView = inflater.inflate(R.layout.progress, null);
    }

    public LoadEffectHandler(LayoutInflater inflater, ViewGroup viewGroup, String message) {
        this(inflater, viewGroup);

        TextView progressTextView = (TextView)progressView.findViewById(R.id.loading_msg);
        progressTextView.setText(message);
    }

    public void setWindowToLock(Window windowToLock) {
        this.windowToLock = windowToLock;
    }

    public void show(){
        progressView.setVisibility(View.VISIBLE);
        if (progressView.getParent() == null)
            viewGroup.addView(progressView, 0);

        if (windowToLock != null) {
            windowToLock.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    public void destroy(){

    }

    public void hide(){
        progressView.setVisibility(View.INVISIBLE);

        if (windowToLock != null){
            windowToLock.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }
}
