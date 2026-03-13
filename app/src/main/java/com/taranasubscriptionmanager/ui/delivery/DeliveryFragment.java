package com.taranasubscriptionmanager.ui.delivery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.taranasubscriptionmanager.R;
import com.taranasubscriptionmanager.viewmodel.UserViewModel;

public class DeliveryFragment extends Fragment {

    private UserViewModel viewModel;
    private DeliveryAdapter adapter;

    public DeliveryFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate layout FIRST
        View view = inflater.inflate(R.layout.fragment_delivery, container, false);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Button
        Button btnStartDay = view.findViewById(R.id.btnStartDay);

        // RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerDelivery);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new DeliveryAdapter();
        recyclerView.setAdapter(adapter);

        // Generate today's deliveries
        viewModel.getActiveUsers().observe(getViewLifecycleOwner(), users -> {

            btnStartDay.setOnClickListener(v -> {

                viewModel.generateTodayDeliveries(users);

                Toast.makeText(getContext(),
                        "Today's deliveries generated",
                        Toast.LENGTH_SHORT).show();
            });

            adapter.setUsers(users);
        });

        return view;
    }
}