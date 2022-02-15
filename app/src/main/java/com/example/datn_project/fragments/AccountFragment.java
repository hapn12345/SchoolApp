package com.example.datn_project.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datn_project.R;
import com.example.datn_project.Utils.AppUtilKt;
import com.example.datn_project.databinding.ContentProfileBinding;
import com.example.datn_project.databinding.FragmentAccountBinding;
import com.example.datn_project.databinding.FragmentHomeBinding;
import com.example.datn_project.utilities.SharedPreferenceUtil;
import com.example.datn_project.viewmodel.HomeViewModel;

public class AccountFragment extends Fragment {
    private FragmentAccountBinding mBinding;
    private ContentProfileBinding contentProfileBinding;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentAccountBinding.inflate(inflater, container, false);
        contentProfileBinding = mBinding.infoProfile;
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
    }

    private void getData() {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getUsers(SharedPreferenceUtil.readAccessToken(getContext())).observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                String name = user.getLastName() + " " + user.getFirstName();
                mBinding.txtName.setText(name);
                String phone = user.getPhoneNumber();
                contentProfileBinding.txtPhoneNumber.setText(phone);
                contentProfileBinding.txtNameUser.setText(name);
                contentProfileBinding.txtEmailUser.setText(user.getEmail());
                if (user.getStudents() == null) {
                    mBinding.txtRole.setText(R.string.admin);
                } else {
                    mBinding.txtRole.setText(R.string.parent);
                }
            }
        });
        mBinding.infoProfile.btnLogout.setOnClickListener(v -> {
            SharedPreferenceUtil.editAccessToken(v.getContext(), "");
            if (getActivity() != null) {
                getActivity().finish();
            }
        });
    }
}