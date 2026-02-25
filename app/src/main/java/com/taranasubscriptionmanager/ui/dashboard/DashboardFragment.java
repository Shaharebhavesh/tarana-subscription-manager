package com.taranasubscriptionmanager.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.taranasubscriptionmanager.R;

public class DashboardFragment extends Fragment {

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Initialize cards
        MaterialCardView cardQuantity = view.findViewById(R.id.cardTodayQuantity);
        MaterialCardView cardRevenue = view.findViewById(R.id.cardTodayRevenue);
        MaterialCardView cardMonthly = view.findViewById(R.id.cardMonthlyRevenue);
        MaterialCardView cardUsers = view.findViewById(R.id.cardActiveUsers);

        // Click Listeners
        cardQuantity.setOnClickListener(v ->
                Toast.makeText(getContext(), "Open Delivery Screen", Toast.LENGTH_SHORT).show());

        cardRevenue.setOnClickListener(v ->
                Toast.makeText(getContext(), "Open Billing Screen", Toast.LENGTH_SHORT).show());

        cardMonthly.setOnClickListener(v ->
                Toast.makeText(getContext(), "Open Reports Screen", Toast.LENGTH_SHORT).show());

        cardUsers.setOnClickListener(v ->
                Toast.makeText(getContext(), "Open Users Screen", Toast.LENGTH_SHORT).show());

        return view;
    }
}