package com.taranasubscriptionmanager.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.card.MaterialCardView;
import com.taranasubscriptionmanager.R;
import com.taranasubscriptionmanager.viewmodel.UserViewModel;

public class DashboardFragment extends Fragment {

    private UserViewModel viewModel;

    private TextView tvTofuQuantity;
    private TextView tvMilkQuantity;
    private TextView tvTodayRevenue;
    private TextView tvActiveUsers;

    public DashboardFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        tvTofuQuantity = view.findViewById(R.id.tvTofuQuantity);
        tvMilkQuantity = view.findViewById(R.id.tvMilkQuantity);
        tvTodayRevenue = view.findViewById(R.id.tvTodayRevenue);
        tvActiveUsers = view.findViewById(R.id.tvActiveUsers);

        MaterialCardView cardTofu = view.findViewById(R.id.cardTofu);
        MaterialCardView cardMilk = view.findViewById(R.id.cardMilk);
        MaterialCardView cardRevenue = view.findViewById(R.id.cardRevenue);
        MaterialCardView cardUsers = view.findViewById(R.id.cardUsers);

        viewModel.getTotalTofuRequired().observe(getViewLifecycleOwner(), qty -> {
            if(qty == null) qty = 0;
            tvTofuQuantity.setText(String.valueOf(qty));
        });

        viewModel.getTotalMilkRequired().observe(getViewLifecycleOwner(), qty -> {
            if(qty == null) qty = 0;
            tvMilkQuantity.setText(String.valueOf(qty));
        });

        viewModel.getActiveUsersCount().observe(getViewLifecycleOwner(), count -> {
            if(count == null) count = 0;
            tvActiveUsers.setText(String.valueOf(count));
        });

        viewModel.getTodayRevenue().observe(getViewLifecycleOwner(), value -> {
            if(value == null) value = 0;
            tvTodayRevenue.setText("₹ " + value);
        });

        cardTofu.setOnClickListener(v ->
                Toast.makeText(getContext(),"Tofu Production",Toast.LENGTH_SHORT).show());

        cardMilk.setOnClickListener(v ->
                Toast.makeText(getContext(),"Milk Production",Toast.LENGTH_SHORT).show());

        cardRevenue.setOnClickListener(v ->
                Toast.makeText(getContext(),"Revenue Details",Toast.LENGTH_SHORT).show());

        cardUsers.setOnClickListener(v ->
                Toast.makeText(getContext(),"Open Users Screen",Toast.LENGTH_SHORT).show());

        return view;
    }
}