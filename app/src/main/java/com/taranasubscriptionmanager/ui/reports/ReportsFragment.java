package com.taranasubscriptionmanager.ui.reports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.taranasubscriptionmanager.R;
import com.taranasubscriptionmanager.viewmodel.UserViewModel;

public class ReportsFragment extends Fragment {

    private UserViewModel viewModel;

    private TextView tvTofu;
    private TextView tvMilk;
    private TextView tvDeliveries;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reports, container, false);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        tvTofu = view.findViewById(R.id.tvTofu);
        tvMilk = view.findViewById(R.id.tvMilk);
        tvDeliveries = view.findViewById(R.id.tvDeliveries);

        viewModel.getTotalTofuRequired().observe(getViewLifecycleOwner(), value -> {

            if(value == null) value = 0;

            tvTofu.setText(String.valueOf(value));

        });

        viewModel.getTotalMilkRequired().observe(getViewLifecycleOwner(), value -> {

            if(value == null) value = 0;

            tvMilk.setText(String.valueOf(value));

        });

        viewModel.getActiveUsersCount().observe(getViewLifecycleOwner(), value -> {

            if(value == null) value = 0;

            tvDeliveries.setText(String.valueOf(value));

        });

        return view;
    }
}