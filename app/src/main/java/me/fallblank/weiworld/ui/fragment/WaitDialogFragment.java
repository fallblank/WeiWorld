package me.fallblank.weiworld.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import me.fallblank.weiworld.R;

/**
 * Created by fallb on 2017/5/3.
 */

public class WaitDialogFragment extends DialogFragment {
    
    TextView mHintView;
    String mHintContent = "";
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        View root = inflater.inflate(R.layout.dialog_progress_wait, container, false);
        mHintView = (TextView) root.findViewById(R.id.tv_hint);
        mHintView.setText(mHintContent);
        return root;
    }
    
    public void setHint(String hint) {
        if (hint == null) return;
        mHintContent = hint;
        if (mHintView != null) {
            mHintView.setText(mHintContent);
        }
    }
    
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }
}
