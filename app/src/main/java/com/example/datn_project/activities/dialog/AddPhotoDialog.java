package com.example.datn_project.activities.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.datn_project.databinding.DialogAddAlbumBinding;

public class AddPhotoDialog extends Dialog {
    private DialogAddAlbumBinding mBinding;
    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public AddPhotoDialog(@NonNull Context context) {
        super(context);
    }

    public AddPhotoDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AddPhotoDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DialogAddAlbumBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        mBinding.imgAdd.setOnClickListener(v -> {
            if (!mBinding.editText.getText().toString().equals("")) {
                onClick.onClickAdd(mBinding.editText.getText().toString());
                dismiss();
            } else {
                mBinding.editText.setError("Nhập tên hoạt động");
            }
        });

    }

    public interface OnClick {
        void onClickAdd(String activityName);
    }
}
