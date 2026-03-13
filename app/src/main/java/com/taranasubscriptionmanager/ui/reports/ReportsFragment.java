package com.taranasubscriptionmanager.ui.reports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.taranasubscriptionmanager.R;

public class ReportsFragment extends Fragment {

    public ReportsFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_reports,container,false);

        TextView today = view.findViewById(R.id.tvTodayRevenue);
        TextView monthly = view.findViewById(R.id.tvMonthlyRevenue);

        today.setText("Today's Revenue : ₹0");
        monthly.setText("Monthly Revenue : ₹0");

        return view;
    }
}